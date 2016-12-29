package pl.edu.agh.miss.model.automaton.factory.Animal;

import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Gender;
import pl.edu.agh.miss.model.automaton.life.Prey;
import pl.edu.agh.miss.model.automaton.life.PreyUtils;

/**
 * Created by mucha on 19.11.16.
 */
public class PreyFactory extends AnimalFactory{

    @Override
    protected Animal animalFactoryMethod() {
        Prey prey = new Prey(Gender.randomGender());
        if (PreyUtils.randomGenerator.nextInt(4) == 0) //25 % szans na dojrzałość
            prey.setAge(PreyUtils.randomGenerator.nextInt(Prey.OLD_AGE - Prey.MATURITY) + Prey.MATURITY);
        else
            prey.setAge(PreyUtils.randomGenerator.nextInt(Prey.MATURITY));
        return prey;
    }

}
