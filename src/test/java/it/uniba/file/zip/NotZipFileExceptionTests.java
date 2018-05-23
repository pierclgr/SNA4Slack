package it.uniba.file.zip;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class NotZipFileExceptionTests {
    static NotZipFileException e;
	
	@BeforeAll
    static void setUpAll(){
		String file = "Path";
		e = new NotZipFileException(file);
    }
	
	@Test
	@DisplayName("Test NotZipFileException() di NotZipFileException")
	void NotZipFileExceptionTest() {
		final String failMsg = "NotZipFileException() is failed";
		String file = "Path";
		assertAll("Check if file is not a zip file with lambdas", () -> {
    		assertNotNull(new NotZipFileException(file),failMsg);
    		assertNotNull(new NotZipFileException(null));	
         });   	

	}

	@Test
	@DisplayName("Test getMessage() di NotZipFileException")
	void getMessageTest() {
		final String failMsg = "getMessage() is failed";
		String file = "Path";
		assertEquals(e.getMessage(),file + " is not a zip file",failMsg);
	}

}
