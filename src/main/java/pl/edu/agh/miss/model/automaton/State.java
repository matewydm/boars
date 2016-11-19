package pl.edu.agh.miss.model.automaton;


import pl.edu.agh.miss.model.automaton.life.Plant;
import pl.edu.agh.miss.model.automaton.life.Predator;
import pl.edu.agh.miss.model.automaton.life.Prey;

import java.util.Set;

public interface State {
    public Set<Prey> getPreys();
    public Set<Predator> getPredators();
    public Set<Plant> getPlants();
}
