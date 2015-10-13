package naaf.sixClient.controller;

import static naaf.sixClient.App.MIN_HEIGHT;
import static naaf.sixClient.App.MIN_WHIDH;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import naaf.sixClient.model.Accueil;
import naaf.sixClient.model.PartieInfo;

public class SixQuiPrendController
{
  Stage prevStage;

  private Accueil accueil;

  @FXML
  private Label label_login;

  @FXML
  private Button btn_connexion;

  @FXML
  private Label label_error;

  @FXML
  private TextField field_login;

  @FXML
  private GridPane yt_connexionForm;

 
  @FXML
  void onConnexion(ActionEvent event)
  {
    label_error.setText(field_login.getText() + " en attente de se connexion ...");
    accueil.setNom(field_login.getText());
    changeScene((Stage) btn_connexion.getScene().getWindow());
  }

  public SixQuiPrendController()
  {
    this.accueil = new Accueil();
    accueil.setListePartie(FXCollections.observableArrayList());
    accueil.setPartiesTermine(FXCollections.observableArrayList());

    accueil.addEndPartie(new PartieInfo());
    accueil.addEndPartie(new PartieInfo());
    accueil.addEndPartie(new PartieInfo());

    PartieInfo p = new PartieInfo();
    p.setEnd(false);
    p.setNom("autre");
    accueil.addInitPartie(p);
    accueil.addInitPartie(new PartieInfo());
    accueil.addInitPartie(new PartieInfo());
  }

  public void setPrevStage(Stage stage)
  {
    this.prevStage = stage;
  }

  private void changeScene(Stage stage)
  {
    stage.setTitle("Session ChatPic");
    Parent myPane = null;
    FXMLLoader loader = null;
    try
    {
      loader = new FXMLLoader(getClass().getResource("/fxml/AccueilSixQuiPrend.fxml"));
      myPane = loader.load();
      Scene scene = new Scene(myPane);
      AccueilSixQuiPrendController sessionController = loader.getController();
      sessionController.init(accueil);
      stage.setScene(scene);
      stage.setMaxWidth(MIN_WHIDH);
      stage.setMaxHeight(MIN_HEIGHT);
      
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

}
