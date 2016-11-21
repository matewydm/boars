package pl.edu.agh.miss.model.automaton.factory;

import org.junit.Test;
import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.State;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by mucha on 20.11.16.
 */
public class GeneralStateFactoryTest {

    @Test
    public void generateState() throws Exception {
        StateFactory stateFactory = new GeneralStateFactory(new Compaction(100));
        State generalState = stateFactory.generateState();
        assertTrue(generalState instanceof State);
    }

    @Test
    public void checkStatePrey() throws Exception {
        StateFactory stateFactory = new GeneralStateFactory(new Compaction(100));
        State generalState = stateFactory.generateState();
        List<Prey> preyList = generalState.getPreys();
        assertTrue(!preyList.isEmpty());
    }
}
