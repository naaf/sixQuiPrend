package naaf.sixClient.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import naaf.sixClient.exception.SixQuiPrendException;

public class Plateau implements Serializable
{

  private static final long serialVersionUID = 1L;
  private final static int SIZE_INIT_CARTE = 4;
  private final static int MAX_CARTE_PAR_RANGEE = 5;
  private List<LinkedList<Carte>> plateauCarte;
  private List<Carte> derniereClonne;

  public Plateau()
  {}

  public void initPlateau(List<Carte> fourCarte) throws SixQuiPrendException
  {
    if (fourCarte.size() != SIZE_INIT_CARTE)
    {
      throw new SixQuiPrendException("il faut quatre carte");
    }
    plateauCarte = new ArrayList<LinkedList<Carte>>();
    derniereClonne = new ArrayList<>();
    derniereClonne.addAll(fourCarte);
    for (int i = 0; i < SIZE_INIT_CARTE; i++)
    {
      LinkedList<Carte> list = new LinkedList<>();
      list.add(fourCarte.get(i));
      plateauCarte.add(list);
    }
  }

  public int getNbBoeufLigne(int ligne) throws SixQuiPrendException
  {
    if (ligne >= SIZE_INIT_CARTE || ligne < 0)
    {
      throw new SixQuiPrendException(ligne, "ligne de être entre [0-3]");
    }
    return plateauCarte.get(ligne).stream().mapToInt(Carte::getNbTeteBoeuf).sum();
  }

  public List<Carte> addCarte(Carte carte) throws SixQuiPrendException
  {
    if (getNbInfCarte() > carte.getId())
    {
      throw new SixQuiPrendException("carte inferieur :" + carte);
    }

    int indiceLigne = 0;
    int ecart = Integer.MAX_VALUE;
    int ecartCourant = 0;

    for (int i = 0; i < SIZE_INIT_CARTE; i++)
    {
      ecartCourant = carte.getId() - derniereClonne.get(i).getId();
      if (ecartCourant > 0 && ecartCourant < ecart)
      {
        ecart = ecartCourant;
        indiceLigne = i;
      }
    }
    List<Carte> listReturn = null;

    if (plateauCarte.get(indiceLigne).size() == MAX_CARTE_PAR_RANGEE)
    {
      listReturn = new LinkedList<>();
      listReturn.addAll(plateauCarte.get(indiceLigne));
      plateauCarte.get(indiceLigne).clear();
    }

    plateauCarte.get(indiceLigne).addLast(carte);
    derniereClonne.set(indiceLigne, carte);
    return listReturn;
  }

  public List<Carte> replaceRange(Carte carte, int ligne) throws IndexOutOfBoundsException
  {
    if (0 > ligne || ligne >= SIZE_INIT_CARTE)
    {
      throw new IndexOutOfBoundsException("ligne=" + ligne + " doit etre [ 0-3 ] ");
    }
    LinkedList<Carte> listReturn = new LinkedList<>();
    listReturn.addAll(plateauCarte.get(ligne));
    plateauCarte.get(ligne).clear();
    plateauCarte.get(ligne).addLast(carte);
    derniereClonne.set(ligne, carte);
    return listReturn;
  }

  public int getNbInfCarte()
  {
    return derniereClonne.stream().mapToInt(Carte::getId).min().getAsInt();
  }

  public List<Carte> getLigne(int ligne)
  {

    return plateauCarte.get(ligne);
  }

  public List<LinkedList<Carte>> getPlateauCarte()
  {
    return plateauCarte;
  }

  public List<Carte> getDerniereClonne()
  {
    return derniereClonne;
  }

  public void setPlateau(Plateau plateau)
  {
    if (plateau != null)
    {
      if (plateauCarte == null || derniereClonne == null)
      {
        plateauCarte = new ArrayList<LinkedList<Carte>>();
        derniereClonne = new ArrayList<>();
        this.plateauCarte.addAll(plateau.getPlateauCarte());
        this.derniereClonne.addAll(plateau.getDerniereClonne());
      }
      else
      {
        this.plateauCarte.clear();
        this.plateauCarte.addAll(plateau.getPlateauCarte());
        this.derniereClonne.clear();
        this.derniereClonne.addAll(plateau.getDerniereClonne());
      }
    }
  }

}
