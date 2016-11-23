package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;

import java.util.List;
import java.util.Set;

/**
 * Created by mucha on 21.11.16.
 */
public interface ActionStrategy {

    Position performAction(Set<Cell> cells, Position position);
}
