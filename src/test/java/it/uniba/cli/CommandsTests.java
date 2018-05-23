package it.uniba.cli;

import it.uniba.cli.Commands;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class CommandsTests {
	String s = "member";
	String a = "member";
	static Commands w;

	@BeforeAll
	static void setUpAll() throws Exception {
		w = new Commands();
	}

	@Test
	@DisplayName("Test contains")
	void containsTest() {
		final String failMsg = "Test contains is failed";
		assertEquals(true, s.contains(a), failMsg);
	}

	@Test
	@DisplayName("Test getCommands")
	void getCommandsTest() {

		final String failMsg = "Test getCommands is failed";
		assertNotNull(w.getCommands(), failMsg);
	}
}