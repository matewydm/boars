package pl.edu.agh.miss.model.automaton;


import java.util.Set;

public class Automaton {

    private Set<Cell> cells;
    private NeighbourhoodStrategy neighbourhoodStrategy;

    public Automaton nextState(){
        Automaton automaton= getInstance();
        return automaton;
    }

    protected Automaton getInstance(){
        return new Automaton();
    }



}
