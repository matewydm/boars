package pl.edu.agh.miss.model.automaton.strategy;

import org.junit.Test;
import pl.edu.agh.miss.model.automaton.AnimalStrategy;
import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;
import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.awt.*;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StrategyTest {
    @Test
    public void checkCriticalBehaviour() throws Exception {
        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(30,30), new GeneralStateFactory(new Compaction(100)));
        Map<Position,State> cellMap = cellsFactory.cellsFactoryMethod();
        Animal prey = cellMap.get(new Position(5,20)).getPreys().get(0);
        AnimalStrategy animalStrategy = new PreyStrategy();
        prey.incrementHunger(Prey.HUNGER_CRITIC);
        animalStrategy.action(new Position(5,20),prey,cellMap);
        assertTrue(prey.getHunger()<Prey.HUNGER_CRITIC);
    }
}
