package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
    public ConcurrentMap<Position,State> cellsFactoryMethod() {
        ConcurrentMap<Position,State> cellMap = new ConcurrentHashMap<>();
        for (int i = 0; i < dimension.getWidth(); i++)
            for (int j = 0; j < dimension.getHeight(); j++) {
                //System.out.println(i + " " + j);
                cellMap.put(new Position(i, j), stateFactory.generateState());
            }
        return cellMap;
    }
}
