package com.timbuchalka;

public class Snake extends Animal{

    private int eyes;
    private int fangs;
    private int skin;

    public Snake(String name, int brain, int body, int size, int weight, int eyes, int fangs,int skin) {
        super("Boa", 2, 1, 50, 15);
        this.eyes = eyes;
        this.fangs = fangs;
        this.skin = skin;
    }

    private void swallow(){
        System.out.println("Snake.swallow() was called");
    }

    @Override
    public void eat() {
        super.eat();
        swallow();
    }
}
