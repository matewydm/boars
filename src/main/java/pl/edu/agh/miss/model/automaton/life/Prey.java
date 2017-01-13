package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.*;

import java.util.Set;


public class Prey extends Animal {
    private static final AnimalMoves animalMoves = new PreyMoves();

    public final static Integer CRITICAL_SEXUAL_DESIRE = 6;
    public final static Integer HUNGER_CUTOFF = 20;
    public final static Integer HUNGER_CRITIC = 60;
    public static final Integer KCAL = 100;
    public static final Integer OLD_AGE = 11;
    public static final Integer MATURITY = 3;
    private static byte PREY_DEFAULT_MOVEMENT = 1;



    public Prey(Gender gender) {
        super(gender);
        setDefaultMovement(PREY_DEFAULT_MOVEMENT);
    }

    @Override
    protected Prey getNewAnimal(Gender gender){
        return new Prey(gender);
    }

    @Override
    public Boolean isReadyForReproduce() {
            return pregnant != null && PreyUtils.isReadyForReproduce(pregnant);
    }

    @Override
    public void setActionStrategy(Set<Cell> cells, Cell currentCell, Position position) {

        boolean isCriticalHungry = this.hunger >= HUNGER_CRITIC;
        boolean isLittleHungry = this.hunger > HUNGER_CUTOFF;
        boolean isCriticalEager = this.sexualDesire >= CRITICAL_SEXUAL_DESIRE;

        if (isHunted/*PreyUtils.predatorNearby(cells)*/) { // do wyboru aktualne pole albo cała okolica! WYBRANE: AKT. POLE
            this.actionStrategy = new RunawayStrategy();
            setHunted(Boolean.FALSE);
        }
        else if (isReadyForReproduce())
            this.actionStrategy = new ReproduceStrategy();
        else if (isCriticalHungry)
            this.actionStrategy = new EatStrategy();
        else if (isCriticalEager && canInseminate())
            this.actionStrategy = new InseminateStrategy();
        else if (isLittleHungry)
            this.actionStrategy = new EatStrategy();
        else if(canInseminate())
            this.actionStrategy = new InseminateStrategy();
        else
            this.actionStrategy = new EatStrategy();

    }

    public static AnimalMoves getAnimalMoves() {
        return animalMoves;
    }

    @Override
    public void updateMortality() {
        Double mortality = getMortality();
        if (getAge() > OLD_AGE) {
            mortality += 7.0;
        }

        if (getHunger() > HUNGER_CRITIC) {
            mortality += 5.0;
        }

        if (getHunger() > HUNGER_CUTOFF) {
            mortality += 3.0;
        }

        if (getHunger() < -HUNGER_CRITIC) {
            mortality -= 2.0;
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
                incrementSexualDesire(2);
            }
            else if (hunger < -HUNGER_CUTOFF) {
                incrementSexualDesire(1);
            }
            if (hunger > 0) {
              //  System.out.println("dec 1");
                decrementSexualDesire(1); //hunger zawsze większy od 0
            }
            else if (hunger > HUNGER_CUTOFF) {
                decrementSexualDesire(10);
            }
        }
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

    public static byte getPreyDefaultMovement() {
        return PREY_DEFAULT_MOVEMENT;
    }


}
