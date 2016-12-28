package pl.edu.agh.miss.model.automaton;

import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AnimalMoves {
    public Set<Position> calculate(Position position, Animal animal) {
        return positionsInRadius(position,evaluateRadius(animal));
    }

    protected abstract byte evaluateRadius(Animal animal);

    public Set<Position> positionsInRadius(Position position, Byte radius) {
        Set<Position> set = new HashSet<>();

        for (int i = -radius; i <= radius; i++)
            for (int j = -radius; j <= radius; j++)
                set.add(new Position(position.getLatitude() + i, position.getLongitude() + j));

        set = set.stream().filter(e -> (e.getLatitude() >= 0 && e.getLongitude() >= 0 &&
                e.getLatitude() < Automaton.getSize() && e.getLongitude() < Automaton.getSize())).collect(Collectors.toSet());

        return set;
    }
}
