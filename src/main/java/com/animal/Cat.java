package com.animal;

public class Cat extends Animal {
    public Cat(String name, int height, int weight) {
        super(name, height, weight);
    }

    @Override
    public void say() {
        System.out.println(name+" 은 고양이다.");
    }
}
