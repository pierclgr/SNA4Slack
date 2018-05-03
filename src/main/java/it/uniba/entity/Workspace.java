package it.uniba.entity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipException;

import org.json.JSONArray;
import org.json.JSONObject;
import it.uniba.file.zip.FileNotInZipException;
import it.uniba.file.zip.NotValidWorkspaceException;
import it.uniba.file.zip.NotZipFileException;
import it.uniba.file.zip.Zip;

public class Workspace {
	public Zip workspaceZip;
	List<Channel> channels;
	List<Member> members;
	
	public Workspace(String workspaceZipFile) throws IOException,NotValidWorkspaceException,FileNotInZipException,NotZipFileException {
		try {
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
					Member currMember=new Member(member.getString("id"),member.getString("name"));
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
		}catch(ZipException e) {
			throw new NotZipFileException(workspaceZipFile);
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
  
	public LinkedList<Channel> getAllChannels() {
		return (LinkedList<Channel>) this.channels;
	}
	
	public LinkedList<Member> getAllMembers() {
		return (LinkedList<Member>) this.members;
	}
	
	public LinkedList<Member> getMembersOfChannel(String channel) {  
		boolean found=false;  
		LinkedList<Member> membersOfChannel=null;  
		ListIterator<Channel> channelsIterator=(ListIterator<Channel>) channels.iterator();  
		while(channelsIterator.hasNext()&&!found) {   
			Channel curr=channelsIterator.next();   
			if(curr.getName().equals(channel)) {    
				membersOfChannel=curr.getMembers();    
				found=true;   
			}  
		}  
		return membersOfChannel; 
	}
}
