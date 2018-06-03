package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChannelNotValidExceptionTests {
	static ChannelNotValidException chNotValidEx;

	@BeforeAll
	static void setUpAll() {
		final String channel = "ritchie";
		chNotValidEx = new ChannelNotValidException(channel);
	}

	@Test
	@DisplayName("Test ChannelNotValidException() di ChannelNotValidException")
	void channelNotValidExceptionTest() {
		final String failMsg = "ChannelNotValidException() is failed";
		final String channel = "ritchie";
		assertNotNull(new ChannelNotValidException(channel), failMsg);
	}

	@Test
	@DisplayName("Test getMessage() di ChannelNotValidException")
	void getMessageTest() {
		final String failMsg = "ChannelNotValidException() is failed";
		assertNotEquals(chNotValidEx.getMessage(), "wrongchannel is not a valid channel", failMsg);
	}

}
