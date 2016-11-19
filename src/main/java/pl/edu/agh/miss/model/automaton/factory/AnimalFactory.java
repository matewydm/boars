package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Plant;

/**
 * Created by mucha on 19.11.16.
 */
public abstract class AnimalFactory {

    public Animal addAnimal(Gender gender) {
        return animalFactoryMethod(gender);
    }

    protected abstract Animal animalFactoryMethod(Gender gender);

}
