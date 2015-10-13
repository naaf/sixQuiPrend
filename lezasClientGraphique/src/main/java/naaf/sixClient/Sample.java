package naaf.sixClient;
//package fr.dant.chatpicClient;
//
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.scene.control.ProgressIndicator;
//import javafx.scene.layout.GridPane;
//
///**
// * 
// * A sample that demonstrates the Progress Indicator control in various modes.
// * 
// *
// * 
// * @see javafx.scene.control.ProgressIndicator
// * 
// * @related controls/ProgressBar
// */
//
//public class Sample extends Application
//{
//  
//
//  private void init(Stage primaryStage)
//  {
//
//    Group root = new Group();
//
//    primaryStage.setResizable(false);
//
//    primaryStage.setScene(new Scene(root, 400, 400));
//
//    GridPane g = new GridPane();
//
//    ProgressIndicator p1 = new ProgressIndicator();
//
//    p1.setPrefSize(50, 50);
///*
//    ProgressIndicator p2 = new ProgressIndicator();
//
//    p2.setPrefSize(50, 50);
//
//    p2.setProgress(0.25F);
//
//    ProgressIndicator p3 = new ProgressIndicator();
//
//    p3.setPrefSize(50, 50);
//
//    p3.setProgress(0.5F);
//
//    ProgressIndicator p4 = new ProgressIndicator();
//
//    p4.setPrefSize(50, 50);
//
//    p4.setProgress(1.0F);
//*/
//    g.add(p1, 1, 0);
///*
//    g.add(p2, 0, 1);
//
//    g.add(p3, 1, 1);
//
//    g.add(p4, 2, 1);
//*/
//    g.setHgap(40);
//
//    g.setVgap(40);
//
//    root.getChildren().add(g);
//
//  }
//
//  public double getSampleWidth()
//  {
//    return 400;
//  }
//
//  public double getSampleHeight()
//  {
//    return 400;
//  }
//
//  @Override
//  public void start(Stage primaryStage) throws Exception
//  {
//
//    init(primaryStage);
//
//    primaryStage.show();
//
//  }
//
//  public static void main(String[] args)
//  {
//    launch(args);
//  }
//
//}