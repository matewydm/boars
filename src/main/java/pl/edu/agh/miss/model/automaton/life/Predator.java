package pl.edu.agh.miss.model.automaton.life;


import java.util.Set;

public class Predator extends Animal {

    public Predator(Gender gender) {
        super(gender);
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
