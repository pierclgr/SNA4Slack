package it.uniba.entity;

public final class Mention {
	private Member from;
	private Member to;
	private int weight;

	public Mention(final Member mentionFrom, final Member mentionTo) {
		this.from = mentionFrom;
		this.to = mentionTo;
		this.weight = 1;
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
		if (from == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + from.hashCode();
		}
		if (to == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + to.hashCode();
		}
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		boolean out = false;
		if (obj instanceof Mention && from.equals(((Mention) obj).getFrom()) && to.equals(((Mention) obj).getTo())) {
			out = true;
		}
		return out;
	}

	@Override
	public String toString() {
		return "(" + this.getFrom().getName() + ", " + this.getTo().getName() + ")";
	}

	public String toFullString() {
		return "(" + this.getFrom().getName() + ", " + this.getTo().getName() + ", " + this.getWeight() + ")";
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(final int mentionWeight) {
		this.weight = mentionWeight;
	}
}
