package com.animal;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal("신기한놈", 222, 555);
        Cat cat = new Cat("나비", 10, 5);
        Dog dog = new Dog("뽀삐", 11, 6);
        Human human = new Human("철수", 150, 40);

        Shihtzu shihtzu = new Shihtzu("아라", 7, 6);

        animal.say();

        cat.status();
        cat.say();

        dog.say();

        human.say();

        shihtzu.say();
    }
}
