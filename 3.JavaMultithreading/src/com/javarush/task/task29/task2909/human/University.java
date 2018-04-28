package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;
    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double value) {
        for (Student student: this.students) {
            if (student.getAverageGrade()==value) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Double maxStudent = this.students.get(0).getAverageGrade();
        Student stud = this.students.get(0);
        for (Student student: this.students
             ) {
            if (student.getAverageGrade()>maxStudent){
                maxStudent = student.getAverageGrade();
                stud = student;
            }
        }
        return stud;
    }

    public Student getStudentWithMinAverageGrade() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getAverageGrade(),o2.getAverageGrade());
            }
        });
        //TODO:
        return students.get(0);
    }
    public void expel(Student student){
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}