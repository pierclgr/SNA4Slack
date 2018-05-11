package it.uniba.entity;

import it.uniba.entity.Mention;

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

	@Override
	public boolean equals(Object obj) {
		boolean out= false;
		if(obj instanceof Mention&&from.equals(((Mention) obj).getFrom())&&to.equals(((Mention) obj).getTo())) {
			out= true;
		}
		return out;
	}

	@Override
	public String toString() {
		return "("+this.getFrom().getName()+", "+this.getTo().getName()+")";
	}
}
