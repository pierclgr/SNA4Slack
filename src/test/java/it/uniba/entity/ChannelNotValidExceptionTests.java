package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChannelNotValidExceptionTests {
	static ChannelNotValidException e;
	
	@BeforeAll
    static void setUpAll(){
		String channel = "ritchie";
		e = new ChannelNotValidException(channel);
    }
	
	@Test
	@DisplayName("Test ChannelNotValidException() di ChannelNotValidException")
	void ChannelNotValidExceptionTest() {
		final String failMsg = "ChannelNotValidException() is failed";
		String channel = "ritchie";
		assertNotNull(new ChannelNotValidException(channel),failMsg);
	}

	
	@Test
	@DisplayName("Test getMessage() di ChannelNotValidException")
	void getMessageTest() {
		final String failMsg = "ChannelNotValidException() is failed";
		assertNotEquals(e.getMessage(),"wrongchannel is not a valid channel",failMsg);
	}

}
