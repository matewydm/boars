package pl.edu.agh.miss.model.automaton.factory;

import org.junit.Test;
import pl.edu.agh.miss.model.automaton.factory.Animal.AnimalFactory;
import pl.edu.agh.miss.model.automaton.factory.Animal.PredatorFactory;
import pl.edu.agh.miss.model.automaton.factory.Animal.PreyFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import static org.junit.Assert.assertTrue;

/**
 * Created by mucha on 20.11.16.
 */
public class AnimalFactoryTest {

    @Test
    public void generatePredator() throws Exception {
        AnimalFactory animalFactory = new PredatorFactory();
        Animal predator = animalFactory.addAnimal();
        assertTrue(predator instanceof Predator);
    }

    @Test
    public void generatePrey() throws Exception {
        AnimalFactory animalFactory = new PreyFactory();
        Animal prey = animalFactory.addAnimal();
        assertTrue(prey instanceof Prey);
    }

}
