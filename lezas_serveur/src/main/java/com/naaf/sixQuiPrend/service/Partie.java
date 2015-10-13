package com.naaf.sixQuiPrend.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.naaf.sixQuiPrend.client.SixQuiPrendClientIF;
import com.naaf.sixQuiPrend.core.Carte;
import com.naaf.sixQuiPrend.core.InfoJoueurs;
import com.naaf.sixQuiPrend.core.InfoPartie;
import com.naaf.sixQuiPrend.core.Plateau;
import com.naaf.sixQuiPrend.exception.SixQuiPrendException;
import com.naaf.sixQuiPrend.i18n.I18n;

public class Partie implements PartieIF {
	private static final Logger logger = LogManager.getLogger(Partie.class);
	private static final ResourceBundle i18n = I18n.getI18n();
	private Plateau plateau;
	private List<Carte> paquet;
	private List<SixQuiPrendClientIF> sixQuiPrendClients;
	private List<InfoJoueurs> infoJoueurs;
	private InfoPartie infoPartie;
	private PartieState state;
	private int[] bornes;

	public Partie(List<SixQuiPrendClientIF> sixQuiPrendClients, String nom, int nbJoueurDemande, int... bornes)
			throws SixQuiPrendException {

		this.sixQuiPrendClients = new ArrayList<SixQuiPrendClientIF>();
		this.sixQuiPrendClients.addAll(sixQuiPrendClients);
		infoJoueurs = new ArrayList<InfoJoueurs>();
		plateau = new Plateau();
		this.bornes = bornes;
		infoPartie = new InfoPartie(nom, nbJoueurDemande);
		state = PartieState.EN_ATTENTE;
	}

