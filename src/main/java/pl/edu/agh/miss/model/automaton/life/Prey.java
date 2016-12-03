package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.DoNothingStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.ReproduceStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.InseminateStrategy;


public class Prey extends Animal implements Foodable {
    private static final AnimalMoves animalMoves = new PreyMoves();

    public final static Integer CRITICAL_SEXUAL_DESIRE = 10;
    public final static Integer HUNGER_CUTOFF = 20;
    public final static Integer HUNGER_CRITIC = 80;
    public static final Integer KCAL = 80;
    public static final Integer OLD_AGE = 40;
    public static final Integer MATURITY = 10;



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

        //TODO podstawowy seter strategi: do przemyslenia jak jest w chuj glodny je,
        // jak jest w chuj pozadliwy to rucha jak nie jest mocno glodny to tez rucha bo co ma robic :D

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
            this.actionStrategy = new DoNothingStrategy();

    }

    public static AnimalMoves getAnimalMoves() {
        return animalMoves;
    }

    @Override
    public void updateMortality() {
        Double mortality = getMortality();
        if (getAge() > OLD_AGE) {
            mortality += 20.0;
        }

        if (getHunger() > HUNGER_CRITIC) {
            mortality += 10.0;
        }

        if (getHunger() > HUNGER_CUTOFF) {
            mortality += 1.0;
        }

//        if (getHunger() < -HUNGER_CRITIC) {
//            mortality -= 5.0;
//        }

        if (mortality < 0.0)
            mortality = 0.0;

        setMortality(mortality);
    }

    @Override
    public void updateSexualDesire() {
        Integer hunger = getHunger();
        Integer age = getAge();

        if (age > MATURITY) {
            if (hunger < -HUNGER_CRITIC)
                incrementSexualDesire(2);
            else if (hunger < -HUNGER_CUTOFF) {
                incrementSexualDesire(1);
            }
            if (hunger > 0)
                decrementSexualDesire(1);
            else if (hunger > HUNGER_CUTOFF)
                decrementSexualDesire(10);
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
