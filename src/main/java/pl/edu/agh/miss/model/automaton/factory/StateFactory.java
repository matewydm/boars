package pl.edu.agh.miss.model.automaton.factory;


import pl.edu.agh.miss.model.automaton.State;

/**
 * Created by mucha on 19.11.16.
 */
public abstract class StateFactory {

    public State addNewField(Integer compaction) {
        return stateFactoryMethod(compaction);
    }

    protected abstract State stateFactoryMethod(Integer compaction);

}
