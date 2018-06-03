package it.uniba.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class AppMainTests {

	@Test
	@DisplayName("Test main() di AppMain")
	void mainTest() {
		final String[] string = { "help" };
		final String failMsg = "main() is failed";
		AppMain.main(string);
		assertTrue(string.length > 0, failMsg);
	}
}