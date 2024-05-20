package com.animal;

public class Shihtzu extends Dog{
    public Shihtzu(String name, int height, int weight) {
        super(name, height, weight);
    }
    @Override
    public void say() {
        System.out.println(super.name+" 은 시츄다.");
    }
}
