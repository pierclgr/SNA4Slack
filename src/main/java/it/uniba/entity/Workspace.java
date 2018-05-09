package it.uniba.entity;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipException;

import org.json.JSONArray;
import org.json.JSONObject;

import it.uniba.entity.Channel;
import it.uniba.entity.Member;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;
import it.uniba.file.zip.Zip;

public class Workspace {
	public Zip workspaceZip;
	LinkedHashMap<String, Channel> channels;
	LinkedHashMap<String, Member> members;
	
	public Workspace(String workspaceZipFile) throws IOException,NotValidWorkspaceException,FileNotInZipException,NotZipFileException {
		try {
			Zip workspaceZip=new Zip(workspaceZipFile);
			if(!workspaceZip.contains("channels.json")||!workspaceZip.contains("users.json")) {
				throw new NotValidWorkspaceException(workspaceZipFile);
			}else {
				this.workspaceZip=workspaceZip;
				this.channels=new LinkedHashMap<String, Channel>();
				this.members=new LinkedHashMap<String, Member>();
	
				JSONArray membersRootArray = new JSONArray(workspaceZip.getFileContent("users.json"));
				for(int i=0; i < membersRootArray.length(); i++) {
					JSONObject member = membersRootArray.getJSONObject(i);
					Member currMember=new Member(member.getString("id"),member.getString("name"));
					members.put(currMember.getName(),currMember);
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
					channels.put(currChannel.getName(),currChannel);
				}
			}
		}catch(ZipException e) {
			throw new NotZipFileException(workspaceZipFile);
		}
	}
	
	private Member getMemberById(String id) {
		Collection<Member> c = this.members.values();
		Iterator<Member> itr = c.iterator();
		while (itr.hasNext()){
			Member itrNext = itr.next();
			if(itrNext.getId().equals(id)) {
				return itrNext;
			}
		}
		return null;
	}
  
	public LinkedHashMap<String, Channel> getAllChannels() {
		return (LinkedHashMap<String, Channel>) this.channels;
	}
	
	public LinkedHashMap<String, Member> getAllMembers() {
		return (LinkedHashMap<String, Member>) this.members;
	}
	
	public LinkedList<Member> getMembersOfChannel(String channelId) {  
		return channels.get(channelId).getMembers();
	}
}
