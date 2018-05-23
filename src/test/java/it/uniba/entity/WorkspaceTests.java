package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import it.uniba.file.PathManager;

@SuppressWarnings("PMD.TooManyStaticImports")
public class WorkspaceTests {
	static Workspace w;

	@BeforeAll
	static void setUpAll() throws Exception {
		String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		;
		w = new Workspace(file);
	}

	@Test
	@DisplayName("Test Workspace() di Workspace")
	void WorkspaceTest() throws Exception {
		final String failMsg = "Workspace() is failed";
		String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		assertNotNull(new Workspace(file), failMsg);
	}

	@Test
	@DisplayName("Test getAllChannels() di Workspace")
	void getAllChannelsTest() {
		final String failMsg = "getAllChannelsTest() is failed";
		assertNotNull(w.getAllChannels(), failMsg);
	}

	@Test
	@DisplayName("Test getAllMembersTest() di Workspace")
	void getAllMembersTest() {
		final String failMsg = "getAllMembersTest() is failed";
		assertNotNull(w.getAllMembers(), failMsg);
	}

	@Test
	@DisplayName("Test getMembersOfChannel() di Workspace")
	void getMembersOfChannelTest() throws Exception {
		final String failMsg = "getMembersOfChannelTest() is failed";
		String channel = "ritchie";
		assertNotNull(w.getMembersOfChannel(channel), failMsg);
	}

	@Test
	@DisplayName("Test getMentionsFromUser() di Workspace")
	void getMentionsFromUserTest() throws Exception {
		final String failMsg = "getMentionsFromUser() is failed";
		String channel = "general";
		String member = "Lanubile";
		assertNotNull(w.getMentionsFromUser(channel, member), failMsg);
	}

	@Test
	@DisplayName("Test getMentionsToUser() di Workspace")
	void getMentionsToUserTest() throws Exception {
		final String failMsg = "getMentionsToUser() is failed";
		String channel = "general";
		String member = "Lanubile";
		assertNotNull(w.getMentionsToUser(channel, member), failMsg);
	}

	@Test
	@DisplayName("Test getMentions() di Workspace")
	void getMentionsTest() throws Exception {
		final String failMsg = "getMentions() is failed";
		String channel = "general";
		assertNotNull(w.getMentions(channel), failMsg);
	}

	@Test
	@DisplayName("Test getWorkspaceZip() di Workspace")
	void getWorkspaceZipTest() {
		final String failMsg = "getWorkspaceZip() is failed";
		assertNotNull(w.getWorkspaceZip(), failMsg);
	}

}
