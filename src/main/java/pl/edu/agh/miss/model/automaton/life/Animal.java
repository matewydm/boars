package pl.edu.agh.miss.model.automaton.life;

import java.util.Set;


public abstract class Animal {

    private Integer age;
    private Integer hunger;
    private Double mortality;
    private Pregnant pregnant;
    private final Gender gender;

    public Animal(Gender gender){
        this.gender = gender;
        this.age =0;
        hunger = 0;
        mortality = 0.0;
    }


    public void incrementAge(){
        age++;
    }

    public void incrementHunger(Integer val) {
        hunger += val;
    }

    public void decrementHunger(Integer val){
        hunger -=val;
    }

    public abstract Set<Animal> reproduce();

    public void impregnate(Pregnant pregnant){
        if(this.gender.equals(Gender.FEMALE) && !isPregnant())
            this.pregnant = pregnant;
        else throw new IllegalArgumentException("Animal cannot be impregnate second time");
    }

    public Boolean isPregnant(){
        return pregnant != null ? Boolean.TRUE:Boolean.FALSE;

    }


}
