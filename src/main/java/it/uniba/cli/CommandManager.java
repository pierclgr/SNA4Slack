package it.uniba.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import it.uniba.entity.Channel;
import it.uniba.entity.ChannelNotValidException;
import it.uniba.entity.Member;
import it.uniba.entity.MemberNotValidException;
import it.uniba.entity.Mention;
import it.uniba.entity.Workspace;
import it.uniba.file.PathManager;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;

/**
 * Classe che contiene l'implementazione dei possibili comandi.
 */
public final class CommandManager {
	/**
	 * Attributo di classe che rappresenta la stringa di errore di un comando non
	 * valido.
	 */
	public static final String NOTVALIDCOMMAND = " is not a valid command, see 'help'.";
	/**
	 * Attributo di classe che rappresenta la stringa del comando mentions.
	 */
	public static final String MENTIONSCOMMAND = "mentions";
	/**
	 * Attributo di classe che rappresenta la stringa del parametro -ch.
	 */
	public static final String CHPARAMETER = "-ch";
	/**
	 * Attributo di classe che rappresenta la stringa del parametro -from.
	 */
	public static final String FROMPARAMETER = "-from";
	/**
	 * Attributo di classe che rappresenta la stringa del parametro -ch.
	 */
	public static final String TOPARAMETER = "-to";
	/**
	 * Attributo di classe che rappresenta la stringa "not found".
	 */
	private static final String NOTFOUND = " not found";
	/**
	 * Attributo di classe che rappresenta il valore 0.
	 */
	private static final int ZERO = 0;
	/**
	 * Attributo di classe che rappresenta il valore 1.
	 */
	private static final int ONE = 1;
	/**
	 * Attributo di classe che rappresenta il valore 2.
	 */
	private static final int TWO = 2;
	/**
	 * Attributo di classe che rappresenta il valore 3.
	 */
	private static final int THREE = 3;
	/**
	 * Attributo di classe che rappresenta il valore 4.
	 */
	private static final int FOUR = 4;
	/**
	 * Attributo di classe che rappresenta il valore 5.
	 */
	private static final int FIVE = 5;
	/**
	 * Attributo di classe che rappresenta il valore 6.
	 */
	private static final int SIX = 6;
	/**
	 * Attributo di classe che rappresenta il valore 7.
	 */
	private static final int SEVEN = 7;
	/**
	 * Attributo di classe che rappresenta il valore 8.
	 */
	private static final int EIGHT = 8;

	/**
	 * Metodo costruttore della classe CommandManager, permette di costruire oggetti
	 * istanze della classe CommandManager.
	 */
	private CommandManager() {
	}

	/**
	 * Scrive sullo standard output stream la lista di tutti i possibili comandi.
	 */
	public static void help() {
		final Commands commands = new Commands();
		int maxNumCharCommand = ZERO;
		int maxNCharDesc = ZERO;
		final LinkedList<Command> commandList = (LinkedList<Command>) commands.getCommands();
		ListIterator<Command> commandsIterator = (ListIterator<Command>) getCommandIterator(commandList);
		while (commandsIterator.hasNext()) {
			final Command curr = commandsIterator.next();
			final int currCommLength = (getCommandName(curr) + " " + getCommandOptions(curr)).length();
			if (currCommLength >= maxNumCharCommand) {
				maxNumCharCommand = currCommLength;
			}
			final String currDesc = getCommandDescription(curr);
			final int currDescLength = getLength(currDesc);
			if (currDescLength >= maxNCharDesc) {
				maxNCharDesc = currDescLength;
			}
		}
		System.out.format("%" + maxNumCharCommand + "s\t%" + maxNCharDesc + "s", "COMMAND", "DESCRIPTION");
		System.out.println();
		System.out.println();
		commandsIterator = (ListIterator<Command>) getCommandIterator(commandList);
		while (commandsIterator.hasNext()) {
			final Command curr = commandsIterator.next();
			System.out.format("%" + maxNumCharCommand + "s\t%" + maxNCharDesc + "s",
					getCommandName(curr) + " " + getCommandOptions(curr), getCommandDescription(curr));
			System.out.println();
		}
	}

