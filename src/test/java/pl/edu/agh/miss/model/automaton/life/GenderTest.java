package pl.edu.agh.miss.model.automaton.life;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenderTest {
    @Test
    public void oppositeGender() throws Exception {
        Gender gender = Gender.FEMALE;
        Assert.assertTrue("Not the same gender",Gender.MALE == gender.oppositeGender());
    }

}