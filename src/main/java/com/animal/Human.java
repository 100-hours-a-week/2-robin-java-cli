package com.animal;

public class Human extends Animal{
    public Human(String name, int height, int weight) {
        super(name, height, weight);
    }

    @Override
    public void say() {
        System.out.println(name+" 은 사람이다.");
    }
}
