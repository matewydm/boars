package pl.edu.agh.miss.model.automaton.factory;

import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.State;
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

    public static final Random randomGenerator = new Random();

    private final static Integer RATIO = 30;

    private Compaction compaction;

    public GeneralStateFactory(Compaction compaction) {
        this.compaction = compaction;
    }

    @Override
    public State addNewState() {

        Set<Prey> preys = generateAnimals(new PreyFactory(),generatePreyAmount(compaction.getCompaction()))
                                .stream().map(e -> (Prey) e).collect(Collectors.toSet());

        Set<Predator> predators = generateAnimals(new PredatorFactory(),generatePredatorAmount(compaction.getCompaction()))
                                .stream().map(e -> (Predator) e).collect(Collectors.toSet());

        Set<Plant> plants = generatePlants(new OridnaryPlantFactory(),generatePlantAmount());

        return new State(preys,predators,plants);
    }

    private Set<Animal> generateAnimals(AnimalFactory animalFactory, Integer animalAmount){
        Set<Animal> animalSet = new HashSet<>();
        for (int i = 0; i < animalAmount; i++)
            animalSet.add(animalFactory.addAnimal());
        return animalSet;
    }

    private Set<Plant> generatePlants(PlantFactory plantFactory, Integer plantAmount){
        Set<Plant> plantSet = new HashSet<>();
        for (int i = 0; i < plantAmount; i++)
            plantSet.add(plantFactory.addPlant());
        return plantSet;
    }

    private Integer generatePredatorAmount(Integer compaction){
        return randomGenerator.nextInt(compaction/RATIO);
    }

    private Integer generatePreyAmount(Integer compaction){
        return randomGenerator.nextInt(compaction);
    }

    private Integer generatePlantAmount(){
        return randomGenerator.nextInt();
    }
}
