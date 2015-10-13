package naaf.sixClient.controller;

import java.util.List;
import java.util.Objects;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import naaf.sixClient.core.InfoJoueurs;
import naaf.sixClient.core.Plateau;
import naaf.sixClient.interaction.InteractionIF;
import naaf.sixClient.model.JoueurInfo;
import naaf.sixClient.model.Partie;

public class PartieSixQuiPrendController implements InteractionIF
{

  // carte dimension 78 * 120
  private final static int WITH_CARTE = 78;

  // FXML
  // ---------------------------------------------------------------------------------------------

  @FXML
  private GridPane plateaux;

  @FXML
  private TableColumn<JoueurInfo, Integer> info_score;

  @FXML
  private TableView<JoueurInfo> infoJoueurs;

  @FXML
  private HBox hboxMain;

  private GridPane mainCartes;

  @FXML
  private TableColumn<JoueurInfo, String> info_nom;

  @FXML
  private Label insLabel;

  @FXML
  private Pane ligne1;
  @FXML
  private Pane ligne2;
  @FXML
  private Pane ligne3;
  @FXML
  private Pane ligne4;

  // Attribut Metier
  // ---------------------------------------------------------------------------------------------
  private Partie partie;
  private EventHandler<MouseEvent> eventClickMain;

  // ---------------------------------------------------------------------------------------------

  // ---------------------------------------------------------------------------------------------

  // Fonction FXML
  // ---------------------------------------------------------------------------------------------
  @FXML
  private void initialize()
  {

    insLabel.setText("Partie Commence ...");

    // Initialize the person table with the two columns.
    info_nom.setCellValueFactory(cellData -> cellData.getValue().getNom());
    info_score.setCellValueFactory(cellData -> cellData.getValue().getScore().asObject());

    // mainCartes.setGridLinesVisible(true);
    mainCartes = new GridPane();
    hboxMain.getChildren().add(mainCartes);

    ligne1.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> supprimeLigne(0));
    ligne2.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> supprimeLigne(1));
    ligne3.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> supprimeLigne(2));
    ligne4.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> supprimeLigne(3));

    eventClickMain = new EventHandler<MouseEvent>()
    {

      @Override
      public void handle(MouseEvent e)
      {
        int n = (int) e.getSceneX() / WITH_CARTE;
        if (n < mainCartes.getChildren().size())
        {
          mainCartes.getChildren().remove(n);
        }
      }
    };

    mainCartes.addEventFilter(MouseEvent.MOUSE_CLICKED, eventClickMain);

  }

  // Fonction Metier
  // ---------------------------------------------------------------------------------------------

  public PartieSixQuiPrendController()
  {}

  public Partie getPartie()
  {
    return partie;
  }

  private void supprimeLigne(int ligne)
  {
    plateaux.getChildren().removeAll(
        plateaux.getChildren().filtered(node -> node instanceof ImageView && GridPane.getRowIndex(node) == ligne));
    disactiverSelectionLigne(false);
  }

  public void initPartie(Partie partie)
  {
    Objects.requireNonNull(partie, "partie");
    this.partie = partie;

    // init infos joueurs
    infoJoueurs.setItems(partie.getJoueurInfos());

    // init plateau
    updatePlateau(partie.getPlateau());

    // init main joueur
    Image imageMain;
    Pane border;
    for (int i = 0; i < partie.getJoueur().getCartes().size(); i++)
    {
      imageMain = new Image("images/" + partie.getJoueur().getCartes().get(i).getId() + ".png");
      ImageView picMain = new ImageView();
      border = new Pane();
      picMain.setImage(imageMain);
      border.getChildren().add(picMain);
      border.getStyleClass().add("image_view");
      border.setCursor(Cursor.HAND);
      GridPane.setRowIndex(border, 0);
      GridPane.setColumnIndex(border, i);
      mainCartes.getChildren().add(border);

    }

  }

  @Override
  public int selectionneCarte(int debut, int fin)
  {
    insLabel.setText("Joue une Carte ...");
    disactiverSelectionLigne(false);
    mainCartes.addEventFilter(MouseEvent.MOUSE_CLICKED, eventClickMain);
    // TODO
    return 0;
  }

  @Override
  public int selectionneRangee()
  {
    insLabel.setText("Selection une Ligne ...");
    mainCartes.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventClickMain);
    disactiverSelectionLigne(true);
    // TODO Auto-generated method stub
    return 0;
  }

  private void disactiverSelectionLigne(boolean visible)
  {
    ligne1.setManaged(visible);
    ligne1.setVisible(visible);
    ligne2.setManaged(visible);
    ligne2.setVisible(visible);
    ligne3.setManaged(visible);
    ligne3.setVisible(visible);
    ligne4.setManaged(visible);
    ligne4.setVisible(visible);
  }

  @Override
  public void updatePlateau(Plateau p)
  {
    // TODO Auto-generated method stub
    plateaux.getChildren().removeAll(plateaux.getChildren().filtered(node -> node instanceof ImageView));
    // plateaux.setGridLinesVisible(true);
    Image imagePlateau;
    for (int i = 0; i < p.getPlateauCarte().size(); i++)
    {
      for (int j = 0; j < p.getPlateauCarte().get(i).size(); j++)
      {

        if (p.getPlateauCarte().get(i).get(j) != null)
        {
          imagePlateau = new Image("images/" + p.getPlateauCarte().get(i).get(j).getId() + ".png");
          ImageView picPlateau = new ImageView();
          picPlateau.setImage(imagePlateau);
          GridPane.setRowIndex(picPlateau, i);
          GridPane.setColumnIndex(picPlateau, j);
          plateaux.getChildren().add(picPlateau);
        }
      }
    }

  }

  @Override
  public void updateInfoJoueurs(List<InfoJoueurs> infoJoueurs)
  {
    // TODO Auto-generated method stub

  }
  // ---------------------------------------------------------------------------------------------

}
