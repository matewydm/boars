package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.List;
import java.util.Set;

import static pl.edu.agh.miss.model.automaton.life.PreyUtils.randomGenerator;

/**
 * Created by mucha on 21.11.16.
 */
public class EatStrategy implements ActionStrategy{
    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {
        Position newPosition = position;

        Position foodPosition = searchFood(cells);

        Cell currentCell = cells.stream().filter(e -> e.getPosition().equals(position)).findAny().get();

        if (foodPosition.equals(position))
            eatOnCurrentPosition(currentCell,animal);
        else {
            newPosition = foodPosition;
        }
        return newPosition;
    }

    private Position searchFood(Set<Cell> cells) {
        Position foodPosition = null;

        int maxQuantity = 0;
        int currentSize;

        for (Cell cell : cells) {
            currentSize = cell.getState().getPlants().size();
            if (currentSize> maxQuantity) {
                maxQuantity = currentSize;
                foodPosition = cell.getPosition();
            }
        }


        return foodPosition;
    }

    private void eatOnCurrentPosition(Cell currentCell, Animal animal) {


        int genNumber = randomGenerator.nextInt(2)+1; // 1..2
        int size =currentCell.getState().getPlants().size();
        int genIndex = 0;
        for (int i = 0; i < genNumber; i++) {
            genIndex = randomGenerator.nextInt(size);
            animal.eat(currentCell.getState().getPlants().get(genIndex));
        }


    }
}
