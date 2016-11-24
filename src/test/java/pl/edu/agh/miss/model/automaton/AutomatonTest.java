package pl.edu.agh.miss.model.automaton;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.moves.PreyMoves;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.*;

public class AutomatonTest {
    @Test
    public void nextState() throws Exception {
        Automaton s1 = new Automaton();
        Assert.assertNotNull("Null Automaton",s1.nextState());
    }

    @Test
    public void drawState() throws Exception {
        Automaton s1 = new Automaton();
        CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(100)));
        Map<Position,State> cellMap = cellsFactory.cellsFactoryMethod();
        s1.setCells(cellMap);

        s1 = s1.nextState();
        s1 = s1.nextState();


    }


}