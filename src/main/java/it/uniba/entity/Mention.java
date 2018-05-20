package it.uniba.entity;

import it.uniba.entity.Mention;

public class Mention {
	private Member from;
	private Member to;
	private int weight;

	public Mention(Member from, Member to) {
		this.from = from;
		this.to = to;
		this.weight=1;
	}

	public Member getFrom() {
		return from;
	}

	public Member getTo() {
		return to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + weight;
		return result;
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
	
	public String toFullString() {
		return "("+this.getFrom().getName()+", "+this.getTo().getName()+", "+this.getWeight()+")";
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight=weight;
	}
}
