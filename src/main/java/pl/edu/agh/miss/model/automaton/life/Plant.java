package pl.edu.agh.miss.model.automaton.life;


public class Plant implements Foodable {
    private static final Integer MAX = 40;
    private static final Integer GROWTH = 5;

    private static final Integer kcal = 30;
    private Integer value;

    public Plant(Integer value) {
        this.value = value;
    }

    @Override
    public Integer beEaten() {
        Integer temp = null;
        if (value > 0) {
            if (value < kcal) {
                temp = value;
                value = 0;
                return temp;
            }
            else {
                value -= kcal;
                return kcal;
            }
        }
        else return 0;

    }

    public void grow(Integer rougness) {
        if (value < MAX - GROWTH/rougness) value += GROWTH/rougness;
        else if (value >= MAX - GROWTH/rougness) value = MAX;

    }

    public Boolean isEmpty() {
        return (value > 0) ? Boolean.FALSE : Boolean.TRUE;
    }

    public Integer getValue() {
        return value;
    }
}
