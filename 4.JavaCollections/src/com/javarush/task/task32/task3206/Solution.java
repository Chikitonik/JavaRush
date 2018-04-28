package com.javarush.task.task32.task3206;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Proxy;

/* 
Дженерики для создания прокси-объекта
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    public Proxy getProxy (Class<?> itemClass, Class ... classes){
        AnnotatedType[] annotatedTypes = itemClass.getAnnotatedInterfaces();

/*        for (int i = 0; i < annotatedTypes.length; i++) {
            annotatedTypes[i] original
        }*/

        /*создается объект с набором методов интерфейса*/
//        SomeInterfaceWithMethods original = new SomeInterfaceWithMethodsImpl();
        /*из него берется ClassLoader*/
//        ClassLoader classLoader = original.getClass().getClassLoader();
        /*также из него берутся все интерфейсы*/
//        Class<?>[] interfaces = original.getClass().getInterfaces();
        /*создается объект класса, созданного специально для прокси*/
//        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(original);
        /*с помощью прокси создается объект, который будет через себя пропускать все методы
         * и при необходимости что-то в них добавлять*/
        return null;
    }
}