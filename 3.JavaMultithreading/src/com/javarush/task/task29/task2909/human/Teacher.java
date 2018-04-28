package com.javarush.task.task29.task2909.human;

public class Teacher extends UniversityPerson {
    protected int course;
    //    private List<Human> children = new ArrayList<>();
    private int numberOfStudents;

    public Teacher(String name, int age, int numberOfStudents) {
//        super(false);
        super(name, age);
        this.numberOfStudents = numberOfStudents;
    }

//    public List<Human> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Human> children) {
//        this.children = children;
//    }

    public void live() {
        teach();
    }

    public void teach() {
    }



    public int getCourse() {
        return course;
    }

    @Override
    public String getPosition() {
        return "Преподаватель";
    }
}