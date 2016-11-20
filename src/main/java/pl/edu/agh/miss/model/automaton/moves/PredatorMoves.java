package pl.edu.agh.miss.model.automaton.moves;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Predator;

import java.util.Set;

public class PredatorMoves extends AnimalMoves<Predator> {
    @Override
    public Set<Position> calculate(Position position, Predator animal) {
        return null; // TODO implement predatorMoves
    }

    @Override
    public Set<Position> positionsInRadius(Position position, Byte radius) {
        return null;
    }
}
