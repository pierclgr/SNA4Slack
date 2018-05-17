package it.uniba.cli;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Commands {
	private List<Command> commands;

	public Commands() {
		this.commands = new LinkedList<Command>();
		commands.add(new Command("members","-f \"fileName\"","Get all members from \"fileName\" zip file"));
		commands.add(new Command("channels","-f \"fileName\"","Get all channels from \"fileName\" zip file"));
		commands.add(new Command("members","-ch \"channelName\" -f \"fileName\"","Get all members of \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("members","-ch -f \"fileName\"","Get all members for all channels from \"fileName\" zip file"));
		commands.add(new Command("mentions","-from \"memberName\" -f \"fileName\"","Get all mentions from \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions","-w -from \"memberName\" -f \"fileName\"","Get all mentions weighed from \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions","-from \"memberName\" -ch \"channelName\" -f \"fileName\"","Get all mentions from \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions","-w -from \"memberName\" -ch \"channelName\" -f \"fileName\"","Get all mentions weighed from \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions","-to \"memberName\" -f \"fileName\"","Get all mentions to \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions","-to \"memberName\" -ch \"channelName\" -f \"fileName\"","Get all mentions to \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
    commands.add(new Command("mentions","-f \"fileName\"","Get all mentions from \"fileName\" zip file"));
    commands.add(new Command("mentions","-ch \"channelName\" -f \"fileName\"","Get all mentions in \"channelName\" channel from \"fileName\" zip file"));
	}
	
	public LinkedList<Command> getCommands(){
		return (LinkedList<Command>) this.commands;
	}

	boolean contains(Command c) {
		boolean found=false;
		ListIterator<Command> iterator=(ListIterator<Command>) commands.iterator();
		while(iterator.hasNext()&&!found) {
			Command curr=iterator.next();
			if(curr.getName().equals(c.getName())) {
				found=true;
			}
		}
		return found;
	}
}
