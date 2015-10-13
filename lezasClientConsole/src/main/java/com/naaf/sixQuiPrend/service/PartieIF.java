package com.naaf.sixQuiPrend.service;

import com.naaf.sixQuiPrend.core.Plateau;
import com.naaf.sixQuiPrend.exception.SixQuiPrendException;

public interface PartieIF {
	static final int MIN_NB_JOUEUR = 2;
	static final int MAX_NB_JOUEUR = 10;
	static final int CARTE_PAR_JOUEUR = 10;
	static final int NB_TOUR = 10;
	static final int CARTE_INIT = 4;
	static final int NB_JOUEUR_NON_SPECIFIE = 11;
	static final int MIN_CARTE = 1;
	static final int MAX_CARTE = 104;

	Plateau getPlateau();

	public int getNbBoeufLigne(int ligne) throws SixQuiPrendException;

	void start() throws SixQuiPrendException;

	void restart();
	// void terminate();

}
