package pl.edu.agh.miss.model.automaton.life;

import java.util.Random;

public enum Gender {
    FEMALE,MALE;

    public static final Random randomGenerator = new Random();

    public static Gender randomGender(){
        return  Gender.values()[randomGenerator.nextInt(Gender.values().length)];
    }

}
