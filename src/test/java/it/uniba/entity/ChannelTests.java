package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class ChannelTests {
    static Channel c1;
    static Channel c2;
    
    @BeforeAll
    static void setUpAll(){
        String id1 = "C9NQ5BM5Z";
        String name1 = "allen";
        String id2 = "C9P0BD724";
        String name2 = "ritchie";
        c1 = new Channel(id1,name1);
        c2 = new Channel(id2,name2); 
    }

    @Test
    @DisplayName("Test Channel() di Channel")
    void ChannelTest(){
        final String failMsg = "Channel() is failed";  
        String id1 = "C9NQ5BM5Z";
        String name1 = "allen";
        String id2 = "C9P0BD724";
        String name2 = "ritchie";
        assertAll("Check channel constructor with lambdas", () -> {
            assertNotNull(new Channel(id1,name1),failMsg);
            assertNotNull(new Channel(id2,name2),failMsg);
        });      
    }

    @Test
    @DisplayName("Test getId() di Channel")
    void getIdTest(){
        final String failMsg = "getId() is failed";
        assertAll("Check channel Id with lambdas", () -> {
            assertEquals("C9NQ5BM5Z",c1.getId(),failMsg);
            assertNotEquals("CA3EZFFGR",c1.getId(),failMsg);
         });  
    }

    @Test
    @DisplayName("Test getName() di Channel")
    void getNameTest(){
        final String failMsg = "getName() is failed";
        assertAll("Check channel name with lambdas", () -> {
            assertEquals("allen",c1.getName(),failMsg);
            assertNotEquals("ritchie",c1.getName(),failMsg);
         });  
    }

    @Test
    @DisplayName("Test getMembers() di Channel")
    void getMembersTest(){
    	final String failMsg = "getMembers() is failed";
    	assertAll("Check members of channel with lambdas", () -> {
    	    assertNotNull(c1.getMembers(),failMsg);
            assertNotNull(c2.getMembers(),failMsg);
        }); 
    }

    @Test
    @DisplayName("Test getMentions() di Channel")
    void getMentionsTest(){
    	final String failMsg = "getMentions() is failed";
    	assertAll("Check mentions in channel with lambdas", () -> {
    	    assertNotNull(c1.getMentions(),failMsg);
            assertNotNull(c2.getMentions(),failMsg);
        }); 
    }

}
