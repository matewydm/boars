package pl.edu.agh.miss.model.automaton.life;


import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.AnimalStrategy;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PredatorMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;
import pl.edu.agh.miss.model.automaton.strategy.animal.PredatorStrategy;

import java.util.Set;

public class Predator extends Animal {
    private static final AnimalStrategy animalStrategy = new PredatorStrategy();
    private static final AnimalMoves animalMoves = new PredatorMoves();

    public Predator(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPredatorDefaultMovement());

    }

    @Override
    public Set<Animal> born() {
        return null;
    }

    @Override
    public Boolean isReadyForReproduce() {
        return null;
    }

    @Override
    public void setActionStrategy() {
        //TODO
        this.actionStrategy = new EatStrategy();
    }

    public static AnimalStrategy getAnimalStrategy() {
        return animalStrategy;
    }

    public static AnimalMoves getAnimalMoves() {
        return animalMoves;
    }
}
