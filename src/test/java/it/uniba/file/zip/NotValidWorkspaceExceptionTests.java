package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.TooManyStaticImports")
public class NotValidWorkspaceExceptionTests {
	static NotValidWorkspaceException e;

	@BeforeAll
	static void setUpAll() {
		String file = "Path";
		e = new NotValidWorkspaceException(file);
	}

	@Test
	@DisplayName("Test NotValidWorkspaceException() di NotValidWorkspaceException")
	void NotValidWorkspaceExceptionTest() {
		final String failMsg = "NotValidWorkspaceException() is failed";
		String file = "Path";
		assertAll("Check if workspace is valid with lambdas", () -> {
			assertNotNull(new NotValidWorkspaceException(file), failMsg);
			assertNotNull(new NotValidWorkspaceException(null));
		});

	}

	@Test
	@DisplayName("Test getMessage() di NotValidWorkspaceException")
	void getMessageTest() {
		final String failMsg = "getMessage() is failed";
		String file = "Path";
		assertEquals(e.getMessage(), file + " is not a valid Slack workspace", failMsg);
	}

}
