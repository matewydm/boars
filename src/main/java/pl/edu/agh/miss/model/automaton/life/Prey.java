package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.Automaton;

import java.util.HashSet;
import java.util.Set;


public class Prey extends Animal implements Foodable {


    public static final Integer kcal = 80;
    public Prey(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPreyDefaultMovement());
    }

    @Override
    public Set<Animal> reproduce() {
        Integer amount = pregnant.getAnimalsNumber();
        Set<Animal> animals = new HashSet<>(amount);
        for(int i =0; i <amount; i++){
            animals.add(getNewAnimal(PreyUtils.randomGender()));
        }
        return animals;
    }

    protected Prey getNewAnimal(Gender gender){
        return new Prey(gender);
    }

    @Override
    public Integer eat() {
        die();
        return kcal;
    }

    @Override
    public Boolean isReadyForReproduce() {
     return PreyUtils.isReadyForReproduce(pregnant);
    }
}
