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
    private List<Animal> supremators;
    private Plant plant;
    private Integer roughness;

    public State(List<Animal> preys, List<Animal> predators, List<Animal> supremators, Plant plant, Integer roughness) {
        this.preys = preys;
        this.predators = predators;
        this.supremators = supremators;
        this.plant = plant;
        this.roughness = roughness;
    }

    public State() {
        preys = new LinkedList<>();
        predators = new LinkedList<>();
        supremators = new LinkedList<>();
    }

    public List<Animal> getPreys() {
        return preys;
    }


    public List<Animal> getPredators() {
        return predators;
    }

    public List<Animal> getSupremators() {
        return supremators;
    }

    public Plant getPlants() { return plant; }

    public void setPlants(Plant plant) {
        this.plant = plant;
    }

    public Integer getRoughness() {
        return roughness;
    }

    public void setRoughness(Integer roughness) {
        this.roughness = roughness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (preys != null ? !preys.equals(state.preys) : state.preys != null) return false;
        if (predators != null ? !predators.equals(state.predators) : state.predators != null) return false;
        if (supremators != null ? !supremators.equals(state.supremators) : state.supremators != null) return false;
        if (plant != null ? !plant.equals(state.plant) : state.plant != null) return false;
        return roughness != null ? roughness.equals(state.roughness) : state.roughness == null;

    }

    @Override
    public int hashCode() {
        int result = preys != null ? preys.hashCode() : 0;
        result = 31 * result + (predators != null ? predators.hashCode() : 0);
        result = 31 * result + (supremators != null ? supremators.hashCode() : 0);
        result = 31 * result + (plant != null ? plant.hashCode() : 0);
        result = 31 * result + (roughness != null ? roughness.hashCode() : 0);
        return result;
    }
}
