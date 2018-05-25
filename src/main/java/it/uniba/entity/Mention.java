package it.uniba.entity;

/**
 * Classe che modella una singola mention.
 */
public final class Mention {
	/**
	 * Member da cui viene effettuata la mention corrente.
	 */
	private final Member fromUser;
	/**
	 * Member a cui � riferita la mention corrente.
	 */
	private final Member toUser;
	/**
	 * Peso della mention corrente.
	 */
	private int weight;

	/**
	 * Metodo costruttre della classe Mention che permette di creare oggetti istanze
	 * della classe Mention.
	 * 
	 * @param mentionFrom
	 *            riferimento ad un oggetto istanza della classe Member che
	 *            rappresenta il member da cui � effettuata la mention corrente.
	 * @param mentionTo
	 *            riferimento ad un oggetto istanza della classe Member che
	 *            rappresenta il member a cui � riferita la mention corrente.
	 */
	public Mention(final Member mentionFrom, final Member mentionTo) {
		this.fromUser = mentionFrom;
		this.toUser = mentionTo;
		this.weight = 1;
	}

	/**
	 * Restituisce il member da cui � effettuata la mention corrente.
	 * 
	 * @return riferimento ad un oggetto istanza della classe Member che rappresenta
	 *         il member da cui � effettuata la mention corrente.
	 */
	public Member getFrom() {
		return fromUser;
	}

	/**
	 * Restituisce il member a cui � riferita la mention corrente.
	 * 
	 * @return riferimento ad un oggetto istanza della classe Member che rappresenta
	 *         il member a cui � riferita la mention corrente.
	 */
	public Member getTo() {
		return toUser;
	}

	/**
	 * Override del metodo della superclasse Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (fromUser == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + fromUser.hashCode();
		}
		if (toUser == null) {
			result = prime * result + 0;
		} else {
			result = prime * result + toUser.hashCode();
		}
		result = prime * result + weight;
		return result;
	}

	/**
	 * Verifica se la mention corrente e la mention passata in input sono uguali.
	 * 
	 * @return boolean che risulta essere true se la mention corrente e la mention
	 *         in input sono uguali.
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean out = false;
		if (obj instanceof Mention && fromUser.equals(((Mention) obj).getFrom())
				&& toUser.equals(((Mention) obj).getTo())) {
			out = true;
		}
		return out;
	}

	/**
	 * Restituisce una rappresentazione parziale sottoforma di stringa della mention
	 * corrente escludendo il peso.
	 * 
	 * @return String che rappresenta il member da cui parte la mention corrente ed
	 *         il member a cui � riferita la mention corrente.
	 */
	@Override
	public String toString() {
		return "(" + this.getFrom().getName() + ", " + this.getTo().getName() + ")";
	}

	/**
	 * Restituisce una rappresentazione completa sottoforma di stringa della mention
	 * corrente includendo anche il peso.
	 * 
	 * @return String che rappresenta lo stato della mention corrente che include il
	 *         member da cui parte la mention, il member a cui � riferita la mention
	 *         e il peso della mention.
	 */
	public String toFullString() {
		return "(" + this.getFrom().getName() + ", " + this.getTo().getName() + ", " + this.getWeight() + ")";
	}

	/**
	 * Restituisce il peso della mention corrente.
	 * 
	 * @return int che rappresenta il peso della mention corrente.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Modifica il peso della mention corrente.
	 * 
	 * @param mentionWeight
	 *            int che rappresenta il valore da assegnare alla mention corrente.
	 */
	public void setWeight(final int mentionWeight) {
		this.weight = mentionWeight;
	}
}
