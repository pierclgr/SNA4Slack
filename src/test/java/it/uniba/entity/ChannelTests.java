package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class ChannelTests {
	static Channel channel1;
	static Channel channel2;

	@BeforeAll
	static void setUpAll() {
		final String id1 = "C9NQ5BM5Z";
		final String name1 = "allen";
		final String id2 = "C9P0BD724";
		final String name2 = "ritchie";
		channel1 = new Channel(id1, name1);
		channel2 = new Channel(id2, name2);
	}

	@Test
	@DisplayName("Test Channel() di Channel 1")
	void channelTest1() {
		final String failMsg = "Channel() is failed";
		final String id1 = "C9NQ5BM5Z";
		final String name1 = "allen";
		assertNotNull(new Channel(id1, name1), failMsg);
	}

	@Test
	@DisplayName("Test Channel() di Channel 2")
	void channelTest2() {
		final String failMsg = "Channel() is failed";
		final String id2 = "C9P0BD724";
		final String name2 = "ritchie";
		assertNotNull(new Channel(id2, name2), failMsg);
	}

	@Test
	@DisplayName("Test getId() di Channel")
	void getIdTest1() {
		final String failMsg = "getId() is failed";
		assertEquals("C9NQ5BM5Z", channel1.getId(), failMsg);
	}

	@Test
	@DisplayName("Test getId() di Channel")
	void getIdTest2() {
		final String failMsg = "getId() is failed";
		assertNotEquals("CA3EZFFGR", channel1.getId(), failMsg);
	}

	@Test
	@DisplayName("Test getName() di Channel")
	void getNameTest1() {
		final String failMsg = "getName() is failed";
		assertEquals("allen", channel1.getName(), failMsg);
	}

	@Test
	@DisplayName("Test getName() di Channel")
	void getNameTest2() {
		final String failMsg = "getName() is failed";
		assertNotEquals("ritchie", channel1.getName(), failMsg);
	}

	@Test
	@DisplayName("Test getMembers() di Channel")
	void getMembersTest1() {
		final String failMsg = "getMembers() is failed";
		assertNotNull(channel1.getMembers(), failMsg);
	}

	@Test
	@DisplayName("Test getMembers() di Channel")
	void getMembersTest2() {
		final String failMsg = "getMembers() is failed";
		assertNotNull(channel2.getMembers(), failMsg);
	}

	@Test
	@DisplayName("Test getMentions() di Channel")
	void getMentionsTest1() {
		final String failMsg = "getMentions() is failed";
		assertNotNull(channel1.getMentions(), failMsg);
	}

	@Test
	@DisplayName("Test getMentions() di Channel")
	void getMentionsTest2() {
		final String failMsg = "getMentions() is failed";
		assertNotNull(channel2.getMentions(), failMsg);
	}
}
