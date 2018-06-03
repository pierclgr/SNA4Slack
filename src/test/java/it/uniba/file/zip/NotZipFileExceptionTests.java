package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class NotZipFileExceptionTests {
	static NotZipFileException exception;

	@BeforeAll
	static void setUpAll() {
		final String file = "Path";
		exception = new NotZipFileException(file);
	}

	@Test
	@DisplayName("Test NotZipFileException() di NotZipFileException")
	void notZipFileExceptionTest1() {
		final String failMsg = "NotZipFileException() is failed";
		final String file = "Path";
		assertNotNull(new NotZipFileException(file), failMsg);
	}

	@Test
	@DisplayName("Test NotZipFileException() di NotZipFileException")
	void notZipFileExceptionTest2() {
		assertNotNull(new NotZipFileException(null));
	}

	@Test
	@DisplayName("Test getMessage() di NotZipFileException")
	void getMessageTest() {
		final String failMsg = "getMessage() is failed";
		final String file = "Path";
		assertEquals(exception.getMessage(), file + " is not a zip file", failMsg);
	}

}
