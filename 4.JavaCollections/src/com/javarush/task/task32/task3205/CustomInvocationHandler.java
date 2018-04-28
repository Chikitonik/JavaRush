package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    /*после с оригинальным классом, методы которого будем изменять*/
    private SomeInterfaceWithMethods original;
    /*стандартный конструктор*/
    public CustomInvocationHandler(SomeInterfaceWithMethods original) {
        this.original = original;
    }
/*реализация метода, кторый реагирует на вызов любого метода*/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*какое-либо действие до запуска оригинального метода*/
        System.out.println(method.getName() + " in");
        /* запуск оригинального метода сохраняется в переменную*/
        Object o = method.invoke(original,args);
        /*какое-либо действие после запуска оригинального метода*/
        System.out.println(method.getName() + " out");
        /*возвращается запуск оригинального метода*/
        return o;
    }
}
