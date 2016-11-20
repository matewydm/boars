package pl.edu.agh.miss.model.automaton;

import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Map;
import java.util.Set;

public interface AnimalStrategy<T extends Animal> {
    void action(Position position, T animal,Map<Position,State> map);
}
