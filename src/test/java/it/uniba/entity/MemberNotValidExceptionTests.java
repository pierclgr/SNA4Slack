package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class MemberNotValidExceptionTests {
	static MemberNotValidException membNotValidEx;

	@BeforeAll
	static void setUpAll() {
		final String member = "U9W4FCFEH";
		membNotValidEx = new MemberNotValidException(member);
	}

	@Test
	@DisplayName("Test MemberNotValidException() di MemberNotValidException")
	void memberNotValidExceptionTest() {
		final String failMsg = "MemberNotValidException() is failed";
		final String member = "U9W4FCFEH";
		assertNotNull(new ChannelNotValidException(member), failMsg);
	}

	@Test
	@DisplayName("Test getMessage() di MemberNotValidException")
	void getMessageTest() {
		final String failMsg = "MemberNotValidException() is failed";
		assertNotEquals(membNotValidEx.getMessage(), "wrongmember is not a valid member", failMsg);
	}

}
