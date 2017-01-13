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

    private final static Double PREY_RATIO = 2.0;
    private final static Double PLANT_RATIO = 2.0;
    private final static Double PREDATOR_RATIO = 0.7;
    private final static Integer PREY_CHANCES = 25;
    private final static Integer PREDATOR_CHANCES = 25;

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

        Plant plants = generatePlants(generatePlantAmount(3));

        Integer roughness = generateRoughness();
        return new State(preys,predators,plants,roughness);
    }

    private Integer generateRoughness() {
        if (randomGenerator.nextInt(10)==0) {
            return 3;
        }
        if (randomGenerator.nextInt(4)==0) {
            return 2;
        }
        return 1;
    }

    private List<Animal> generateAnimals(AnimalFactory animalFactory, Integer animalAmount){
        List<Animal> animalList = new LinkedList<>();
        for (int i = 0; i < animalAmount; i++)
            animalList.add(animalFactory.generateAnimal());
        return animalList;
    }

    private Plant generatePlants(Integer plantAmount){
        return new Plant(plantAmount);
    }

    private Integer generatePredatorAmount(Integer compaction){
        Integer result = 0;
        if (randomGenerator.nextInt(100/PREDATOR_CHANCES) == 0) {
            result = randomGenerator.nextInt((int)(compaction * PREDATOR_RATIO));
        }
        return result;
    }

    private Integer generatePreyAmount(Integer compaction){
        Integer result = 0;
        if (randomGenerator.nextInt(100/PREY_CHANCES) == 0) {
            result = randomGenerator.nextInt((int)(compaction*PREY_RATIO));
        }
        return result;
    }

    private Integer generatePlantAmount(Integer compaction){
        return randomGenerator.nextInt((int)(compaction*PLANT_RATIO));
    }
}