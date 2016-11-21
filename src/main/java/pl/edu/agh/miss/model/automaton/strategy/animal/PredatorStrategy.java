package pl.edu.agh.miss.model.automaton.strategy.animal;

import pl.edu.agh.miss.model.automaton.AnimalStrategy;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;
import pl.edu.agh.miss.model.automaton.life.Predator;

import java.util.Map;

public class PredatorStrategy implements AnimalStrategy<Predator> {
    @Override
    public void action(Position position, Predator predator, Map<Position,State> map) {
        return; // TODO implement predatorStrategy
    }
}
