package pl.edu.agh.miss.model.automaton.life;

import java.util.Set;

public class Pregnant {
    private Integer pregnantDay;
    private Integer animalsNumber;

    public Pregnant(Integer animalsNumber) {
        pregnantDay = 0;
        this.animalsNumber = animalsNumber;
    }


    public void incrementDays() {
        pregnantDay++;
    }

    public Integer getPregnantDay() {
        return pregnantDay;
    }

    public Integer getAnimalsNumber() {
        return animalsNumber;
    }
}
