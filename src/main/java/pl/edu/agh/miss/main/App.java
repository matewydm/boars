package pl.edu.agh.miss.main;

//-Djava.util.concurrent.ForkJoinPool.common.parallelism=7
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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
import java.awt.*;
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
    private Text counter;


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, this::iterateAutomaton), new KeyFrame(Duration.millis(SPEED)));

        timeline.setCycleCount(Timeline.INDEFINITE);


        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(4)));
        automaton = new Automaton(cellsFactory);



        BorderPane borderPane = new BorderPane();

        VBox leftBar = new VBox();
        leftBar.setPrefWidth(300);
        leftBar.setSpacing(5);

        VBox rightBar = new VBox();
        rightBar.setPrefWidth(300);

        Pane boardNode = new AnchorPane();
        boardNode.setPrefSize(BOARD_SIZE,BOARD_SIZE);

        borderPane.setCenter(boardNode);
        borderPane.setLeft(leftBar);
        borderPane.setRight(rightBar);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("gol.css");

        Text preyMovementText = new Text("PREY MOVEMENT");
        preyMovementText.setTextAlignment(TextAlignment.CENTER);
        TextField preyMovementField = new TextField(Byte.toString(Prey.getPreyDefaultMovement()));
        preyMovementField.setAlignment(Pos.CENTER);

        HBox preyPlusMinus = new HBox();
        Button preyPlusButton = new Button("+1");
        Button preyMinusButton = new Button("-1");
        preyPlusMinus.getChildren().addAll(preyMinusButton,preyPlusButton);

        leftBar.getChildren().addAll(preyMovementText,preyMovementField);


        for (int x = 0; x < BOARD_SIZE; x = x + CELL_SIZE) {
            for (int y = 0; y < BOARD_SIZE; y = y + CELL_SIZE) {
                StackPane cell = StackPaneBuilder.create().layoutX(x).layoutY(y).prefHeight(CELL_SIZE).prefWidth(CELL_SIZE).styleClass("plant-cell").build();
                boardNode.getChildren().add(cell);
                boardMap.put(new Position(x,y), cell);
            }
        }

        Font font = Font.font("Arial",FontWeight.BOLD,30);

        preys = new Text(20,30,"PREYS: 0");
        preys.setFont(font);
        predators = new Text(BOARD_SIZE-280,30,"PREDATORS: 0");
        predators.setFont(font);
        counter = new Text(BOARD_SIZE/2-30,30,"0");
        counter.setFont(font);

        boardNode.getChildren().addAll(preys,predators,counter);




        primaryStage.setTitle("Automaton");
        primaryStage.setScene(scene);
        primaryStage.show();

        timeline.play();
    }

    private void iterateAutomaton(ActionEvent event) {
        System.out.println("Iteration: " + (++COUNTER));

        ExecutorService es = Executors.newFixedThreadPool(7);
        Callable<Automaton> callable = new MyCallable(automaton);
        Future<Automaton> result = es.submit(callable);

        try {
            automaton = result.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        es.shutdown();

        System.out.println("Preys "+ automaton.getPreyNumber());
        System.out.println("Predators: "+ automaton.getPredatorNumber());


        repaint();
    }

    private void repaint() {
        predators.setText("PREDATORS: " + Integer.toString(automaton.getPredatorNumber()));
        preys.setText("PREYS: " + Integer.toString(automaton.getPreyNumber()));
        counter.setText(Integer.toString(COUNTER));

        for (int x = 0; x < Automaton.getSize(); x++) {
            for (int y = 0; y < Automaton.getSize(); y++) {
                StackPane pane = boardMap.get(new Position(x*CELL_SIZE,y*CELL_SIZE));
                pane.getStyleClass().clear();

                Position position = new Position(x,y);
                State state = automaton.getCells().get(position);

                boolean isPlants = !(state.getPlants().getValue() == 0);
                boolean isPreys = !state.getPreys().isEmpty();
                boolean isPredators = !state.getPredators().isEmpty();
                Integer roughness = state.getRoughness();

                if (isPreys && isPredators) {
                    pane.getStyleClass().add("predator_prey-cell");
                }
                else if (isPreys) {
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

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    class MyCallable implements Callable<Automaton>{

        private Automaton automaton;

        public MyCallable(Automaton automaton) {
            this.automaton = automaton;
        }

        @Override
        public Automaton call() throws Exception {
            return automaton.nextState();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
