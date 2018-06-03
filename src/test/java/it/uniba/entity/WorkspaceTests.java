package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.*;
import it.uniba.file.PathManager;
import it.uniba.file.zip.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class WorkspaceTests {
	static Workspace workspace;
	static final String WRONG = "wrongchannel";
	static final String GENERALCH = "general";
	static final String LANUBILEUSR = "Lanubile";

	@BeforeAll
	static void setUpAll() throws NotZipFileException, NotValidWorkspaceException, FileNotInZipException, IOException {
		final String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		workspace = new Workspace(file);
	}

	@Test
	@DisplayName("Test Workspace() di Workspace")
	void workspaceTest1() throws NotZipFileException, FileNotInZipException, NotValidWorkspaceException, IOException {
		final String failMsg = "Workspace() is failed";
		final String workspace = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		assertNotNull(new Workspace(workspace), failMsg);
	}

	@Test
	@DisplayName("Test Workspace() di Workspace")
	void workspaceTest2() {
		final String wrongfile = PathManager.getAbsolutePath("res/img/guida-studente/Schermata1.png");
		assertThrows(NotZipFileException.class, () -> {
			new Workspace(wrongfile);
		});
	}

	@Test
	@DisplayName("Test Workspace() di Workspace")
	void workspaceTest3() {
		final String notvalidworkspace = PathManager.getAbsolutePath("res/Slack Workspace no channels no users.zip");
		assertThrows(NotValidWorkspaceException.class, () -> {
			new Workspace(notvalidworkspace);
		});
	}

	@Test
	@DisplayName("Test getAllChannels() di Workspace")
	void getAllChannelsTest() {
		final String failMsg = "getAllChannelsTest() is failed";
		assertNotNull(workspace.getAllChannels(), failMsg);
	}

	@Test
	@DisplayName("Test getAllMembersTest() di Workspace")
	void getAllMembersTest() {
		final String failMsg = "getAllMembersTest() is failed";
		assertNotNull(workspace.getAllMembers(), failMsg);
	}

	@Test
	@DisplayName("Test getMembersOfChannel() di Workspace")
	void getMembersOfChannelTest1() throws ChannelNotValidException {
		final String failMsg = "getMembersOfChannelTest() is failed";
		final String channel = "ritchie";
		assertNotNull(workspace.getMembersOfChannel(channel), failMsg);
	}

	@Test
	@DisplayName("Test getMembersOfChannel() di Workspace")
	void getMembersOfChannelTest2() {
		assertThrows(ChannelNotValidException.class, () -> {
			workspace.getMembersOfChannel(WRONG);
		});
	}

	@Test
	@DisplayName("Test getMentionsFromUser() di Workspace")
	void getMentionsFromUserTest1() throws MemberNotValidException, ChannelNotValidException {
		final String failMsg = "getMentionsFromUser() is failed";
		final String channel = GENERALCH;
		final String member = LANUBILEUSR;
		assertNotNull(workspace.getMentionsFromUser(channel, member), failMsg);
	}

	@Test
	@DisplayName("Test getMentionsFromUser() di Workspace")
	void getMentionsFromUserTest2() {
		final String channel = GENERALCH;
		assertThrows(MemberNotValidException.class, () -> {
			workspace.getMentionsFromUser(channel, "wrongmember");
		});
	}

	@Test
	@DisplayName("Test getMentionsFromUser() di Workspace")
	void getMentionsFromUserTest3() {
		final String member = LANUBILEUSR;
		assertThrows(ChannelNotValidException.class, () -> {
			workspace.getMentionsFromUser(WRONG, member);
		});
	}

	@Test
	@DisplayName("Test getMentionsToUser() di Workspace")
	void getMentionsToUserTest1() throws ChannelNotValidException, MemberNotValidException {
		final String failMsg = "getMentionsToUser() is failed";
		final String channel = GENERALCH;
		final String member = LANUBILEUSR;
		assertNotNull(workspace.getMentionsToUser(channel, member), failMsg);
	}

	@Test
	@DisplayName("Test getMentionsToUser() di Workspace")
	void getMentionsToUserTest2() {
		final String channel = GENERALCH;
		assertThrows(MemberNotValidException.class, () -> {
			workspace.getMentionsToUser(channel, "wrongmember");
		});
	}

	@Test
	@DisplayName("Test getMentionsToUser() di Workspace")
	void getMentionsToUserTest3() {
		final String member = LANUBILEUSR;
		assertThrows(ChannelNotValidException.class, () -> {
			workspace.getMentionsToUser(WRONG, member);
		});
	}

	@Test
	@DisplayName("Test getMentions() di Workspace")
	void getMentionsTest1() throws ChannelNotValidException {
		final String failMsg = "getMentions() is failed";
		final String channel = GENERALCH;
		assertNotNull(workspace.getMentions(channel), failMsg);
	}

	@Test
	@DisplayName("Test getMentions() di Workspace")
	void getMentionsTest2() {
		assertThrows(ChannelNotValidException.class, () -> {
			workspace.getMentions(WRONG);
		});
	}

	@Test
	@DisplayName("Test getWorkspaceZip() di Workspace")
	void getWorkspaceZipTest() {
		final String failMsg = "getWorkspaceZip() is failed";
		assertNotNull(workspace.getWorkspaceZip(), failMsg);
	}

}
