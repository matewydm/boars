package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Plant;

import java.util.Set;

/**
 * Created by mucha on 19.11.16.
 */
public abstract class AnimalFactory {

    public Animal addAnimal() {
        return animalFactoryMethod();
    }

    protected abstract Animal animalFactoryMethod();


}
