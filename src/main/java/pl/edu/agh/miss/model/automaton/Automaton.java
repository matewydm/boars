package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Automaton {
    private final static int SIZE = 10;
    private final static byte PREY_DEFAULT_MOVEMENT = 3;
    private final static byte PREDATOR_DEFAULT_MOVEMENT = 3;

    private Map<Position,State> cells;

    private PreyMoves preyMoves;

    public Automaton () {
        cells = new HashMap<>();
        preyMoves = new PreyMoves();
    }

    public Automaton nextState(){
        Automaton automaton = getInstance();
        // generowanie strategi - odbedzie sie dzieki wartosciom w instancji Prey
        cells.entrySet().stream().forEach(e -> e.getValue().getPreys().stream().forEach(Prey::setActionStrategy));
        //tutaj bedziemy robic dzialanie na podstawie tego jaka strategia jest zawarta w obiekcie Animal
        return automaton;
    }

    protected Automaton getInstance(){
        return new Automaton();
    }

    public static int getSize() {
        return SIZE;
    }
    public static byte getPreyDefaultMovement() { return PREY_DEFAULT_MOVEMENT; }
    public static byte getPredatorDefaultMovement() { return PREDATOR_DEFAULT_MOVEMENT; }
}
