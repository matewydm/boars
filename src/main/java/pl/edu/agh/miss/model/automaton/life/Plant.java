package pl.edu.agh.miss.model.automaton.life;


public class Plant implements Foodable {

    private static final Integer kcal = 20;
    private Integer value = 100;

    @Override
    public Integer beEaten() {
        //TODO poprawic logike zwracnia wartosci
//        if (value > 0) {
//            if (value < kcal)
//                value = 0;
//            else
//                value -= kcal;
//            return kcal;
//        }
//        else return 0;
        return kcal;
    }

    public void grow() {
        if (value < 90) value += 10;
        else if (value >= 90 && value <= 100) value = 100;

    }

    public Integer getValue() {
        return value;
    }
}
