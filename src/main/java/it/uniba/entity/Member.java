package it.uniba.entity;

import java.util.LinkedList;
import java.util.List;

public class Member {
	private String id;
	private String name;
	private List<Channel> channels;
	
	public Member(String id, String name) {
		this.id=id;
		this.name=name;
		this.channels=new LinkedList<Channel>();
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedList<Channel> getChannels(){
		return (LinkedList<Channel>) channels;
	}
}
