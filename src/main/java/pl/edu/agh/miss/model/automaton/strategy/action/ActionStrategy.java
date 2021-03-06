package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Set;

/**
 * Created by mucha on 21.11.16.
 */
public interface ActionStrategy {

    Position performAction(Set<Cell> cells, Cell currentCell,Position position, Animal animal);
}
