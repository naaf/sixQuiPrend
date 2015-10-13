package naaf.sixClient.controller;

import static naaf.sixClient.App.MIN_HEIGHT;
import static naaf.sixClient.App.MIN_WHIDH;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import naaf.sixClient.core.Carte;
import naaf.sixClient.core.Joueur;
import naaf.sixClient.core.Plateau;
import naaf.sixClient.exception.SixQuiPrendException;
import naaf.sixClient.model.Accueil;
import naaf.sixClient.model.JoueurInfo;
import naaf.sixClient.model.Partie;
import naaf.sixClient.model.PartieInfo;

public class AccueilSixQuiPrendController
{
  // FXML
  // ---------------------------------------------------------------------------------------------
  @FXML
  private TableColumn<PartieInfo, Integer> s_col_score;

  @FXML
  private Label labelNomJoueur;

  @FXML
  private Label nbJoueurLabel;

  @FXML
  private Slider nbJoueurSlider;

  @FXML
  private TableColumn<PartieInfo, String> s_col_partie;

  @FXML
  private Button bnt_creerPartie;

  @FXML
  private TableColumn<PartieInfo, String> p_col_nbJoueurs;

  @FXML
  private TableView<PartieInfo> tab_parties;

  @FXML
  private TextField TF_nomPartie;

  @FXML
  private TableView<PartieInfo> tab_score;

  @FXML
  private TableColumn<PartieInfo, String> p_col_partie;

  // ---------------------------------------------------------------------------------------------
  // Attribut Metier

  // ---------------------------------------------------------------------------------------------
  private Accueil accueil;

  // ---------------------------------------------------------------------------------------------

  // Fonction FXML
  // ---------------------------------------------------------------------------------------------
  @FXML
  private void initialize()
  {
    // Initialize the person table with the two columns.
    s_col_partie.setCellValueFactory(cellData -> cellData.getValue().getNom());
    s_col_score.setCellValueFactory(cellData -> cellData.getValue().getScore().asObject());

    p_col_partie.setCellValueFactory(cellData -> cellData.getValue().getNom());
    p_col_nbJoueurs.setCellValueFactory(cellData -> {
      int nbE = cellData.getValue().getNbJoueurEffectif();
      int nbR = cellData.getValue().getNbJoueurRequis();
      return new SimpleStringProperty(nbE + " / " + nbR);
    });
    tab_score.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> System.out.println(newValue.getNom()));

    nbJoueurSlider.valueProperty().addListener(new ChangeListener<Number>()
    {
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
      {
        nbJoueurLabel.setText(Integer.toString(new_val.intValue()));
      }
    });
  }

  @FXML
  void onCreePartie(ActionEvent event)
  {

    if (!TF_nomPartie.getText().isEmpty())
    {
      accueil.addInitPartie(new PartieInfo(TF_nomPartie.getText(), (int) nbJoueurSlider.getValue()));
    }
    changeScene((Stage) bnt_creerPartie.getScene().getWindow());
  }

  private void changeScene(Stage stage)
  {
    stage.setTitle("Six Qui Prend");
    Parent myPane = null;
    FXMLLoader loader = null;
    try
    {
      loader = new FXMLLoader(getClass().getResource("/fxml/PartieSixQuiPrend.fxml"));
      myPane = loader.load();
     
      PartieSixQuiPrendController partieController = loader.getController();
      String css = this.getClass().getResource("/css/lezas.css").toExternalForm();
      Scene scene = new Scene(myPane);
      scene.getStylesheets().add(css);
      stage.setScene(scene);
      stage.setMaxWidth(MIN_WHIDH);
      stage.setMaxHeight(MIN_HEIGHT);
      stage.setMinWidth(MIN_WHIDH);
      stage.setMinHeight(MIN_HEIGHT);
      
   // TODO debut
      Partie p = new Partie();
      List<JoueurInfo> js = new ArrayList<JoueurInfo>();
      Joueur j = new Joueur("test");
      List<Carte> lc= new ArrayList<Carte>();
      for(int i = 1 ; i <= 10; i++)
        lc.add(new Carte(i, i));
      
      j.setCartes(lc);
      for(int i = 1 ; i <= 9; i++)
         js.add(new JoueurInfo("joueur" + i, 0));
      js.add(new JoueurInfo(j.getName(),j.getScore()));
      
      List<Carte> fourCarte= new ArrayList<Carte>();
      for(int i = 10 ; i < 14; i++)
        fourCarte.add(new Carte(i, i));
      Plateau pl = new Plateau();
      try
      {
        pl.initPlateau(fourCarte);
        p.setJoueur(j);
        p.addJoueurInfos(js);
        p.setPlateau(pl);
        partieController.initPartie(p);
      }
      catch (SixQuiPrendException e)
      {
        e.printStackTrace();
      }
      //TODO fin
    
      
      stage.setOnCloseRequest(new EventHandler<WindowEvent>()
      {
        public void handle(WindowEvent we)
        {
          System.out.println("Stage is closing");
          stage.close();
        }
      });

      // prevStage.close();

      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  // ---------------------------------------------------------------------------------------------

  // Fonction accesseur
  // ---------------------------------------------------------------------------------------------

  public void init(Accueil accueil)
  {
    Objects.requireNonNull(accueil, "accueil");
    this.accueil = accueil;
    labelNomJoueur.setText(accueil.getNom());
    tab_score.setItems(accueil.getPartiesTermine().filtered(PartieInfo::isEnd));
    tab_parties.setItems(accueil.getListePartie().filtered(p -> !p.isEnd()));
  }

  public Accueil getAccueil()
  {
    return accueil;
  }

  // Fonction Metier
  // ---------------------------------------------------------------------------------------------

  // ---------------------------------------------------------------------------------------------

}
