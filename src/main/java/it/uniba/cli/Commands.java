package it.uniba.cli;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Classe che modella l'insieme di comandi possibili.
 */
public final class Commands {
	/**
	 * Attributo di classe che rappresenta la stringa del comando mentions.
	 */
	private static final String MENTIONCOMMAND = "mentions";
	/**
	 * Lista di comandi possibili.
	 */
	private final List<Command> allCommands;

	/**
	 * Metodo costruttore della classe Commands, permette di creare oggetti istanze
	 * della classe Commands.
	 */
	public Commands() {
		this.allCommands = new LinkedList<Command>();
		allCommands.add(new Command("members", "-f \"fileName\"", "Get all members from \"fileName\" zip file"));
		allCommands.add(new Command("channels", "-f \"fileName\"", "Get all channels from \"fileName\" zip file"));
		allCommands.add(new Command("members", "-ch -f \"fileName\"",
				"Get all members for all channels from \"fileName\" zip file"));
		allCommands.add(new Command("members", "-ch \"channelName\" -f \"fileName\"",
				"Get all members of \"channelName\" channel from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-f \"fileName\"", "Get all mentions from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-w -f \"fileName\"",
				"Get all mentions weighed" + " from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-ch \"channelName\" -f \"fileName\"",
				"Get all mentions in \"channelName\" channel from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-w -ch \"channelName\" -f \"fileName\"",
				"Get all mentions weighed in \"channelName\" channel from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-w -to \"memberName\" -f \"fileName\"",
				"Get all mentions weighed to \"memberName\" member from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-from \"memberName\" -f \"fileName\"",
				"Get all mentions from \"memberName\" member from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-w -from \"memberName\" -f \"fileName\"",
				"Get all mentions weighed from \"memberName\" member from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-from \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions from \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-w -from \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions weighed from \"memberName\" member in \"channelName\" channel from "
						+ "\"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-to \"memberName\" -f \"fileName\"",
				"Get all mentions to \"memberName\" member from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-to \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions to \"memberName\" member in \"channelName\" channel from \"fileName\" zip file"));
		allCommands.add(new Command(MENTIONCOMMAND, "-w -to \"memberName\" -ch \"channelName\" -f \"fileName\"",
				"Get all mentions weighed to \"memberName\" member in \"channelName\" channel from "
						+ "\"fileName\" zip file"));
	}

	/**
	 * Restituisce la lista contenente i comandi possibili.
	 * 
	 * @return riferimento ad una LinkedList<Command> ovvero la lista di comandi
	 *         possibili.
	 */
	public List<Command> getCommands() {
		return (LinkedList<Command>) this.allCommands;
	}

	/**
	 * Permette di verificare la presenza di un comando all'interno della lista di
	 * comandi possibili.
	 * 
	 * @param command
	 *            istanza della clsse Command che rappresenta il comando di cui
	 *            verificare la presenza.
	 * @return boolen che vale true se il comando in input alla funzione è presente
	 *         nella lista di comandi possibili, false altrimenti.
	 */
	boolean contains(final Command command) {
		boolean found = false;
		final ListIterator<Command> iterator = (ListIterator<Command>) allCommands.iterator();
		while (iterator.hasNext() && !found) {
			final Command curr = iterator.next();
			if (curr.getName().equals(command.getName())) {
				found = true;
			}
		}
		return found;
	}
}
