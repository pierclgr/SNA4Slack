package it.uniba.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import it.uniba.file.PathManager;
import it.uniba.file.zip.*;

@SuppressWarnings("PMD.TooManyStaticImports")
public class WorkspaceTests {
	static Workspace w;

	@BeforeAll
	static void setUpAll() throws Exception {
		String file = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		w = new Workspace(file);
	}

	@Test
	@DisplayName("Test Workspace() di Workspace")
	void WorkspaceTest() {
		final String failMsg = "Workspace() is failed";
		String workspace = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
		String notvalidworkspace = PathManager.getAbsolutePath("res/Slack Workspace no channels no users.zip");
		String wrongfile = PathManager.getAbsolutePath("res/img/guida-studente/Schermata1.png");
		assertAll("Check workspace constructor with lambdas", () -> {
			assertNotNull(new Workspace(workspace), failMsg);
			assertThrows(NotZipFileException.class,() -> {
				new Workspace(wrongfile);
			});
			assertThrows(NotValidWorkspaceException.class,() -> {
				new Workspace(notvalidworkspace);
			});
		});
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
	void getMembersOfChannelTest() {
		final String failMsg = "getMembersOfChannelTest() is failed";
		String channel = "ritchie";
		assertAll("Check members of channel with lambdas", () -> {
		    assertNotNull(w.getMembersOfChannel(channel), failMsg);
			assertThrows(ChannelNotValidException.class, () -> {
			    w.getMembersOfChannel("wrongchannel");
			});
		});
	}

	@Test
	@DisplayName("Test getMentionsFromUser() di Workspace")
	void getMentionsFromUserTest() {
		final String failMsg = "getMentionsFromUser() is failed";
		String channel = "general";
		String member = "Lanubile";
		assertAll("Check mentions from user with lambdas", () -> {
			assertNotNull(w.getMentionsFromUser(channel, member), failMsg);
			assertThrows(MemberNotValidException.class, () -> {
			    w.getMentionsFromUser(channel, "wrongmember");
			});
			assertThrows(ChannelNotValidException.class, () -> {
			    w.getMentionsFromUser("wrongchannel", member);
			});
		});
	}

	@Test
	@DisplayName("Test getMentionsToUser() di Workspace")
	void getMentionsToUserTest() {
		final String failMsg = "getMentionsToUser() is failed";
		String channel = "general";
		String member = "Lanubile";
		assertAll("Check mentions to user with lambdas", () -> {
			assertNotNull(w.getMentionsToUser(channel, member), failMsg);
			assertThrows(MemberNotValidException.class, () -> {
			    w.getMentionsToUser(channel, "wrongmember");
			});
			assertThrows(ChannelNotValidException.class, () -> {
			    w.getMentionsToUser("wrongchannel", member);
			});
		});
	}

	@Test
	@DisplayName("Test getMentions() di Workspace")
	void getMentionsTest() {
		final String failMsg = "getMentions() is failed";
		String channel = "general";
		assertAll("Check mentions with lambdas", () -> {
		    assertNotNull(w.getMentions(channel), failMsg);
			assertThrows(ChannelNotValidException.class, () -> {
			    w.getMentions("wrongchannel");
			});
		}); 
	}

	@Test
	@DisplayName("Test getWorkspaceZip() di Workspace")
	void getWorkspaceZipTest() {
		final String failMsg = "getWorkspaceZip() is failed";
		assertNotNull(w.getWorkspaceZip(), failMsg);
	}

}
