package pl.edu.agh.miss.model.automaton.strategy;

import org.junit.Test;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class BornStrategy {
    @Test
    public void checkBornAnimals() throws Exception {
        Animal mother = new Prey(Gender.FEMALE);
        mother.impregnate();
        List<Animal> infants = mother.born();
        Animal newBorn = infants.get(0);
        assertTrue(newBorn instanceof Prey);
    }

    @Test
    public void checkCellUpdate() throws Exception {

    }

}
