package pl.edu.agh.miss.model.automaton.moves;

import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.AnimalMoves;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.life.PreyUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PreyMoves extends AnimalMoves {

    private final static Float PREGNANCY_CRIPPLENESS_COEF = 0.6f;
    private final static Float SUSPICIOUSNESS_COEF = 0.7f;
    private final static Float HUNGER_WEAKNESS_COEF = 1.0f;
    private final static Float MOTIVATION_SEARCHFOOD_COEF = -1.0f;
    private final static Float MOTIVATION_STUFFED_COEF = -0.3f;


    @Override
    protected byte evaluateRadius(Animal animal) {
        byte movement = animal.getMovement();

        float penalty = 0;

        penalty += consideringPregnancy(animal);
        penalty += consideringHunger(animal);

        movement += Math.round(penalty);


        return movement;

    }

    private float consideringPregnancy(Animal animal) {
        float penalty = 0;

        if (animal.isPregnant()) {
            if (animal.getPregnant().getPregnantDay() > PreyUtils.PREGNANCY_LENGTH - PreyUtils.PREGNANCY_CRITIC)
                penalty -= PREGNANCY_CRIPPLENESS_COEF; // disability in movement
            else {
                penalty += SUSPICIOUSNESS_COEF; // suspiciousness
            }
        }
        else {
            return 0;
        }

        return penalty;
    }

    private float consideringHunger(Animal animal) {
        float penalty = 0;

        if (animal.getHunger() > 0) {
            if (animal.getHunger() < Prey.HUNGER_CUTOFF)
                penalty += HUNGER_WEAKNESS_COEF;
            else {
                penalty -= MOTIVATION_SEARCHFOOD_COEF;
            }
        }

        if (animal.getHunger() < -Prey.HUNGER_CUTOFF)
            penalty -= MOTIVATION_STUFFED_COEF;

        return penalty;
    }


}