package com.naaf.sixQuiPrend.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.naaf.sixQuiPrend.client.SixQuiPrendClientIF;
import com.naaf.sixQuiPrend.core.InfoPartie;
import com.naaf.sixQuiPrend.exception.SixQuiPrendException;

public interface SixQuiPrendServiceIF extends Remote {

	public boolean joindrePartie(String nom, SixQuiPrendClientIF c) throws RemoteException;

	public void quitterPartie(String nom) throws RemoteException;

	public boolean creePartie(SixQuiPrendClientIF c, String nom, int nbJoueurDemande, int... bornes)
			throws RemoteException, SixQuiPrendException;

	public List<InfoPartie> infoParties() throws RemoteException;

	/**
	 * 
	 * @param nom
	 * @return -1 si partie n'existe pas ou est en cours de jeu nb de joueurs
	 *         actuel si partie est en attente de joueurs pour demarrer
	 */
	public InfoPartie getNbJoueurPartie(String nom) throws RemoteException;
}
