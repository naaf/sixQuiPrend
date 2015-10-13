package com.naaf.sixQuiPrend.core;

import java.util.List;

public interface InteractionIF {

	int selectionneCarte(int debut, int fin);

	int selectionneRangee();

	void updatePlateau(Plateau p);

	void updateInfoJoueurs(List<InfoJoueurs> infoJoueurs);

}
