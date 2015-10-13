package naaf.sixClient;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import naaf.sixClient.controller.SixQuiPrendController;

/**
 * Hello world!
 *
 */
public class App extends Application
{

  public final static int MIN_WHIDH = 800;
  public final static int MIN_HEIGHT = 740;

  @Override
  public void start(Stage primaryStage)
  {
    Parent root;
    try
    {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainForm.fxml"));
      root = loader.load();

      SixQuiPrendController myController = loader.getController();

      // Set Data to FXML through controller
      myController.setPrevStage(primaryStage);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return;
    }
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setMaxWidth(MIN_WHIDH);
    primaryStage.setMaxHeight(MIN_HEIGHT);
    primaryStage.setTitle("Connexion");
    primaryStage.show();
  }

  public static void main(String[] args)
  {
    System.out.println("bonjour");
    launch(args);
  }

}
