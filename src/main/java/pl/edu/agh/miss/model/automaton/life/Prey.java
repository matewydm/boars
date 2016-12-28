package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.DoNothingStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.ReproduceStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.InseminateStrategy;


public class Prey extends Animal {
    private static final AnimalMoves animalMoves = new PreyMoves();

    public final static Integer CRITICAL_SEXUAL_DESIRE = 6;
    public final static Integer HUNGER_CUTOFF = 20;
    public final static Integer HUNGER_CRITIC = 60;
    public static final Integer KCAL = 60;
    public static final Integer OLD_AGE = 11;
    public static final Integer MATURITY = 3;



    public Prey(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPreyDefaultMovement());
    }

    @Override
    protected Prey getNewAnimal(Gender gender){
        return new Prey(gender);
    }

    @Override
    public Integer beEaten() {
        die();
        return KCAL;
    }

    @Override
    public Boolean isReadyForReproduce() {
            return pregnant != null && PreyUtils.isReadyForReproduce(pregnant);
    }

    @Override
    public void setActionStrategy() {

        //TODO podstawowy seter strategi: do przemyslenia jak jest glodny je,

        boolean isCriticalHungry = this.hunger >= HUNGER_CRITIC;
        boolean isLittleHungry = this.hunger > HUNGER_CUTOFF;
        boolean isCriticalEager = this.sexualDesire >= CRITICAL_SEXUAL_DESIRE;


        if (isReadyForReproduce())
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
        System.out.println("mortality = " + mortality);
        if (getAge() > OLD_AGE) {
            mortality += 10.0;
        }

        if (getHunger() > HUNGER_CRITIC) {
            mortality += 10.0;
            System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        }

        if (getHunger() > HUNGER_CUTOFF) {
            mortality += 5.0;
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
}
