package pl.edu.agh.miss.model.automaton.life;


import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.moves.PredatorMoves;
import pl.edu.agh.miss.model.automaton.strategy.action.EatStrategy;

public class Predator extends Animal {
    private static final AnimalMoves animalMoves = new PredatorMoves();

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
        return null;
    }

    @Override
    public void setActionStrategy() {
        //TODO
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
}
