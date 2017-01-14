package pl.edu.agh.miss.main;

//-Djava.util.concurrent.ForkJoinPool.common.parallelism=7
import com.sun.org.apache.regexp.internal.RE;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.edu.agh.miss.model.automaton.*;
import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;
import pl.edu.agh.miss.model.automaton.life.Prey;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.concurrent.*;



public class App extends Application {
    private Automaton automaton;
    private Observer observer;


    private static final int CELL_SIZE =20;
    private static final int BOARD_SIZE = Automaton.getSize()*CELL_SIZE;
    private static final int SPEED = 100; // w milisekundach
    private static int COUNTER = 0;

    private Map<Position, StackPane> boardMap = new HashMap<>();
    private Text preys;
    private Text predators;
    private Text supremators;
    private Text counter;


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, this::iterateAutomaton), new KeyFrame(Duration.millis(SPEED)));

        timeline.setCycleCount(Timeline.INDEFINITE);


        CellsFactory cellsFactory = new SimpleCellsFactory(new java.awt.Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(4)));
        automaton = new Automaton(cellsFactory);



        BorderPane borderPane = new BorderPane();

        VBox rightBar = new VBox();
        rightBar.setSpacing(10);

        VBox leftBar = new VBox();
        leftBar.setSpacing(10);
        leftBar.setPrefWidth(320);


        Pane boardNode = new AnchorPane();
        boardNode.setPrefSize(BOARD_SIZE,BOARD_SIZE);

        borderPane.setCenter(boardNode);

        borderPane.setRight(rightBar);
        borderPane.setLeft(leftBar);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("gol.css");

        Text preyMovementText = new Text("PREY MOVEMENT");
        preyMovementText.setTextAlignment(TextAlignment.CENTER);
        TextField preyMovementField = new TextField(Byte.toString(Prey.getPreyDefaultMovement()));
        preyMovementField.setAlignment(Pos.CENTER);


        for (int x = 0; x < BOARD_SIZE; x = x + CELL_SIZE) {
            for (int y = 0; y < BOARD_SIZE; y = y + CELL_SIZE) {
                StackPane cell = StackPaneBuilder.create().layoutX(x).layoutY(y).prefHeight(CELL_SIZE).prefWidth(CELL_SIZE).styleClass("plant-cell").build();
                boardNode.getChildren().add(cell);
                boardMap.put(new Position(x,y), cell);
            }
        }

        Font font = Font.font("Arial",FontWeight.BOLD,30);

        preys = new Text("PREYS: 0");
        preys.setFont(font);
        predators = new Text("PREDATORS: 0");
        predators.setFont(font);
        supremators =  new Text("SUPREMATORS: 0");
        supremators.setFont(font);
        counter = new Text("ITERATIONS: 0");
        counter.setFont(font);

        leftBar.getChildren().addAll(counter,preys,predators,supremators);

//        boardNode.getChildren().addAll(preys,predators,supremators,counter);

        Text legend = new Text(10,30,"LEGEND:");
        legend.setFont(font);

        HBox redBox = createBox(Color.RED,"PREY AND PREDATOR OR SUPREMATOR");
        HBox yellowBox = createBox(Color.YELLOW, "PREY");
        HBox predatorBox = createBox(Color.LAVENDERBLUSH, "PREDATOR");
        HBox blackBox = createBox(Color.BLACK, "SUPREMATOR");
        HBox darkGreenBox = createBox(Color.DARKGREEN, "PLANT ON FERTILE TERRAIN");
        HBox greenBox = createBox(Color.GREEN,"PLANT ON ROUGH TERRAIN");
        HBox darkolivegreenBox = createBox(Color.DARKOLIVEGREEN, "PLANT ON VERY ROUGH TERRAIN");


        rightBar.getChildren().addAll(legend,redBox,yellowBox,predatorBox,blackBox,darkGreenBox,greenBox,darkolivegreenBox);


        primaryStage.setTitle("Automaton");
        primaryStage.setScene(scene);
        primaryStage.show();

        timeline.play();
    }

    private void iterateAutomaton(ActionEvent event) {
        COUNTER++;

        automaton = automaton.nextState();


        repaint();
    }

    private void repaint() {
        predators.setText("PREDATORS: " + Integer.toString(automaton.getPredatorNumber()));
        preys.setText("PREYS: " + Integer.toString(automaton.getPreyNumber()));
        supremators.setText("SUPREMATORS: " + Integer.toString(automaton.getSuprematorNumber()));

        counter.setText("ITERATIONS: " + Integer.toString(COUNTER));

        for (int x = 0; x < Automaton.getSize(); x++) {
            for (int y = 0; y < Automaton.getSize(); y++) {
                StackPane pane = boardMap.get(new Position(x*CELL_SIZE,y*CELL_SIZE));
                pane.getStyleClass().clear();

                Position position = new Position(x,y);
                State state = automaton.getCells().get(position);

                boolean isPlants = !(state.getPlants().getValue() == 0);
                boolean isPreys = !state.getPreys().isEmpty();
                boolean isPredators = !state.getPredators().isEmpty();
                boolean isSupremators = !state.getSupremators().isEmpty();
                Integer roughness = state.getRoughness();

                if (isPreys && (isPredators || isSupremators)) {
                    pane.getStyleClass().add("predator_prey-cell");
                }
                else if (isPreys) {
                    pane.getStyleClass().add("prey-cell");
                }
                else if (isPredators) {
                    pane.getStyleClass().add("predator-cell");
                }
                else if (isSupremators) {
                    pane.getStyleClass().add("supremator-cell");
                }
                else if (isPlants) {
                    setTerrain(pane,roughness);
                }
            }
        }
    }

    private void setTerrain(StackPane pane, Integer roughness) {
        switch (roughness.intValue()) {
            case 1: pane.getStyleClass().add("plant-normal");
                break;
            case 2: pane.getStyleClass().add("plant-rough");
                break;
            case 3: pane.getStyleClass().add("plant-roughest");
                break;
        }
    }

    private HBox createBox(Color color, String text) {
        HBox redBox = new HBox();
        javafx.scene.shape.Rectangle red = new javafx.scene.shape.Rectangle(20, 20);
        red.setFill(color);
        red.setStroke(Color.BLACK);
        red.setStrokeWidth(3);

        Font fontLegend = Font.font("Arial",FontWeight.BOLD,14);
        Text redLegend = new Text(text);
        redLegend.setFont(fontLegend);


        redBox.getChildren().addAll(red,redLegend);

        return redBox;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
