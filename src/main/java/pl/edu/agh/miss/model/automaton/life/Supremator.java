package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.moves.PredatorMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.*;

import java.util.Set;

public class Supremator extends Animal {

    private static final AnimalMoves animalMoves = new PredatorMoves();

    public final static Integer CRITICAL_SEXUAL_DESIRE = 6;
    public final static Integer HUNGER_CUTOFF = 80;
    public final static Integer HUNGER_CRITIC = 100;
    public final static Integer KCAL = 30;
    public static final Integer OLD_AGE = 15;
    public static final Integer MATURITY = 8;
    private static byte SUPREMATOR_DEFAULT_MOVEMENT = 1;



    public Supremator(Gender gender) {
        super(gender);
        setDefaultMovement(SUPREMATOR_DEFAULT_MOVEMENT);
        setKCAL(KCAL);
    }

    @Override
    protected Animal getNewAnimal(Gender gender) {
        return new Supremator(gender);
    }

    @Override
    public Boolean isReadyForReproduce() {
        return pregnant != null && SuprematorUtils.isReadyForReproduce(pregnant);
    }

    @Override
    public void setActionStrategy(Set<Cell> cells, Cell currentCell, Position position) {
        boolean isLittleHungry = this.hunger > HUNGER_CUTOFF;
        boolean isCriticalEager = this.sexualDesire >= CRITICAL_SEXUAL_DESIRE;

        if (isReadyForReproduce()) {
            this.actionStrategy = new ReproduceStrategy();
        }
        else if (isLittleHungry) {
            this.actionStrategy = new SuprematorStrategy();
        }
        else if (isCriticalEager && canInseminate()) {
            this.actionStrategy = new InseminateStrategy();
        }
        else {
            this.actionStrategy = new SuprematorStrategy();
        }
    }

    @Override
    public void updateMortality() {
        Double mortality = getMortality();

        if (getAge() > OLD_AGE) {
            mortality += 7.0;

        }

        if (getHunger() > HUNGER_CRITIC) {
            mortality += 10.0;

        }

        if (getHunger() > HUNGER_CUTOFF) {
            mortality += 5.0;
        }

        if (getHunger() < -HUNGER_CRITIC) {
            mortality -= 5.0;
        }

        if (mortality < 0.0)
            mortality = 0.0;

        setMortality(mortality);
    }

    @Override
    public void updateSexualDesire() {
        Integer hunger = getHunger();

        if (age > MATURITY) {
            if (hunger < -HUNGER_CRITIC) {
                incrementSexualDesire(6);
            }
            else if (hunger < -HUNGER_CUTOFF) {
                incrementSexualDesire(3);
            }
            if (hunger > 0) {
                //  System.out.println("dec 1");
                decrementSexualDesire(1); //hunger zawsze większy od 0
            }
            else if (hunger > HUNGER_CUTOFF) {
                decrementSexualDesire(10);
            }
        }

        if (sexualDesire > CRITICAL_SEXUAL_DESIRE)
            sexualDesire = CRITICAL_SEXUAL_DESIRE;
        if (sexualDesire < 0)
            sexualDesire = 0;
    }



    @Override
    public boolean canInseminate() {
        Integer age = getAge();
        return age > MATURITY && !isPregnant();
    }

    @Override
    public void decrementMortality(Double val) {
        if(age<= OLD_AGE && mortality - val >0)
            mortality -=val;
    }

    public static byte getPredatorDefaultMovement() {
        return SUPREMATOR_DEFAULT_MOVEMENT;
    }
}
