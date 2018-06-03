package it.uniba.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class CommandTests {

	Command commandTest = new Command("members", "-f res/ingsw1718May3.zip",
			"Get all members from \"fileName\" zip file");

	@Test
	@DisplayName("Test getName() di Command")
	void getNameTest() {
		final String failMsg = "Test getName() is failed";
		assertEquals(commandTest.getName(), commandTest.getName(), failMsg);
	}

	@Test
	@DisplayName("Test getOptions() di Command")
	void getOptionsTest() {
		final String failMsg = "Test getOptions() is failed";
		assertNotNull(commandTest.getOptions(), failMsg);
	}

	@Test
	@DisplayName("Test getDescription() di Command")
	void getDescriptionTest() {
		final String failMsg = "Test getDescription() is failed";
		assertNotNull(commandTest.getDescription(), failMsg);
	}
}
