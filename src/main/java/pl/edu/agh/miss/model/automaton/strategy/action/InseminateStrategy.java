package pl.edu.agh.miss.model.automaton.strategy.action;

import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mucha on 21.11.16.
 */
public class InseminateStrategy implements ActionStrategy {
    @Override
    public Position performAction(Set<Cell> cells, Cell currentCell,Position position, Animal animal) {

        Position newPosition = position;
        Gender oppositeGender = animal.getGender().oppositeGender();

        if (animal instanceof Prey) {
            List<Animal> preys = currentCell.getState().getPreys().stream().filter(e -> e.getGender()!=animal.getGender()).filter(e -> e.canInseminate()).collect(Collectors.toList());
            if(animal.getGender() == Gender.MALE) {
                newPosition = performMaleAction(cells, newPosition, oppositeGender, preys,animal);
            }else{
                newPosition = performFemaleAction(cells, animal, newPosition, preys);
            }
        }
//
//        if (animal instanceof Predator) {
//            List<Animal> predators = currentCell.getState().getPredators().stream().filter(e -> e.getGender()!=animal.getGender()).filter(e -> e.canInseminate()).collect(Collectors.toList());
//            if(animal.getGender() == Gender.MALE) {
//                newPosition = performMaleAction(cells, newPosition, oppositeGender, predators, animal);
//            }else{
//                newPosition = performFemaleAction(cells, animal, newPosition, predators);
//            }
//        }

        return newPosition;
    }


    private Position performFemaleAction(Set<Cell> cells, Animal animal, Position newPosition, List<Animal> animals) {
        if(animals.isEmpty()){

            //TODO
            if(animal instanceof Prey)
                EatStrategy.searchFood(cells);
            if(animal instanceof Predator)
                PredatorStrategy.searchFood(cells);
        }
        else {
            animal.impregnate();

        }
        return newPosition;
    }

    private Position performMaleAction(Set<Cell> cells, Position newPosition, Gender oppositeGender, List<Animal> animals, Animal animal) {
        if(!animals.isEmpty()) {
            Collections.shuffle(animals);
            Animal impregnable = animals.get(0);
            impregnable.impregnate();
        }else{
            newPosition = searchToReproduce(cells,oppositeGender,animal);
        }
        return newPosition;
    }

    //Idze w pole gdzie jest najwiecej zwierzat o przeciwnej płci
    private Position searchToReproduce(Set<Cell> cells,Gender gender, Animal animal) {
        Position newPosition = null;
        Long maxNumberOfAnimal =-1L;

        for(Cell cell : cells){
            Long number = 0L;

            //TODO
            if (animal instanceof Prey) {
                number = cell.getState().getPreys().stream().filter(gender::equals).count();
            }
            if (animal instanceof Predator) {
                number = cell.getState().getPredators().stream().filter(gender::equals).count();
            }

            if(number>maxNumberOfAnimal) {
                maxNumberOfAnimal =number;
                newPosition = cell.getPosition();
            }
        }
        return newPosition;

    }

}
