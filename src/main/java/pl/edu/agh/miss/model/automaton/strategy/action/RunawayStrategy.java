package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Set;
import static pl.edu.agh.miss.model.automaton.life.PredatorUtils.randomGenerator;

public class RunawayStrategy implements ActionStrategy {

    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {




        return position;

    }

}
