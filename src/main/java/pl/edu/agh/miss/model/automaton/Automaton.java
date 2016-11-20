package pl.edu.agh.miss.model.automaton;


import java.util.Map;
import java.util.Set;

public class Automaton {
    private final static int SIZE = 10;
    private final static byte PREY_DEFAULT_MOVEMENT = 3;
    private final static byte PREDATOR_DEFAULT_MOVEMENT = 3;

    private Map<Position,State> cells;
    private NeighbourhoodStrategy neighbourhoodStrategy;

    public Automaton nextState(){
        Automaton automaton= getInstance();
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
