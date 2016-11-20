package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;

/**
 * Created by mucha on 19.11.16.
 */
public class PreyFactory extends AnimalFactory{

    @Override
    protected Animal animalFactoryMethod() {
        return new Prey(Gender.randomGender());
    }

}
