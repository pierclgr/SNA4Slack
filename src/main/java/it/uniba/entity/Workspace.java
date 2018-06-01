package it.uniba.entity;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.json.JSONArray;
import org.json.JSONObject;

import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;
import it.uniba.file.zip.FileZip;

/**
 * Classe che modella il workspace.
 */
public final class Workspace {
	/**
	 * zip file.
	 */
	private FileZip workspaceZip;
	/**
	 * lista di channel nel workspace.
	 */
	private Map<String, Channel> channels;
	/**
	 * lista di members nel workspace.
	 */
	private Map<String, Member> members;

	static final String PROFILE = "profile";

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
	 *             non � valido.
	 * @throws FileNotInZipException
	 *             Lancia FileNotInZipException quando il file specificato non �
	 *             all'interno dello zip file.
	 * @throws NotZipFileException
	 *             Lancia NotZipFileException quando il file zip specificato non �
	 *             valido.
	 */
	public Workspace(final String workspaceZipFile)
			throws IOException, NotValidWorkspaceException, FileNotInZipException, NotZipFileException {
		try {
			final FileZip workspace = new FileZip(workspaceZipFile);
			Member currMember;
			if (workspace.contains("channels.json") && workspace.contains("users.json")) {
				this.workspaceZip = workspace;
				this.channels = new LinkedHashMap<String, Channel>();
				this.members = new LinkedHashMap<String, Member>();
				String realName = "";
				String displayName = "";

				final JSONArray membersRootArray = new JSONArray(workspaceZip.getFileContent("users.json"));
				for (int i = 0; i < membersRootArray.length(); i++) {
					final JSONObject member = membersRootArray.getJSONObject(i);
					if (member.getJSONObject(PROFILE).has("real_name")) {
						realName = member.getJSONObject(PROFILE).getString("real_name");
					}
					if (member.getJSONObject(PROFILE).has("display_name")) {
						displayName = member.getJSONObject(PROFILE).getString("display_name");
					}
					currMember = new Member(member.getString("id"), member.getString("name"), realName, displayName);
					members.put(member.getString("id"), currMember);
				}
				final JSONArray channelsRootArray = new JSONArray(workspaceZip.getFileContent("channels.json"));
				for (int j = 0; j < channelsRootArray.length(); j++) {
					final JSONObject channel = channelsRootArray.getJSONObject(j);
					final Channel currChannel = new Channel(channel.getString("id"), channel.getString("name"));
					final JSONArray membersArray = channel.getJSONArray("members");
					for (int k = 0; k < membersArray.length(); k++) {
						final String memberId = membersArray.getString(k);
						currChannel.getMembers().add(members.get(memberId));
						members.get(memberId).getChannels().add(currChannel);
					}
					channels.put(currChannel.getName(), currChannel);
				}
				final Enumeration<? extends ZipEntry> entries = workspaceZip.getZipFile().entries();
				while (entries.hasMoreElements()) {
					final ZipEntry zipEntry = (ZipEntry) entries.nextElement();
					if (!zipEntry.isDirectory()) {
						final String curFileName = zipEntry.getName();
						final String currChannel = curFileName.split("/")[0];
						final Pattern patternDate = Pattern
								.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
						final String channelCont = workspaceZip.getFileContent(curFileName);
						if (channels.get(currChannel) != null
								&& patternDate.matcher(curFileName.split("/")[1].split("\\.")[0]).matches()
								&& curFileName.split("/")[1].split("\\.")[1].equals("json")) {
							final JSONArray messagesRootArray = new JSONArray(channelCont);
							for (int i = 0; i < messagesRootArray.length(); i++) {
								final JSONObject message = messagesRootArray.getJSONObject(i);
								if (!message.isNull("user")) {
									final String currSender = message.getString("user");
									final String currMessage = message.getString("text");
									final Pattern pattern = Pattern
											.compile("[<@]+(?=.*[0-9])(?=.*[A-Z])([A-Z0-9]+)+[>]");
									final Matcher matcher = pattern.matcher(currMessage);
									final List<String> allMatches = new LinkedList<String>();
									while (matcher.find()) {
										final String curr = matcher.group().replaceAll("<@", "").replaceAll(">", "");
										if (members.containsKey(curr) && !curr.equals(currSender)) {
											allMatches.add(curr);
										}
									}
									final ListIterator<String> matchesIterator = (ListIterator<String>) allMatches
											.iterator();
									while (matchesIterator.hasNext()) {
										final String currReceiver = matchesIterator.next();
										final Mention currMention = new Mention(members.get(currSender),
												members.get(currReceiver));
										if (channels.get(currChannel).containsMention(currMention)) {
											final ListIterator<Mention> mentionIterator = channels.get(currChannel)
													.getMentions().listIterator();
											while (mentionIterator.hasNext()) {
												final Mention curr = mentionIterator.next();
												if (curr.equals(currMention)) {
													curr.setWeight(curr.getWeight() + 1);
												}
											}
										} else {
											channels.get(currChannel).getMentions().add(currMention);
										}
									}
								}
							}
						}
					}
				}
			} else {
				throw new NotValidWorkspaceException(workspaceZipFile);
			}
		} catch (ZipException e) {
			throw (NotZipFileException) new NotZipFileException(workspaceZipFile).initCause(e);
		}
	}

	private Iterator<Member> getMembersIterator(final Collection<Member> membersCollection) {
		return membersCollection.iterator();
	}

	/**
	 * Restituisce la hashmap di tutti i channels nel workspace corrente.
	 * 
	 * @return riferimento ad una LinkedHashMap<String, Channel> che rappresenta la
	 *         lista di tutti i channels del workspace corrente.
	 */
	public Map<String, Channel> getAllChannels() {
		return (LinkedHashMap<String, Channel>) this.channels;
	}

