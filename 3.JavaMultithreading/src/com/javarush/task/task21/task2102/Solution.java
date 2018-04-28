package com.javarush.task.task21.task2102;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* 
Сравниваем модификаторы
*/
public class Solution {
    public static void main(String[] args) {
        int modifiersOfThisClass = Solution.class.getModifiers();//получение модификатора доступа
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.PUBLIC));   //true
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.STATIC));   //false

        int modifiersOfMethod = getMainMethod().getModifiers();//возвращает модификатор main
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfMethod, Modifier.STATIC));      //true
    }

    public static boolean isAllModifiersContainSpecificModifier(int allModifiers, int specificModifier) {
        //содержит ли параметр allModifiers модификатор specificModifier
        String s = Integer.toBinaryString(allModifiers);
        int a = Integer.valueOf(s);
        s = String.format("%08d", a);
        String s2 = Integer.toBinaryString(specificModifier);
        int a2 = Integer.valueOf(s2);
        s2 = String.format("%08d", a2);
        String s3 = Integer.toBinaryString(allModifiers&specificModifier);
        int a3 = Integer.valueOf(s3);
        s3 = String.format("%08d", a3);
        System.out.println(s + "---" + s2 + "---" + s3);
        int slide = allModifiers&specificModifier;
        return slide==specificModifier;
    }

    private static Method getMainMethod() {
        Method[] methods = Solution.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("main")) return method;//9
        }

        return null;
    }
}
