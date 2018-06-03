package it.uniba.cli;

import org.junit.jupiter.api.*;
import it.uniba.file.PathManager;

@SuppressWarnings("PMD.TooManyStaticImports")
public class CommandManagerTests {

	static String workspace = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
	static String channel = "general";
	static String member = "Lanubile";
	static final String WRONG = "wrong";
	static final String MENTIONS = "mentions";
	static final String CHANNEL = "-ch";
	static final String TOUSER = "-to";
	static final String FROM = "-from";

	static String notvalidworkspace = PathManager.getAbsolutePath("res/Slack Workspace no channels no users.zip");
	static String wrongfile = PathManager.getAbsolutePath("res/img/guida-studente/Schermata1.png");
	static String filenotfound = PathManager
			.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip/Schermata1.png");
	static String wrongchannel = "wrongchannel";
	static String wrongmember = "wrongmember";

	@Test
	@DisplayName("Test Help() di CommandManager")
	void helpTest() {
		CommandManager.help();
	}

	@Test
	@DisplayName("Test getChannels() di CommandManager")
	void getChannelsTest() {
		CommandManager.getChannels(workspace);
		CommandManager.getChannels(notvalidworkspace);
		CommandManager.getChannels(wrongfile);
		CommandManager.getChannels(filenotfound);
	}

	@Test
	@DisplayName("Test getMembers() di CommandManager")
	void getMembersTest() {
		CommandManager.getMembers(workspace);
		CommandManager.getMembers(notvalidworkspace);
		CommandManager.getMembers(wrongfile);
		CommandManager.getMembers(filenotfound);
	}

	@Test
	@DisplayName("Test getMembersOfChannel() di CommandManager")
	void getMembersOfChannelTest() {
		CommandManager.getMembersOfChannel(workspace, channel);
		CommandManager.getMembersOfChannel(notvalidworkspace, channel);
		CommandManager.getMembersOfChannel(wrongfile, channel);
		CommandManager.getMembersOfChannel(filenotfound, channel);
	}

	@Test
	@DisplayName("Test getMembersForChannels() di CommandManager")
	void getMembersForChannelsTest() {
		CommandManager.getMembersForChannels(workspace);
		CommandManager.getMembersForChannels(notvalidworkspace);
		CommandManager.getMembersForChannels(wrongfile);
		CommandManager.getMembersForChannels(filenotfound);
	}

	@Test
	@DisplayName("Test getMentionsFromUser() di CommandManager")
	void getMentionsFromUserTest() {
		CommandManager.getMentionsFromUser(workspace, member);
		CommandManager.getMentionsFromUser(workspace, channel, member);
		CommandManager.getMentionsFromUser(notvalidworkspace, member);
		CommandManager.getMentionsFromUser(notvalidworkspace, channel, member);
		CommandManager.getMentionsFromUser(wrongfile, member);
		CommandManager.getMentionsFromUser(wrongfile, channel, member);
		CommandManager.getMentionsFromUser(filenotfound, member);
		CommandManager.getMentionsFromUser(filenotfound, channel, member);
	}

	@Test
	@DisplayName("Test getMentionsFromUserWeighed() di CommandManager")
	void getMentionsFromUserWeighedTest() {
		CommandManager.getMentionsFromUserWeighed(workspace, member);
		CommandManager.getMentionsFromUserWeighed(workspace, channel, member);
		CommandManager.getMentionsFromUserWeighed(notvalidworkspace, member);
		CommandManager.getMentionsFromUserWeighed(notvalidworkspace, channel, member);
		CommandManager.getMentionsFromUserWeighed(wrongfile, member);
		CommandManager.getMentionsFromUserWeighed(wrongfile, channel, member);
		CommandManager.getMentionsFromUserWeighed(filenotfound, member);
		CommandManager.getMentionsFromUserWeighed(filenotfound, channel, member);
	}

	@Test
	@DisplayName("Test getMentions() di CommandManager")
	void getMentionsTest() {
		CommandManager.getMentions(workspace);
		CommandManager.getMentions(notvalidworkspace);
		CommandManager.getMentions(wrongfile);
		CommandManager.getMentions(filenotfound);
		CommandManager.getMentions(workspace, channel);
		CommandManager.getMentions(notvalidworkspace, channel);
		CommandManager.getMentions(wrongfile, channel);
		CommandManager.getMentions(filenotfound, channel);
	}

	@Test
	@DisplayName("Test getMentionsWeighed() di CommandManager")
	void getMentionsWeighedTest() {
		CommandManager.getMentionsWeighed(workspace);
		CommandManager.getMentionsWeighed(notvalidworkspace);
		CommandManager.getMentionsWeighed(wrongfile);
		CommandManager.getMentionsWeighed(filenotfound);
		CommandManager.getMentionsWeighed(workspace, channel);
		CommandManager.getMentionsWeighed(notvalidworkspace, channel);
		CommandManager.getMentionsWeighed(wrongfile, channel);
		CommandManager.getMentionsWeighed(filenotfound, channel);
	}

