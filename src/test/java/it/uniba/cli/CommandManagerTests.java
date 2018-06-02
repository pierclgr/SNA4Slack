package it.uniba.cli;

import org.junit.jupiter.api.*;
import it.uniba.file.PathManager;

@SuppressWarnings("PMD.TooManyStaticImports")
public class CommandManagerTests {
	
	static String workspace = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip");
	static String channel = "general";
	static String member = "Lanubile";
	
	static String notvalidworkspace = PathManager.getAbsolutePath("res/Slack Workspace no channels no users.zip");
	static String wrongfile = PathManager.getAbsolutePath("res/img/guida-studente/Schermata1.png");
	static String filenotfound = PathManager.getAbsolutePath("res/ingsw1718 Slack export May 16 2018.zip/Schermata1.png");
    static String wrongchannel = "wrongchannel";
    static String wrongmember = "wrongmember";
    
	@Test
	@DisplayName("Test Help() di CommandManager")
	void HelpTest() {
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
		
		final String[] s = {};
		CommandManager.manage(s);
		
		final String[] sa = {"help"};
		CommandManager.manage(sa);
		final String[] sb = {"wrong"};
		CommandManager.manage(sb);
		
		final String[] sc = {"wrong", "wrong"};
		CommandManager.manage(sc);
		
		final String[] sd = {"members", "-f", ""};
		CommandManager.manage(sd);
		final String[] se = {"channels", "-f", ""};
		CommandManager.manage(se);
		final String[] sf = {"mentions", "-f", ""};
		CommandManager.manage(sf);
		final String[] sg = {"wrong", "", ""};
		CommandManager.manage(sg);
		
		final String[] sh = {"members", "-ch", "-f", ""};
		CommandManager.manage(sh);
		final String[] si = {"mentions", "-w", "-f", ""};
		CommandManager.manage(si);
		final String[] sj = {"wrong", "", "", ""};
		CommandManager.manage(sj);
		
		final String[] sk = {"members", "-ch", "", "-f", ""};
		CommandManager.manage(sk);
		final String[] sl = {"mentions", "-ch", "", "-f", ""};
		CommandManager.manage(sl);
		final String[] sm = {"wrong", "-ch", "", "-f", ""};
		CommandManager.manage(sm);
		final String[] sn = {"mentions", "-to", "", "-f", ""};
		CommandManager.manage(sn);
		final String[] so = {"mentions", "-from", "", "-f", ""};
		CommandManager.manage(so);
		final String[] sp = {"mentions", "wrong", "", "-f", ""};
		CommandManager.manage(sp);
		final String[] sq = {"", "", "", "wrong", ""};
		CommandManager.manage(sq);
		
		final String[] sr = {"mentions", "-w", "-from", "", "-f", ""};
		CommandManager.manage(sr);
		final String[] ss = {"mentions", "-w", "-ch", "", "-f", ""};
		CommandManager.manage(ss);
		final String[] st = {"mentions", "-w", "-to", "", "-f", ""};
		CommandManager.manage(st);
		final String[] su = {"mentions", "-w", "wrong", "", "-f", ""};
		CommandManager.manage(su);
		final String[] sv = {"wrong", "", "", "", "", ""};
		CommandManager.manage(sv);
		
		final String[] sw = {"mentions", "-to", "", "-ch", "", "-f", ""};
		CommandManager.manage(sw);
		final String[] sx = {"mentions", "-from", "", "-ch", "", "-f", ""};
		CommandManager.manage(sx);
		final String[] sy = {"wrong", "", "", "", "", "", ""};
		CommandManager.manage(sy);
		
		final String[] sz = {"mentions", "-w", "-to", "", "-ch", "", "-f", ""};
		CommandManager.manage(sz);
		final String[] saa = {"mentions", "-w", "-from", "", "-ch", "", "-f", ""};
		CommandManager.manage(saa);
		final String[] sab = {"mentions", "-w", "wrong", "", "-ch", "", "-f", ""};
		CommandManager.manage(sab);
		final String[] sac = {"wrong", "", "", "", "", "", "", ""};
		CommandManager.manage(sac);

		final String[] sad = {"wrong", "", "", "", "", "", "", "", ""};
		CommandManager.manage(sad);
	}

}