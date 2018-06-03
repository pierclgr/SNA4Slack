package it.uniba.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class CommandsTests {
	static Commands commandList;

	@BeforeAll
	static void setUpAll() {
		commandList = new Commands();
	}

	@Test
	@DisplayName("Test getCommands() di Commands")
	void getCommandsTest() {
		final String failMsg = "Test getCommands() is failed";
		assertNotNull(commandList.getCommands(), failMsg);
	}
}