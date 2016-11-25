package pl.edu.agh.miss.model.automaton.life;


import pl.edu.agh.miss.model.automaton.Cell;
import pl.edu.agh.miss.model.automaton.Position;
import pl.edu.agh.miss.model.automaton.strategy.action.ActionStrategy;

import java.util.*;


public abstract class Animal {

    protected ActionStrategy actionStrategy;

    protected Integer age;
    // -100 : 100
    protected Integer hunger;
    // 0 : 3
    protected Byte defaultMovement;
    // 0 - 100
    protected Double mortality;
    // 0 - 10
    protected Integer sexualDesire;

    protected Pregnant pregnant;
    private final Gender gender;
    private LifeStatus status;

    public Animal(Gender gender){
        status = LifeStatus.ALIVE;
        this.gender = gender;
        this.age =0;
        hunger = 0;
        mortality = 0.0;
        sexualDesire = 0;
    }

    public ActionStrategy getActionStrategy(){
        return actionStrategy;
    }

    public void incrementAge(){
        age++;
        if(isPregnant())
            pregnant.incrementDays();
    }

    public void incrementSexualDesire(Integer val) { sexualDesire += val; }

    public void decrementSexualDesire(Integer val) {
        sexualDesire -= val;
        if (sexualDesire < 0)
            sexualDesire = 0;
    }

    public void incrementHunger(Integer val) {
        hunger += val;
    }

    public void decrementHunger(Integer val){
        hunger -=val;
    }

    public List<Animal> born() {
        Integer amount = pregnant.getAnimalsNumber();
        List<Animal> animals = new LinkedList<Animal>();
        for(int i =0; i < amount; i++){
            animals.add(getNewAnimal(PreyUtils.randomGender())); // animalUtils
        }
        pregnant = null;
        return animals;
    }

    protected abstract Animal getNewAnimal(Gender gender);

    public abstract Boolean isReadyForReproduce();

    public abstract void setActionStrategy();

    public void impregnate(){
        if(this.gender.equals(Gender.MALE) || isPregnant())
             throw new IllegalArgumentException("Animal cannot be impregnate second time");
    }

    public Boolean isPregnant(){
        return pregnant != null ? Boolean.TRUE:Boolean.FALSE;
    }

    public void eat(Foodable food){
        decrementHunger(food.beEaten());
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

    public Position performAction(Set<Cell> cells, Position position) {
        return actionStrategy.performAction(cells,position,this);
    }

    public void setMortality(Double mortality) {
        this.mortality = mortality;
    }

    public void setPregnant(Pregnant pregnant) { this.pregnant = pregnant; }

    public abstract void updateMortality();

    public boolean isAlive(){
        return status == LifeStatus.ALIVE?Boolean.TRUE:Boolean.FALSE;
    }


    public void throwDice() {
        updateMortality();
        Random random = new Random();
        Boolean die = (random.nextDouble()*100 < getMortality()) ? true : false;

        if (die) {
            die();
        }

    }

    public abstract void updateSexualDesire();

    public void update() {
        if (isAlive()) {
            updateSexualDesire();
            incrementAge();
            throwDice();
        }
    }

}
