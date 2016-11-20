package pl.edu.agh.miss.model.automaton;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.strategy.PreyStrategy;

import java.util.*;

import static org.junit.Assert.*;

public class AutomatonTest {
    @Test
    public void nextState() throws Exception {
        Automaton s1 = new Automaton();
        Assert.assertNotNull("Null Automaton",s1.nextState());
    }
    @Test
    public void cellContains() throws Exception {
        Map<Position,State> cells = new HashMap<>();
        cells.put(new Position(1,2),new State(null,null,null));
        Assert.assertTrue("Contains given position",cells.containsKey(new Position(1,2)));
        Assert.assertTrue("Contains given position",cells.containsValue(new State(null,null,null)));
    }

    @Test
    public void cellNotContained() throws Exception {
        Map<Position,State> cells = new HashMap<>();
        Set<Prey> preys = new HashSet<>();
        preys.add(new Prey(Gender.FEMALE));
        cells.put(new Position(1,2),new State(preys,null,null));
        State state = new State(null,null,null);
        Assert.assertNotEquals(state,cells.get(new Position(1,2)));
    }

    @Test
    public void preyPositionsDefaultMovement() throws Exception {
        Animal prey = new Prey(Gender.FEMALE);
        Position position = new Position(0,0);
        NeighbourhoodStrategy preyStrategy = new PreyStrategy();
        Set<Position> positions = preyStrategy.calculate(position,prey);
        Set<Position> correctValues = new HashSet<>();

        for(int i = 0; i <= prey.getMovement(); i++) {
            for (int j = 0; j <= prey.getMovement(); j++) {
                correctValues.add(new Position(i,j));
            }
        }

        Iterator<Position> it = positions.iterator();
        while(it.hasNext())
            System.out.println(it.next());

        assertEquals("Check whether default positions are correct",positions,correctValues);

    }

}