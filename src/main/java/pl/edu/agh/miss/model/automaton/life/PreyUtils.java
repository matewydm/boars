package pl.edu.agh.miss.model.automaton.life;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PreyUtils {
    public static final Random randomGenerator = new Random();
    public static final Integer BroodNumber = 4;
    private static final List<Gender> genders = Collections.unmodifiableList(Arrays.asList(Gender.values()));

    public static Boolean isReadyForReproduce(final Pregnant pregnant){
        if (pregnant.getPregnantDay() >50) return true;
        else if(pregnant.getPregnantDay() > 40) return randomGenerator.nextBoolean();
            else return false;
    }

    public static Gender randomGender(){
       return  genders.get(randomGenerator.nextInt(genders.size()));
    }

    public static Integer randomBrood(){
        return randomGenerator.nextInt(BroodNumber)+2;
    }

}
