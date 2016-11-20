package pl.edu.agh.miss.model.automaton.factory.Animal;

import pl.edu.agh.miss.model.automaton.life.Animal;

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
