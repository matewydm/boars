package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mucha on 20.11.16.
 */
public class SimpleCellsFactory implements CellsFactory {

    private Dimension dimension;
    private StateFactory stateFactory;

    public SimpleCellsFactory(Dimension dimension, StateFactory stateFactory){
        this.dimension = dimension;
        this.stateFactory = stateFactory;

    }

    @Override
    public Map<Position,State> cellsFactoryMethod() {
        Map<Position,State> cellMap = new HashMap<>();
        for (int i = 0; i < dimension.getWidth(); i++)
            for (int j = 0; j < dimension.getHeight(); j++) {
                System.out.println(i + " " + j);
                cellMap.put(new Position(i, j), stateFactory.addNewState());
            }
        return cellMap;
    }
}
