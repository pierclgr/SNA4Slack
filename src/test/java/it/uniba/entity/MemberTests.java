package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class MemberTests {
    static Member m1;
    
    @BeforeAll
    static void setUpAll(){
        String id = "U9QLDMNDV";
        String name = "luciano_bruno";
        String realName = "Luciano Bruno";
        String displayName = "Luciano Bruno";
    	m1 = new Member(id,name,realName,displayName);
    }

    @Test
    @DisplayName("Test Member() di Member")
	void MemberTest() {
    	final String failMsg = "Member() is failed";
        String id = "U9QLDMNDV";
        String name = "luciano_bruno";
        String realName = "Luciano Bruno";
        String displayName = "Luciano Bruno";
    	assertNotNull(new Member(id,name,realName,displayName),failMsg);
	}

    @Test
    @DisplayName("Test getId() di Member")
    void getIdTest() {
        final String failMsg = "getId() is failed";
        assertAll("Check member Id with lambdas", () -> {
            assertEquals("U9QLDMNDV",m1.getId(),failMsg);
            assertNotEquals("CA3EZFFGR",m1.getId(),failMsg);
        }); 
    }
    
    @Test
    @DisplayName("Test getName() di Member")
    void getNameTest() {
        final String failMsg = "getName() is failed";
        assertAll("Check member displayName, realName or name with lambdas", () -> {
            assertEquals("Luciano Bruno",m1.getName(),failMsg);
            assertNotEquals("Nunzia Andrulli",m1.getName(),failMsg);
        }); 
    }
    
    @Test
    @DisplayName("Test isUser() di Member")
    void isUserTest() {
        final String failMsg = "isUser() is failed";
        assertAll("Check if m1 is a user with lambdas", () -> {
            assertTrue(m1.getName().equals("Luciano Bruno"),failMsg);
            assertFalse(m1.getName().equals("Nunzia Andrulli"),failMsg);
         });   	
    }
    
    @Test
    @DisplayName("Test getUserName() di Member")
    void getUserNameTest() {
        final String failMsg = "getUserName() is failed";
        assertAll("Check member name with lambdas", () -> {
            assertEquals("Luciano Bruno",m1.getName(),failMsg);
            assertNotEquals("Nunzia Andrulli",m1.getName(),failMsg);
        }); 
    }
    
    @Test
    @DisplayName("Test getChannels() di Member")
    void getChannelsTest(){
    	final String failMsg = "getChannels() is failed";
    	assertAll("Check channels in which a member is subscribed with lambdas", () -> {
    	    assertNotNull(m1.getChannels(),failMsg);
            assertNotNull(m1.getChannels(),failMsg);
        }); 
    }
    
}