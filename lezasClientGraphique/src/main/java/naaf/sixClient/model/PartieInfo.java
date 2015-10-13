package naaf.sixClient.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PartieInfo
{
  private final StringProperty nom;
  private final IntegerProperty nbJoueurEffectif;
  private final IntegerProperty nbJoueurRequis;
  private final IntegerProperty score;
  private final BooleanProperty end;

  public PartieInfo(String nom, int nbRequis)
  {
    this.nom = new SimpleStringProperty(nom);
    this.nbJoueurEffectif = new SimpleIntegerProperty(1);
    this.nbJoueurRequis = new SimpleIntegerProperty(nbRequis);
    this.end = new SimpleBooleanProperty(false);
    this.score = new SimpleIntegerProperty(0);
  }

  public PartieInfo()
  {
    this.nom = new SimpleStringProperty("Partie12");
    this.nbJoueurEffectif = new SimpleIntegerProperty(1);
    this.nbJoueurRequis = new SimpleIntegerProperty(10);
    this.end = new SimpleBooleanProperty(true);
    this.score = new SimpleIntegerProperty(1234);
  }

  public StringProperty getNom()
  {
    return nom;
  }

  public Integer getNbJoueurEffectif()
  {
    return nbJoueurEffectif.get();
  }

  public Integer getNbJoueurRequis()
  {
    return nbJoueurRequis.get();
  }

  public Boolean isEnd()
  {
    return end.getValue();
  }

  public void setNom(String nom)
  {
    this.nom.set(nom);
  }

  public void setNbJoueurEffectif(Integer nbJoueurEffectif)
  {
    this.nbJoueurEffectif.set(nbJoueurEffectif);
  }

  public void setNbJoueurRequis(Integer nbJoueurRequis)
  {
    this.nbJoueurRequis.set(nbJoueurRequis);
  }

  public IntegerProperty getScore()
  {
    return score;
  }

  public void setEnd(Boolean end)
  {
    this.end.set(end);
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nom == null) ? 0 : nom.get().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    PartieInfo other = (PartieInfo) obj;
    if (nom == null)
    {
      if (other.nom != null) return false;
    }
    else if (!nom.get().equals(other.nom.get())) return false;
    return true;
  }

  

}
