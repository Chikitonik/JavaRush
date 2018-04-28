package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {

    //
    private List<Human> children = new ArrayList<>();

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    //    public void setChildren(List<Human> children) {
//        this.children = children;
//    }
    public void addChild(Human human) {
        children.add(human);
    }

    public void removeChild(Human human) {
        children.remove(human);
    }

    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

    protected Size size;

    public class Size {
        public int height, weight;

    }

    private BloodGroup bloodGroup;


    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

//    public Human(boolean isSoldier) {
//        this.isSoldier = isSoldier;
//        this.id = nextId;
//        nextId++;
//    }

    public Human() {
        this.id = nextId;
        nextId++;
    }

    public Human(String name, int age) {
        this.setAge(age);
        this.setName(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void live() {
//        if (isSoldier)
//            fight();
    }

//    public void fight() {
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public String getPosition() {
        return "Человек";
    }

    public void printData() {
        System.out.println(this.getPosition() + ": " + this.name);
    }
}
