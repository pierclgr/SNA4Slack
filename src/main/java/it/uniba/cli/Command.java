package it.uniba.cli;

public class Command {
	String name;
	String options;
	String description;
	
	Command(String name, String options, String description){
		this.name = name;
		this.options = options;
		this.description = description;
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
