package pl.edu.agh.miss.main.controller;

import javafx.fxml.FXML;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.io.IOException;

public class ViewController {

    @FXML
    private void onPreyMoveInc() throws IOException {
        byte moves = Prey.getPreyDefaultMovement();
        Prey.setDefaultMovement((byte)(moves+1));
    }

    @FXML
    private void onPreyMoveDec() throws IOException {
        byte moves = Prey.getPreyDefaultMovement();
        Prey.setDefaultMovement((byte)(moves-1));
    }

}
