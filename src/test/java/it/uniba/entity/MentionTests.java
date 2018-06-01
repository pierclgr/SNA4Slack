package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class MentionTests {
	static Mention ment, ment2, ment3;

	@BeforeAll
	static void setUpAll() {
		String id1 = "U9QLDMNDV";
		String name1 = "luciano_bruno";
		String realName1 = "Luciano Bruno";
		String displayName1 = "Luciano Bruno";
		Member from = new Member(id1, name1, realName1, displayName1);

		String id2 = "U9BD7NMPC";
		String name2 = "filippo.lanubile";
		String realName2 = "Filippo";
		String displayName2 = "Lanubile";
		Member to = new Member(id2, name2, realName2, displayName2);

		ment = new Mention(from, to);
		ment2 = new Mention(null,to);
		ment3 = new Mention(from,null);
	}

	@Test
	@DisplayName("Test Mention() di Mention")
	void MentionTest() {
		final String failMsg = "Member() is failed";

		String id1 = "U9QLDMNDV";
		String name1 = "luciano_bruno";
		String realName1 = "Luciano Bruno";
		String displayName1 = "Luciano Bruno";
		Member from = new Member(id1, name1, realName1, displayName1);

		String id2 = "U9BD7NMPC";
		String name2 = "filippo.lanubile";
		String realName2 = "Filippo";
		String displayName2 = "Lanubile";
		Member to = new Member(id2, name2, realName2, displayName2);
		assertNotNull(new Mention(from, to), failMsg);
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
	void hashCodeTest() {
		final String failMsg = "hashCode() is failed";
		assertAll("Check hashcode with lambdas", () -> {
			assertNotNull(ment.hashCode(), failMsg);
			assertNotNull(ment2.hashCode(), failMsg);
			assertNotNull(ment3.hashCode(), failMsg);
		});
	}

	@Test
	@DisplayName("Test equals() di Mention")
	void equalsTest() {
		final String failMsg = "equals() is failed";

		String id1 = "U9QLDMNDV";
		String name1 = "luciano_bruno";
		String realName1 = "Luciano Bruno";
		String displayName1 = "Luciano Bruno";
		Member from = new Member(id1, name1, realName1, displayName1);

		String id2 = "U9W4FCFEH";
		String name2 = "domenicolovino0";
		String realName2 = "Domenico Lovino";
		String displayName2 = "Domenico";
		Member to = new Member(id2, name2, realName2, displayName2);

		Mention ment2 = new Mention(from, to);
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
		int weight = 1;
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
