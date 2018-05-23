package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class FileNotInZipExceptionTests {
	static FileNotInZipException e;

	@BeforeAll
	static void setUpAll() {
		String file = "Path";
		e = new FileNotInZipException(file);
	}

	@Test
	@DisplayName("Test FileNotInZipException() di FileNotInZipException")
	void FileNotInZipExceptionTest() {
		final String failMsg = "FileNotInZipException() is failed";
		String file = "Path";
		assertAll("Check if file is not in zip with lambdas", () -> {
			assertNotNull(new FileNotInZipException(file), failMsg);
			assertNotNull(new FileNotInZipException(null));
		});

	}

	@Test
	@DisplayName("Test getMessage() di FileNotInZipException")
	void getMessageTest() {
		final String failMsg = "getMessage() is failed";
		String file = "Path";
		assertEquals(e.getMessage(), file + " cannot be found in this zip archive", failMsg);
	}

}
