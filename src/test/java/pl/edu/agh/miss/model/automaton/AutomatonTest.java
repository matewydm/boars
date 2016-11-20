package pl.edu.agh.miss.model.automaton;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;

import java.util.*;

import static org.junit.Assert.*;

public class AutomatonTest {
    @Test
    public void nextState() throws Exception {
        Automaton s1 = new Automaton();
        Assert.assertNotNull("Null Automaton",s1.nextState());
    }


}