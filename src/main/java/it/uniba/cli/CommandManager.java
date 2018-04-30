package it.uniba.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.ListIterator;

import it.uniba.entity.Channel;
import it.uniba.entity.Member;
import it.uniba.entity.Workspace;
import it.uniba.file.PathManager;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;

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
			LinkedList<Channel> workspaceChannels=slackWorkspace.getAllChannels();
			ListIterator<Channel> channelsIterator=(ListIterator<Channel>) workspaceChannels.iterator();
			while(channelsIterator.hasNext()) {
				System.out.println(channelsIterator.next().getName());
			}
		} catch (IOException e) {
			if(e instanceof FileNotFoundException||e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace)+" not found");
			}else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException|NotValidWorkspaceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void getMembers(String workspace) {
		try {
			Workspace slackWorkspace=new Workspace(PathManager.getAbsolutePath(workspace));
			LinkedList<Member> workspaceMembers=slackWorkspace.getAllMembers();
			ListIterator<Member> membersIterator=(ListIterator<Member>) workspaceMembers.iterator();
			while(membersIterator.hasNext()) {
				System.out.println(membersIterator.next().getRealName());
			}
		} catch (IOException e) {
			if(e instanceof FileNotFoundException||e instanceof NoSuchFileException) {
				System.out.println(PathManager.getAbsolutePath(workspace)+" not found");
			}else {
				e.printStackTrace();
			}
		} catch (FileNotInZipException|NotValidWorkspaceException e) {
			System.out.println(e.getMessage());

		}
	}
}