	public PartieState getState() {
		return state;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	private void distributionCarte() throws SixQuiPrendException, RemoteException {

		for (int i = 0; i < sixQuiPrendClients.size(); i++) {
			List<Carte> cartesJoueur = new ArrayList<Carte>();
			cartesJoueur.addAll(paquet.subList(i * CARTE_PAR_JOUEUR, i * CARTE_PAR_JOUEUR + CARTE_PAR_JOUEUR));
			sixQuiPrendClients.get(i).initCartes(cartesJoueur);
		}
		List<Carte> cartesPlateau = new ArrayList<Carte>();
		cartesPlateau.addAll(paquet.subList(sixQuiPrendClients.size() * CARTE_PAR_JOUEUR,
				sixQuiPrendClients.size() * CARTE_PAR_JOUEUR + CARTE_INIT));
		plateau.initPlateau(cartesPlateau);
	}

	public void tour() throws RemoteException {
		HashMap<Integer, Carte> cartesJoue = new HashMap<>();
		int nbJoueur = sixQuiPrendClients.size();
		for (int i = 0; i < nbJoueur; i++) {
			cartesJoue.put(i, sixQuiPrendClients.get(i).joue());
		}
		setCartesJoueurs(cartesJoue);
	}

	private void setCartesJoueurs(HashMap<Integer, Carte> hmap) throws RemoteException {
		hmap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(tuple -> {
			List<Carte> cartesRamasses = null;
			int score = 0;
			try {
				if (tuple.getValue().getId() < plateau.getNbInfCarte()) {
					int index;

					index = sixQuiPrendClients.get(tuple.getKey()).selectionRangee();

					score = plateau.getNbBoeufLigne(index);

					cartesRamasses = plateau.replaceRange(tuple.getValue(), index);
					sixQuiPrendClients.get(tuple.getKey()).addCarteRamasse(cartesRamasses);
				} else {
					if ((cartesRamasses = plateau.addCarte(tuple.getValue())) != null) {
						score = cartesRamasses.stream().mapToInt(Carte::getNbTeteBoeuf).sum();
						sixQuiPrendClients.get(tuple.getKey()).addCarteRamasse(cartesRamasses);
					}
				}

				infoJoueurs.get(tuple.getKey()).add(score);
			} catch (SixQuiPrendException e) {
				e.printStackTrace();
				// TODO
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});

	}

	private List<Carte> generatePaquetCarte(int nbJoueur, int... bornes) throws SixQuiPrendException {
		if (nbJoueur < MIN_NB_JOUEUR || nbJoueur > MAX_NB_JOUEUR) {
			throw new SixQuiPrendException("nb joueurs =" + nbJoueur + " doit etre [ 2-10 ] ");
		}

		int borneInf = bornes.length >= 1 ? bornes[0] : MIN_CARTE;
		int borneSup = bornes.length >= 2 ? bornes[1] : MAX_CARTE;
		int ecart = borneSup - borneInf + MIN_CARTE;
		int nbCarteOpt = (nbJoueur * CARTE_PAR_JOUEUR + CARTE_INIT);

		if (ecart < nbCarteOpt) {
			throw new SixQuiPrendException("le nb de cartes : " + ecart + " doit etre superieur: " + nbCarteOpt);
		}

		Random rand = new Random();
		int k = 0;
		List<Integer> lInt = new ArrayList<Integer>();
		for (int i = 0; i < nbCarteOpt; i++) {
			k = rand.nextInt(ecart) + borneInf;
			if (lInt.contains(k)) {
				i--;
			} else {
				lInt.add(k);
			}
		}
		return constructionCarte(lInt);
	}

	private List<Carte> constructionCarte(List<Integer> listId) {
		List<Carte> cartes = new ArrayList<>();
		int teteBoeuf = 0;
		for (int i : listId) {
			if (i % 10 == 0) {
				teteBoeuf = 3;
			} else if (i % 5 == 0 && i % 11 == 0) {
				teteBoeuf = 7;
			} else if (i % 5 == 0) {
				teteBoeuf = 2;
			} else if (i % 11 == 0) {
				teteBoeuf = 5;
			} else {
				teteBoeuf = 1;
			}
			cartes.add(new Carte(i, teteBoeuf));
		}
		return cartes;

	}

	public boolean addJoueur(SixQuiPrendClientIF joueur) {
		return sixQuiPrendClients.add(joueur) && infoPartie.incremente() ;
	}

	public List<SixQuiPrendClientIF> getSixQuiPrendClients() {
		return sixQuiPrendClients;
	}

	public InfoPartie getInfoPartie() {
		return infoPartie;
	}

	@Override
	public int getNbBoeufLigne(int ligne) throws SixQuiPrendException {
		return plateau.getNbBoeufLigne(ligne);
	}

	@Override
	public void start() throws SixQuiPrendException {

		paquet = generatePaquetCarte(sixQuiPrendClients.size(), bornes);
		try {
			state = PartieState.EN_JEU;
			for (int i = 0; i < sixQuiPrendClients.size(); i++) {
				sixQuiPrendClients.get(i).msg("Partie Commence");
				infoJoueurs.add(new InfoJoueurs(sixQuiPrendClients.get(i).getLogin()));
			}
			logger.info(i18n.getString("start_party"),
					infoJoueurs.stream().map(InfoJoueurs::getName).collect(Collectors.toList()));

			distributionCarte();
			for (int i = 0; i < NB_TOUR; i++) {
				for (SixQuiPrendClientIF cl : sixQuiPrendClients) {
					cl.setPlateau(plateau);
					cl.setInfoJoueurs(infoJoueurs);

				}
				tour();
			}

			for (SixQuiPrendClientIF cl : sixQuiPrendClients) {
				cl.msg("Partie Terminé");
				cl.setPlateau(plateau);
				cl.setInfoJoueurs(infoJoueurs);
			}
			logger.info(i18n.getString("end_party"), infoJoueurs);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void setState(PartieState state) {
		this.state = state;
	}

	public void setPartieListener(PartieListener listener) {
		infoPartie.setPartieListener(listener);
	}

	@Override
	public void restart() {

	}
}
