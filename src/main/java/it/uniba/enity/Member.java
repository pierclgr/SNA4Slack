package it.uniba.enity;

public class Member {
	private String id;
	private String realName;
	
	public Member(String id, String realName) {
		this.id=id;
		this.realName=realName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getRealName() {
		return realName;
	}
}
