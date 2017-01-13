package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;

import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.life.Supremator;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ReproduceStrategy implements ActionStrategy {
    @Override
    public Position performAction(Set<Cell> cells, Cell currentCell, Position position, Animal animal) {
        List<Animal> infants = animal.giveBirth();
        infants.forEach(e -> e.incrementHunger(5));

        if (animal instanceof Prey) {
            currentCell.getState().getPreys().addAll(infants);
        }

        if (animal instanceof Supremator) {
            currentCell.getState().getSupremators().addAll(infants);
        }

        if (animal instanceof Predator) {
            currentCell.getState().getPredators().addAll(infants);
        }




        return position;
    }
}
