package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.AnimalType;
import pl.edu.agh.miss.model.automaton.life.SuprematorUtils;

import java.util.Iterator;
import java.util.Set;

import static pl.edu.agh.miss.model.automaton.life.PredatorUtils.randomGenerator;
import static pl.edu.agh.miss.model.automaton.life.SuprematorUtils.PREDATOR_KILLING_CHANCES;
import static pl.edu.agh.miss.model.automaton.life.SuprematorUtils.PREY_KILLING_CHANCES;

public class SuprematorStrategy implements ActionStrategy {

    @Override
    public Position performAction(Set<Cell> cells, Cell currentCell, Position position, Animal animal) {
        Position newPosition = position;

        if(!currentCell.getState().getPredators().isEmpty()) {
            hunt(currentCell,animal, AnimalType.PREDATOR);
        }
        else if (!currentCell.getState().getPreys().isEmpty()) {
            hunt(currentCell,animal, AnimalType.PREY);
        }
        else {
            newPosition = searchFood(cells);
        }

        return newPosition;
    }

    public static Position searchFood(Set<Cell> cells) {
        int size = cells.size();
        int choose = randomGenerator.nextInt(size);
        Cell cell = null;
        Iterator<Cell> it = cells.iterator();
        while(choose-- >= 0) {
            cell = it.next();
        }
        return cell.getPosition();
    }

    private void hunt(Cell currentCell, Animal animal, AnimalType animalType) {
        Animal suprematorsPrey = null;

        int chances = 100;

        switch (animalType) {
            case PREY:  {
                chances = PREY_KILLING_CHANCES;
                suprematorsPrey = currentCell.getState().getPreys().get(0);
                break;
            }
            case PREDATOR: {
                chances = PREDATOR_KILLING_CHANCES;
                suprematorsPrey = currentCell.getState().getPredators().get(0);
                break;
            }
        }

        if (randomGenerator.nextInt(100/chances) == 0) {
            eat(currentCell, animal,suprematorsPrey);
        }
        else {
            fiasco(suprematorsPrey);
        }
    }

    private void eat(Cell currentCell, Animal animal, Animal prey) {
        animal.eat(prey);
    }

    private void fiasco(Animal prey) {
        prey.setHunted(Boolean.TRUE);
    }
}
