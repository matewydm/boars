package pl.edu.agh.miss.model.automaton;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;

import java.awt.*;

public class AutomatonTest {
    @Test
    public void nextState() throws Exception {
        Automaton s1 = new Automaton(new SimpleCellsFactory(new Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(100))));
        Assert.assertNotNull("Null Automaton",s1.nextState());
    }

    @Test
    public void drawState() throws Exception {
        Automaton s1 = new Automaton(new SimpleCellsFactory(new Dimension(Automaton.getSize(),Automaton.getSize()), new GeneralStateFactory(new Compaction(100))));
        s1.setCells(s1.getCells());

        s1 = s1.nextState();
        s1 = s1.nextState();


    }


}