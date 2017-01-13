package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.moves.PredatorMoves;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.RunawayStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Automaton {
    private final static int SIZE = 40;
    private ConcurrentMap<Position,State> cells;

    private PreyMoves preyMoves;
    private PredatorMoves predatorMoves;

    private Automaton () {
        cells = new ConcurrentHashMap<>();
        preyMoves = new PreyMoves();
        predatorMoves = new PredatorMoves();
    }
    public Automaton(CellsFactory factory){
        this.cells = factory.cellsFactoryMethod();
        preyMoves = new PreyMoves();
        predatorMoves = new PredatorMoves();

    }


    public Automaton nextState(){
        Automaton automaton = getInstance();

        for (Position position : cells.keySet()){

            State currentState = cells.get(position);
            Cell currentCell = new Cell(position,currentState);

            List<Animal> preys = cells.get(position).getPreys();
            int size = preys.size();

            for (int i = 0; i < size; i++) {
                Animal prey = preys.get(i);

                Set<Position> positionSet = preyMoves.calculate(position,prey);
                Set<Cell> cellSet = getCellsArea(positionSet);

                prey.setActionStrategy(cellSet,currentCell,position);
            }
        }


        for (Position position : cells.keySet()){

            State currentState = cells.get(position);
            Cell currentCell = new Cell(position,currentState);

            List<Animal> predators = cells.get(position).getPredators();
            int size = predators.size();


            //wykonywanie ruchów drapieżników
            for (int i = 0; i < size; i++) {
                Animal predator = predators.get(i);

                Set<Position> positionSet = predatorMoves.calculate(position,predator);
                Set<Cell> cellSet = getCellsArea(positionSet);

                predator.setActionStrategy(cellSet,currentCell,position);
            }
        }


//
//        for (State state: cells.values()) {
//            if (state.getPredators().isEmpty()) { // getPredators uwzględnia predators i supremators
//                state.getPreys().forEach(Animal::setActionStrategy);
//            } else {
//                state.getPreys().forEach(Animal::setRunawayStrategy);
//            }
//        }

//        cells.values().stream().forEach(e -> e.getPredators().forEach(Animal::setActionStrategy)); // dla predators i supremators

        for (Position position : cells.keySet()){

            State currentState = cells.get(position);
            Cell currentCell = new Cell(position,currentState);

            List<Animal> preys = cells.get(position).getPreys();
            int size = preys.size();

            // wykonywanie ruchów ofiar
            for (int i = 0; i < size; i++) {
                Animal prey = preys.get(i);
                Set<Position> positionSet = preyMoves.calculate(position,prey);
                Set<Cell> cellSet = getCellsArea(positionSet);

                Position newPosition = prey.performAction(cellSet,currentCell,position);
                // zjadł roślinę, urodził młode, zaktualizowało się to na obecnej mapie
                State newState = automaton.getState(newPosition);
                newState.getPreys().add(prey);

            }
        }




        for (Position position : cells.keySet()){

            List<Animal> predators = cells.get(position).getPredators();
            int size = predators.size();

            State currentState = cells.get(position);
            Cell currentCell = new Cell(position,currentState);

            //wykonywanie ruchów drapieżników
            for (int i = 0; i < size; i++) {
                Animal predator = predators.get(i);

                Set<Position> positionSet = predatorMoves.calculate(position,predator);
                Set<Cell> cellSet = getCellsArea(positionSet);
                Position newPosition = predator.performAction(cellSet,currentCell,position);
                State newState = automaton.getState(newPosition);
                newState.getPredators().add(predator);

            }
        }


        /*

            TUTAJ WYKONYWANIE RUCHÓW PRZEZ SUPREMATORÓW

         */


        //przenoszenie roślin zaktualizowanych przez ofiary i terenu
        for (Position position: cells.keySet()) {
            State state = automaton.getCells().get(position);
            state.setPlants(this.getPlants(position));
            state.setRoughness(this.getRoughness(position));
        }

        int mass = 0;

        //aktualizacja atrybutów zwierząt i roślin - do osobnej metody
        for (Position position: cells.keySet()) {
            State currentState = automaton.getState(position);
            currentState.getPlants().grow(currentState.getRoughness());
            mass += currentState.getPlants().getValue();
            currentState.getPreys().forEach(Animal::update);
            currentState.getPredators().forEach(Animal::update);
        }

        System.out.println("Plants mass: " + mass);

        //nowe preysy
        for (Position position: cells.keySet()) {
            List<Animal> oldStatePreys = cells.get(position).getPreys();
            List<Animal> newStatePreys = automaton.getState(position).getPreys();
            int oldSize = oldStatePreys.size();
            int newSize = newStatePreys.size();
            if (oldSize > newSize) { // dodano nowe zwierzątka preysow
                for (int i = 0; i < oldSize; i++) {
                    Animal animal = oldStatePreys.get(i);
                    if (animal.getAge() == 0) // młode
                        newStatePreys.add(animal);
                }
            }
        }

        for (Position position: cells.keySet()) {
            List<Animal> oldStatePredators = cells.get(position).getPredators();
            List<Animal> newStatePredators = automaton.getState(position).getPredators();
            int oldSize = oldStatePredators.size();
            int newSize = newStatePredators.size();
            if (oldSize > newSize) { // dodano nowe zwierzątka predatorsow
                for (int i = 0; i < oldSize; i++) {
                    Animal animal = oldStatePredators.get(i);
                    if (animal.getAge() == 0) // młode
                        newStatePredators.add(animal);
                }
            }
        }

        for (Position position: cells.keySet()) {
            List<Animal> preys = automaton.getPreys(position);
            List<Animal> deadPreys = preys.stream().filter(prey -> !prey.isAlive()).collect(Collectors.toCollection(LinkedList::new));
            preys.removeAll(deadPreys);
        }

        for (Position position: cells.keySet()) {
            List<Animal> predators = automaton.getPredators(position);
            List<Animal> deadPredators = predators.stream().filter(predator -> !predator.isAlive()).collect(Collectors.toCollection(LinkedList::new));
            predators.removeAll(deadPredators);
        }

        return automaton;
    }

    private void update(Position position, State state) {
        cells.put(position,state);

    }

    private State getState(Position position) {
       return cells.get(position);
    }

    protected Automaton getInstance(){
        Automaton newAutomaton = new Automaton();

        for(Position p : cells.keySet()) {
            newAutomaton.getCells().put(p,new State());
        }

        newAutomaton.preyMoves = preyMoves;
        newAutomaton.predatorMoves = predatorMoves;

        return newAutomaton;
    }

    public static int getSize() {
        return SIZE;
    }
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


    public void setCells(ConcurrentMap<Position, State> cells) {
        this.cells = cells;
    }

    public List<Animal> getPreys(Position position) {
       return getCells().get(position).getPreys();
    }

    public List<Animal> getPredators(Position position) {
        return getCells().get(position).getPredators();
    }

    public Plant getPlants(Position position) {
        return getCells().get(position).getPlants();
    }

    public Integer getRoughness(Position position) {
        return getCells().get(position).getRoughness();
    }

    public Integer getPreyNumber(){

        List<Animal> a = cells.entrySet().stream().flatMap(e -> e.getValue().getPreys().stream()).collect(Collectors.toList());
        return a.size();
    }

    public Integer getPredatorNumber() {
        List<Animal> a = cells.entrySet().stream().flatMap(e -> e.getValue().getPredators().stream()).collect(Collectors.toList());
        return a.size();
    }
}
