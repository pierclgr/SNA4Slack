package it.uniba.cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.ListIterator;

import it.uniba.entity.Member;
import it.uniba.entity.Workspace;
import it.uniba.file.PathManager;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;


public class CommandManager {

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
