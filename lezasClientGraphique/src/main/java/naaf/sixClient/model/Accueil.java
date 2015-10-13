package naaf.sixClient.model;

import javafx.collections.ObservableList;

public class Accueil
{
  private String nom;
  private ObservableList<PartieInfo> partiesTermine;
  private ObservableList<PartieInfo> listePartie;

  public Accueil()
  {}

  public Accueil(String nom, ObservableList<PartieInfo> partiesTermine, ObservableList<PartieInfo> listePartie)
  {
    super();
    this.nom = nom;
    this.partiesTermine = partiesTermine;
    this.listePartie = listePartie;
  }

  public String getNom()
  {
    return nom;
  }

  public ObservableList<PartieInfo> getPartiesTermine()
  {
    return partiesTermine;
  }

  public ObservableList<PartieInfo> getListePartie()
  {
    return listePartie;
  }

  public void setNom(String nom)
  {
    this.nom = nom;
  }

  public void setPartiesTermine(ObservableList<PartieInfo> partiesTermine)
  {
    this.partiesTermine = partiesTermine;
  }

  public void setListePartie(ObservableList<PartieInfo> listePartie)
  {
    this.listePartie = listePartie;
  }

  private boolean add(ObservableList<PartieInfo> list, PartieInfo p)
  {
    if (!list.contains(p))
    {
      return list.add(p);
    }
    return false;
  }

  public boolean addInitPartie(PartieInfo p)
  {
    return add(listePartie, p);
  }

  public boolean addEndPartie(PartieInfo p)
  {
    return add(partiesTermine, p);
  }

}
