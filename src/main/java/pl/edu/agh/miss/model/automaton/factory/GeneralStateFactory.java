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

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mucha on 19.11.16.
 */
public class GeneralStateFactory implements StateFactory {

    private final static Double PREY_RATIO = 0.1;
    private final static Double PLANT_RATIO = 0.2;
    private final static Double PREDATOR_RATIO = 0.05;

    private Random randomGenerator;
    private Compaction compaction;

    public GeneralStateFactory(Compaction compaction) {
        this.randomGenerator = new Random();
        this.compaction = compaction;
    }

    @Override
    public State generateState() {

        List<Animal> preys = generateAnimals(new PreyFactory(),generatePreyAmount(compaction.getCompaction()))
                                .stream().map(e -> (Prey) e).collect(Collectors.toList());

        List<Animal> predators = generateAnimals(new PredatorFactory(),generatePredatorAmount(compaction.getCompaction()))
                                .stream().map(e -> (Predator) e).collect(Collectors.toList());

        List<Plant> plants = generatePlants(generatePlantAmount(compaction.getCompaction()));

        return new State(preys,predators,plants);
    }

    private List<Animal> generateAnimals(AnimalFactory animalFactory, Integer animalAmount){
        List<Animal> animalList = new LinkedList<>();
        for (int i = 0; i < animalAmount; i++)
            animalList.add(animalFactory.generateAnimal());
        return animalList;
    }

    private List<Plant> generatePlants(Integer plantAmount){
        List<Plant> plantList = new LinkedList<>();
        for (int i = 0; i < plantAmount; i++)
            plantList.add(new Plant());
        return plantList;
    }

    private Integer generatePredatorAmount(Integer compaction){
        return randomGenerator.nextInt((int)Math.ceil(compaction*PREDATOR_RATIO));
    }

    private Integer generatePreyAmount(Integer compaction){
        return randomGenerator.nextInt((int)Math.ceil(compaction*PREY_RATIO));
    }

    private Integer generatePlantAmount(Integer compaction){
        return randomGenerator.nextInt((int)Math.ceil(compaction*PLANT_RATIO));
    }
}
