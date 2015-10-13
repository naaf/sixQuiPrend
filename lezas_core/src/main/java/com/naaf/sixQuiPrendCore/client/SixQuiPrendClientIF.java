package com.naaf.sixQuiPrendCore.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.naaf.sixQuiPrend.core.Carte;
import com.naaf.sixQuiPrend.core.InfoJoueurs;
import com.naaf.sixQuiPrend.core.Plateau;

public interface SixQuiPrendClientIF extends Remote
{
  Carte joue() throws RemoteException;

  int selectionRangee() throws RemoteException;

  void initCartes(List<Carte> cartes) throws RemoteException;

  void addCarteRamasse(List<Carte> cartes) throws RemoteException;

  List<Carte> getCarteRamasse() throws RemoteException;

  void setPlateau(Plateau p) throws RemoteException;

  String getLogin() throws RemoteException;

  default void msg(String s) throws RemoteException
  {
    System.out.println(s);
  }

  int getScore() throws RemoteException;

  void setInfoJoueurs(List<InfoJoueurs> infoJoueurs) throws RemoteException;
}
