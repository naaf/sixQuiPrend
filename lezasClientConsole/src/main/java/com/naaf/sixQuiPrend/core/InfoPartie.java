package com.naaf.sixQuiPrend.core;

import static com.naaf.sixQuiPrend.service.PartieIF.MAX_NB_JOUEUR;
import static com.naaf.sixQuiPrend.service.PartieIF.MIN_NB_JOUEUR;
import static com.naaf.sixQuiPrend.service.PartieIF.NB_JOUEUR_NON_SPECIFIE;
import java.io.Serializable;
import com.naaf.sixQuiPrend.service.PartieListener;

public class InfoPartie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private int nbJoueureEffectif;
	private int nbJoueurDemande;

	private PartieListener listener;

	public InfoPartie() {
	}

	public InfoPartie(String nom, int nbJoueurDemande) {
		super();
		this.nom = nom;
		this.nbJoueurDemande = nbJoueurDemande;
		this.nbJoueureEffectif = 1;
	}

	public InfoPartie(String nom, int nbJoueureEffectif, int nbJoueurDemande) {
		super();
		this.nom = nom;
		this.nbJoueureEffectif = nbJoueureEffectif;
		this.nbJoueurDemande = nbJoueurDemande;
	}

	public void setPartieListener(PartieListener listener) {
		this.listener = listener;
	}

	protected void firePartieListener() {
		listener.nbJoueurComplete(nom);
	}

	public String getNom() {
		return nom;
	}

	public int getNbJoueureEffectif() {
		return nbJoueureEffectif;
	}

	public int getNbJoueurDemande() {
		return nbJoueurDemande;
	}

	public boolean incremente() {
		if (MAX_NB_JOUEUR > nbJoueureEffectif || nbJoueurDemande > nbJoueureEffectif) {
			nbJoueureEffectif++;

			if (NB_JOUEUR_NON_SPECIFIE != nbJoueurDemande && nbJoueureEffectif == nbJoueurDemande) {
				firePartieListener();
			}
			if (NB_JOUEUR_NON_SPECIFIE == nbJoueurDemande && nbJoueureEffectif > MIN_NB_JOUEUR) {
				firePartieListener();
			}
			return true;
		}
		return false;
	}

}
