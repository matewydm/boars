package pl.edu.agh.miss.model.automaton.life;


import pl.edu.agh.miss.model.automaton.Automaton;

import java.util.Set;

public class Predator extends Animal {

    public Predator(Gender gender) {
        super(gender);
        setDefaultMovement(Automaton.getPredatorDefaultMovement());
    }

    @Override
    public Set<Animal> born() {
        return null;
    }

    @Override
    public Boolean isReadyForReproduce() {
        return null;
    }
}
