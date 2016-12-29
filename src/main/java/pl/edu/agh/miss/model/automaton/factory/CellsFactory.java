package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by mucha on 20.11.16.
 */
public interface CellsFactory  {

    ConcurrentMap<Position,State> cellsFactoryMethod();
}
