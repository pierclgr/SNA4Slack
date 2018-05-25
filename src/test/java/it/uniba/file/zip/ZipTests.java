package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import it.uniba.file.PathManager;

@SuppressWarnings("PMD.TooManyStaticImports")
public class ZipTests {
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
	@DisplayName("Test Zip() di Zip")
	void ZipTest() throws Exception {
		final String failMsg = "Zip() is failed";
		String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		assertNotNull(new FileZip(file), failMsg);
	}

	@Test
	@DisplayName("Test getFileContent() di Zip")
	void getFileContentTest() throws Exception {
		final String failMsg = "getFileContent() is failed";
		String file = "users.json";
		assertNotNull(z.getFileContent(file), failMsg);
	}

	@Test
	@DisplayName("Test contains() di Zip")
	void containsTest() {
		final String failMsg = "contains() is failed";
		String file = "users.json";
		assertNotNull(z.contains(file), failMsg);
	}

	@Test
	@DisplayName("Test close() di Zip")
	void closeTest() throws Exception {

	}

	@Test
	@DisplayName("Test getZipFile() di Zip")
	void getZipFileTest() {
		final String failMsg = "getZipFile() is failed";
		assertNotNull(z.getZipFile(), failMsg);
	}

}
