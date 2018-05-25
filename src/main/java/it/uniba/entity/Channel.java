package it.uniba.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Classe che modella un singolo channel.
 */
public final class Channel {
	/**
	 * Id del channel corrente.
	 */
	private final String uID;
	/**
	 * Nome del channel corrente.
	 */
	private final String name;
	/**
	 * Lista dei members del channel corrente.
	 */
	private final List<Member> members;
	/**
	 * Lista delle metions effettuate nel channel corrente.
	 */
	private final List<Mention> mentions;

	/**
	 * Metodo costruttore della classe Channel che permette di creare oggetti
	 * istanze della classe Channel.
	 * 
	 * @param channelId
	 *            String che rappresenta l'id del channel corrente.
	 * @param channelName
	 *            String che rappresenta il nome del channel corrente.
	 */
	public Channel(final String channelId, final String channelName) {
		this.uID = channelId;
		this.name = channelName;
		this.members = new LinkedList<Member>();
		this.mentions = new LinkedList<Mention>();
	}

	/**
	 * Restituisce l'id del channel corrente.
	 * 
	 * @return String che rappresenta l'id del channel corrente.
	 */
	public String getId() {
		return uID;
	}

	/**
	 * Restituisce il nome del channel corrente.
	 * 
	 * @return String che rappresenta il nome del channel corrente.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Restituisce la lista dei members del channel corrente.
	 * 
	 * @return riferimento ad una LinkedList<Member> che rappresenta la lista di
	 *         members del channel corrente.
	 */
	public LinkedList<Member> getMembers() {
		return (LinkedList<Member>) this.members;
	}

	/**
	 * Restituisce la lista di mention effettuate nel channel corrente.
	 * 
	 * @return riferimento ad una LinkedList<Mention> che rappresenta la lista di
	 *         mention effettuate all'interno del channel corrente.
	 */
	public LinkedList<Mention> getMentions() {
		return (LinkedList<Mention>) this.mentions;
	}

	/**
	 * Verifica la presenza di una particolare mention all'interno del channel
	 * corrente.
	 * 
	 * @param mentionIn
	 *            riferimento ad un oggetto istanza della classe Mention che
	 *            rappresenta la mention di cui si vuole verificare la presenza
	 *            all'interno del channel corrente.
	 * @return boolean che risulta essere true se la mention in input � presente
	 *         all'interno del channel, false altrimenti.
	 */
	public boolean containsMention(final Mention mentionIn) {
		final ListIterator<Mention> mentionsIterator = (ListIterator<Mention>) mentions.iterator();
		while (mentionsIterator.hasNext()) {
			final Mention curr = mentionsIterator.next();
			if (curr.equals(mentionIn)) {
				return true;
			}
		}
		return false;
	}
}
