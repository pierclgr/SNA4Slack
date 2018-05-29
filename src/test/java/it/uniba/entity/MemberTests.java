package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class MemberTests {
	static Member m1, m2, m3, m4;

	@BeforeAll
	static void setUpAll() {
		String id = "U9QLDMNDV";
		String name = "luciano_bruno";
		String realName = "Luciano Bruno";
		String displayName = "Luciano Bruno";
		m1 = new Member(id, name, realName, displayName);
		m2 = new Member(id, name, realName, "");
		m3 = new Member(id, name, "", "");
		m4 = new Member(id, "", "", "");
	}

	@Test
	@DisplayName("Test Member() di Member")
	void MemberTest() {
		final String failMsg = "Member() is failed";
		String id = "U9QLDMNDV";
		String name = "luciano_bruno";
		String realName = "Luciano Bruno";
		String displayName = "Luciano Bruno";
		assertNotNull(new Member(id, name, realName, displayName), failMsg);
	}

	@Test
	@DisplayName("Test getId() di Member")
	void getIdTest() {
		final String failMsg = "getId() is failed";
		assertAll("Check member ID with lambdas", () -> {
			assertEquals("U9QLDMNDV", m1.getId(), failMsg);
			assertNotEquals("CA3EZFFGR", m1.getId(), failMsg);
		});
	}

	@Test
	@DisplayName("Test getName() di Member")
	void getNameTest() {	
		final String failMsg = "getName() is failed";
		assertAll("Check member displayName, realName, name or ID with lambdas", () -> {
			assertEquals("Luciano Bruno", m1.getName(), failMsg);
			assertEquals("Luciano Bruno", m2.getName(), failMsg);
			assertEquals("luciano_bruno", m3.getName(), failMsg);
			assertEquals("U9QLDMNDV", m4.getName(), failMsg);
		});
	}

	@Test
	@DisplayName("Test isUser() di Member")
	void isUserTest() {
		final String failMsg = "isUser() is failed";
		assertAll("Check if m1 is a user with lambdas", () -> {
			assertTrue(m1.getName().equals("Luciano Bruno"), failMsg);
			assertFalse(m1.getName().equals("Nunzia Andrulli"), failMsg);
		});
	}

	@Test
	@DisplayName("Test getUserName() di Member")
	void getUserNameTest() {
		final String failMsg = "getUserName() is failed";
		assertAll("Check member name with lambdas", () -> {
			assertEquals("luciano_bruno", m1.getUserName(), failMsg);
			assertNotEquals("nunziaandrulli", m1.getUserName(), failMsg);
		});
	}

	@Test
	@DisplayName("Test getChannels() di Member")
	void getChannelsTest() {
		final String failMsg = "getChannels() is failed";
		assertAll("Check channels in which a member is subscribed with lambdas", () -> {
			assertNotNull(m1.getChannels(), failMsg);
			assertNotNull(m1.getChannels(), failMsg);
		});
	}

}