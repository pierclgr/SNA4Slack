package it.uniba.entity;

import java.util.LinkedList;
import java.util.List;

public class Member {
	private String id;
	private String name;
	private String real_name;
	private String display_name;
	private List<Channel> channels;

	public Member(String id, String name, String real_name, String display_name) {
		this.id=id;
		this.name=name;
		this.real_name=real_name;
		this.display_name=display_name;
		this.channels=new LinkedList<Channel>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		if(!display_name.equals("")) {
			return display_name;
		}else if(!real_name.equals("")) {
			return real_name;
		}else  if(!name.equals("")) {
			return name;
		}else {
			return id;
		}
	}

	public boolean isUser(String member) {
		if(display_name.equals(member)) {
			return true;
		}else if(real_name.equals(member)) {
			return true;
		}else if(name.equals(member)) {
			return true;
		}else if(id.equals(member)) {
			return true;
		}else {
			return false;
		}
	}

	public String getUserName() {
		return name;
	}

	public LinkedList<Channel> getChannels(){
		return (LinkedList<Channel>) channels;
	}
}
