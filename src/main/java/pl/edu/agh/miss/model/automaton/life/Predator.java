package pl.edu.agh.miss.model.automaton.life;


import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PredatorMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.InseminateStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.PredatorStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.ReproduceStrategy;

public class Predator extends Animal {
    private static final AnimalMoves animalMoves = new PredatorMoves();

    public final static Integer CRITICAL_SEXUAL_DESIRE = 6;
    public final static Integer HUNGER_CUTOFF = 40;
    public final static Integer HUNGER_CRITIC = 60;
    public static final Integer KCAL = 30;
    public static final Integer OLD_AGE = 15;
    public static final Integer MATURITY = 4;


    public Predator(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPredatorDefaultMovement());

    }

    @Override
    protected Animal getNewAnimal(Gender gender) {
        return new Predator(gender);
    }

    @Override
    public Boolean isReadyForReproduce() {
        return pregnant != null && PredatorUtils.isReadyForReproduce(pregnant);
    }

    @Override
    public void setActionStrategy() {
        boolean isCriticalHungry = this.hunger >= HUNGER_CRITIC;
        boolean isLittleHungry = this.hunger > HUNGER_CUTOFF;
        boolean isCriticalEager = this.sexualDesire >= CRITICAL_SEXUAL_DESIRE;
//        System.out.println("hunger : " + getHunger());



        if (isReadyForReproduce()) {
            this.actionStrategy = new ReproduceStrategy();
//            System.out.println("reproduce");
        }
        else if (isLittleHungry) {
            this.actionStrategy = new PredatorStrategy();
//            System.out.println("predator");
        }
        else if (isCriticalEager && canInseminate()) {
            this.actionStrategy = new InseminateStrategy();
//            System.out.println("inseminate");
//            System.out.println("\tinseminate strategyy");
//            System.out.println("\thunger :" + getHunger());
//            System.out.println("\tage : " + getAge());
//            System.out.println("\tmortality : " + getMortality());
        }
//        else if (isLittleHungry) {
//            this.actionStrategy = new PredatorStrategy();
//            System.out.println("predator");
//        }
//        else if(canInseminate())
//            this.actionStrategy = new InseminateStrategy();
        else {
            this.actionStrategy = new PredatorStrategy();
        }
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

    @Override
    public Integer beEaten() {
        return null; //TODO
    }
}
