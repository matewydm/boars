package pl.edu.agh.miss.model.automaton.life;

import java.util.HashSet;
import java.util.Set;


public class Prey extends Animal implements Foodable {


    public static final Integer KCAL = 80;

    public Prey(Gender gender) {
        super(gender);
    }

    @Override
    public Set<Animal> born() {
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
    public void impregnate() {
        super.impregnate();
        pregnant = new Pregnant(PreyUtils.randomBrood());
    }

    @Override
    public Integer eat() {
        die();
        return KCAL;
    }

    @Override
    public Boolean isReadyForReproduce() {
     return PreyUtils.isReadyForReproduce(pregnant);
    }
}
