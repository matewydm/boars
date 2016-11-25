package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;

import java.util.*;
import java.util.stream.Collectors;

public class Automaton {
    private final static int SIZE = 20;
    private final static byte PREY_DEFAULT_MOVEMENT = 3;
    private final static byte PREDATOR_DEFAULT_MOVEMENT = 3;

    private Map<Position,State> cells;

    private PreyMoves preyMoves;

    private Automaton () {
        cells = new HashMap<>();
        preyMoves = new PreyMoves();
    }
    public Automaton(CellsFactory factory){
        this.cells = factory.cellsFactoryMethod();
        preyMoves = new PreyMoves();

    }
    //ile jest prey n a całej mapie
    public Integer getPreyNumber(){

        List<Animal> a = cells.entrySet().stream().flatMap(e -> e.getValue().getPreys().stream()).collect(Collectors.toList());
        return a.size();
    }
    //ile jest plan na całej mapie
    public Integer getPlantNumber(){
         return cells.entrySet().stream().flatMap(e -> e.getValue().getPlants().stream()).map(e -> e.getValue()).reduce(Integer::sum).get();
    }

    public Automaton nextState(){
        Automaton automaton = getInstance();
        // generowanie strategi - odbedzie sie dzieki wartosciom w instancji Prey
        cells.entrySet().stream().forEach(e -> e.getValue().getPreys().stream().forEach(Animal::setActionStrategy));
        //tutaj bedziemy robic dzialanie na podstawie tego jaka strategia jest zawarta w obiekcie Animal

        //Map<Position,State> newMap = automaton.getCells();
        for (Position position : cells.keySet()){
            for (Animal prey : cells.get(position).getPreys()){
                Set<Position> positionSet = preyMoves.calculate(position,prey);
                Set<Cell> cellSet = getCellsArea(positionSet);
                Position newPosition = prey.performAction(cellSet,position);
                // zjadł roślinę, urodził młode, zaktualizowało się to na obecnej mapie
                State newState = automaton.getState(newPosition);
                newState.getPreys().add(prey);
                automaton.update(position,newState);
                // przenoszę zwierzę na nową mapę
            }
        }




        // tutaj wykonywanie ruchów przez drapieżców



        //przenoszenie roślin zaktualizowanych przez ofiary
        for (Position position: cells.keySet()) {
            automaton.addPlants(position,this.getPlants(position));
        }
//        cells.entrySet().stream().forEach(e -> automaton.addPlants(e.getKey(),this.getPlants(e.getKey())));


        //aktualizacja atrybutów zwierząt i roślin - do osobnej metody
        for (Position position: cells.keySet()) {
            State currentState = automaton.getState(position);

            for (Plant plant: currentState.getPlants()) {
                plant.grow();
            }
            for (Animal animal: currentState.getPreys()) { // na razie tylko ofiary
                animal.update();
            }
        }

        // usuwanie śmierdziuchów, które gniją
        for (Position position: cells.keySet()) {
            List<Animal> preys = automaton.getPreys(position);
            List<Animal> dead = new LinkedList<>();
            for(Animal prey: preys){
                if(!prey.isAlive()){
                    dead.add(prey);
                }
            }
            preys.removeAll(dead);

        }




        return automaton;
    }

    private void update(Position position, State state) {
        cells.put(position,state);

    }

    private void addPlants(Position position, List<Plant> plants) {
        getPlants(position).addAll(plants);
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

    public List<Animal> getPreys(Position position) {
       return getCells().get(position).getPreys();
    }

    public List<Plant> getPlants(Position position) {
        return getCells().get(position).getPlants();
    }
}
