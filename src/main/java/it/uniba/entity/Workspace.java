package it.uniba.entity;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.json.JSONArray;
import org.json.JSONObject;

import it.uniba.entity.Channel;
import it.uniba.entity.Member;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;
import it.uniba.file.zip.Zip;

public class Workspace {
	private Zip workspaceZip;
	LinkedHashMap<String, Channel> channels;
	LinkedHashMap<String, Member> members;

	public Workspace(String workspaceZipFile)
			throws IOException, NotValidWorkspaceException, FileNotInZipException, NotZipFileException {
		try {
			Zip workspaceZip = new Zip(workspaceZipFile);
			if (!workspaceZip.contains("channels.json") || !workspaceZip.contains("users.json")) {
				throw new NotValidWorkspaceException(workspaceZipFile);
			} else {
				this.workspaceZip = workspaceZip;
				this.channels = new LinkedHashMap<String, Channel>();
				this.members = new LinkedHashMap<String, Member>();
				String real_name = "";
				String display_name = "";

				JSONArray membersRootArray = new JSONArray(workspaceZip.getFileContent("users.json"));
				for (int i = 0; i < membersRootArray.length(); i++) {
					JSONObject member = membersRootArray.getJSONObject(i);
					if (member.getJSONObject("profile").has("real_name")) {
						real_name = member.getJSONObject("profile").getString("real_name");
					}
					if (member.getJSONObject("profile").has("display_name")) {
						display_name = member.getJSONObject("profile").getString("display_name");
					}
					Member currMember = new Member(member.getString("id"), member.getString("name"), real_name,
							display_name);
					members.put(member.getString("id"), currMember);
				}
				JSONArray channelsRootArray = new JSONArray(workspaceZip.getFileContent("channels.json"));
				for (int j = 0; j < channelsRootArray.length(); j++) {
					JSONObject channel = channelsRootArray.getJSONObject(j);
					Channel currChannel = new Channel(channel.getString("id"), channel.getString("name"));
					JSONArray membersArray = channel.getJSONArray("members");
					for (int k = 0; k < membersArray.length(); k++) {
						String memberId = membersArray.getString(k);
						currChannel.getMembers().add(members.get(memberId));
						members.get(memberId).getChannels().add(currChannel);
					}
					channels.put(currChannel.getName(), currChannel);
				}
				Enumeration<? extends ZipEntry> e = workspaceZip.getZipFile().entries();
				while (e.hasMoreElements()) {
					ZipEntry ze = (ZipEntry) e.nextElement();
					if (!ze.isDirectory()) {
						String curFileName = ze.getName();
						String currChannel = curFileName.split("/")[0];
						Pattern patternDate = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
						String channelCont = workspaceZip.getFileContent(curFileName);
						if (channels.get(currChannel) != null) {
							if (patternDate.matcher(curFileName.split("/")[1].split("\\.")[0]).matches()
									&& curFileName.split("/")[1].split("\\.")[1].equals("json")) {
								JSONArray messagesRootArray = new JSONArray(channelCont);
								for (int i = 0; i < messagesRootArray.length(); i++) {
									JSONObject message = messagesRootArray.getJSONObject(i);
									if (!message.isNull("user")) {
										String currSender = message.getString("user");
										String currMessage = message.getString("text");
										Pattern pattern = Pattern.compile("[<@]+(?=.*[0-9])(?=.*[A-Z])([A-Z0-9]+)+[>]");
										Matcher matcher = pattern.matcher(currMessage);
										List<String> allMatches = new LinkedList<String>();
										while (matcher.find()) {
											String curr = matcher.group().replaceAll("<@", "").replaceAll(">", "");
											if(members.containsKey(curr)) {
												if (!curr.equals(currSender)) {
													allMatches.add(curr);
												}
											}	
										}
										ListIterator<String> it = (ListIterator<String>) allMatches.iterator();
										while (it.hasNext()) {
											String currReceiver = it.next();
											Mention currMention = new Mention(members.get(currSender),members.get(currReceiver));
											if (!channels.get(currChannel).containsMention(currMention)) {
												channels.get(currChannel).getMentions().add(currMention);
											}else {
												ListIterator<Mention> mentionIterator = channels.get(currChannel).getMentions().listIterator();
												while (mentionIterator.hasNext()) {
													Mention curr = mentionIterator.next();
													if(curr.equals(currMention)) {
														curr.setWeight(curr.getWeight()+1);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

			}
		} catch (ZipException e) {
			throw new NotZipFileException(workspaceZipFile);
		}
	}

	public LinkedHashMap<String, Channel> getAllChannels() {
		return (LinkedHashMap<String, Channel>) this.channels;
	}

	public LinkedHashMap<String, Member> getAllMembers() {
		return (LinkedHashMap<String, Member>) this.members;
	}

	public LinkedList<Member> getMembersOfChannel(String channelName) throws ChannelNotValidException {
		if (channels.containsKey(channelName)) {
			return channels.get(channelName).getMembers();
		} else {
			throw new ChannelNotValidException(channelName);
		}
	}

	public LinkedList<Mention> getMentionsFromUser(String channelInput, String memberInput)
			throws ChannelNotValidException, MemberNotValidException {
		Collection<Member> membersCollection = members.values();
		Iterator<Member> membersIterator = membersCollection.iterator();
		boolean found = false;
		while (membersIterator.hasNext()&&!found) {
			Member currMember = membersIterator.next();
			if(currMember.isUser(memberInput)) {
				memberInput = currMember.getId();
				found = true;
			}
		}
		if (found) {
			if (channels.containsKey(channelInput)) {
				LinkedList<Mention> out = new LinkedList<Mention>();
				LinkedList<Mention> mentions = channels.get(channelInput).getMentions();
				ListIterator<Mention> it = (ListIterator<Mention>) mentions.iterator();
				while (it.hasNext()) {
					Mention curMention = it.next();
					if (curMention.getFrom().equals(members.get(memberInput))) {
						out.add(curMention);
					}
				}
				return out;
			} else {
				throw new ChannelNotValidException(channelInput);
			}
		} else {
			throw new MemberNotValidException(memberInput);
		}

	}

	public LinkedList<Mention> getMentionsToUser(String channelInput, String memberInput)
			throws ChannelNotValidException, MemberNotValidException {
		Collection<Member> membersCollection = members.values();
		Iterator<Member> membersIterator = membersCollection.iterator();
		boolean found = false;
		while (membersIterator.hasNext()&&!found) {
			Member currMember = membersIterator.next();
			if(currMember.isUser(memberInput)) {
				memberInput = currMember.getId();
				found = true;
			}
		}
		if (found) {
			if (channels.containsKey(channelInput)) {
				LinkedList<Mention> out = new LinkedList<Mention>();
				LinkedList<Mention> mentions = channels.get(channelInput).getMentions();
				ListIterator<Mention> it = (ListIterator<Mention>) mentions.iterator();
				while (it.hasNext()) {
					Mention curMention = it.next();
					if (curMention.getTo().equals(members.get(memberInput))) {
						out.add(curMention);
					}
				}
				return out;
			} else {
				throw new ChannelNotValidException(channelInput);
			}
		} else {
			throw new MemberNotValidException(memberInput);
		}
	}

	public LinkedList<Mention> getMentions(String channelName) throws ChannelNotValidException {
		if (channels.containsKey(channelName)) {
			return channels.get(channelName).getMentions();
		} else {
			throw new ChannelNotValidException(channelName);
		}
	}
	
	public Zip getWorkspaceZip() {
		return workspaceZip;
	}
}
