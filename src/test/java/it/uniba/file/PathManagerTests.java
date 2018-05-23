package it.uniba.file;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PathManagerTests {

	@Test
	@DisplayName("Test getAbsoultePath() di Mention")
	void getAbsolutePathTest() {
		final String failMsg = "getAbsolutePath() is failed";
		assertNotNull(PathManager.getAbsolutePath("Path"),failMsg);
	}

}

