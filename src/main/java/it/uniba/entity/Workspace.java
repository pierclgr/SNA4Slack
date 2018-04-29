package it.uniba.entity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.Zip;

public class Workspace {
	public Zip workspaceZip;
	List<Channel> channels;
	List<Member> members;
	
	public Workspace(String workspaceZipFile) throws IOException,NotValidWorkspaceException,FileNotInZipException {
		Zip workspaceZip=new Zip(workspaceZipFile);
		if(!workspaceZip.contains("channels.json")||!workspaceZip.contains("users.json")) {
			throw new NotValidWorkspaceException(workspaceZipFile);
		}else {
			this.workspaceZip=workspaceZip;
			this.channels=new LinkedList<Channel>();
			this.members=new LinkedList<Member>();

			JSONArray membersRootArray = new JSONArray(workspaceZip.getFileContent("users.json"));
			for(int i=0; i < membersRootArray.length(); i++) {
				JSONObject member = membersRootArray.getJSONObject(i);
				Member currMember=new Member(member.getString("id"),member.getString("real_name"));
				members.add(currMember);
			}
			JSONArray channelsRootArray = new JSONArray(workspaceZip.getFileContent("channels.json"));
			for(int j=0; j<channelsRootArray.length(); j++) {
				JSONObject channel= channelsRootArray.getJSONObject(j);
				Channel currChannel=new Channel(channel.getString("id"),channel.getString("name"));
				JSONArray membersArray = channel.getJSONArray("members");
				for(int k=0; k<membersArray.length(); k++){
					String memberId=membersArray.getString(k);
					currChannel.getMembers().add(getMemberById(memberId));
					getMemberById(memberId).getChannels().add(currChannel);
				}
				channels.add(currChannel);
			}
		}
	}
	
	private Member getMemberById(String id) {
		Member member = null;
		for(int i=0; i<members.size();i++) {
			if(this.members.get(i).getId().equals(id)) {
				member= this.members.get(i);
			}
		}
		return member;
	}
}
