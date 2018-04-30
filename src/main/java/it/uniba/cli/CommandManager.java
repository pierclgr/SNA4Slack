package it.uniba.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.ListIterator;

import it.uniba.entity.Channel;
import it.uniba.entity.Workspace;
import it.uniba.file.PathManager;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;

public class CommandManager {
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
}
