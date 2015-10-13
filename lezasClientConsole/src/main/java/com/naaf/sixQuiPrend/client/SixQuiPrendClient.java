package com.naaf.sixQuiPrend.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import com.naaf.sixQuiPrend.core.Carte;
import com.naaf.sixQuiPrend.core.InfoJoueurs;
import com.naaf.sixQuiPrend.core.InteractionIF;
import com.naaf.sixQuiPrend.core.JoueurIF;
import com.naaf.sixQuiPrend.core.Plateau;
import com.naaf.sixQuiPrend.exception.SixQuiPrendException;

public class SixQuiPrendClient extends UnicastRemoteObject implements SixQuiPrendClientIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Plateau plateau;
	private JoueurIF joueur;
	private InteractionIF ihmInteraction;
	private List<InfoJoueurs> infoJoueurs;

	public SixQuiPrendClient(JoueurIF joueur, InteractionIF ihmInteraction) throws RemoteException {
		super();
		this.joueur = joueur;
		this.ihmInteraction = ihmInteraction;
		this.plateau = new Plateau();
		infoJoueurs = new ArrayList<InfoJoueurs>();
	}

	@Override
	public Carte joue() {
		int choix = ihmInteraction.selectionneCarte(0, joueur.getCartes().size() - 1);
		return joueur.getCarte(choix);
	}

	@Override
	public int selectionRangee() {
		return ihmInteraction.selectionneRangee();
	}

	@Override
	public void initCartes(List<Carte> cartes) {
		joueur.setCartes(cartes);
	}

	@Override
	public void addCarteRamasse(List<Carte> cartes) {
		joueur.addCarteRamasse(cartes);
	}

	@Override
	public void setPlateau(Plateau p) {
		this.plateau.setPlateau(p);
		ihmInteraction.updatePlateau(p);
	}

	public InteractionIF getIhmInteraction() {
		return ihmInteraction;
	}

	public void setIhmInteraction(InteractionIF ihmInteraction) {
		this.ihmInteraction = ihmInteraction;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public JoueurIF getJoueur() {
		return joueur;
	}

	public int getNbBoeufLigne(int ligne) throws SixQuiPrendException {
		return plateau.getNbBoeufLigne(ligne);
	}

	@Override
	public String getLogin() {
		return joueur.getName();
	}

	@Override
	public List<Carte> getCarteRamasse() throws RemoteException {
		return getCarteRamasse();
	}

	@Override
	public void setInfoJoueurs(List<InfoJoueurs> infoJoueurs) throws RemoteException {
		this.infoJoueurs = infoJoueurs;
		ihmInteraction.updateInfoJoueurs(this.infoJoueurs);
	}

	@Override
	public int getScore() throws RemoteException {
		return joueur.getScore();
	}

	public List<Carte> getCartes() {
		return joueur.getCartes();
	}

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((joueur == null) ? 0 : joueur.getName().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    SixQuiPrendClient other = (SixQuiPrendClient) obj;
    if (joueur == null)
    {
      if (other.joueur != null) return false;
    }
    else if (!joueur.getName().equals(other.joueur.getName())) return false;
    return true;
  }

	
	
}
