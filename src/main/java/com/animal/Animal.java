package com.animal;

public class Animal {
    protected String name;
    protected int height;
    protected int weight;

    public Animal() {}
    public Animal(String name ,int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void status() {
        System.out.println("이름 : "+name+" 키 : "+height+" 몸무게 : "+weight);
    }
    public void say() {
        System.out.println(name+" 이는 동물입니다.");
    }
}

