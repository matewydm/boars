package pl.edu.agh.miss.model.automaton.life;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SuprematorUtils {
    public static final Random randomGenerator = new Random();
    public static final byte PREGNANCY_LENGTH = 12;
    public static final byte PREGNANCY_CRITIC = 10;
    public static final Integer BroodNumber = 2;
    public static final Integer PREY_KILLING_CHANCES = 20;
    public static final Integer PREDATOR_KILLING_CHANCES = 8;

    private static final List<Gender> genders = Collections.unmodifiableList(Arrays.asList(Gender.values()));

    public static Boolean isReadyForReproduce(final Pregnant pregnant){
        if (pregnant.getPregnantDay() >PREGNANCY_LENGTH) return true;
        else if(pregnant.getPregnantDay() > PREGNANCY_LENGTH - PREGNANCY_CRITIC) return randomGenerator.nextBoolean();
        else return false;
    }

    public static Gender randomGender(){
        return  genders.get(randomGenerator.nextInt(genders.size()));
    }

    public static Integer randomBrood(){
        return randomGenerator.nextInt(BroodNumber)+1;
    }
}
