package it.uniba.cli;

import java.util.ListIterator;

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
}
