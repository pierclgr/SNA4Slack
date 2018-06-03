package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.TooManyStaticImports")
public class NotValidWorkspaceExceptionTests {
	static NotValidWorkspaceException exception;

	@BeforeAll
	static void setUpAll() {
		final String file = "Path";
		exception = new NotValidWorkspaceException(file);
	}

	@Test
	@DisplayName("Test NotValidWorkspaceException() di NotValidWorkspaceException")
	void notValidWorkspaceExceptionTest1() {
		final String failMsg = "NotValidWorkspaceException() is failed";
		final String file = "Path";
		assertNotNull(new NotValidWorkspaceException(file), failMsg);
	}

	@Test
	@DisplayName("Test NotValidWorkspaceException() di NotValidWorkspaceException")
	void notValidWorkspaceExceptionTest2() {
		assertNotNull(new NotValidWorkspaceException(null));
	}

	@Test
	@DisplayName("Test getMessage() di NotValidWorkspaceException")
	void getMessageTest() {
		final String failMsg = "getMessage() is failed";
		final String file = "Path";
		assertEquals(exception.getMessage(), file + " is not a valid Slack workspace", failMsg);
	}

}
