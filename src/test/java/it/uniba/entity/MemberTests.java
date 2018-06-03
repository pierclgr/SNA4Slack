package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class MemberTests {
	static final String DEFIDUSR = "U9QLDMNDV";
	static final String DEFNAMEUSR = "luciano_bruno";
	static final String DEFRLNAMEUSR = "Luciano Bruno";
	static Member member1, member2, member3, member4;

	@BeforeAll
	static void setUpAll() {
		final String idUser = DEFIDUSR;
		final String name = DEFNAMEUSR;
		final String realName = DEFRLNAMEUSR;
		final String displayName = DEFRLNAMEUSR;
		member1 = new Member(idUser, name, realName, displayName);
		member2 = new Member(idUser, name, realName, "");
		member3 = new Member(idUser, name, "", "");
		member4 = new Member(idUser, "", "", "");
	}

	@Test
	@DisplayName("Test Member() di Member")
	void memberTest() {
		final String failMsg = "Member() is failed";
		final String idUser = DEFIDUSR;
		final String name = DEFNAMEUSR;
		final String realName = DEFRLNAMEUSR;
		final String displayName = DEFRLNAMEUSR;
		assertNotNull(new Member(idUser, name, realName, displayName), failMsg);
	}

	@Test
	@DisplayName("Test getId() di Member")
	void getIdTest1() {
		final String failMsg = "getId() is failed";
		assertEquals(DEFIDUSR, member1.getId(), failMsg);
	}

	@Test
	@DisplayName("Test getId() di Member")
	void getIdTest2() {
		final String failMsg = "getId() is failed";
		assertNotEquals("CA3EZFFGR", member1.getId(), failMsg);
	}

	@Test
	@DisplayName("Test getName() di Member1")
	void getNameTest1() {
		final String failMsg = "getName() for member 1 is failed";
		assertEquals(DEFRLNAMEUSR, member1.getName(), failMsg);
	}

	@Test
	@DisplayName("Test getName() di Member2")
	void getNameTest2() {
		final String failMsg = "getName() for member 2 is failed";
		assertEquals(DEFRLNAMEUSR, member2.getName(), failMsg);
	}

	@Test
	@DisplayName("Test getName() di Member3")
	void getNameTest3() {
		final String failMsg = "getName() for member 3 is failed";
		assertEquals(DEFNAMEUSR, member3.getName(), failMsg);
	}

	@Test
	@DisplayName("Test getName() di Member4")
	void getNameTest4() {
		final String failMsg = "getName() for member 4 is failed";
		assertEquals(DEFIDUSR, member4.getName(), failMsg);
	}

	@Test
	@DisplayName("Test isUser() di Member")
	void isUserTest1() {
		final String failMsg = "isUser() is failed";
		final String member1Name = new String(member1.getName());
		assertTrue(member1Name.equals(DEFRLNAMEUSR), failMsg);
	}

	@Test
	@DisplayName("Test isUser() di Member")
	void isUserTest2() {
		final String failMsg = "isUser() is failed";
		final String member1Name = new String(member1.getName());
		final String nunziaName = "Nunzia Andrulli";
		assertFalse(member1Name.equals(nunziaName), failMsg);
	}

	@Test
	@DisplayName("Test getUserName() di Member")
	void getUserNameTest1() {
		final String failMsg = "getUserName() is failed";
		assertEquals(DEFNAMEUSR, member1.getUserName(), failMsg);
	}

	@Test
	@DisplayName("Test getUserName() di Member")
	void getUserNameTest2() {
		final String failMsg = "getUserName() is failed";
		assertNotEquals("nunziaandrulli", member1.getUserName(), failMsg);
	}

	@Test
	@DisplayName("Test getChannels() di Member")
	void getChannelsTest1() {
		final String failMsg = "getChannels() is failed";
		assertNotNull(member1.getChannels(), failMsg);
	}

	@Test
	@DisplayName("Test getChannels() di Member")
	void getChannelsTest2() {
		final String failMsg = "getChannels() is failed";
		assertNotNull(member1.getChannels(), failMsg);
	}
}