	/**
	 * Restituisce la lista di tutti i members nel workspce corrente.
	 * 
	 * @return riferimento ad una LinkedHashMap<String, Member> che rappresenta la
	 *         lista di tutti i members del workspace corrente.
	 */
	public Map<String, Member> getAllMembers() {
		return (LinkedHashMap<String, Member>) this.members;
	}

	/**
	 * Restituisce la lista dei members di un determinato channel.
	 * 
	 * @param channelName
	 *            String che rappresenta un determinato channel.
	 * @return riferimento ad una LinkedList<Member> che rappresenta la lista di
	 *         member di un determinato channel.
	 * @throws ChannelNotValidException
	 *             Lancia un eccezione quando il channel specificato non � valido.
	 */
	public List<Member> getMembersOfChannel(final String channelName) throws ChannelNotValidException {
		if (channels.containsKey(channelName)) {
			final Channel curChannel = channels.get(channelName);
			return (LinkedList<Member>) getMemberFromChannel(curChannel);
		} else {
			throw new ChannelNotValidException(channelName);
		}
	}

	private List<Member> getMemberFromChannel(final Channel curChannel) {
		return curChannel.getMembers();
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
	 * @throws ChannelNotValidException
	 *             Lancia un eccezione quando il channel specificato non � valido.
	 * @throws MemberNotValidException
	 *             Lancia un eccezione quando il member specificato non � valido.
	 */
	public List<Mention> getMentionsFromUser(final String channelInput, final String memberInput)
			throws ChannelNotValidException, MemberNotValidException {
		String memberID = memberInput;
		final Collection<Member> membersCollection = members.values();
		final Iterator<Member> membersIterator = getMembersIterator(membersCollection);
		boolean found = false;
		while (membersIterator.hasNext() && !found) {
			final Member currMember = membersIterator.next();
			if (memberIsUser(currMember, memberID)) {
				memberID = getMemberID(currMember);
				found = true;
			}
		}
		if (found) {
			if (channels.containsKey(channelInput)) {
				final LinkedList<Mention> out = new LinkedList<Mention>();
				final Channel curChannel = channels.get(channelInput);
				final LinkedList<Mention> mentions = (LinkedList<Mention>) getMentions(curChannel);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionsIterator(mentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final Member fromMember = getMentionFrom(currMention);
					if (memberEquals(fromMember, members.get(memberID))) {
						out.add(currMention);
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

	private Member getMentionFrom(final Mention currMention) {
		return currMention.getFrom();
	}

	private String getMemberID(final Member currMember) {
		return currMember.getId();
	}

	private boolean memberIsUser(final Member currMember, final String memberID) {
		return currMember.isUser(memberID);
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
	 * @throws ChannelNotValidException
	 *             Lancia un eccezione quando il channel specificato non � valido.
	 * @throws MemberNotValidException
	 *             Lancia un eccezione quando il member specificato non � valido.
	 */
	public List<Mention> getMentionsToUser(final String channelInput, final String memberInput)
			throws ChannelNotValidException, MemberNotValidException {
		String memberID = memberInput;
		final Collection<Member> membersCollection = members.values();
		final Iterator<Member> membersIterator = getMembersIterator(membersCollection);
		boolean found = false;
		while (membersIterator.hasNext() && !found) {
			final Member currMember = membersIterator.next();
			if (memberIsUser(currMember, memberID)) {
				memberID = getMemberID(currMember);
				found = true;
			}
		}
		if (found) {
			if (channels.containsKey(channelInput)) {
				final LinkedList<Mention> out = new LinkedList<Mention>();
				final Channel currChannel = channels.get(channelInput);
				final LinkedList<Mention> mentions = (LinkedList<Mention>) getMentions(currChannel);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionsIterator(mentions);
				while (mentionsIterator.hasNext()) {
					final Mention curMention = mentionsIterator.next();
					final Member toMember = getMentionTo(curMention);
					if (memberEquals(toMember, getMemberByID(members, memberID))) {
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

	private List<Mention> getMentions(final Channel currChannel) {
		return currChannel.getMentions();
	}

	private Iterator<Mention> getMentionsIterator(final List<Mention> mentions) {
		return mentions.iterator();
	}

	private Member getMentionTo(final Mention curMention) {
		return curMention.getTo();
	}

	private boolean memberEquals(final Member firstMember, final Member secondMember) {
		return firstMember.equals(secondMember);
	}

	private Member getMemberByID(final Map<String, Member> membersMap, final String memberID) {
		return membersMap.get(memberID);
	}

	/**
	 * Restituisce la lista di mention fatte in un determinato channel.
	 * 
	 * @param channelName
	 *            String che rappresenta un detrminatro channel.
	 * @return riferimento ad un LinkedList<Mention> che rappresenta la lista di
	 *         mention fatte in un determinato channel.
	 * @throws ChannelNotValidException
	 *             Lancia un eccezione quando il channel specificato non � valido.
	 */
	public List<Mention> getMentions(final String channelName) throws ChannelNotValidException {
		if (channels.containsKey(channelName)) {
			final Channel currChannel = channels.get(channelName);
			return getMentionFromChannel(currChannel);
		} else {
			throw new ChannelNotValidException(channelName);
		}
	}

	private List<Mention> getMentionFromChannel(final Channel currChannel) {
		return currChannel.getMentions();
	}

	/**
	 * Restituisce il riferimento all'attributo workspaceZip della classe.
	 * 
	 * @return riferimento ad un oggetto istanza della classe zip che rappresenta il
	 *         workspace corrente.
	 */
	public FileZip getWorkspaceZip() {
		return workspaceZip;
	}
}
