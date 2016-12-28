package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;

import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ReproduceStrategy implements ActionStrategy {
    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {
        List<Animal> infants = animal.giveBirth();
        infants.forEach(e -> e.incrementHunger(5));
        Cell currentCell = cells.stream().filter(e -> e.getPosition().equals(position)).findAny().get();
        if (animal instanceof Prey) {
            currentCell.getState().getPreys().addAll(infants);
        }

        if (animal instanceof Predator) {
            currentCell.getState().getPredators().addAll(infants);
        }

        return position;
    }
}
