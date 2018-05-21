package it.uniba.cli;

/**
 * Classe che modella un singolo comando.
 */
public final class Command {
	/**
	 * Nome del comando corrente.
	 */

	private String name;
	/**
	 * Opzioni del comando corrente.
	 */
	private String options;

	/**
	 * Descrizione del comando corrente.
	 */
	private String description;

	/**
	 * Metodo costruttore della classe Command permette di creare oggetti istanze
	 * della classe Command.
	 * 
	 * @param commandName
	 *            Sring che indica il nome del comando corrente.
	 * @param commandOptions
	 *            String che indica le possibili opzioni associate al comando
	 *            corrente.
	 * @param commandDescription
	 *            String che indica la descrizione associata al comando corrente.
	 */
	Command(final String commandName, final String commandOptions, final String commandDescription) {
		this.name = commandName;
		this.options = commandOptions;
		this.description = commandDescription;
	}

	/**
	 * Restituisce il nome del comando corrente.
	 * 
	 * @return String che rappresenta il nome del comando corrente.
	 */
	String getName() {
		return this.name;
	}

	/**
	 * Restituisce le opzioni associate al comando corrente.
	 * 
	 * @return String che rappresenta le opzioni associate al comando corrente.
	 */
	String getOptions() {
		return this.options;
	}

	/**
	 * Restituisce la descrizione associata al comando corrente.
	 * 
	 * @return String che rappresenta la descrizione associata al comando corrente.
	 */
	String getDescription() {
		return this.description;
	}
}
