package pl.edu.agh.miss.model.automaton.life;

import java.util.Set;


public class Prey extends Animal {

    public Prey(Gender gender) {
        super(gender);
    }

    @Override
    public Set<Animal> reproduce() {
        return null;
    }
}
