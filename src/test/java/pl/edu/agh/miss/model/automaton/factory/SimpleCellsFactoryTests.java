package pl.edu.agh.miss.model.automaton.factory;

import org.junit.Test;
import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;

import java.awt.*;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mucha on 20.11.16.
 */
public class SimpleCellsFactoryTests {

    @Test
    public void generateCellsTest() throws Exception {
        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(10,20), new GeneralStateFactory(new Compaction(100)));
        Map<Position,State> cellMap = cellsFactory.cellsFactoryMethod();
        assertTrue(cellMap.get(new Position(5,10)) instanceof State);
    }

    @Test
    public void generateCellsOutOfRangeTest() throws Exception {
        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(10,20), new GeneralStateFactory(new Compaction(100)));
        Map<Position,State> cellMap = cellsFactory.cellsFactoryMethod();
        assertFalse(cellMap.get(new Position(5,20)) instanceof State);
    }

    @Test
    public void generateCellsContainTest() throws Exception {
        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(10,20), new GeneralStateFactory(new Compaction(100)));
        Map<Position,State> cellMap = cellsFactory.cellsFactoryMethod();
        assertTrue(cellMap.get(new Position(9,19)).getPlants() != null);
    }

}
