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

public final class CommandManager {
	static final int ZERO = 0;
	static final int ONE = 1;
	static final int TWO = 2;
	static final int THREE = 3;
	static final int FOUR = 4;
	static final int FIVE = 5;
	static final int SIX = 6;
	static final int SEVEN = 7;
	static final int EIGHT = 8;
	static final int NINE = 9;

	private CommandManager() {
	}

	public static void help() {
		Commands commands = new Commands();
		int maxNumCharCommand = ZERO;
		int maxNumCharDescription = ZERO;
		ListIterator<Command> commandsIterator = (ListIterator<Command>) commands.getCommands().iterator();
		while (commandsIterator.hasNext()) {
			Command curr = commandsIterator.next();
			if ((curr.getName() + " " + curr.getOptions()).length() >= maxNumCharCommand) {
				maxNumCharCommand = (curr.getName() + " " + curr.getOptions()).length();
			}
			if (curr.getDescription().length() >= maxNumCharDescription) {
				maxNumCharDescription = curr.getDescription().length();
			}
		}
		System.out.format("%" + maxNumCharCommand + "s\t%" + maxNumCharDescription + "s", "COMMAND", "DESCRIPTION");
		System.out.println();
		System.out.println();
		commandsIterator = (ListIterator<Command>) commands.getCommands().iterator();
		while (commandsIterator.hasNext()) {
			Command curr = commandsIterator.next();
			System.out.format("%" + maxNumCharCommand + "s\t%" + maxNumCharDescription + "s",
					curr.getName() + " " + curr.getOptions(), curr.getDescription());
			System.out.println();
		}
	}

