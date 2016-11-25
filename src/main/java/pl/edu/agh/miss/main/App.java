package pl.edu.agh.miss.main;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.edu.agh.miss.model.automaton.*;
import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.LifeStatus;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.awt.*;
import java.io.*;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

public class App extends Application {

    private static final int CELL_SIZE = 50;
    private static final int BOARD_SIZE = Automaton.getSize()*CELL_SIZE;
    private static final int SPEED = 1000; // w milisekundach
    private static int counter = 0;
    private Automaton automaton;

    private Map<Position, StackPane> boardMap = new HashMap<>();


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, this::iterateAutomaton), new KeyFrame(Duration.millis(SPEED)));

        timeline.setCycleCount(Timeline.INDEFINITE);


        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(100)));
        automaton = new Automaton(cellsFactory);

        Pane root = new Pane();
        Scene scene = new Scene(root, BOARD_SIZE, BOARD_SIZE);
        scene.getStylesheets().add("gol.css");


        for (int x = 0; x < BOARD_SIZE; x = x + CELL_SIZE) {
            for (int y = 0; y < BOARD_SIZE; y = y + CELL_SIZE) {
                StackPane cell = StackPaneBuilder.create().layoutX(x).layoutY(y).prefHeight(CELL_SIZE).prefWidth(CELL_SIZE).styleClass("plant-cell").build();
                root.getChildren().add(cell);
                boardMap.put(new Position(x,y), cell);
            }
        }

        primaryStage.setTitle("Automaton");
        primaryStage.setScene(scene);
        primaryStage.show();

        timeline.play();
    }

    private void iterateAutomaton(ActionEvent event) {
        System.out.println("Iteration: " + (++counter));
        automaton = automaton.nextState();
        System.out.println(automaton.getPreyNumber());
        System.out.println(automaton.getPlantNumber());
        for (int x = 0; x < Automaton.getSize(); x++) {
            for (int y = 0; y < Automaton.getSize(); y++) {
                StackPane pane = boardMap.get(new Position(x*CELL_SIZE,y*CELL_SIZE));
                pane.getStyleClass().clear();

                boolean isPlants = !automaton.getCells().get(new Position(x,y)).getPlants().isEmpty();
                boolean isPreys = !automaton.getCells().get(new Position(x,y)).getPreys().isEmpty();

                if (x == 0 && y == 0) {
                    java.util.List<Animal> preys = automaton.getPreys(new Position(x,y));

                    java.util.List<Plant> plants = automaton.getPlants(new Position(x,y));

                    int plantMass = 0;
                    for(int i = 0 ; i < plants.size(); i++)
                        plantMass += plants.get(i).getValue();

                    System.out.println("Liczebności na polu (0,0):");
                    System.out.println("\t Preys: " + preys.size());
                    System.out.println("\t Plants mass " + plantMass);
                }

                if (isPlants && isPreys) {
                    pane.getStyleClass().add("prey_plant-cell");
                }
                else if (isPreys && !isPlants) {
                    pane.getStyleClass().add("prey-cell");
                }
                else if (isPlants && !isPreys) {
                    pane.getStyleClass().add("plant-cell");
                }
                else {
                    pane.getStyleClass().add("root");
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
