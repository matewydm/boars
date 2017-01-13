package pl.edu.agh.miss.model.automaton.strategy.action;

import javafx.geometry.Pos;
import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.PredatorUtils;

import java.util.Iterator;
import java.util.Set;

import static pl.edu.agh.miss.model.automaton.life.PredatorUtils.randomGenerator;

public class PredatorStrategy implements ActionStrategy {
    @Override
    public Position performAction(Set<Cell> cells, Cell currentCell, Position position, Animal animal) {
        Position newPosition = position;

        if(!currentCell.getState().getPreys().isEmpty()) {
            hunt(currentCell,animal);
        }
        else {
            newPosition = searchFood(cells);
        }

        return newPosition;
    }

    private Position searchFood(Set<Cell> cells) {

        int size = cells.size();
        int choose = randomGenerator.nextInt(size);
        Cell cell = null;
        Iterator<Cell> it = cells.iterator();
        while(choose-- >= 0) {
            cell = it.next();
        }
        return cell.getPosition();
    }

    private void hunt(Cell currentCell, Animal animal) {
        Animal prey = currentCell.getState().getPreys().get(0);
        if (randomGenerator.nextInt(100/ PredatorUtils.KILLING_CHANCES) == 0) {
            eat(currentCell, animal,prey);
        }
        else {
            fiasco(prey);
        }
    }

    private void eat(Cell currentCell, Animal animal, Animal prey) {
        animal.eat(prey);
    }

    private void fiasco(Animal prey) {
        prey.setHunted(Boolean.TRUE);
    }

}