	private static Iterator<Command> getCommandIterator(final List<Command> commandList) {
		return commandList.iterator();
	}

	private static String getCommandName(final Command curr) {
		return curr.getName();
	}

	private static String getCommandOptions(final Command curr) {
		return curr.getOptions();
	}

	private static String getCommandDescription(final Command curr) {
		return curr.getDescription();
	}

	private static int getLength(final String stringa) {
		return stringa.length();
	}

	/**
	 * Scrive sullo standard output stream tutti i channels presenti nel workspace
	 * passatogli in input.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 */
	public static void getChannels(final String workspace) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> workspaceChannels = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> channels = getChannelCollection(workspaceChannels);
			final Iterator<Channel> channelsIterator = getChannelIterator(channels);
			while (channelsIterator.hasNext()) {
				final Channel currChannel = channelsIterator.next();
				System.out.println(getChannelName(currChannel));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream tutti i members presenti nel workspace
	 * pasatogli in input.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 */
	public static void getMembers(final String workspace) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Member> workspaceMembers = (LinkedHashMap<String, Member>) slackWorkspace
					.getAllMembers();
			final Collection<Member> members = getMemberCollection(workspaceMembers);
			final Iterator<Member> membersIterator = getMemberIterator(members);
			while (membersIterator.hasNext()) {
				final Member currMember = membersIterator.next();
				System.out.println(getMemberName(currMember));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());

		}
	}

	private static Collection<Member> getMemberCollection(final Map<String, Member> workspaceMembers) {
		return workspaceMembers.values();
	}

	private static Iterator<Member> getMemberIterator(final Collection<Member> members) {
		return members.iterator();
	}

	private static String getMemberName(final Member currMember) {
		return currMember.getName();
	}

	/**
	 * Scrive sullo standard output stream tutti i members di uno specifico channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta il channel di cui si vogliono ottenere i
	 *            Members.
	 */
	public static void getMembersOfChannel(final String workspace, final String channel) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Member> channelMembers = (LinkedList<Member>) slackWorkspace.getMembersOfChannel(channel);
			final ListIterator<Member> membersIterator = (ListIterator<Member>) getMemberIterator(channelMembers);
			while (membersIterator.hasNext()) {
				final Member currMember = membersIterator.next();
				System.out.println(getMemberName(currMember));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | FileNotInZipException | NotValidWorkspaceException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream tutti i member raggruppati per channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 */
	public static void getMembersForChannels(final String workspace) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> workspaceChannels = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> channels = getChannelCollection(workspaceChannels);
			final Iterator<Channel> channelsIterator = getChannelIterator(channels);
			while (channelsIterator.hasNext()) {
				final Channel currChannel = channelsIterator.next();
				final LinkedList<Member> channelMembers = (LinkedList<Member>) slackWorkspace
						.getMembersOfChannel(getChannelName(currChannel));
				final ListIterator<Member> membersIterator = (ListIterator<Member>) getMemberIterator(channelMembers);
				System.out.println("Members of \"" + getChannelName(currChannel) + "\":");
				while (membersIterator.hasNext()) {
					final Member currMember = membersIterator.next();
					System.out.println("\t" + getMemberName(currMember));
				}
				if (channelsIterator.hasNext()) {
					System.out.println();
				}
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		} catch (ChannelNotValidException e) {
			e.printStackTrace();
		}
	}

	private static Iterator<Member> getMemberIterator(final List<Member> channelMembers) {
		return channelMembers.iterator();
	}

	/**
	 * Scrive sullo standard output stream tutte le mentions effettuate da uno
	 * specifico member.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param member
	 *            String che rappresenta il member di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsFromUser(final String workspace, final String member) {
		try {
			final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> chList = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> chCollection = getChannelCollection(chList);
			final Iterator<Channel> channelsIeretor = getChannelIterator(chCollection);
			while (channelsIeretor.hasNext()) {
				final Channel currChannel = channelsIeretor.next();
				final LinkedList<Mention> currChMentions = (LinkedList<Mention>) slackWorkspace
						.getMentionsToUser(getChannelName(currChannel), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
						currChMentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
						System.out.println(currMention);
					}
				}
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException | ChannelNotValidException e) {
			e.printStackTrace();
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista pesata di mentions effettuate da
	 * uno specifico member.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param member
	 *            String che rappresenta il member di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsFromUserWeighed(final String workspace, final String member) {
		try {
			final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> channelList = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> chCollection = getChannelCollection(channelList);
			final Iterator<Channel> channelsIeretor = getChannelIterator(chCollection);
			while (channelsIeretor.hasNext()) {
				final Channel currChannel = channelsIeretor.next();
				final LinkedList<Mention> currChMentions = (LinkedList<Mention>) slackWorkspace
						.getMentionsToUser(getChannelName(currChannel), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
						currChMentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
					if (out.containsKey(currMentionKey)) {
						final Mention outMent = out.get(currMentionKey);
						setMentionWeight(outMent, getMentionWeight(currMention) + getMentionWeight(outMent));
					} else {
						out.put(currMentionKey, currMention);
					}
				}
			}
			final Collection<Mention> mentCollection = out.values();
			final Iterator<Mention> outIterator = getMentionIterator(mentCollection);
			while (outIterator.hasNext()) {
				final Mention currMent = outIterator.next();
				System.out.println(mentionToFullString(currMent));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException | ChannelNotValidException e) {
			e.printStackTrace();
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista di mentions effettuate da uno
	 * specifico member in un determinato channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta il channel di cui si vogliono conoscere le
	 *            mention.
	 * @param member
	 *            String che rappresenta il member di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsFromUser(final String workspace, final String channel, final String member) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
					.getMentionsFromUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
					workspaceMentions);
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next());
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista pesata di mentions effettuate da
	 * uno specifico member in un determinato channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta il channel di cui si vogliono conoscere le
	 *            mention.
	 * @param member
	 *            String che rappresenta il member di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsFromUserWeighed(final String workspace, final String channel, final String member) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
					.getMentionsFromUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
					workspaceMentions);
			while (mentionsIterator.hasNext()) {
				final Mention currMention = mentionsIterator.next();
				System.out.println(mentionToFullString(currMention));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista di mentions riferite ad uno
	 * specifico member.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param member
	 *            String che rappresenta il member di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsToUser(final String workspace, final String member) {
		try {
			final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> channelMap = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> chCollection = getChannelCollection(channelMap);
			final Iterator<Channel> channelsIterator = getChannelIterator(chCollection);
			while (channelsIterator.hasNext()) {
				final Channel currChannel = channelsIterator.next();
				final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
						.getMentionsToUser(getChannelName(currChannel), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
						workspaceMentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
						System.out.println(currMention);
					}
				}
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException | ChannelNotValidException e) {
			e.printStackTrace();
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista di mentions riferite ad uno
	 * specifico member in un determinato channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta il channel di cui si vogliono conoscere le
	 *            mention.
	 * @param member
	 *            String che rappresenta il member di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsToUser(final String workspace, final String channel, final String member) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
					.getMentionsToUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
					workspaceMentions);
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next());
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista di mentions in un determinato
	 * channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta il channel di cui si vogliono conoscere lee
	 *            mention.
	 */
	public static void getMentions(final String workspace, final String channel) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace.getMentions(channel);
			final Iterator<Mention> mentionsIterator = getMentionIterator(workspaceMentions);
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next());
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | FileNotInZipException | NotValidWorkspaceException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista di tutte le mentions.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 */
	public static void getMentions(final String workspace) {
		try {
			final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> channelMap = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> chCollection = getChannelCollection(channelMap);
			final Iterator<Channel> channelsIterator = getChannelIterator(chCollection);
			while (channelsIterator.hasNext()) {
				final Channel currchannel = channelsIterator.next();
				final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
						.getMentions(getChannelName(currchannel));
				final Iterator<Mention> mentionsIterator = getMentionIterator(workspaceMentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
						System.out.println(currMention);
					}
				}
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		} catch (ChannelNotValidException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scrive sullo standard output stream la lista pesata di tutte le mentions.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 */
	public static void getMentionsWeighed(final String workspace) {
		final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> channelMap = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> chCollection = getChannelCollection(channelMap);
			final Iterator<Channel> channelsIterator = getChannelIterator(chCollection);
			while (channelsIterator.hasNext()) {
				final Channel currChannel = channelsIterator.next();
				final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
						.getMentions(getChannelName(currChannel));
				final Iterator<Mention> mentionsIterator = getMentionIterator(workspaceMentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
					if (out.containsKey(currMentionKey)) {
						final Mention outMent = out.get(currMentionKey);
						setMentionWeight(outMent, getMentionWeight(currMention) + getMentionWeight(outMent));
					} else {
						out.put(currMentionKey, currMention);
					}
				}
			}
			final Collection<Mention> mentionColl = out.values();
			final Iterator<Mention> outIterator = getMentionIterator(mentionColl);
			while (outIterator.hasNext()) {
				final Mention currMention = outIterator.next();
				System.out.println(mentionToFullString(currMention));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		} catch (ChannelNotValidException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scrive sullo standard output stream la lista pesata di tutte le mentions
	 * prenti in un determinato channel.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta il channel di cui si vogliono conoscere le
	 *            mention.
	 */
	public static void getMentionsWeighed(final String workspace, final String channel) {
		final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace.getMentions(channel);
			final Iterator<Mention> mentionsIterator = getMentionIterator(workspaceMentions);
			while (mentionsIterator.hasNext()) {
				final Mention currMention = mentionsIterator.next();
				final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
				out.put(currMentionKey, currMention);
			}
			final Collection<Mention> mentionColl = out.values();
			final Iterator<Mention> outIterator = getMentionIterator(mentionColl);
			while (outIterator.hasNext()) {
				final Mention currMention = outIterator.next();
				System.out.println(mentionToFullString(currMention));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | FileNotInZipException | NotValidWorkspaceException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scrive sullo standard output stream la lista pesata di tutte le mentios
	 * riferite ad un particolare member.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param member
	 *            String che rappresenta un particolare member di cui si vogliono
	 *            conoscere le mention.
	 */
	public static void getMentionsToUserWeighed(final String workspace, final String member) {
		try {
			final LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedHashMap<String, Channel> channelMap = (LinkedHashMap<String, Channel>) slackWorkspace
					.getAllChannels();
			final Collection<Channel> chCollection = getChannelCollection(channelMap);
			final Iterator<Channel> channelsIterator = getChannelIterator(chCollection);
			while (channelsIterator.hasNext()) {
				final Channel currChannel = channelsIterator.next();
				final LinkedList<Mention> currChMentions = (LinkedList<Mention>) getMentionsToUser(slackWorkspace,
						getChannelName(currChannel), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
						currChMentions);
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = getMentionFromID(currMention) + "," + getMentionToID(currMention);
					if (out.containsKey(currMentionKey)) {
						final Mention outMent = out.get(currMentionKey);
						setMentionWeight(outMent, getMentionWeight(outMent) + getMentionWeight(currMention));
					} else {
						out.put(currMentionKey, currMention);
					}
				}
			}
			final Collection<Mention> mentionColl = out.values();
			final Iterator<Mention> outIterator = getMentionIterator(mentionColl);
			while (outIterator.hasNext()) {
				final Mention currMent = outIterator.next();
				System.out.println(mentionToFullString(currMent));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException | ChannelNotValidException e) {
			e.printStackTrace();
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	private static String getMentionToID(final Mention currMention) {
		return currMention.getToId();
	}

	private static Collection<Channel> getChannelCollection(final Map<String, Channel> channelMap) {
		return channelMap.values();
	}

	private static Iterator<Channel> getChannelIterator(final Collection<Channel> chCollection) {
		return chCollection.iterator();
	}

	private static List<Mention> getMentionsToUser(final Workspace slackWorkspace, final String currChannel,
			final String member) throws ChannelNotValidException, MemberNotValidException {
		return slackWorkspace.getMentionsToUser(currChannel, member);
	}

	private static String getChannelName(final Channel currChannel) {
		return currChannel.getName();
	}

	private static void setMentionWeight(final Mention outMent, final int newWeight) {
		outMent.setWeight(newWeight);
	}

	private static String getMentionFromID(final Mention currMention) {
		return currMention.getFromId();
	}

	private static int getMentionWeight(final Mention inMention) {
		return inMention.getWeight();
	}

	/**
	 * Scrive sullo standard output stream la lista pesata di tutte le mentions
	 * prenti in un determinato channel riferite ad un detereminato member.
	 * 
	 * @param workspace
	 *            String che rappresenta il percorso del workspace.
	 * @param channel
	 *            String che rappresenta un particolare channel di cui si vogliono
	 *            conoscere le mention.
	 * @param member
	 *            String che rappresenta un particolare member di cui si vogliono
	 *            conoscere le mention.
	 */
	public static void getMentionsToUserWeighed(final String workspace, final String channel, final String member) {
		try {
			final Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			final LinkedList<Mention> workspaceMentions = (LinkedList<Mention>) slackWorkspace
					.getMentionsToUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) getMentionIterator(
					workspaceMentions);
			while (mentionsIterator.hasNext()) {
				final Mention currMention = mentionsIterator.next();
				System.out.println(mentionToFullString(currMention));
			}
		} catch (FileNotFoundException | NoSuchFileException e) {
			System.out.println(PathManager.getAbsolutePath(workspace) + NOTFOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	private static Iterator<Mention> getMentionIterator(final List<Mention> workspaceMentions) {
		return workspaceMentions.iterator();
	}

	private static Iterator<Mention> getMentionIterator(final Collection<Mention> workspaceMentions) {
		return workspaceMentions.iterator();
	}

	private static String mentionToFullString(final Mention currMention) {
		return currMention.toFullString();
	}

	/**
	 * Permette di richiamare il giusto metodo associato al comando specificato
	 * dall'utente attraverso la riga di comando.
	 * 
	 * @param args
	 *            array di String ciascuno dei quali contiene un singolo elemento
	 *            del comando digitato dall'utente da riga di comando.
	 */
	public static void manage(final String... args) {
		switch (args.length) {
		case ZERO:
			help();
			break;
		case ONE:
			oneArgs(args);
			break;
		case TWO:
			twoArgs(args);
			break;
		case THREE:
			threeArgs(args);
			break;
		case FOUR:
			fourArgs(args);
			break;
		case FIVE:
			fiveArgs(args);
			break;
		case SIX:
			sixArgs(args);
			break;
		case SEVEN:
			sevenArgs(args);
			break;
		case EIGHT:
			eightArgs(args);
			break;
		default:
			System.out.println("Command not found, see 'help'.");
			break;
		}
	}

	private static void oneArgs(final String... args) {
		final String first;
		first = args[ZERO];
		if ("help".equals(first)) {
			help();
		} else {
			System.out.println("'" + first + "'" + NOTVALIDCOMMAND);
		}
	}

	private static void twoArgs(final String... args) {
		final String first, second;
		first = args[ZERO];
		second = args[ONE];
		System.out.println("'" + first + " " + second + "'" + NOTVALIDCOMMAND);
	}

	private static void threeArgs(final String... args) {
		final String first, second, third;
		first = args[ZERO];
		second = args[ONE];
		third = args[TWO];
		if ("members".equals(first) && "-f".equals(second)) {
			getMembers(third);
		} else if ("channels".equals(first) && "-f".equals(second)) {
			getChannels(third);
		} else if (MENTIONSCOMMAND.equals(first) && "-f".equals(second)) {
			getMentions(third);
		} else {
			System.out.println("'" + first + " " + second + " " + third + "'" + NOTVALIDCOMMAND);
		}
	}

	private static void fourArgs(final String... args) {
		final String first, second, third, fourth;
		first = args[ZERO];
		second = args[ONE];
		third = args[TWO];
		fourth = args[THREE];
		if ("members".equals(first) && CHPARAMETER.equals(second) && "-f".equals(third)) {
			getMembersForChannels(fourth);
		} else if (MENTIONSCOMMAND.equals(first) && "-w".equals(second) && "-f".equals(third)) {
			getMentionsWeighed(fourth);
		} else {
			System.out.println("'" + first + " " + second + " " + third + " " + fourth + "'" + NOTVALIDCOMMAND);
		}
	}

	private static void fiveArgs(final String... args) {
		final String first, second, third, fourth, fifth;
		first = args[ZERO];
		second = args[ONE];
		third = args[TWO];
		fourth = args[THREE];
		fifth = args[FOUR];
		if ("-f".equals(fourth)) {
			if (CHPARAMETER.equals(second)) {
				if ("members".equals(first)) {
					getMembersOfChannel(fifth, third);
				} else if (MENTIONSCOMMAND.equals(first)) {
					getMentions(fifth, third);
				}
			} else if (MENTIONSCOMMAND.equals(first)) {
				if (TOPARAMETER.equals(second)) {
					getMentionsToUser(fifth, third);
				} else if (FROMPARAMETER.equals(second)) {
					getMentionsFromUser(fifth, third);
				}
			}
		} else {
			System.out.println(
					"'" + first + " " + second + " " + third + " " + fourth + " " + fifth + "'" + NOTVALIDCOMMAND);
		}
	}

	private static void sixArgs(final String... args) {
		final String first, second, third, fourth, fifth, sixth;
		first = args[ZERO];
		second = args[ONE];
		third = args[TWO];
		fourth = args[THREE];
		fifth = args[FOUR];
		sixth = args[FIVE];
		if (MENTIONSCOMMAND.equals(first) && "-w".equals(second) && "-f".equals(fifth)) {
			if (FROMPARAMETER.equals(third)) {
				getMentionsFromUserWeighed(sixth, fourth);
			} else if (CHPARAMETER.equals(third)) {
				getMentionsWeighed(sixth, fourth);
			} else if (TOPARAMETER.equals(third)) {
				getMentionsToUserWeighed(sixth, fourth);
			}
		} else {
			System.out.println("'" + first + " " + second + " " + third + " " + fourth + " " + fifth + "'" + sixth + "'"
					+ NOTVALIDCOMMAND);
		}
	}

	private static void sevenArgs(final String... args) {
		final String first, second, third, fourth, fifth, sixth, seventh;
		first = args[ZERO];
		second = args[ONE];
		third = args[TWO];
		fourth = args[THREE];
		fifth = args[FOUR];
		sixth = args[FIVE];
		seventh = args[SIX];
		if (MENTIONSCOMMAND.equals(first) && TOPARAMETER.equals(second) && CHPARAMETER.equals(fourth)
				&& "-f".equals(sixth)) {
			getMentionsToUser(seventh, fifth, third);
		} else if (MENTIONSCOMMAND.equals(first) && FROMPARAMETER.equals(second) && CHPARAMETER.equals(fourth)
				&& "-f".equals(sixth)) {
			getMentionsFromUser(seventh, fifth, third);
		} else {
			System.out.println("'" + first + " " + second + " " + third + " " + fourth + " " + fifth + " " + sixth + " "
					+ seventh + "'" + NOTVALIDCOMMAND);
		}
	}

	private static void eightArgs(final String... args) {
		final String first, second, third, fourth, fifth, sixth, seventh, eighth;
		first = args[ZERO];
		second = args[ONE];
		third = args[TWO];
		fourth = args[THREE];
		fifth = args[FOUR];
		sixth = args[FIVE];
		seventh = args[SIX];
		eighth = args[SEVEN];
		if (MENTIONSCOMMAND.equals(first) && "-w".equals(second) && CHPARAMETER.equals(fifth) && "-f".equals(seventh)) {
			if (TOPARAMETER.equals(third)) {
				getMentionsToUserWeighed(eighth, sixth, fourth);
			} else if (FROMPARAMETER.equals(third)) {
				getMentionsFromUserWeighed(eighth, sixth, fourth);
			}
		} else {
			System.out.println("'" + first + " " + second + " " + third + " " + fourth + " " + fifth + " " + sixth + " "
					+ seventh + " " + eighth + "'" + NOTVALIDCOMMAND);
		}
	}
}
