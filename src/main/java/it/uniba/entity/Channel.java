package it.uniba.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public final class Channel {
	private String id;
	private String name;
	private List<Member> members;
	private List<Mention> mentions;

	public Channel(final String channelId, final String channelName) {
		this.id = channelId;
		this.name = channelName;
		this.members = new LinkedList<Member>();
		this.mentions = new LinkedList<Mention>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LinkedList<Member> getMembers() {
		return (LinkedList<Member>) this.members;
	}

	public LinkedList<Mention> getMentions() {
		return (LinkedList<Mention>) this.mentions;
	}

	public boolean containsMention(final Mention m) {
		ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) mentions.iterator();
		while (mentionsIterator.hasNext()) {
			Mention curr = mentionsIterator.next();
			if (curr.equals(m)) {
				return true;
			}
		}
		return false;
	}
}
