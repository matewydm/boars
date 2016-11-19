package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Predator;

/**
 * Created by mucha on 19.11.16.
 */
public class PredatorFactory extends AnimalFactory {

    @Override
    protected Animal animalFactoryMethod(Gender gender) {
        return new Predator(gender);
    }
}
