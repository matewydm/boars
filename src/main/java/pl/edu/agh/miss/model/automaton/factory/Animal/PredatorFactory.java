package pl.edu.agh.miss.model.automaton.factory.Animal;

import pl.edu.agh.miss.model.automaton.life.*;

/**
 * Created by mucha on 19.11.16.
 */
public class PredatorFactory extends AnimalFactory {

    @Override
    protected Animal animalFactoryMethod() {
        Predator predator = new Predator(Gender.randomGender());
        if (PredatorUtils.randomGenerator.nextInt(10) == 0)
            predator.setAge(PredatorUtils.randomGenerator.nextInt(Predator.OLD_AGE - Predator.MATURITY) + Predator.MATURITY);
        else
            predator.setAge(PredatorUtils.randomGenerator.nextInt(Predator.MATURITY));
        return predator;
    }
}