	public static void getChannels(final String workspace) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedHashMap<String, Channel> workspaceChannels = slackWorkspace.getAllChannels();
			Collection<Channel> c = workspaceChannels.values();
			Iterator<Channel> channelsIterator = c.iterator();
			while (channelsIterator.hasNext()) {
				System.out.println(channelsIterator.next().getName());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMembers(final String workspace) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedHashMap<String, Member> workspaceMembers = slackWorkspace.getAllMembers();
			Collection<Member> c = workspaceMembers.values();
			Iterator<Member> membersIterator = c.iterator();
			while (membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getName());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());

		}
	}

	public static void getMembersOfChannel(final String workspace, final String channel) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Member> channelMembers = slackWorkspace.getMembersOfChannel(channel);
			ListIterator<Member> membersIterator = (ListIterator<Member>) channelMembers.iterator();
			while (membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getName());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | FileNotInZipException | NotValidWorkspaceException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMembersForChannels(final String workspace) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedHashMap<String, Channel> workspaceChannels = slackWorkspace.getAllChannels();
			Collection<Channel> c = workspaceChannels.values();
			Iterator<Channel> channelsIterator = c.iterator();
			while (channelsIterator.hasNext()) {
				Channel curr = channelsIterator.next();
				LinkedList<Member> channelMembers = slackWorkspace.getMembersOfChannel(curr.getName());
				ListIterator<Member> membersIterator = (ListIterator<Member>) channelMembers.iterator();
				System.out.println("Members of \"" + curr.getName() + "\":");
				while (membersIterator.hasNext()) {
					System.out.println("\t" + membersIterator.next().getName());
				}
				if (channelsIterator.hasNext()) {
					System.out.println();
				}
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		} catch (ChannelNotValidException e) {
			e.printStackTrace();
		}
	}

	public static void getMentionsFromUser(final String workspace, final String member) {
		try {
			LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			Collection<Channel> channelsCollection = slackWorkspace.getAllChannels().values();
			Iterator<Channel> channelsIeretor = channelsCollection.iterator();
			while (channelsIeretor.hasNext()) {
				LinkedList<Mention> currChannelMentions = slackWorkspace
						.getMentionsFromUser(channelsIeretor.next().getName(), member);
				ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) currChannelMentions.iterator();
				while (mentionsIterator.hasNext()) {
					Mention currMention = mentionsIterator.next();
					String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
						System.out.println(currMention);
					}
				}
			}
		} catch (IOException | ChannelNotValidException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsFromUserWeighed(final String workspace, final String member) {
		try {
			LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			Collection<Channel> channelsCollection = slackWorkspace.getAllChannels().values();
			Iterator<Channel> channelsIeretor = channelsCollection.iterator();
			while (channelsIeretor.hasNext()) {
				LinkedList<Mention> currChannelMentions = slackWorkspace
						.getMentionsFromUser(channelsIeretor.next().getName(), member);
				ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) currChannelMentions.iterator();
				while (mentionsIterator.hasNext()) {
					Mention currMention = mentionsIterator.next();
					String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
					} else {
						out.get(currMentionKey)
								.setWeight(out.get(currMentionKey).getWeight() + currMention.getWeight());
					}
				}
			}
			Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
			}
		} catch (IOException | ChannelNotValidException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsFromUser(final String workspace, final String channel, final String member) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsFromUser(channel, member);
			ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsFromUserWeighed(final String workspace, final String channel, final String member) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsFromUser(channel, member);
			ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next().toFullString());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsToUser(final String workspace, final String member) {
		try {
			LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			Collection<Channel> channelsCollection = slackWorkspace.getAllChannels().values();
			Iterator<Channel> channelsIterator = channelsCollection.iterator();
			while (channelsIterator.hasNext()) {
				LinkedList<Mention> workspaceMentions = slackWorkspace
						.getMentionsToUser(channelsIterator.next().getName(), member);
				ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
				while (mentionsIterator.hasNext()) {
					Mention currMention = mentionsIterator.next();
					String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
						System.out.println(currMention);
					}
				}
			}
		} catch (IOException | ChannelNotValidException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsToUser(final String workspace, final String channel, final String member) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsToUser(channel, member);
			ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentions(final String workspace, final String channel) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(channel);
			Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | FileNotInZipException | NotValidWorkspaceException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentions(final String workspace) {
		try {
			LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			Collection<Channel> channelsCollection = slackWorkspace.getAllChannels().values();
			Iterator<Channel> channelsIterator = channelsCollection.iterator();
			while (channelsIterator.hasNext()) {
				Channel currchannel = channelsIterator.next();
				LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(currchannel.getName());
				Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
				while (mentionsIterator.hasNext()) {
					Mention currMention = mentionsIterator.next();
					String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
						System.out.println(currMention);
					}
				}
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		} catch (ChannelNotValidException e) {
			e.printStackTrace();
		}
	}

	public static void getMentionsWeighed(final String workspace) {
		LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			Collection<Channel> channelsCollection = slackWorkspace.getAllChannels().values();
			Iterator<Channel> channelsIterator = channelsCollection.iterator();
			while (channelsIterator.hasNext()) {
				Channel currchannel = channelsIterator.next();
				LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(currchannel.getName());
				Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
				while (mentionsIterator.hasNext()) {
					Mention currMention = mentionsIterator.next();
					String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
					} else {
						out.get(currMentionKey)
								.setWeight(currMention.getWeight() + out.get(currMentionKey).getWeight());
					}
				}
			}
			Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException | NotValidWorkspaceException | NotZipFileException e) {
			System.out.println(e.getMessage());
		} catch (ChannelNotValidException e) {
			e.printStackTrace();
		}
	}

	public static void getMentionsWeighed(final String workspace, final String channel) {
		LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Mention> workspaceMentions = slackWorkspace.getMentions(channel);
			Iterator<Mention> mentionsIterator = workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				Mention currMention = mentionsIterator.next();
				String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
				out.put(currMentionKey, currMention);
			}
			Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | FileNotInZipException | NotValidWorkspaceException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsToUserWeighed(final String workspace, final String member) {
		try {
			LinkedHashMap<String, Mention> out = new LinkedHashMap<String, Mention>();
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			Collection<Channel> channelsCollection = slackWorkspace.getAllChannels().values();
			Iterator<Channel> channelsIeretor = channelsCollection.iterator();
			while (channelsIeretor.hasNext()) {
				LinkedList<Mention> currChannelMentions = slackWorkspace
						.getMentionsToUser(channelsIeretor.next().getName(), member);
				ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) currChannelMentions.iterator();
				while (mentionsIterator.hasNext()) {
					Mention currMention = mentionsIterator.next();
					String currMentionKey = currMention.getFrom().getId() + "," + currMention.getTo().getId();
					if (!out.containsKey(currMentionKey)) {
						out.put(currMentionKey, currMention);
					} else {
						out.get(currMentionKey)
								.setWeight(out.get(currMentionKey).getWeight() + currMention.getWeight());
					}
				}
			}
			Iterator<Mention> outIterator = out.values().iterator();
			while (outIterator.hasNext()) {
				System.out.println(outIterator.next().toFullString());
			}
		} catch (IOException | ChannelNotValidException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (MemberNotValidException | NotValidWorkspaceException | FileNotInZipException | NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void getMentionsToUserWeighed(final String workspace, final String channel, final String member) {
		try {
			Workspace slackWorkspace = new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Mention> workspaceMentions = slackWorkspace.getMentionsToUser(channel, member);
			ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) workspaceMentions.iterator();
			while (mentionsIterator.hasNext()) {
				System.out.println(mentionsIterator.next().toFullString());
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException || e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace) + " not found");
			} else {
				e.printStackTrace();
			}
		} catch (ChannelNotValidException | MemberNotValidException | NotValidWorkspaceException | FileNotInZipException
				| NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void manage(final String[] args) {
		switch (args.length) {
		case ZERO:
			CommandManager.help();
			break;
		case ONE:
			if (args[ZERO].equals("help")) {
				CommandManager.help();
			} else {
				System.out.println("'" + args[ZERO] + "'" + " is not a valid command, see 'help'.");
			}
			break;
		case TWO:
			System.out.println("'" + args[ZERO] + " " + args[ONE] + "'" + " is not a valid command, see 'help'.");
			break;
		case THREE:
			if (args[ZERO].equals("members") && args[ONE].equals("-f")) {
				CommandManager.getMembers(args[TWO]);
			} else if (args[ZERO].equals("channels") && args[ONE].equals("-f")) {
				CommandManager.getChannels(args[TWO]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-f")) {
				CommandManager.getMentions(args[TWO]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + "'"
						+ " is not a valid command, see 'help'.");
			}
			break;
		case FOUR:
			if (args[ZERO].equals("members") && args[ONE].equals("-ch") && args[TWO].equals("-f")) {
				CommandManager.getMembersForChannels(args[THREE]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-w") && args[TWO].equals("-f")) {
				CommandManager.getMentionsWeighed(args[THREE]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + "'"
						+ " is not a valid command, see 'help'.");
			}
			break;
		case FIVE:
			if (args[ZERO].equals("members") && args[ONE].equals("-ch") && args[THREE].equals("-f")) {
				CommandManager.getMembersOfChannel(args[FOUR], args[TWO]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-ch") && args[THREE].equals("-f")) {
				CommandManager.getMentions(args[FOUR], args[TWO]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-to") && args[THREE].equals("-f")) {
				CommandManager.getMentionsToUser(args[FOUR], args[TWO]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-from") && args[THREE].equals("-f")) {
				CommandManager.getMentionsFromUser(args[FOUR], args[TWO]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + "'" + " is not a valid command, see 'help'.");
			}
			break;
		case SIX:
			if (args[ZERO].equals("mentions") && args[ONE].equals("-w") && args[TWO].equals("-from")
					&& args[FOUR].equals("-f")) {
				CommandManager.getMentionsFromUserWeighed(args[FIVE], args[THREE]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-w") && args[TWO].equals("-ch")
					&& args[FOUR].equals("-f")) {
				CommandManager.getMentionsWeighed(args[FIVE], args[THREE]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-w") && args[TWO].equals("-to")
					&& args[FOUR].equals("-f")) {
				CommandManager.getMentionsToUserWeighed(args[FIVE], args[THREE]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + "'" + args[FIVE] + "'" + " is not a valid command, see 'help'.");
			}
			break;
		case SEVEN:
			if (args[ZERO].equals("mentions") && args[ONE].equals("-to") && args[THREE].equals("-ch")
					&& args[FIVE].equals("-f")) {
				CommandManager.getMentionsToUser(args[SIX], args[FOUR], args[TWO]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-from") && args[THREE].equals("-ch")
					&& args[FIVE].equals("-f")) {
				CommandManager.getMentionsFromUser(args[SIX], args[FOUR], args[TWO]);
			} else {
				System.out.println(
						"'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " " + args[FOUR]
								+ " " + args[FIVE] + " " + args[SIX] + "'" + " is not a valid command, see 'help'.");
			}
			break;
		case EIGHT:
			if (args[ZERO].equals("mentions") && args[ONE].equals("-w") && args[TWO].equals("-to")
					&& args[FOUR].equals("-ch") && args[SIX].equals("-f")) {
				CommandManager.getMentionsToUserWeighed(args[SEVEN], args[FIVE], args[THREE]);
			} else if (args[ZERO].equals("mentions") && args[ONE].equals("-w") && args[TWO].equals("-from")
					&& args[FOUR].equals("-ch") && args[SIX].equals("-f")) {
				CommandManager.getMentionsFromUserWeighed(args[SEVEN], args[FIVE], args[THREE]);
			} else {
				System.out.println("'" + args[ZERO] + " " + args[ONE] + " " + args[TWO] + " " + args[THREE] + " "
						+ args[FOUR] + " " + args[FIVE] + " " + args[SIX] + " " + args[SEVEN] + "'"
						+ " is not a valid command, see 'help'.");
			}
			break;
		default:
			System.out.println("Command not found, see 'help'.");
			break;
		}
	}
}
