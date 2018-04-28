package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.Objects;

/* 
Ошибка в equals/hashCode
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

//    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (o instanceof Solution) return false;
////
////        Solution solution1 = (Solution) o;
////
////        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
////        if (anInt != solution1.anInt) return false;
////        if (date != null ? !date.equals(solution1.date) : solution1.date == null) return false;
////        if (solution != null ? !solution.equals(solution1.solution) : solution1.solution == null) return false;
////        if (string != null ? !string.equals(solution1.string) : solution1.string == null) return false;
////
////        return true;
////    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution1 = (Solution) o;
        return anInt == solution1.anInt &&
                Double.compare(solution1.aDouble, aDouble) == 0 &&
                Objects.equals(string, solution1.string) &&
                Objects.equals(date, solution1.date) &&
                Objects.equals(solution, solution1.solution);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {

        Solution solution1 = new Solution(1,"dfg", 1.2, new Date());
        try {
            Solution solution2 = new Solution(1,"dfg", 1.2, new Date());
            solution2 = (Solution)solution1.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("not cloneable");
            e.printStackTrace();
        }
    }
}
