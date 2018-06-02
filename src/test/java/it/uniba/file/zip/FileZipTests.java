package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.uniba.file.PathManager;

@SuppressWarnings("PMD.TooManyStaticImports")
public class FileZipTests {
	static FileZip z;

	@BeforeAll
	static void setUpAll() throws Exception {
		String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		z = new FileZip(file);
	}

	@AfterAll
	static void tearDownAll() throws Exception {
		z.close();
	}

	@Test
	@DisplayName("Test FileZip() di FileZip")
	void FileZipTest() throws Exception {
		final String failMsg = "FileZip() is failed";
		String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		assertNotNull(new FileZip(file), failMsg);
	}

	@Test
	@DisplayName("Test getFileContent() di FileZip")
	void getFileContentTest() throws Exception {
		final String failMsg = "getFileContent() is failed";
		String file = "users.json";
		assertAll("Check content of a file in zip with lambdas", () -> {
		    assertNotNull(z.getFileContent(file), failMsg);
			assertThrows(FileNotInZipException.class, () -> {
			    z.getFileContent("wrongchannel.json");
			});
		});
	}

	@Test
	@DisplayName("Test contains() di FileZip")
	void containsTest() {
		final String failMsg = "contains() is failed";
		String file = "users.json";
		assertAll("Check if zip contains a file with lambdas", () -> {
			assertNotNull(z.contains(file), failMsg);
			assertTrue(z.contains(file), failMsg);
			assertFalse(z.contains("wrongchannel.json"), failMsg);
		});
	}

	@Test
	@DisplayName("Test close() di FileZip")
	void closeTest() throws Exception {

	}

	@Test
	@DisplayName("Test getZipFile() di FileZip")
	void getZipFileTest() {
		final String failMsg = "getZipFile() is failed";
		assertNotNull(z.getZipFile(), failMsg);
	}

}