	@Test
	@DisplayName("Test getMentionsToUser() di CommandManager")
	void getMentionsToUserTest() {
		CommandManager.getMentionsToUser(workspace, member);
		CommandManager.getMentionsToUser(workspace, channel, member);
		CommandManager.getMentionsToUser(workspace, member);
		CommandManager.getMentionsToUser(workspace, channel, member);
		CommandManager.getMentionsToUser(notvalidworkspace, member);
		CommandManager.getMentionsToUser(notvalidworkspace, channel, member);
		CommandManager.getMentionsToUser(wrongfile, member);
		CommandManager.getMentionsToUser(wrongfile, channel, member);
		CommandManager.getMentionsToUser(filenotfound, member);
		CommandManager.getMentionsToUser(filenotfound, channel, member);
	}

	@Test
	@DisplayName("Test getMentionsToUserWeighed() di CommandManager")
	void getMentionsToUserWeighedTest() {
		CommandManager.getMentionsToUserWeighed(workspace, member);
		CommandManager.getMentionsToUserWeighed(workspace, channel, member);
		CommandManager.getMentionsToUserWeighed(workspace, member);
		CommandManager.getMentionsToUserWeighed(workspace, channel, member);
		CommandManager.getMentionsToUserWeighed(notvalidworkspace, member);
		CommandManager.getMentionsToUserWeighed(notvalidworkspace, channel, member);
		CommandManager.getMentionsToUserWeighed(wrongfile, member);
		CommandManager.getMentionsToUserWeighed(wrongfile, channel, member);
		CommandManager.getMentionsToUserWeighed(filenotfound, member);
		CommandManager.getMentionsToUserWeighed(filenotfound, channel, member);
	}

	@Test
	@DisplayName("Test manage() di CommandManager")
	void manageTest() {

		final String[] string = {};
		CommandManager.manage(string);

		final String[] stringA = { "help" };
		CommandManager.manage(stringA);
		final String[] stringB = { WRONG };
		CommandManager.manage(stringB);

		final String[] stringC = { WRONG, WRONG };
		CommandManager.manage(stringC);

		final String[] stringD = { "members", "-f", "" };
		CommandManager.manage(stringD);
		final String[] stringE = { "channels", "-f", "" };
		CommandManager.manage(stringE);
		final String[] stringF = { MENTIONS, "-f", "" };
		CommandManager.manage(stringF);
		final String[] stringG = { WRONG, "", "" };
		CommandManager.manage(stringG);

		final String[] stringH = { "members", CHANNEL, "-f", "" };
		CommandManager.manage(stringH);
		final String[] stringI = { MENTIONS, "-w", "-f", "" };
		CommandManager.manage(stringI);
		final String[] stringJ = { WRONG, "", "", "" };
		CommandManager.manage(stringJ);

		final String[] stringK = { "members", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringK);
		final String[] stringL = { MENTIONS, CHANNEL, "", "-f", "" };
		CommandManager.manage(stringL);
		final String[] stringM = { WRONG, CHANNEL, "", "-f", "" };
		CommandManager.manage(stringM);
		final String[] stringN = { MENTIONS, TOUSER, "", "-f", "" };
		CommandManager.manage(stringN);
		final String[] stringO = { MENTIONS, FROM, "", "-f", "" };
		CommandManager.manage(stringO);
		final String[] stringP = { MENTIONS, WRONG, "", "-f", "" };
		CommandManager.manage(stringP);
		final String[] stringQ = { "", "", "", WRONG, "" };
		CommandManager.manage(stringQ);

		final String[] stringR = { MENTIONS, "-w", FROM, "", "-f", "" };
		CommandManager.manage(stringR);
		final String[] stringS = { MENTIONS, "-w", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringS);
		final String[] stringT = { MENTIONS, "-w", TOUSER, "", "-f", "" };
		CommandManager.manage(stringT);
		final String[] stringU = { MENTIONS, "-w", WRONG, "", "-f", "" };
		CommandManager.manage(stringU);
		final String[] stringV = { WRONG, "", "", "", "", "" };
		CommandManager.manage(stringV);

		final String[] stringW = { MENTIONS, TOUSER, "", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringW);
		final String[] stringX = { MENTIONS, FROM, "", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringX);
		final String[] stringY = { WRONG, "", "", "", "", "", "" };
		CommandManager.manage(stringY);

		final String[] stringZ = { MENTIONS, "-w", TOUSER, "", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringZ);
		final String[] stringAA = { MENTIONS, "-w", FROM, "", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringAA);
		final String[] stringAB = { MENTIONS, "-w", WRONG, "", CHANNEL, "", "-f", "" };
		CommandManager.manage(stringAB);
		final String[] stringAC = { WRONG, "", "", "", "", "", "", "" };
		CommandManager.manage(stringAC);

		final String[] stringAD = { WRONG, "", "", "", "", "", "", "", "" };
		CommandManager.manage(stringAD);
	}

}