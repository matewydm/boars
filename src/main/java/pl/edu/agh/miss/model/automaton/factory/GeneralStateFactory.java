package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.State;
import pl.edu.agh.miss.model.automaton.factory.Animal.AnimalFactory;
import pl.edu.agh.miss.model.automaton.factory.Animal.PredatorFactory;
import pl.edu.agh.miss.model.automaton.factory.Animal.PreyFactory;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mucha on 19.11.16.
 */
public class GeneralStateFactory implements StateFactory {

    private final static Integer PLANT_RATIO = 100;
    private final static Integer PREDATOR_RATIO = 30;

    private Random randomGenerator;
    private Compaction compaction;

    public GeneralStateFactory(Compaction compaction) {
        this.randomGenerator = new Random();
        this.compaction = compaction;
    }

    @Override
    public State addNewState() {

        Set<Prey> preys = generateAnimals(new PreyFactory(),generatePreyAmount(compaction.getCompaction()))
                                .stream().map(e -> (Prey) e).collect(Collectors.toSet());

        Set<Predator> predators = generateAnimals(new PredatorFactory(),generatePredatorAmount(compaction.getCompaction()))
                                .stream().map(e -> (Predator) e).collect(Collectors.toSet());

        Set<Plant> plants = generatePlants(generatePlantAmount(compaction.getCompaction()));

        return new State(preys,predators,plants);
    }

    private Set<Animal> generateAnimals(AnimalFactory animalFactory, Integer animalAmount){
        Set<Animal> animalSet = new HashSet<>();
        for (int i = 0; i < animalAmount; i++)
            animalSet.add(animalFactory.addAnimal());
        return animalSet;
    }

    private Set<Plant> generatePlants(Integer plantAmount){
        Set<Plant> plantSet = new HashSet<>();
        for (int i = 0; i < plantAmount; i++)
            plantSet.add(new Plant());
        return plantSet;
    }

    private Integer generatePredatorAmount(Integer compaction){
        return randomGenerator.nextInt(compaction/PREDATOR_RATIO);
    }

    private Integer generatePreyAmount(Integer compaction){
        return randomGenerator.nextInt(compaction);
    }

    private Integer generatePlantAmount(Integer compaction){
        return randomGenerator.nextInt(compaction*PLANT_RATIO);
    }
}
