package it.uniba.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe che modella un singolo member.
 */
public final class Member {
	/**
	 * Id del member corrente.
	 */
	private String id;
	/**
	 * nome del member corrente.
	 */
	private String name;
	/**
	 * RealName del member corrente.
	 */
	private String realName;
	/**
	 * DisplayName del member corrente.
	 */
	private String displayName;
	/**
	 * Lista di channels in cui è presente il member corrente.
	 */
	private List<Channel> channels;

	/**
	 * Metodo costruttore della classe Member. Permette di create oggetti istanze
	 * della classe Member.
	 * 
	 * @param memberId
	 *            String che rappresenta l'id del member corrente.
	 * @param memberName
	 *            String che rappresenta il nome del member corrente.
	 * @param memberRealName
	 *            String che rappresenta il realName del member corrente.
	 * @param memberDisplayName
	 *            String che rappresenta il displayName del member corrente.
	 */
	public Member(final String memberId, final String memberName, final String memberRealName,
			final String memberDisplayName) {
		this.id = memberId;
		this.name = memberName;
		this.realName = memberRealName;
		this.displayName = memberDisplayName;
		this.channels = new LinkedList<Channel>();
	}

	/**
	 * Restituisce l'id del member corrente.
	 * 
	 * @return String che rappresenta l'id del member corrente.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Restituisce il nome del member corrente.
	 * 
	 * @return String che rappresenta il nome del member corrente.
	 */
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

	/**
	 * Verifica che la stringa passatagli identifica un member realmente esistente.
	 * 
	 * @param member
	 *            String che rappresenta un possibile member.
	 * @return boolean che ha valore true se la stringa passatagli in input
	 *         identifica un member esistente, false altrimenti.
	 */
	public boolean isUser(final String member) {
		return displayName.equals(member) || realName.equals(member) || name.equals(member) || id.equals(member);
	}

	/**
	 * Restituisce il nome del member corrente.
	 * 
	 * @return String che rappresenta il nome del member corrente.
	 */
	public String getUserName() {
		return name;
	}

	/**
	 * Restituisce la lista dei channel in cui è presente il member corrente.
	 * 
	 * @return riferimento ad una LinkedList<Channel> che rappresenta la lista dei
	 *         channel in cui è presente il member corrente.
	 */
	public LinkedList<Channel> getChannels() {
		return (LinkedList<Channel>) channels;
	}
}
