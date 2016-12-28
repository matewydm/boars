package pl.edu.agh.miss.main;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    private static final int CELL_SIZE =50;
    private static final int BOARD_SIZE = Automaton.getSize()*CELL_SIZE;
    private static final int SPEED = 600; // w milisekundach
    private static int counter = 0;
    private Automaton automaton;

    private Map<Position, StackPane> boardMap = new HashMap<>();


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, this::iterateAutomaton), new KeyFrame(Duration.millis(SPEED)));

        timeline.setCycleCount(Timeline.INDEFINITE);


        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(10)));
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
        System.out.println("Preys "+ automaton.getPreyNumber());
        System.out.println("Predators: "+ automaton.getPredatorNumber());

        repaint();
    }

    private void repaint() {
        for (int x = 0; x < Automaton.getSize(); x++) {
            for (int y = 0; y < Automaton.getSize(); y++) {
                StackPane pane = boardMap.get(new Position(x*CELL_SIZE,y*CELL_SIZE));
                pane.getStyleClass().clear();

                Position position = new Position(x,y);

                boolean isPlants = !(automaton.getCells().get(position).getPlants().getValue() == 0);
                boolean isPreys = !automaton.getCells().get(position).getPreys().isEmpty();
                boolean isPredators = !automaton.getCells().get(position).getPredators().isEmpty();
                Integer roughness = automaton.getCells().get(position).getRoughness();
                if (isPreys && isPredators) {
                    pane.getStyleClass().add("predator_prey-cell");
                }
                if (isPreys) {
                    pane.getStyleClass().add("prey-cell");
                }
                else if (isPredators) {
                    pane.getStyleClass().add("predator-cell");
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

    public static void main(String[] args) {
        launch(args);
    }
}
