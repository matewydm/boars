package pl.edu.agh.miss.model.automaton.strategy;

import javafx.geometry.Pos;
import pl.edu.agh.miss.model.automaton.AnimalStrategy;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.State;
import pl.edu.agh.miss.model.automaton.life.Animal;
import pl.edu.agh.miss.model.automaton.life.Prey;
import static pl.edu.agh.miss.model.automaton.life.PreyUtils.randomGenerator;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PreyStrategy implements AnimalStrategy<Prey> {
    @Override
    public void action(Position position, Prey prey, Map<Position,State> map) {
        Position result = null;

        if (prey.getHunger() > 0) {
            if (prey.getHunger() >= Prey.HUNGER_CUTOFF) {
                desperateToEat(position,prey,map);
            }
            else {
                searchForFood(position,prey,map);
            }
        }
        else {
            if (predatorIsClose(position)) {
                runToSecureSpot(position, prey,map);
            }
            else {
                haveFun(position,prey,map);
            }
        }

        return;
    }

    private void desperateToEat(Position position, Prey prey, Map<Position,State> map) {
        // stay where you are and eat no matter what or change position maximum by one in radius
        State currentCell = map.get(position);
        Boolean plantsOnPosition = currentCell.getPlants().size() > 0;
        if (plantsOnPosition) {
            // eat a few plants because you're hungry to death
            int genNumber = randomGenerator.nextInt(2)+1; // 1..2
            int size =currentCell.getPlants().size();
            int genIndex = 0;
            for (int i = 0; i < genNumber; i++) {
                genIndex = randomGenerator.nextInt(size);
                prey.eat(currentCell.getPlants().get(genIndex));
            }
        }
        else {
            byte radius = 1;
            Set<Position> newPosition = Prey.getAnimalMoves().positionsInRadius(position,radius);
            Iterator<Position> it = newPosition.iterator();
            Position chosenPosition = null;
            while(it.hasNext()) {
                Position currentPosition = it.next();
                if (!map.get(currentPosition).getPlants().isEmpty()) {
                    chosenPosition = currentPosition;
                    break;
                }
            }

            if (chosenPosition != null) {
                currentCell.getPreys().remove(prey);
                map.get(chosenPosition).getPreys().add(prey);
            }
        }
    }

    private void searchForFood(Position position, Prey prey,Map<Position,State> map) {
        return;
    }

    private void runToSecureSpot(Position position, Prey prey,Map<Position,State> map) {
        return;
    }

    private void haveFun(Position position, Prey prey,Map<Position,State> map) {
        return;
    }

    private Boolean predatorIsClose(Position position) {
        return Boolean.FALSE;
    }


}
