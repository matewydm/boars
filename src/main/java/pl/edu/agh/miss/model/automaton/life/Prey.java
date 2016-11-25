package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.AnimalStrategy;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;
import pl.edu.agh.miss.model.automaton.strategy.animal.PreyStrategy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Prey extends Animal implements Foodable {
    private static final AnimalStrategy animalStrategy = new PreyStrategy();
    private static final AnimalMoves animalMoves = new PreyMoves();

    public final static Integer HUNGER_CUTOFF = 40;
    public final static Integer HUNGER_CRITIC = 80;
    public static final Integer KCAL = 80;
    public static final Integer OLD_AGE = 10;


    public Prey(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPreyDefaultMovement());
    }

    @Override
    protected Prey getNewAnimal(Gender gender){
        return new Prey(gender);
    }

    @Override
    public void impregnate() {
        super.impregnate();
        pregnant = new Pregnant(PreyUtils.randomBrood());
    }

    @Override
    public Integer beEaten() {
        die();
        return KCAL;
    }

    @Override
    public Boolean isReadyForReproduce() {
     return PreyUtils.isReadyForReproduce(pregnant);
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

    @Override
    public void updateMortality() {
        Double mortality = getMortality();
        if (getAge() > OLD_AGE) {
            mortality += 10.0;
        }

        if (getHunger() > HUNGER_CRITIC) {
            mortality += 10.0;
        }

        if (getHunger() > HUNGER_CUTOFF) {
            mortality += 3.0;
        }

        if (getHunger() < -HUNGER_CRITIC) {
            mortality -= 5.0;
        }

        if (mortality < 0.0)
            mortality = 0.0;

        setMortality(mortality);
    }


}
