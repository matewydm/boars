package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.Set;

public class State {
    private Set<Prey> preys;
    private Set<Predator> predators;
    private Set<Plant> plants;

    public State(Set<Prey> preys, Set<Predator> predators, Set<Plant> plants) {
        this.preys = preys;
        this.predators = predators;
        this.plants = plants;
    }

    public Set<Prey> getPreys() {
        return preys;
    }

    public void setPreys(Set<Prey> preys) {
        this.preys = preys;
    }

    public Set<Predator> getPredators() {
        return predators;
    }

    public void setPredators(Set<Predator> predators) {
        this.predators = predators;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
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
