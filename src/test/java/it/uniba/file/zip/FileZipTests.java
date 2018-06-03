package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.zip.ZipException;

import org.junit.jupiter.api.*;

import it.uniba.file.PathManager;

@SuppressWarnings("PMD.TooManyStaticImports")
public class FileZipTests {
	static FileZip zipFile;

	@BeforeAll
	static void setUpAll() throws IOException {
		final String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		zipFile = new FileZip(file);
	}

	@AfterAll
	static void tearDownAll() throws IOException {
		zipFile.close();
	}

	@Test
	@DisplayName("Test FileZip() di FileZip")
	void fileZipTest() throws IOException {
		final String failMsg = "FileZip() is failed";
		final String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		assertNotNull(new FileZip(file), failMsg);
	}

	@Test
	@DisplayName("Test getFileContent() di FileZip")
	void getFileContentTest1() throws ZipException, IOException, FileNotInZipException {
		final String failMsg = "getFileContent() is failed";
		final String file = "users.json";
		assertNotNull(zipFile.getFileContent(file), failMsg);
	}

	@Test
	@DisplayName("Test getFileContent() di FileZip")
	void getFileContentTest2() {
		assertThrows(FileNotInZipException.class, () -> {
			zipFile.getFileContent("wrongchannel.json");
		});
	}

	@Test
	@DisplayName("Test contains() di FileZip")
	void containsTest1() {
		final String failMsg = "contains() is failed";
		final String file = "users.json";
		assertNotNull(zipFile.contains(file), failMsg);
	}

	void containsTest2() {
		final String failMsg = "contains() is failed";
		final String file = "users.json";
		assertTrue(zipFile.contains(file), failMsg);
	}

	void containsTest3() {
		final String failMsg = "contains() is failed";
		assertFalse(zipFile.contains("wrongchannel.json"), failMsg);
	}

	@Test
	@DisplayName("Test getZipFile() di FileZip")
	void getZipFileTest() {
		final String failMsg = "getZipFile() is failed";
		assertNotNull(zipFile.getZipFile(), failMsg);
	}

}
