package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Set;

public class DoNothingStrategy implements ActionStrategy {
    private static final Integer SEXUAL_DESIRE = 1;
    private static final Double REST_VALUE =5.0;
    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {
        animal.decrementSexualDesire(SEXUAL_DESIRE);
        animal.decrementMortality(REST_VALUE);
        return position;
    }
}
