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
import it.uniba.entity.Member;
import it.uniba.entity.Workspace;
import it.uniba.file.PathManager;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;

public class CommandManager {
	public static void help(){
		Commands commands=new Commands();
		int maxNumCharCommand=0;
		int maxNumCharDescription=0;
		ListIterator<Command> commandsIterator=(ListIterator<Command>) commands.getCommands().iterator();
		while (commandsIterator.hasNext()) {
			Command curr=commandsIterator.next();
			if((curr.getName()+" "+curr.getOptions()).length()>=maxNumCharCommand) {
				maxNumCharCommand=(curr.getName()+" "+curr.getOptions()).length();
			}
			if(curr.getDescription().length()>=maxNumCharDescription) {
				maxNumCharDescription=curr.getDescription().length();
			}
		}
		System.out.format("%"+maxNumCharCommand+"s\t%"+maxNumCharDescription+"s","COMMAND","DESCRIPTION");
		System.out.println();
		System.out.println();
		commandsIterator=(ListIterator<Command>) commands.getCommands().iterator();
		while (commandsIterator.hasNext()) {
			Command curr=commandsIterator.next();
			System.out.format("%"+maxNumCharCommand+"s\t%"+maxNumCharDescription+"s",curr.getName()+" "+curr.getOptions(),curr.getDescription());
			System.out.println();
	    }
	  }


	public static void getChannels(String workspace) {
		try {
			Workspace slackWorkspace=new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedHashMap<String, Channel> workspaceChannels=slackWorkspace.getAllChannels();
			Collection<Channel> c = workspaceChannels.values();
			Iterator<Channel> channelsIterator = c.iterator(); 
			while(channelsIterator.hasNext()) {
				System.out.println(channelsIterator.next().getName());
			}
		} catch (IOException e) {
			if(e instanceof FileNotFoundException||e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace)+" not found");
			}else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException|NotValidWorkspaceException|NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void getMembers(String workspace) {
		try {
			Workspace slackWorkspace=new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedHashMap<String, Member> workspaceMembers=slackWorkspace.getAllMembers();
			Collection<Member> c = workspaceMembers.values();
			Iterator<Member> membersIterator = c.iterator();
			while(membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getName());
			}
		} catch (IOException e) {
			if(e instanceof FileNotFoundException||e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace)+" not found");
			}else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException|NotValidWorkspaceException|NotZipFileException e) {
			System.out.println(e.getMessage());

		}
	}
	
	public static void getMembersOfChannel(String workspace, String channel) {
		try {
			Workspace slackWorkspace=new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Member> channelMembers=slackWorkspace.getMembersOfChannel(channel);
			ListIterator<Member> membersIterator=(ListIterator<Member>) channelMembers.iterator();
			while(membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getName());
			}
		} catch (IOException e) {
			if(e instanceof FileNotFoundException||e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace)+" not found");
			}else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException|NotValidWorkspaceException|NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void getMembersForChannels(String workspace) {
		try {
			Workspace slackWorkspace=new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedHashMap<String, Channel> workspaceChannels=slackWorkspace.getAllChannels();
			Collection<Channel> c = workspaceChannels.values();
			Iterator<Channel> channelsIterator = c.iterator(); 
			while(channelsIterator.hasNext()) {
				Channel curr=channelsIterator.next();
				LinkedList<Member> channelMembers=slackWorkspace.getMembersOfChannel(curr.getName());
				ListIterator<Member> membersIterator=(ListIterator<Member>) channelMembers.iterator();
				System.out.println("Members of \""+curr.getName()+"\":");
				while(membersIterator.hasNext()) {
					System.out.println("\t"+membersIterator.next().getName());
				}
				if(channelsIterator.hasNext()) {
					System.out.println();
				}
			}
		} catch (IOException e) {
			if(e instanceof FileNotFoundException||e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace)+" not found");
			}else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException|NotValidWorkspaceException|NotZipFileException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void Gestione(String[] args) {
		switch(args.length) {
			case 0 : 
				CommandManager.help();
				break;
			case 1:
				if(args[0].equals("help"))
					CommandManager.help();
				else 
					System.out.println("'" + args[0] + "'" + " is not a valid command, see 'help'.");
				break;
			case 2 :
				System.out.println("'" + args[0]+ " " + args[1] + "'" + " is not a valid command, see 'help'.");
				break;
			case 3 : 
				if(args[0].equals("members")&& args[1].equals("-f"))
					CommandManager.getMembers(args[2]);
				else if(args[0].equals("channels") && args[1].equals("-f"))
					CommandManager.getChannels(args[2]);
				else
					System.out.println("'" + args[0] + " " + args[1] + "'" + " is not a valid command, see 'help'.");
				break;
			case 4:
				if(args[0].equals("members")&&args[1].equals("-ch")&&args[2].equals("-f"))
					CommandManager.getMembersForChannels(args[3]);
				else
					System.out.println("'" + args[0] + " " + args[1] + "'" + " is not a valid command, see 'help'.");
				break;
			case 5: 
				if(args[0].equals("members")&&args[1].equals("-ch")&&args[3].equals("-f"))
					CommandManager.getMembersOfChannel(args[4], args[2]);
				else
					System.out.println("'" + args[0] + " " + args[1] + " " + args[2] + "'" + " is not a valid command, see 'help'.");
				break;
			default:
				System.out.println("Command not found, see 'help'.");
				break;
		}
	}
}
