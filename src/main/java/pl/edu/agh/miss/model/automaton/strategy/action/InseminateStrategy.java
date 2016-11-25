package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mucha on 21.11.16.
 */
public class InseminateStrategy implements ActionStrategy {
    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {
        Position newPosition = position;
        Cell currentCell = cells.stream().filter(e -> e.getPosition().equals(position)).findAny().get();
        Gender oppositeGender = animal.getGender().oppositeGender();
        List<Animal> preys = currentCell.getState().getPreys().stream().filter(e -> e.getGender()!=animal.getGender()).filter(e -> !e.isPregnant()).collect(Collectors.toList());
        if(animal.getGender() == Gender.MALE) {
            newPosition = performMaleAction(cells, newPosition, oppositeGender, preys);
        }else{
            newPosition = performFemaleAction(cells, animal, newPosition, preys);
        }

        return newPosition;


    }

    private Position performFemaleAction(Set<Cell> cells, Animal animal, Position newPosition, List<Animal> preys) {
        if(preys.isEmpty()){
   //         newPosition = searchFood(cells);
        }
        else {
            animal.impregnate();

        }
        return newPosition;
    }

    private Position performMaleAction(Set<Cell> cells, Position newPosition, Gender oppositeGender, List<Animal> preys) {
        if(!preys.isEmpty()) {
            Collections.shuffle(preys);
            Animal prey = preys.get(0);
            prey.impregnate();
        }else{

            newPosition = searchToReproduce(cells,oppositeGender);
        }
        return newPosition;
    }

    //Idze w pole gdzie jest najwiecej zwierzat o przeciwnej płci
    private Position searchToReproduce(Set<Cell> cells,Gender gender) {
        Position newPosition = null;
        Long maxNumberOfAnimal =-1L;

        for(Cell cell : cells){
             Long number = cell.getState().getPreys().stream().filter(gender::equals).count();
            if(number>maxNumberOfAnimal) {
                maxNumberOfAnimal =number;
                newPosition = cell.getPosition();
            }
        }
        return newPosition;

    }

    private Position searchFood(Set<Cell> cells) {
        Position foodPosition = null;

        int maxQuantity = -1;
        int currentSize;

        for (Cell cell : cells) {
            currentSize = cell.getState().getPlants().size();
            if (currentSize > maxQuantity) {
                maxQuantity = currentSize;
                foodPosition = cell.getPosition();
            }
        }

        return foodPosition;
    }
}
