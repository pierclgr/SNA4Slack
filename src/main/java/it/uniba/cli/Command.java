package it.uniba.cli;

public final class Command {
	private String name;
	private String options;
	private String description;

	Command(final String commandName, final String commandOptions, final String commandDescription) {
		this.name = commandName;
		this.options = commandOptions;
		this.description = commandDescription;
	}

	String getName() {
		return this.name;
	}

	String getOptions() {
		return this.options;
	}

	String getDescription() {
		return this.description;
	}
}
