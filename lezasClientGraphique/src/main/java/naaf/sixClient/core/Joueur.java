package naaf.sixClient.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Joueur implements JoueurIF, Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private List<Carte> carteRamasse;
  private List<Carte> cartes;
  private int score;

  public Joueur(String name)
  {
    this.name = name;
    carteRamasse = new ArrayList<Carte>();
    cartes = new ArrayList<Carte>();
    score = 0;
  }

  public Joueur(String name, List<Carte> cartes)
  {
    super();
    this.name = name;
    this.cartes = cartes;
  }

  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public int getScore()
  {
    compabiliseScore();
    return score;
  }

  public List<Carte> getCarteRamasse()
  {
    return carteRamasse;
  }

  public List<Carte> getCartes()
  {
    return cartes;
  }

  @Override
  public void setCartes(List<Carte> cartes)
  {
    if (cartes != null)
    {
      this.cartes.clear();
      this.cartes.addAll(cartes);
    }
  }

  @Override
  public Carte getCarte(int indice)
  {
    if (cartes.size() <= indice || indice < 0)
    {
      throw new IndexOutOfBoundsException( "de carte est inexistant");
    }
    return cartes.remove(indice);
  }

  @Override
  public void addCarteRamasse(List<Carte> cartes)
  {
    if (cartes != null)
    {
      carteRamasse.addAll(cartes);
      compabiliseScore();
    }
  }

  @Override
  public void compabiliseScore()
  {
    score = carteRamasse.stream().mapToInt(Carte::getNbTeteBoeuf).sum();
  }

}
