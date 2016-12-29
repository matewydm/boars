package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Iterator;
import java.util.Set;

import static pl.edu.agh.miss.model.automaton.life.PredatorUtils.randomGenerator;

public class PredatorStrategy implements ActionStrategy {
    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {
        Position newPosition = position;
        Cell currentCell = cells.stream().filter(e -> e.getPosition().equals(position)).findAny().get();

        if (randomGenerator.nextInt(4) == 0) { // 25% szans
            if (!currentCell.getState().getPreys().isEmpty()) {
                eatOnCurrentPosition(currentCell, animal);
            } else {
                newPosition = searchFood(cells);
            }
        }

        return newPosition;
    }


    private Position searchFood(Set<Cell> cells) {
        Iterator<Cell> it = cells.iterator();
        Cell cell = null;

        for (; it.hasNext(); ) {
            cell = it.next();
            if (randomGenerator.nextInt(2) == 0)
                break;
        }

        return cell.getPosition();
    }

    private void eatOnCurrentPosition(Cell currentCell, Animal animal) {
        animal.eat(currentCell.getState().getPreys().get(0));
    }

}