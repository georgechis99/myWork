package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        Animal animal = new Animal("Animal",1,1,5,5);

        Snake snake = new Snake("Python",1,1,50,15,2,2,1);
        Dog dog = new Dog("Yorkie",8,20,2,4,1,20,"Long silky");

//        dog.eat();
        dog.walk();
        snake.eat();
//        dog.run();
    }
}
