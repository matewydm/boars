package pl.edu.agh.miss.model.automaton;

import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.life.PreyUtils;
import pl.edu.agh.miss.model.automaton.strategy.PreyStrategy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class AutomatonTest {
    @Test
    public void nextState() throws Exception {
        Automaton s1 = new Automaton();
        Assert.assertNotNull("Null Automaton",s1.nextState());
    }
    @Test
    public void cellContains() throws Exception {
        Set<Cell> cells = new HashSet<>();
        cells.add(new Cell(new Position(1,2),new State(null,null,null)));
        Cell cell = new Cell(new Position(1,2),new State(null,null,null));
        Assert.assertTrue("Contains given position",cells.contains(cell));
    }

    @Test
    public void cellNotContained() throws Exception {
        Set<Cell> cells = new HashSet<>();
        Set<Prey> preys = new HashSet<>();
        preys.add(new Prey(Gender.FEMALE));
        cells.add(new Cell(new Position(1,2),new State(preys,null,null)));
        Cell cell = new Cell(new Position(1,2),new State(null,null,null));
        Assert.assertFalse("Given position not contained",cells.contains(cell));
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