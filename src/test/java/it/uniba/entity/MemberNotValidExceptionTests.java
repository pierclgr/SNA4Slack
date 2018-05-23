package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class MemberNotValidExceptionTests {
	static MemberNotValidException e;

	@BeforeAll
	static void setUpAll() {
		String member = "U9W4FCFEH";
		e = new MemberNotValidException(member);
	}

	@Test
	@DisplayName("Test MemberNotValidException() di MemberNotValidException")
	void MemberNotValidExceptionTest() {
		final String failMsg = "MemberNotValidException() is failed";
		String member = "U9W4FCFEH";
		assertNotNull(new ChannelNotValidException(member), failMsg);
	}

	@Test
	@DisplayName("Test getMessage() di MemberNotValidException")
	void getMessageTest() {
		final String failMsg = "MemberNotValidException() is failed";
		assertNotEquals(e.getMessage(), "wrongmember is not a valid member", failMsg);
	}

}
