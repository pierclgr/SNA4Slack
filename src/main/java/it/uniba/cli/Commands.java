package it.uniba.cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import it.uniba.file.PathManager;

public class Commands {
	private List<Command> commands;
	private int numberOfAllCommands;

	public Commands() throws IOException{
		this.commands = new LinkedList<Command>();
		numberOfAllCommands=calculateNumberOfCommands();
		for (int j = 0; j < numberOfAllCommands; j++) {
			Command command = new Command(readCommand(j), readOptions(j), readDescription(j));
			addCommand(command);
		}
	}
	
	int calculateNumberOfCommands() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(PathManager.getAbsolutePath("res/files/commands.txt")));
		String line = reader.readLine();
		int i = 0;
		while (line != null) {
			line = reader.readLine();
			i++;
		}
		reader.close();
		return i;
	}
	
	public int getNumberOfCommands() {
		return commands.size();
	}

	public Command getCommand(int i) {
		return this.commands.get(i);
	}

	public void addCommand(Command c) {
		this.commands.add(c);
	}

	public String readCommand(int i) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(PathManager.getAbsolutePath("res/files/commands.txt")));
		String line = reader.readLine();
		int j = 0;
		while (line != null && j<i) {
			line = reader.readLine();
			j++;
		}
		reader.close();
		return line;
	}

	boolean contains(Command c) {
		boolean found  = false;
		for(int i=0; i<commands.size()&&!found; i++) {
			if(this.getCommand(i).getName().equals(c.getName()))
				found = true;
		}
		return found;
	}

	String readOptions(int i) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(PathManager.getAbsolutePath("res/files/options.txt")));
		String line = reader.readLine();
		int j=0;
		while (line != null && j<i) {
			line = reader.readLine();
			j++;
		}
		reader.close();
		return line;
	}
	
	String readDescription(int i) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(PathManager.getAbsolutePath("res/files/descriptions.txt")));
		String line = reader.readLine();
		int j=0;
		while (line != null && j<i) {
			line = reader.readLine();
			j++;
		}
		reader.close();
		return line;
	}
}
