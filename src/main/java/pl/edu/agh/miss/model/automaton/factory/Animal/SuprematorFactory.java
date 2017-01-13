package pl.edu.agh.miss.model.automaton.factory.Animal;


import pl.edu.agh.miss.model.automaton.life.*;

public class SuprematorFactory extends AnimalFactory {

    @Override
    protected Animal animalFactoryMethod() {
        Supremator supremator= new Supremator(Gender.randomGender());
        if (PredatorUtils.randomGenerator.nextInt(10) == 0)
            supremator.setAge(PredatorUtils.randomGenerator.nextInt(Predator.OLD_AGE - Predator.MATURITY) + Predator.MATURITY);
        else
            supremator.setAge(PredatorUtils.randomGenerator.nextInt(Predator.MATURITY));
        return supremator;
    }
}

