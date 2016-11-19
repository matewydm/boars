package pl.edu.agh.miss.model.automaton;

import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Set;

public interface NeighbourhoodStrategy {
    public Set<Position> calculate(Position position, Animal animal);
}
