package pl.edu.agh.miss.model.automaton.moves;

import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Predator;

import java.util.Set;

public class PredatorMoves extends AnimalMoves {

    @Override
    protected byte evaluateRadius(Animal animal) {
        // TODO
        return animal.getMovement();
    }

}
