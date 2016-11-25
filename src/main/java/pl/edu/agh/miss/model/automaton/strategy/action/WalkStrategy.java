package pl.edu.agh.miss.model.automaton.strategy.action;


import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;

import java.util.Set;

/**
 * Created by mucha on 21.11.16.
 */
// w sumie nie musi miec logiki chodzenia bo po co ma chdozic skoro labo zweirze jee (idzie po jedzienie) albo chce ruchac wiec idze po kobitke
@Deprecated
public class WalkStrategy implements ActionStrategy{
    @Override
    public Position performAction(Set<Cell> cells, Position position, Animal animal) {
        return null;
    }
}
