package pl.edu.agh.miss.model.automaton.life;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PredatorUtils {
    public static final Random randomGenerator = new Random();
    public static final byte PREGNANCY_LENGTH = 8;
    public static final byte PREGNANCY_CRITIC = 6;
    public static final Integer BroodNumber = 4;
    public static final Integer KILLING_CHANCES = 10;

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
