//package pl.edu.agh.miss.main.view;
//
//import javafx.application.Application;
//import javafx.beans.binding.Bindings;
//import javafx.beans.binding.NumberBinding;
//import javafx.beans.value.ChangeListener;
//import javafx.geometry.Bounds;
//import javafx.geometry.HPos;
//import javafx.geometry.Rectangle2D;
//import javafx.geometry.VPos;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//
//import java.io.PrintWriter;
//import java.util.List;
//
//public class View extends Application {
//    public static final int NUM_CELLS = 50;
//    public static final int BOARD_SIZE = 500;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        BorderPane root = new BorderPane();
//
//
//        ColumnConstraints columnConstraints1 = new ColumnConstraints();
//        columnConstraints1.setHgrow(Priority.NEVER);
//        columnConstraints1.setPercentWidth(10);
//
//        ColumnConstraints columnConstraints2 = new ColumnConstraints();
//        columnConstraints2.setHgrow(Priority.ALWAYS);
//        columnConstraints2.setPercentWidth(90);
//
//        ColumnConstraints columnConstraints3 = new ColumnConstraints();
//        columnConstraints3.setHgrow(Priority.NEVER);
//        columnConstraints3.setPercentWidth(10);
//
//        RowConstraints rowConstraints1 = new RowConstraints();
//        rowConstraints1.setVgrow(Priority.NEVER);
//        rowConstraints1.setPercentHeight(10);
//
//        RowConstraints rowConstraints2 = new RowConstraints();
//        rowConstraints2.setVgrow(Priority.ALWAYS);
//        rowConstraints2.setPercentHeight(90);
//
//        RowConstraints rowConstraints3 = new RowConstraints();
//        rowConstraints3.setVgrow(Priority.NEVER);
//        rowConstraints3.setPercentHeight(10);
//
//
////        root.getColumnConstraints().addAll(columnConstraints1,columnConstraints2,columnConstraints3);
////        root.getRowConstraints().addAll(rowConstraints1,rowConstraints2,rowConstraints3);
////
////
////        root.setGridLinesVisible(true);
//
////        AnchorPane center = new AnchorPane();
//
//        GridPane board = new GridPane();
//        for (int i = 0; i < NUM_CELLS; i++) {
//            for (int j = 0; j < NUM_CELLS; j++) {
//                Rectangle r = new Rectangle();
//                r.setFill(Color.WHEAT);
//                r.setStroke(Color.BLACK);
//                board.add(r,i,j);
//            }
//        }
//
//        AnchorPane.setTopAnchor(board, 0.0);
//        AnchorPane.setRightAnchor(board, 0.0);
//        AnchorPane.setLeftAnchor(board, 0.0);
//        AnchorPane.setBottomAnchor(board, 0.0);
////        center.getChildren().setAll(board);
//
//
//        root.setCenter(board);
//        Scene scene = new Scene(root);
//
//        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
//
//
//        primaryStage.setScene(scene);
//        primaryStage.setWidth(600);
//        primaryStage.setHeight(600);
//
//        primaryStage.show();
//
//        primaryStage.setTitle("PREDATOR-PREY AUTOMATON");
//        primaryStage.show();
//
//    }
//}