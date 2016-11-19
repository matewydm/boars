package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Plant;

/**
 * Created by mucha on 19.11.16.
 */
public abstract class PlantFactory {

    public Plant addPlant() {
        return plantFactoryMethod();
    }

    protected abstract Plant plantFactoryMethod();

}
