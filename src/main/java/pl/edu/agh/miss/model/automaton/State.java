package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class State {
    private List<Animal> preys;
    private List<Animal> predators;
    private List<Plant> plants;

    public State(List<Animal> preys, List<Animal> predators, List<Plant> plants) {
        this.preys = preys;
        this.predators = predators;
        this.plants = plants;
    }

    public State() {
        preys = new LinkedList<>();
        predators = new LinkedList<>();
        plants = new LinkedList<>();
    }

    public List<Animal> getPreys() {
        return preys;
    }

    public void setPreys(List<Animal> preys) {
        this.preys = preys;
    }

    public List<Animal> getPredators() {
        return predators;
    }

    public void setPredators(List<Animal> predators) {
        this.predators = predators;
    }

    public List<Plant> getPlants() { return plants; }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (preys != null ? !preys.equals(state.preys) : state.preys != null) return false;
        if (predators != null ? !predators.equals(state.predators) : state.predators != null) return false;
        return plants != null ? plants.equals(state.plants) : state.plants == null;

    }

    @Override
    public int hashCode() {
        int result = preys != null ? preys.hashCode() : 0;
        result = 31 * result + (predators != null ? predators.hashCode() : 0);
        result = 31 * result + (plants != null ? plants.hashCode() : 0);
        return result;
    }
}
