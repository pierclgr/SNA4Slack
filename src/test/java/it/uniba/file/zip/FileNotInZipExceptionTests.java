package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class FileNotInZipExceptionTests {
	static FileNotInZipException exception;

	@BeforeAll
	static void setUpAll() {
		final String file = "Path";
		exception = new FileNotInZipException(file);
	}

	@Test
	@DisplayName("Test FileNotInZipException() di FileNotInZipException")
	void fileNotInZipExceptionTest1() {
		final String failMsg = "FileNotInZipException() is failed";
		final String file = "Path";
		assertNotNull(new FileNotInZipException(file), failMsg);
	}

	@Test
	@DisplayName("Test FileNotInZipException() di FileNotInZipException")
	void fileNotInZipExceptionTest2() {
		assertNotNull(new FileNotInZipException(null));
	}

	@Test
	@DisplayName("Test getMessage() di FileNotInZipException")
	void getMessageTest() {
		final String failMsg = "getMessage() is failed";
		final String file = "Path";
		assertEquals(exception.getMessage(), file + " cannot be found in this zip archive", failMsg);
	}

}
