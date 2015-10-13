package com.naaf.sixQuiPrend.ihm;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import com.naaf.sixQuiPrend.client.SixQuiPrendClient;
import com.naaf.sixQuiPrend.core.Carte;
import com.naaf.sixQuiPrend.core.InfoJoueurs;
import com.naaf.sixQuiPrend.core.InfoPartie;
import com.naaf.sixQuiPrend.core.InteractionIF;
import com.naaf.sixQuiPrend.core.Joueur;
import com.naaf.sixQuiPrend.core.Plateau;
import com.naaf.sixQuiPrend.exception.SixQuiPrendException;
import com.naaf.sixQuiPrend.service.SixQuiPrendServiceIF;

public class SixQuiPrendConsole implements InteractionIF
{
  private SixQuiPrendClient sixQuiPrendClient = null;
  private SixQuiPrendServiceIF serviceSixQuiPrend = null;
  static final int CARTE_INIT = 4;

  public SixQuiPrendConsole(SixQuiPrendServiceIF serviceSixQuiPrend) throws RemoteException
  {
    super();
    this.serviceSixQuiPrend = serviceSixQuiPrend;
    String nom = saisiNom("entrez votre nom :", x -> false);
    Joueur j = new Joueur(nom);
    this.sixQuiPrendClient = new SixQuiPrendClient(j, this);
  }

  private void titre()
  {
    System.out.println("+*************************************************************+");
    System.out.println("*                                                             *");
    System.out.println("*                       SIX QUI PREND                         *");
    System.out.println("*                                                             *");
    System.out.println("+*************************************************************+");
  }

  private void menu()
  {
    System.out.println("Menu");
    System.out.println("1) Liste des parties");
    System.out.println("2) Créer une partie");
    System.out.println("3) Joindre une partie");
    System.out.println("4) Quitter");
  }

  private void listePartie(List<InfoPartie> infoPartie)
  {
    System.out.println("Liste des parties");
    System.out.println("______________________________________________________________");
    System.out.println("      nom partie          |   nombre de joueurs               |");
    System.out.println("__________________________|___________________________________|");
    infoPartie.forEach(infoP -> {
      String total = infoP.getNbJoueurDemande() == 0 ? "" : "/ " + infoP.getNbJoueurDemande();
      System.out.printf("%20s      |     %3d %5s\n", infoP.getNom(), infoP.getNbJoueureEffectif(), total);
    });
    System.out.println("");
    System.out.println("_______________________________________________________________");
  }

  // ----------------------------------------------------------------------------
  @Override
  public void updatePlateau(Plateau plateau)
  {
    try
    {
      System.out.println("*Ligne|nb_boeuf|  C1     |  C2     |  C3     |  C4     |  C5     |  C6     |");
      System.out.println("*-----|--------|-----------------------------------------------------------|");

      for (int i = 0; i < CARTE_INIT; i++)
      {
        System.out.printf("* [%d] |", i);
        System.out.printf("  %3d   |", plateau.getNbBoeufLigne(i));
        for (Carte c : plateau.getLigne(i))
        {
          System.out.printf("%8s |", c);
        }
        System.out.println("");
      }

      System.out.println("+**************************************************************************+");
    }
    catch (SixQuiPrendException e)
    {
      e.printStackTrace();
    }

  }

  @Override
  public void updateInfoJoueurs(List<InfoJoueurs> infoJoueurs)
  {
    System.out.println("+**************************+");
    System.out.println("| Joueur   |    Score      |");
    System.out.println("+**************************+");
    infoJoueurs.stream().forEach(info -> System.out.printf("| %7s |    %5d      |\n", info.getName(), info.getScore()));
  }

  private int inputEntier(String msg, int debut, int fin)
  {
    int choixCarte = -1;
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(System.in);
    try
    {
      do
      {
        System.out.print(msg);
        choixCarte = sc.nextInt();
        if (choixCarte < debut || choixCarte > fin)
        {
          throw new IndexOutOfBoundsException();
        }
      }
      while (choixCarte < debut || choixCarte > fin);
    }
    catch (IndexOutOfBoundsException e)
    {
      System.out.println("Le nombre doit être ENTRE [" + debut + ", " + fin + "]");
    }
    catch (Exception e)
    {
      System.out.println("Veuillez entre un NOMBRE");
      sc.nextLine();
    }

    return choixCarte;
  }

  @Override
  public int selectionneCarte(int debut, int fin)
  {
    String msg = "Veuillez choisir une Carte entre [" + debut + ", " + fin + "] : ";
    System.out.println(sixQuiPrendClient.getCartes());
    return inputEntier(msg, debut, fin);
  }

  @Override
  public int selectionneRangee()
  {
    String msg = "Veuillez choisir une rangée entre [" + 0 + ", " + 3 + "] : ";
    return inputEntier(msg, 0, 3);
  }

  public SixQuiPrendClient getSixQuiPrendClient()
  {
    return sixQuiPrendClient;
  }

  public void setSixQuiPrendClient(SixQuiPrendClient sixQuiPrendClient)
  {
    this.sixQuiPrendClient = sixQuiPrendClient;
  }

  public void start() throws SixQuiPrendException, RemoteException
  {
    titre();
    menu();
    int choix = 0;
    do
    {
      choix = inputEntier("Votre choix entre [1-4] :", 1, 4);
      handleChoix(choix);
    }
    while (choix < 2);
  }

  private void creerPartie(List<InfoPartie> infoParties) throws RemoteException, SixQuiPrendException
  {
    Integer nb;
    listePartie(infoParties);
    String nom = saisiNom("nom de la partie : ", x -> contain(infoParties, x));

    nb = inputEntier("\nnb de joueurs min [2-10] :", 2, 10);

    System.out.printf("Partie %s est créé attend %s joueurs\n", nom, Integer.toString(nb));
    serviceSixQuiPrend.creePartie(sixQuiPrendClient, nom, nb);

  }

  private boolean contain(List<InfoPartie> infoPartie, final String nom)
  {
    return infoPartie.stream().map(InfoPartie::getNom).anyMatch(n -> nom.compareTo(n) == 0);
  }

  @SuppressWarnings("resource")
  private String saisiNom(String msg, Predicate<String> cond)
  {
    String nom = "";
    
    Scanner sc = new Scanner(System.in);
    do
    {
      System.out.print(msg);
      nom = sc.nextLine();
    }
    while (cond.test(nom));
    return nom;
  }

  private void joindrePartie(List<InfoPartie> infoParties) throws RemoteException
  {
    System.out.println("***joindre une partie***");
    listePartie(infoParties);
    String nom = saisiNom(" nom de la partie à joindre :", x -> !contain(infoParties, x));
    int nb = infoParties.stream().filter(p -> p.getNom().equals(nom)).findFirst().get().getNbJoueurDemande();

    System.out.printf("Vous avez joins la partie %s en attend %s joueurs\n", nom, nb == 0 ? "n" : Integer.toString(nb));
    serviceSixQuiPrend.joindrePartie(nom, sixQuiPrendClient);
  }

  private void handleChoix(int choix) throws RemoteException, SixQuiPrendException
  {
    List<InfoPartie> listInfoPartie = serviceSixQuiPrend.infoParties();
    switch (choix)
    {
      case 1:
        listePartie(listInfoPartie);
        break;
      case 2:
        creerPartie(listInfoPartie);
        break;
      case 3:
        joindrePartie(listInfoPartie);
        break;
      case 4:
        // TODO quitter proprement
        System.out.println("Au revoir");
        System.exit(0);
        break;
      default:
    }
  }

}
