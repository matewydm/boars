package pl.edu.agh.miss.model.automaton.life;


import java.util.Set;


public abstract class Animal {

    private Integer age;
    private Integer hunger;
    private Byte defaultMovement;
    private Double mortality;

    protected Pregnant pregnant;
    private final Gender gender;
    private LifeStatus status;

    public Animal(Gender gender){
        status = LifeStatus.ALIVE;
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

    public abstract Boolean isReadyForReproduce();

    public void impregnate(Pregnant pregnant){
        if(this.gender.equals(Gender.FEMALE) && !isPregnant())
            this.pregnant = pregnant;
        else throw new IllegalArgumentException("Animal cannot be impregnate second time");
    }

    public Boolean isPregnant(){
        return pregnant != null ? Boolean.TRUE:Boolean.FALSE;
    }

    public void eat(Foodable food){
        decrementHunger(food.eat());
    }


    public void die() {
        status = LifeStatus.DEAD;
    }

    public LifeStatus getStatus() {
        return status;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHunger() {
        return hunger;
    }

    public Double getMortality() {
        return mortality;
    }
    public Pregnant getPregnant() {
        return pregnant;
    }

    public Gender getGender() {
        return gender;
    }

    public Byte getMovement() {return defaultMovement;}

    public void setDefaultMovement(Byte defaultMovement) { this.defaultMovement = defaultMovement; }
}
