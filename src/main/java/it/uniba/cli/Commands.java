package it.uniba.cli;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Classe che modella l'insieme di comandi possibili.
 */
public final class Commands {
	/**
	 * Lista di comandi possibili.
	 */
	private List<Command> commands;

	/**
	 * Metodo costruttore della classe Commands, permette di creare oggetti istanze
	 * della classe Commands.
	 */
	public Commands() {
		this.commands = new LinkedList<Command>();
		commands.add(new Command("members", "-f \"fileName\"", "Get all members from \"fileName\" zip file"));
		commands.add(new Command("channels", "-f \"fileName\"", "Get all channels from \"fileName\" zip file"));
		commands.add(new Command("members", "-ch -f \"fileName\"",
				"Get all members for all channels from \"fileName\" zip file"));
		commands.add(new Command("members", "-ch \"channelName\" -f \"fileName\"",
				"Get all members of \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-f \"fileName\"", "Get all mentions from \"fileName\" zip file"));
		commands.add(
				new Command("mentions", "-w -f \"fileName\"", "Get all mentions weighed from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-ch \"channelName\" -f \"fileName\"",
				"Get all mentions in \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-w -ch \"channelName\" -f \"fileName\"",
				"Get all mentions weighed in \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-w -to \"memberName\" -f \"fileName\"",
				"Get all mentions weighed to \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-from \"memberName\" -f \"fileName\"",
				"Get all mentions from \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-w -from \"memberName\" -f \"fileName\"",
				"Get all mentions weighed from \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-from \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions from \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-w -from \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions weighed from \"memberName\" member in \"channelName\" channel from "
						+ "\"fileName\" zip file"));
		commands.add(new Command("mentions", "-to \"memberName\" -f \"fileName\"",
				"Get all mentions to \"memberName\" member from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-to \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions to \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
		commands.add(new Command("mentions", "-w -to \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions weighed to \"memberName\" member in \"channelName\" channel from "
						+ "\"fileName\" zip file"));
	}

	/**
	 * Restituisce la lista contenente i comandi possibili.
	 * 
	 * @return riferimento ad una LinkedList<Command> ovvero la lista di comandi
	 *         possibili.
	 */
	public LinkedList<Command> getCommands() {
		return (LinkedList<Command>) this.commands;
	}

	/**
	 * Permette di verificare la presenza di un comando all'interno della lista di
	 * comandi possibili.
	 * 
	 * @param c
	 *            istanza della clsse Command che rappresenta il comando di cui
	 *            verificare la presenza.
	 * @return boolen che vale true se il comando in input alla funzione è presente
	 *         nella lista di comandi possibili, false altrimenti.
	 */
	boolean contains(final Command c) {
		boolean found = false;
		ListIterator<Command> iterator = (ListIterator<Command>) commands.iterator();
		while (iterator.hasNext() && !found) {
			Command curr = iterator.next();
			if (curr.getName().equals(c.getName())) {
				found = true;
			}
		}
		return found;
	}
}
