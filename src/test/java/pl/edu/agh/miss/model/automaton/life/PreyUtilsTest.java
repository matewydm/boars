package pl.edu.agh.miss.model.automaton.life;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PreyUtilsTest {
    @Test
    public void isReadyForReproduceTrue() throws Exception {
        Pregnant pregnant = new Pregnant(1);
        for(int i =0; i<51;i++) pregnant.incrementDays();;
        Assert.assertTrue(PreyUtils.isReadyForReproduce(pregnant));
    }

    @Test
    public void isReadyForReproduceFalse() throws Exception {
        Pregnant pregnant = new Pregnant(1);
        Assert.assertFalse(PreyUtils.isReadyForReproduce(pregnant));
    }
    @Test
    public void randomGender() throws Exception {
        Gender gender = PreyUtils.randomGender();
        assertTrue(gender == Gender.FEMALE || gender == Gender.MALE);
    }

    @Test
    public void getNewPrey() throws Exception {
        Animal animal = new Prey(Gender.FEMALE);
        Animal infant = animal.getNewAnimal(Gender.FEMALE);
        assertFalse(infant instanceof Predator);
    }

    @Test
    public void getNewPredator() throws Exception {
        Animal animal = new Predator(Gender.FEMALE);
        Animal infant = animal.getNewAnimal(Gender.MALE);
        assertTrue(infant instanceof Predator);
    }

    @Test
    public void randomBrood() throws Exception {
        int high = 5;
        int low = 1;
        for(int i=0;i<20;i++) {
            int random = PreyUtils.randomBrood();
            assertTrue("Error, random is too high", high >= random);
            assertTrue("Error, random is too low", low <= random);
        }
    }

}