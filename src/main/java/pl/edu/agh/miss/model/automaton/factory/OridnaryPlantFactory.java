package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.life.Plant;

/**
 * Created by mucha on 19.11.16.
 */
public class OridnaryPlantFactory extends PlantFactory {
    @Override
    protected Plant plantFactoryMethod() {
        return new Plant();
    }
}
