package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class MentionTests {
	static Mention ment, ment2, ment3;
	static final String DEFNAMEUSR = "Luciano Bruno";

	@BeforeAll
	static void setUpAll() {
		final String id1 = "U9QLDMNDV";
		final String name1 = "luciano_bruno";
		final String realName1 = DEFNAMEUSR;
		final String displayName1 = DEFNAMEUSR;
		final Member from = new Member(id1, name1, realName1, displayName1);

		final String id2 = "U9BD7NMPC";
		final String name2 = "filippo.lanubile";
		final String realName2 = "Filippo";
		final String displayName2 = "Lanubile";
		final Member toUser = new Member(id2, name2, realName2, displayName2);

		ment = new Mention(from, toUser);
		ment2 = new Mention(null, toUser);
		ment3 = new Mention(from, null);
	}

	@Test
	@DisplayName("Test Mention() di Mention")
	void mentionTest() {
		final String failMsg = "Member() is failed";

		final String id1 = "U9QLDMNDV";
		final String name1 = "luciano_bruno";
		final String realName1 = DEFNAMEUSR;
		final String displayName1 = DEFNAMEUSR;
		final Member from = new Member(id1, name1, realName1, displayName1);

		final String id2 = "U9BD7NMPC";
		final String name2 = "filippo.lanubile";
		final String realName2 = "Filippo";
		final String displayName2 = "Lanubile";
		final Member toUser = new Member(id2, name2, realName2, displayName2);
		assertNotNull(new Mention(from, toUser), failMsg);
	}

	@Test
	@DisplayName("Test getFrom() di Mention")
	void getFromTest() {
		final String failMsg = "getFrom() is failed";
		assertNotNull(ment.getFrom(), failMsg);
	}

	@Test
	@DisplayName("Test getFromId() di Mention")
	void getFromIdTest() {
		final String failMsg = "getFromId() is failed";
		assertNotNull(ment.getFromId(), failMsg);
	}

	@Test
	@DisplayName("Test getTo() di Mention")
	void getToTest() {
		final String failMsg = "getTo() is failed";
		assertNotNull(ment.getTo(), failMsg);
	}

	@Test
	@DisplayName("Test hashCode() di Mention")
	void hashCodeTest1() {
		final String failMsg = "hashCode() is failed";
		assertNotNull(ment.hashCode(), failMsg);
	}

	@Test
	@DisplayName("Test hashCode() di Mention")
	void hashCodeTest2() {
		final String failMsg = "hashCode() is failed";
		assertNotNull(ment2.hashCode(), failMsg);
	}

	@Test
	@DisplayName("Test hashCode() di Mention")
	void hashCodeTest3() {
		final String failMsg = "hashCode() is failed";
		assertNotNull(ment3.hashCode(), failMsg);
	}

	@Test
	@DisplayName("Test equals() di Mention")
	void equalsTest() {
		final String failMsg = "equals() is failed";

		final String id1 = "U9QLDMNDV";
		final String name1 = "luciano_bruno";
		final String realName1 = DEFNAMEUSR;
		final String displayName1 = DEFNAMEUSR;
		final Member from = new Member(id1, name1, realName1, displayName1);

		final String id2 = "U9W4FCFEH";
		final String name2 = "domenicolovino0";
		final String realName2 = "Domenico Lovino";
		final String displayName2 = "Domenico";
		final Member toUser = new Member(id2, name2, realName2, displayName2);

		final Mention ment2 = new Mention(from, toUser);
		assertFalse(ment.equals(ment2), failMsg);
	}

	@Test
	@DisplayName("Test toString() di Mention")
	void toStringTest() {
		final String failMsg = "toString() is failed";
		assertEquals(ment.toString(), "(Luciano Bruno, Lanubile)", failMsg);
	}

	@Test
	@DisplayName("Test toString() di Mention")
	void toFullStringTest() {
		final String failMsg = "toFullString() is failed";
		assertEquals(ment.toFullString(), "(Luciano Bruno, Lanubile, 1)", failMsg);
	}

	@Test
	@DisplayName("Test getWeight() di Mention")
	void getWeightTest() {
		final String failMsg = "getWeight() is failed";
		assertTrue(ment.getWeight() > 0, failMsg);
	}

	@Test
	@DisplayName("Test setWeight() di Mention")
	void setWeightTest() {
		final String failMsg = "setWeight() is failed";
		final int weight = 1;
		ment.setWeight(weight);
		assertEquals(ment.getWeight(), weight, failMsg);
	}

	@Test
	@DisplayName("Test getToId() di Mention")
	void getToIdTest() {
		final String failMsg = "getToId() is failed";
		assertNotNull(ment.getToId(), failMsg);
	}

}
