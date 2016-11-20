package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.AnimalStrategy;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;
import pl.edu.agh.miss.model.automaton.strategy.PredatorStrategy;
import pl.edu.agh.miss.model.automaton.strategy.PreyStrategy;

import java.util.HashSet;
import java.util.Set;


public class Prey extends Animal implements Foodable {
    private static final AnimalStrategy animalStrategy = new PreyStrategy();
    private static final AnimalMoves animalMoves = new PreyMoves();

    public final static Integer HUNGER_CUTOFF = 40;
    public final static Integer HUNGER_CRITIC = 80;
    public static final Integer KCAL = 80;


    public Prey(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPreyDefaultMovement());
    }

    @Override
    public Set<Animal> born() {
        Integer amount = pregnant.getAnimalsNumber();
        Set<Animal> animals = new HashSet<>(amount);
        for(int i =0; i <amount; i++){
            animals.add(getNewAnimal(PreyUtils.randomGender()));
        }
        return animals;
    }

    protected Prey getNewAnimal(Gender gender){
        return new Prey(gender);
    }

    @Override
    public void impregnate() {
        super.impregnate();
        pregnant = new Pregnant(PreyUtils.randomBrood());
    }

    @Override
    public Integer eat() {
        die();
        return KCAL;
    }

    @Override
    public Boolean isReadyForReproduce() {
     return PreyUtils.isReadyForReproduce(pregnant);
    }

    public static AnimalStrategy getAnimalStrategy() {
        return animalStrategy;
    }

    public static AnimalMoves getAnimalMoves() {
        return animalMoves;
    }
}
