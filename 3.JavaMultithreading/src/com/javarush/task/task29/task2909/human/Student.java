package com.javarush.task.task29.task2909.human;

import java.util.Date;

public class Student extends UniversityPerson {
    private int course;
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.averageGrade = averageGrade;
    }


    public void live() {
        learn();
    }

    public void learn() {
    }

    public void incAverageGrade(double delta){
        double tempGrade = getAverageGrade();
        setAverageGrade(tempGrade += delta);
    }

    public void setCourse(int value) {
        course = value;
    }
    public void setAverageGrade(double value) {
        averageGrade = value;
    }

//    public void setBeginningOfSession(int day, int month, int year) {
    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }
}