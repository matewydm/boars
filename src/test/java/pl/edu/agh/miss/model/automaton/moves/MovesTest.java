package pl.edu.agh.miss.model.automaton.moves;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MovesTest {
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
        List<Prey> preys = new LinkedList<>();
        preys.add(new Prey(Gender.FEMALE));
        cells.add(new Cell(new Position(1,2),new State(preys,null,null)));
        Cell cell = new Cell(new Position(1,2),new State(null,null,null));
        Assert.assertFalse("Given position not contained",cells.contains(cell));
    }

    @Test
    public void preyPositionsDefaultMovement() throws Exception {
        Animal prey = new Prey(Gender.FEMALE);
        Position position = new Position(0,0);
        Set<Position> positions = prey.getAnimalMoves().calculate(position,prey);
        Set<Position> correctValues = new HashSet<>();

        for(int i = 0; i <= prey.getMovement(); i++) {
            for (int j = 0; j <= prey.getMovement(); j++) {
                correctValues.add(new Position(i,j));
            }
        }

        assertEquals("Check whether default positions are correct",positions,correctValues);

    }
}
