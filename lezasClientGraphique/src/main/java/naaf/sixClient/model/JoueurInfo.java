package naaf.sixClient.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JoueurInfo
{
  private final StringProperty nom;
  private final IntegerProperty score;

  public JoueurInfo(String nom, int score)
  {
    this.nom = new SimpleStringProperty(nom);
    this.score = new SimpleIntegerProperty(score);
  }

  public StringProperty getNom()
  {
    return nom;
  }

  public IntegerProperty getScore()
  {
    return score;
  }

 
  public void setNom(String nom)
  {
    this.nom.set(nom);
  }

  public void setScore(int score)
  {
    this.score.set(score);
  }
  
  
}
