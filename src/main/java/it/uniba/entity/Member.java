package it.uniba.entity;

import java.util.LinkedList;
import java.util.List;

public final class Member {
	private String id;
	private String name;
	private String realName;
	private String displayName;
	private List<Channel> channels;

	public Member(final String memberId, final String memberName,
			final String memberRealName, final String memberDisplayName) {
		this.id = memberId;
		this.name = memberName;
		this.realName = memberRealName;
		this.displayName = memberDisplayName;
		this.channels = new LinkedList<Channel>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		if (!displayName.equals("")) {
			return displayName;
		} else if (!realName.equals("")) {
			return realName;
		} else if (!name.equals("")) {
			return name;
		} else {
			return id;
		}
	}

	public boolean isUser(final String member) {
		return displayName.equals(member) || realName.equals(member) || name.equals(member) || id.equals(member);
	}

	public String getUserName() {
		return name;
	}

	public LinkedList<Channel> getChannels() {
		return (LinkedList<Channel>) channels;
	}
}
