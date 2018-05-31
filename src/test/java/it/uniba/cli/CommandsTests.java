package it.uniba.cli;

import it.uniba.cli.Commands;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class CommandsTests {
	static Commands w;

	@BeforeAll
	static void setUpAll() throws Exception {
		w = new Commands();
	}

	@Test
	@DisplayName("Test getCommands() di Commands")
	void getCommandsTest() {
		final String failMsg = "Test getCommands() is failed";
		assertNotNull(w.getCommands(), failMsg);
	}
}