package pl.edu.agh.miss.model.automaton.life;


import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PredatorMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.InseminateStrategy;
import pl.edu.agh.miss.model.automaton.strategy.action.ReproduceStrategy;

public class Predator extends Animal {
    private static final AnimalMoves animalMoves = new PredatorMoves();

    public final static Integer CRITICAL_SEXUAL_DESIRE = 6;
    public final static Integer HUNGER_CUTOFF = 10;
    public final static Integer HUNGER_CRITIC = 40;
    public static final Integer KCAL = 30;
    public static final Integer OLD_AGE = 12;
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
        // TODO
    }

    @Override
    public void updateSexualDesire() {

    }

    @Override
    public boolean canInseminate() {
        //TODO
        return false;
    }

    @Override
    public void decrementMortality(Double val) {
        //TODO
    }
}
