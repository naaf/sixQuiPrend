package naaf.sixClient.interaction;

import java.util.List;
import naaf.sixClient.core.InfoJoueurs;
import naaf.sixClient.core.Plateau;

public interface InteractionIF
{

  int selectionneCarte(int debut, int fin);

  int selectionneRangee();

  void updatePlateau(Plateau p);

  void updateInfoJoueurs(List<InfoJoueurs> infoJoueurs);

}
