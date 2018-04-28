package com.javarush.task.task29.task2909.human;

public class Soldier extends Human {
    protected int course;

    //    protected boolean isSoldier;
    //    public Human(boolean isSoldier) {
//        this.isSoldier = isSoldier;
//        this.id = nextId;
//        nextId++;
//    }
    public void live() {
//        if (isSoldier)
            fight();
    }
    public void fight() {
    }

    public Soldier(String name, int age) {
        super();
        super.setAge(age);
        super.setName(name);
    }

    public int getCourse() {
        return course;
    }
}
