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

import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;
import it.uniba.file.zip.Zip;

/**
 * Classe che modella il workspace.
 */
public final class Workspace {
	/**
	 * zip file.
	 */
	private Zip workspaceZip;
	/**
	 * lista di channel nel workspace.
	 */
	private LinkedHashMap<String, Channel> channels;
	/**
	 * lista di members nel workspace.
	 */
	private LinkedHashMap<String, Member> members;

	/**
	 * Metodo costruttore della classe workspace. Permette di creare oggetti istanze
	 * della classe workspace.
	 * 
	 * @param workspaceZipFile
	 *            String che rappresenta il percorso del workspace.
	 * @throws IOException
	 *             Lancia IOException se ci sono errori di input/output.
	 * @throws NotValidWorkspaceException
	 *             Lancia NotValidWorkspaceException quando il workspace specificato
	 *             non è valido.
	 * @throws FileNotInZipException
	 *             Lancia FileNotInZipException quando il file specificato non è
	 *             all'interno dello zip file.
	 * @throws NotZipFileException
	 *             Lancia NotZipFileException quando il file zip specificato non è
	 *             valido.
	 */
	public Workspace(final String workspaceZipFile)
			throws IOException, NotValidWorkspaceException, FileNotInZipException, NotZipFileException {
		try {
			Zip workspace = new Zip(workspaceZipFile);
			if (!workspace.contains("channels.json") || !workspace.contains("users.json")) {
				throw new NotValidWorkspaceException(workspaceZipFile);
			} else {
				this.workspaceZip = workspace;
				this.channels = new LinkedHashMap<String, Channel>();
				this.members = new LinkedHashMap<String, Member>();
				String realName = "";
				String displayName = "";

				JSONArray membersRootArray = new JSONArray(workspaceZip.getFileContent("users.json"));
				for (int i = 0; i < membersRootArray.length(); i++) {
					JSONObject member = membersRootArray.getJSONObject(i);
					if (member.getJSONObject("profile").has("real_name")) {
						realName = member.getJSONObject("profile").getString("real_name");
					}
					if (member.getJSONObject("profile").has("display_name")) {
						displayName = member.getJSONObject("profile").getString("display_name");
					}
					Member currMember = new Member(member.getString("id"), member.getString("name"), realName,
							displayName);
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
											if (members.containsKey(curr)) {
												if (!curr.equals(currSender)) {
													allMatches.add(curr);
												}
											}
										}
										ListIterator<String> it = (ListIterator<String>) allMatches.iterator();
										while (it.hasNext()) {
											String currReceiver = it.next();
											Mention currMention = new Mention(members.get(currSender),
													members.get(currReceiver));
											if (!channels.get(currChannel).containsMention(currMention)) {
												channels.get(currChannel).getMentions().add(currMention);
											} else {
												ListIterator<Mention> mentionIterator = channels.get(currChannel)
														.getMentions().listIterator();
												while (mentionIterator.hasNext()) {
													Mention curr = mentionIterator.next();
													if (curr.equals(currMention)) {
														curr.setWeight(curr.getWeight() + 1);
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

	/**
	 * Restituisce la lista di tutti i channels nel workspace corrente.
	 * 
	 * @return riferimento ad una LinkedHashMap<String, Channel> che rappresenta la
	 *         lista di tutti i channels del workspace corrente.
	 */
	public LinkedHashMap<String, Channel> getAllChannels() {
		return (LinkedHashMap<String, Channel>) this.channels;
	}

	/**
	 * Restituisce la lista di tutti i members nel workspce corrente.
	 * 
	 * @return riferimento ad una LinkedHashMap<String, Member> che rappresenta la
	 *         lista di tutti i members del workspace corrente.
	 */
	public LinkedHashMap<String, Member> getAllMembers() {
		return (LinkedHashMap<String, Member>) this.members;
	}

	/**
	 * Restituisce la lista dei members di un determinato channel.
	 * 
	 * @param channelName
	 *            String che rappresenta un determinato channel.
	 * @return riferimento ad una LinkedList<Member> che rappresenta la lista di
	 *         member di un determinato channel.
	 * @throws ChannelNotValidException Lancia un eccezione quando il channel specificato non è valido.
	 */
	public LinkedList<Member> getMembersOfChannel(final String channelName) throws ChannelNotValidException {
		if (channels.containsKey(channelName)) {
			return channels.get(channelName).getMembers();
		} else {
			throw new ChannelNotValidException(channelName);
		}
	}

	/**
	 * Restituisce la lista di mentions fatte da un determinato member in un
	 * determinato channel.
	 * 
	 * @param channelInput
	 *            String che rappresenta un detrminato channel.
	 * @param memberInput
	 *            String che rappresenta un detrminato member.
	 * @return riferimento ad un LinkedList<Mention> che rappresenta la lista di
	 *         mention fatte da un determinato member in un detrminato channel.
	 * @throws ChannelNotValidException Lancia un eccezione quando il channel specificato non è valido.
	 * @throws MemberNotValidException Lancia un eccezione quando il member specificato non è valido.
	 */
	public LinkedList<Mention> getMentionsFromUser(final String channelInput, final String memberInput)
			throws ChannelNotValidException, MemberNotValidException {
		String memberID = memberInput;
		Collection<Member> membersCollection = members.values();
		Iterator<Member> membersIterator = membersCollection.iterator();
		boolean found = false;
		while (membersIterator.hasNext() && !found) {
			Member currMember = membersIterator.next();
			if (currMember.isUser(memberID)) {
				memberID = currMember.getId();
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
					if (curMention.getFrom().equals(members.get(memberID))) {
						out.add(curMention);
					}
				}
				return out;
			} else {
				throw new ChannelNotValidException(channelInput);
			}
		} else {
			throw new MemberNotValidException(memberID);
		}

	}

	/**
	 * Restituisce le mention riferite ad uno specifico member in uno specifico
	 * channel.
	 * 
	 * @param channelInput
	 *            String che rappresenta un detrminatro channel.
	 * @param memberInput
	 *            String che rappresenta un determinato member.
	 * @return riferimento ad un LinkedList<Mention> che rappresenta la lista di
	 *         mention fatte in uno specifico channel ad un particolare member.
	 * @throws ChannelNotValidException Lancia un eccezione quando il channel specificato non è valido.
	 * @throws MemberNotValidException Lancia un eccezione quando il member specificato non è valido.
	 */
	public LinkedList<Mention> getMentionsToUser(final String channelInput, final String memberInput)
			throws ChannelNotValidException, MemberNotValidException {
		String memberID = memberInput;
		Collection<Member> membersCollection = members.values();
		Iterator<Member> membersIterator = membersCollection.iterator();
		boolean found = false;
		while (membersIterator.hasNext() && !found) {
			Member currMember = membersIterator.next();
			if (currMember.isUser(memberID)) {
				memberID = currMember.getId();
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
					if (curMention.getTo().equals(members.get(memberID))) {
						out.add(curMention);
					}
				}
				return out;
			} else {
				throw new ChannelNotValidException(channelInput);
			}
		} else {
			throw new MemberNotValidException(memberID);
		}
	}

	/**
	 * Restituisce la lista di mention fatte in un determinato channel.
	 * 
	 * @param channelName
	 *            String che rappresenta un detrminatro channel.
	 * @return riferimento ad un LinkedList<Mention> che rappresenta la lista di
	 *         mention fatte in un determinato channel.
	 * @throws ChannelNotValidException Lancia un eccezione quando il channel specificato non è valido.
	 */
	public LinkedList<Mention> getMentions(final String channelName) throws ChannelNotValidException {
		if (channels.containsKey(channelName)) {
			return channels.get(channelName).getMentions();
		} else {
			throw new ChannelNotValidException(channelName);
		}
	}

	/**
	 * Restituisce il riferimento all'attributo workspaceZip della classe.
	 * 
	 * @return riferimento ad un oggetto istanza della classe zip che rappresenta il
	 *         workspace corrente.
	 */
	public Zip getWorkspaceZip() {
		return workspaceZip;
	}
}
