package it.uniba.enity;

import java.util.LinkedList;
import java.util.List;

public class Member {
	private String id;
	private String realName;
	private List<Channel> channels;
	
	public Member(String id, String realName) {
		this.id=id;
		this.realName=realName;
		this.channels=new LinkedList<Channel>();
	}
	
	public String getId() {
		return id;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public LinkedList<Channel> getChannels(){
		return (LinkedList<Channel>) channels;
	}
}
