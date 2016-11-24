package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;

import java.util.*;
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

        cells.entrySet().stream().forEach(e -> e.getValue().getPreys().stream().forEach(r - > preyMoves.calculate(e,r)));
        Map<Position,State> newMap = automaton.getCells();
        for (Position position : cells.keySet()){
            for (Prey prey : cells.get(position).getPreys()){
                Set<Position> positionSet = preyMoves.calculate(position,prey);
                Set<Cell> cellSet = getCellsArea(positionSet);
                Position newPosition = prey.performAction(cellSet,position);
                //TODO kurwa napisac mape kaldus leszczu i poprawe tego chujowego fora na jakis ladny strumien
                newMap.get(newPosition).getPreys().add(prey);
                newMap.get(position).getPlants().addAll(cells.get(position).getPlants());
            }
        }
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
}
