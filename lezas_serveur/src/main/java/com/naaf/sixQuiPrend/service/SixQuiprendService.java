package com.naaf.sixQuiPrend.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.naaf.sixQuiPrend.client.SixQuiPrendClientIF;
import com.naaf.sixQuiPrend.core.InfoPartie;
import com.naaf.sixQuiPrend.exception.SixQuiPrendException;
import com.naaf.sixQuiPrend.i18n.I18n;

public class SixQuiprendService extends UnicastRemoteObject implements SixQuiPrendServiceIF, PartieListener
{

  private static final Logger logger = LogManager.getLogger(Partie.class);
  private static final ResourceBundle i18n = I18n.getI18n();
  private static final long serialVersionUID = 1L;

  private Map<String, Partie> mapPartie;

  public SixQuiprendService() throws RemoteException
  {
    mapPartie = new HashMap<>();
  }

  public boolean joindrePartie(String nom, SixQuiPrendClientIF joueur) throws RemoteException
  {
    
    logger.info(i18n.getString("join_party"), joueur.getLogin(), nom);
    if (mapPartie.containsKey(nom))
    {
      return mapPartie.get(nom).addJoueur(joueur);
    }
    return false;
  }

  @Override
  public void quitterPartie(String nom) throws RemoteException
  {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean creePartie(SixQuiPrendClientIF c, String nom, int nbJoueurDemande, int... bornes)
      throws RemoteException, SixQuiPrendException
  {
    
    if (mapPartie.containsKey(nom))
    {
      return false;
    }
    List<SixQuiPrendClientIF> joueurs = new ArrayList<SixQuiPrendClientIF>();
    joueurs.add(c);
    Partie partie = new Partie(joueurs, nom, nbJoueurDemande, bornes);
    partie.setPartieListener(this);
    mapPartie.put(nom, partie);
    logger.info(i18n.getString("create_party"),c.getLogin(),nom);
    return true;
  }

  @Override
  public List<InfoPartie> infoParties()
  {
    return mapPartie.entrySet().stream().map(Map.Entry::getValue).map(Partie::getInfoPartie)
        .collect(Collectors.toList());

    // amelioration
  }

  @Override
  public InfoPartie getNbJoueurPartie(String nom)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void nbJoueurComplete(String nomPartie)
  {
    try
    {
      mapPartie.get(nomPartie).setState(PartieState.EN_JEU);
      mapPartie.get(nomPartie).start();
    }
    catch (SixQuiPrendException e)
    {
      e.printStackTrace();
    }
    // TODO Auto-generated method stub
  }

}
