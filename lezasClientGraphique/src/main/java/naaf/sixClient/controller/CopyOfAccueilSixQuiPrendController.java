package naaf.sixClient.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import naaf.sixClient.Message;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CopyOfAccueilSixQuiPrendController
{
  // FXML
  // ---------------------------------------------------------------------------------------------
  @FXML
  private HBox hboxTool;

  @FXML
  private Canvas canvas;

  @FXML
  private TextArea messageList;

  @FXML
  private Button btn_changeUser;

  @FXML
  private Button btn_chatDeconnexion;

  @FXML
  private TextArea messageSend;

  @FXML
  private Button btnSend;

  // ---------------------------------------------------------------------------------------------
  // Attribut Metier
  // ---------------------------------------------------------------------------------------------
  private String login;
  private List<Message> messages = new ArrayList<Message>();

  // ---------------------------------------------------------------------------------------------

  // Fonction FXML
  // ---------------------------------------------------------------------------------------------
  @FXML
  void OnchatDeconnexion(ActionEvent event)
  {

  }

  @FXML
  void onChangeUser(ActionEvent event)
  {
    
  }

  @FXML
  void onSend(ActionEvent event)
  {
    String msg = messageSend.getText();
    messageSend.clear();
    messages.add(new Message(login, msg));
    affichageMSG(messages);
  }

  // ---------------------------------------------------------------------------------------------

  // Fonction accesseur
  // ---------------------------------------------------------------------------------------------

  public String getLogin()
  {
    return login;
  }

  public void setLogin(String login)
  {
    this.login = login;
  }

  // ---------------------------------------------------------------------------------------------

  // Fonction Metier
  // ---------------------------------------------------------------------------------------------
  public void initCanvas()
  {
    final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    initDraw(graphicsContext);

    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
    {

      @Override
      public void handle(MouseEvent event)
      {
        graphicsContext.beginPath();
        graphicsContext.moveTo(event.getX(), event.getY());
        graphicsContext.stroke();
      }
    });

    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>()
    {

      @Override
      public void handle(MouseEvent event)
      {
        graphicsContext.lineTo(event.getX(), event.getY());
        graphicsContext.stroke();
      }
    });

    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>()
    {

      @Override
      public void handle(MouseEvent event)
      {

      }
    });
  }

  private void initDraw(GraphicsContext gc)
  {
    double canvasWidth = gc.getCanvas().getWidth();
    double canvasHeight = gc.getCanvas().getHeight();

    gc.setFill(Color.LIGHTGRAY);
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(5);

    gc.fill();
    gc.strokeRect(0, 0, canvasWidth, canvasHeight);

    gc.setFill(Color.RED);
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(1);

  }

  private void affichageMSG(List<Message> messages)
  {
    messageList.clear();
    StringBuffer bs = new StringBuffer();
    for (Message msg : messages)
    {
      bs.append(msg.toString());
    }

    messageList.setText(bs.toString());
    
  }

  public byte[] saveDraw(int width, int height)
  {
    byte[] imageInByte = null;
    WritableImage wim = new WritableImage(width, height);
    canvas.snapshot(null, wim);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try
    {
      ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", baos);
      baos.flush();
      imageInByte = baos.toByteArray();
      baos.close();
    }
    catch (Exception s)
    {}
    
    return imageInByte;
  }
  

}
