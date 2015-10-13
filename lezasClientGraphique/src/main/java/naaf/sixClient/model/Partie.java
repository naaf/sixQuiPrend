package naaf.sixClient.model;

import java.util.List;
import naaf.sixClient.core.Joueur;
import naaf.sixClient.core.Plateau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Partie
{
  private ObservableList<JoueurInfo> joueurInfos;
  private Joueur joueur;
  private Plateau plateau;

  public Partie()
  {}

  public ObservableList<JoueurInfo> getJoueurInfos()
  {
    return joueurInfos;
  }

  public Joueur getJoueur()
  {
    return joueur;
  }

  public Plateau getPlateau()
  {
    return plateau;
  }

  public void setJoueurInfos(ObservableList<JoueurInfo> joueurInfos)
  {
    this.joueurInfos = joueurInfos;
  }

  public void setJoueur(Joueur joueur)
  {
    this.joueur = joueur;
  }

  public void setPlateau(Plateau plateau)
  {
    this.plateau = plateau;
  } 
  public void addJoueurInfos(List<JoueurInfo> joueurInfos)
  {
    this.joueurInfos = FXCollections.observableArrayList(joueurInfos);
  }

}
