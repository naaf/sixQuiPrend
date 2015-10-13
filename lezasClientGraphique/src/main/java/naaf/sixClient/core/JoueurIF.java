package naaf.sixClient.core;

import java.util.List;

public interface JoueurIF
{
  Carte getCarte(int indice);

  void setCartes(List<Carte> cartes);

  List<Carte> getCartes();

  void addCarteRamasse(List<Carte> cartes);

  int getScore();

  String getName();

  void compabiliseScore();
}
