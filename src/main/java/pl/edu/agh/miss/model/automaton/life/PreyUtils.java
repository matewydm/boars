package pl.edu.agh.miss.model.automaton.life;

import pl.edu.agh.miss.model.automaton.Cell;

import java.util.*;

public class PreyUtils {
    public static final Random randomGenerator = new Random();
    public static final byte PREGNANCY_LENGTH = 12;
    public static final byte PREGNANCY_CRITIC = 10;
    public static final Integer BroodNumber = 5;

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

    public static Boolean preyNearby(Set<Cell> cells) {
        Iterator<Cell> it = cells.iterator();

        for(; it.hasNext();) {
            if (!it.next().getState().getPredators().isEmpty()) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    public static Boolean preyNearby(Cell currentCell) {
        return (currentCell.getState().getPredators().isEmpty()) ? Boolean.FALSE : Boolean.TRUE;
    }

}
