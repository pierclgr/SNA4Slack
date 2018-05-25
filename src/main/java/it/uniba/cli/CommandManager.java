package it.uniba.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
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
		ListIterator<Command> commandsIterator = (ListIterator<Command>) commands.getCommands().iterator();
		while (commandsIterator.hasNext()) {
			final Command curr = commandsIterator.next();
			if ((curr.getName() + " " + curr.getOptions()).length() >= maxNumCharCommand) {
				maxNumCharCommand = (curr.getName() + " " + curr.getOptions()).length();
			}
			if (curr.getDescription().length() >= maxNCharDesc) {
				maxNCharDesc = curr.getDescription().length();
			}
		}
		System.out.format("%" + maxNumCharCommand + "s\t%" + maxNCharDesc + "s", "COMMAND", "DESCRIPTION");
		System.out.println();
		System.out.println();
		commandsIterator = (ListIterator<Command>) commands.getCommands().iterator();
		while (commandsIterator.hasNext()) {
			final Command curr = commandsIterator.next();
			System.out.format("%" + maxNumCharCommand + "s\t%" + maxNCharDesc + "s",
					curr.getName() + " " + curr.getOptions(), curr.getDescription());
			System.out.println();
		}
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
			final LinkedHashMap<String, Channel> workspaceChannels = slackWorkspace.getAllChannels();
			final Collection<Channel> channels = workspaceChannels.values();
			final Iterator<Channel> channelsIterator = channels.iterator();
			while (channelsIterator.hasNext()) {
				System.out.println(channelsIterator.next().getName());
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
			final LinkedHashMap<String, Member> workspaceMembers = slackWorkspace.getAllMembers();
			final Collection<Member> members = workspaceMembers.values();
			final Iterator<Member> membersIterator = members.iterator();
			while (membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getName());
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
			final LinkedList<Member> channelMembers = slackWorkspace.getMembersOfChannel(channel);
			final ListIterator<Member> membersIterator = (ListIterator<Member>) channelMembers.iterator();
			while (membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getName());
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
			final LinkedHashMap<String, Channel> workspaceChannels = slackWorkspace.getAllChannels();
			final Collection<Channel> channels = workspaceChannels.values();
			final Iterator<Channel> channelsIterator = channels.iterator();
			while (channelsIterator.hasNext()) {
				final Channel curr = channelsIterator.next();
				final LinkedList<Member> channelMembers = slackWorkspace.getMembersOfChannel(curr.getName());
				final ListIterator<Member> membersIterator = (ListIterator<Member>) channelMembers.iterator();
				System.out.println("Members of \"" + curr.getName() + "\":");
				while (membersIterator.hasNext()) {
					System.out.println("\t" + membersIterator.next().getName());
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
			final Collection<Channel> chCollection = slackWorkspace.getAllChannels().values();
			final Iterator<Channel> channelsIeretor = chCollection.iterator();
			while (channelsIeretor.hasNext()) {
				final LinkedList<Mention> currChMentions = slackWorkspace
						.getMentionsFromUser(channelsIeretor.next().getName(), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) currChMentions.iterator();
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
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
			final Collection<Channel> chCollection = slackWorkspace.getAllChannels().values();
			final Iterator<Channel> channelsIeretor = chCollection.iterator();
			while (channelsIeretor.hasNext()) {
				final LinkedList<Mention> currChMentions = slackWorkspace
						.getMentionsFromUser(channelsIeretor.next().getName(), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) currChMentions.iterator();
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (out.containsKey(currMentionKey)) {
						out.get(currMentionKey)
								.setWeight(out.get(currMentionKey).getWeight() + currMention.getWeight());
					} else {
						out.put(currMentionKey, currMention);
					}
				}
			}
			final Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
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
			final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsFromUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
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
			final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsFromUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next().toFullString());
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
			final Collection<Channel> chCollection = slackWorkspace.getAllChannels().values();
			final Iterator<Channel> channelsIterator = chCollection.iterator();
			while (channelsIterator.hasNext()) {
				final LinkedList<Mention> workspaceMentions = slackWorkspace
						.getMentionsToUser(channelsIterator.next().getName(), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
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
			final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsToUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
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
			final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(channel);
			final Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
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
			final Collection<Channel> chCollection = slackWorkspace.getAllChannels().values();
			final Iterator<Channel> channelsIterator = chCollection.iterator();
			while (channelsIterator.hasNext()) {
				final Channel currchannel = channelsIterator.next();
				final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(currchannel.getName());
				final Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
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
			final Collection<Channel> chCollection = slackWorkspace.getAllChannels().values();
			final Iterator<Channel> channelsIterator = chCollection.iterator();
			while (channelsIterator.hasNext()) {
				final Channel currChannel = channelsIterator.next();
				final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(currChannel.getName());
				final Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (out.containsKey(currMentionKey)) {
						out.get(currMentionKey)
								.setWeight(currMention.getWeight() + out.get(currMentionKey).getWeight());
					} else {
						out.put(currMentionKey, currMention);
					}
				}
			}
			final Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
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
			final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(channel);
			final Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				final Mention currMention = mentionsIterator.next();
				final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
				out.put(currMentionKey, currMention);
			}
			final Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
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
			final Collection<Channel> chCollection = slackWorkspace.getAllChannels().values();
			final Iterator<Channel> channelsIterator = chCollection.iterator();
			while (channelsIterator.hasNext()) {
				final LinkedList<Mention> currChMentions = slackWorkspace
						.getMentionsToUser(channelsIterator.next().getName(), member);
				final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) currChMentions.iterator();
				while (mentionsIterator.hasNext()) {
					final Mention currMention = mentionsIterator.next();
					final String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (out.containsKey(currMentionKey)) {
						out.get(currMentionKey)
								.setWeight(out.get(currMentionKey).getWeight() + currMention.getWeight());
					} else {
						out.put(currMentionKey, currMention);
					}
				}
			}
			final Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
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
			final LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsToUser(channel, member);
			final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next().toFullString());
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
			CommandManager.help();
			break;
		case ONE:
			if (args[ZERO].equals("help")) {
				CommandManager.help();
			} else {
				System.out.println("'" + args[ZERO] + "'" + NOTVALIDCOMMAND);
			}
			break;
		case TWO:
			System.out.println("'" + args[ZERO] + " " + args[ONE] + "'" + NOTVALIDCOMMAND);
			break;
		case THREE:
			if (args[ZERO].equals("members") && args[ONE].equals("-f")) {
				CommandManager.getMembers(args[TWO]);
			} else if (args[ZERO].equals("channels") && args[ONE].equals("-f")) {
				CommandManager.getChannels(args[TWO]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-f")) {
				CommandManager.getMentions(args[TWO]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + "'" + NOTVALIDCOMMAND);
			}
			break;
		case FOUR:
			if (args[ZERO].equals("members") && args[ONE].equals(CHPARAMETER) && args[TWO].equals("-f")) {
				CommandManager.getMembersForChannels(args[THREE]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-w") && args[TWO].equals("-f")) {
				CommandManager.getMentionsWeighed(args[THREE]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + "'"
						+ NOTVALIDCOMMAND);
			}
			break;
		case FIVE:
			if (args[ZERO].equals("members") && args[ONE].equals(CHPARAMETER) && args[THREE].equals("-f")) {
				CommandManager.getMembersOfChannel(args[FOUR], args[TWO]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals(CHPARAMETER)
					&& args[THREE].equals("-f")) {
				CommandManager.getMentions(args[FOUR], args[TWO]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals(TOPARAMETER)
					&& args[THREE].equals("-f")) {
				CommandManager.getMentionsToUser(args[FOUR], args[TWO]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals(FROMPARAMETER)
					&& args[THREE].equals("-f")) {
				CommandManager.getMentionsFromUser(args[FOUR], args[TWO]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + "'" + NOTVALIDCOMMAND);
			}
			break;
		case SIX:
			if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-w") && args[TWO].equals(FROMPARAMETER)
					&& args[FOUR].equals("-f")) {
				CommandManager.getMentionsFromUserWeighed(args[FIVE], args[THREE]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-w") && args[TWO].equals(CHPARAMETER)
					&& args[FOUR].equals("-f")) {
				CommandManager.getMentionsWeighed(args[FIVE], args[THREE]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-w") && args[TWO].equals(TOPARAMETER)
					&& args[FOUR].equals("-f")) {
				CommandManager.getMentionsToUserWeighed(args[FIVE], args[THREE]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + "'" + args[FIVE] + "'" + NOTVALIDCOMMAND);
			}
			break;
		case SEVEN:
			if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals(TOPARAMETER) && args[THREE].equals(CHPARAMETER)
					&& args[FIVE].equals("-f")) {
				CommandManager.getMentionsToUser(args[SIX], args[FOUR], args[TWO]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals(FROMPARAMETER)
					&& args[THREE].equals(CHPARAMETER) && args[FIVE].equals("-f")) {
				CommandManager.getMentionsFromUser(args[SIX], args[FOUR], args[TWO]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + " " + args[FIVE] + " " + args[SIX] + "'" + NOTVALIDCOMMAND);
			}
			break;
		case EIGHT:
			if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-w") && args[TWO].equals(TOPARAMETER)
					&& args[FOUR].equals(CHPARAMETER) && args[SIX].equals("-f")) {
				CommandManager.getMentionsToUserWeighed(args[SEVEN], args[FIVE], args[THREE]);
			} else if (args[ZERO].equals(MENTIONSCOMMAND) && args[ONE].equals("-w") && args[TWO].equals(FROMPARAMETER)
					&& args[FOUR].equals(CHPARAMETER) && args[SIX].equals("-f")) {
				CommandManager.getMentionsFromUserWeighed(args[SEVEN], args[FIVE], args[THREE]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + " " + args[FIVE] + " " + args[SIX] + " " + args[SEVEN] + "'" + NOTVALIDCOMMAND);
			}
			break;
		default:
			System.out.println("Command not found, see 'help'.");
			break;
		}
	}
}
