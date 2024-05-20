package com.animal;

public class Dog extends Animal {
    public Dog(String name, int height, int weight) {
        super(name, height, weight);
    }

    @Override
    public void say() {
        System.out.println(name+ " 은 개다.");
    }
}
