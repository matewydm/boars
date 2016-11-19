package pl.edu.agh.miss.model.automaton.life;

import java.util.Set;

public class Pregnant {
    private Integer pregnantDay;
    private Set<Animal> newAnimal;

    public Pregnant(Set<Animal> animals) {
        this.newAnimal = animals;
    }

    public void incrementDays() {
        pregnantDay++;
    }


}
