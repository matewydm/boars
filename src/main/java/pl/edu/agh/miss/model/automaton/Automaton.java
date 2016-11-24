package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.life.LifeStatus;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;

import java.util.*;
import java.util.stream.Collectors;

public class Automaton {
    private final static int SIZE = 20;
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

        cells.entrySet().stream().forEach(e -> e.getValue().getPreys().stream().forEach(r - > preyMoves.calculate(e,r)));
        Map<Position,State> newMap = automaton.getCells();
        for (Position position : cells.keySet()){
            for (Prey prey : cells.get(position).getPreys()){
                Set<Position> positionSet = preyMoves.calculate(position,prey);
                Set<Cell> cellSet = getCellsArea(positionSet);
                Position newPosition = prey.performAction(cellSet,position);
                // zjadł roślinę, zaktualizowało się to na obecnej mapie
                State newState = newMap.get(newPosition);
                newState.getPreys().add(prey);
                newMap.put(position,newState);
                // przenoszę zwierzę na nową mapę
            }
        }




        // tutaj wykonywanie ruchów przez drapieżców



        //przenoszenie roślin zaktualizowanych przez ofiary
        for (Position position: cells.keySet()) {
            newMap.get(position).getPlants().addAll(cells.get(position).getPlants());
        }


        //aktualizacja atrybutów zwierząt i roślin - do osobnej metody
        for (Position position: cells.keySet()) {
            State currentState = newMap.get(position);

            for (Plant plant: currentState.getPlants()) {
                plant.grow();
            }
            for (Prey prey: currentState.getPreys()) {
                if (prey.getStatus() != LifeStatus.DEAD) // tylko dla żyjących - wilki zjadły, zaktualizowaly status ofiar
                    prey.incrementAge();
                    prey.throwDice(); // uwzględnia aktualizację śmiertelności

            }
        }

        // usuwanie śmierdziuchów, które gniją
        for (Position position: cells.keySet()) {
            List<Prey> preys = newMap.get(position).getPreys();
            List<Prey> dead = new LinkedList<>();
            for(Prey prey: preys){
                if(prey.getStatus() == LifeStatus.DEAD){
                    dead.add(prey);
                }
            }
            preys.removeAll(dead);

        }




        return automaton;
    }

    protected Automaton getInstance(){
        Automaton newAutomaton = new Automaton();

        for(Position p : cells.keySet()) {
            newAutomaton.getCells().put(p,new State());
        }

        newAutomaton.preyMoves = preyMoves;

        return newAutomaton;
    }

    public static int getSize() {
        return SIZE;
    }
    public static byte getPreyDefaultMovement() { return PREY_DEFAULT_MOVEMENT; }
    public static byte getPredatorDefaultMovement() { return PREDATOR_DEFAULT_MOVEMENT; }
    public Map<Position, State> getCells() {
        return cells;
    }

    private Set<Cell> getCellsArea(Set<Position> positionSet){
        Set<Cell> cellSet = new HashSet<>();
        for(Position position:  positionSet) {
            if (cells.containsKey(position)) {
                State state = cells.get(position); // whoops
                cellSet.add(new Cell(position,state));
            }
        }
        return cellSet;
    }


    public void setCells(Map<Position, State> cells) {
        this.cells = cells;
    }
}
