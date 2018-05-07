package it.uniba.entity;

public class Mention {
	private Member from;
	private Member to;

	public Mention(Member from, Member to) {
		this.from = from;
		this.to = to;
	}

	public Member getFrom() {
		return from;
	}

	public Member getTo() {
		return to;
	}
}
