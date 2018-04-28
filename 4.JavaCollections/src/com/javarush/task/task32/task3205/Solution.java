package com.javarush.task.task32.task3205;

import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
Создание прокси-объекта
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        /*SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        *//* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        *//*
    }

    public static SomeInterfaceWithMethods getProxy() {
        *//*создается объект с набором методов интерфейса*//*
        SomeInterfaceWithMethods original = new SomeInterfaceWithMethodsImpl();
        *//*из него берется ClassLoader*//*
        ClassLoader classLoader = original.getClass().getClassLoader();
        *//*также из него берутся все интерфейсы*//*
        Class<?>[] interfaces = original.getClass().getInterfaces();
        *//*создается объект класса, созданного специально для прокси*//*
        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(original);
        *//*с помощью прокси создается объект, который будет через себя пропускать все методы
        * и при необходимости что-то в них добавлять*//*
        SomeInterfaceWithMethods someInterfaceWithMethods = (SomeInterfaceWithMethods)Proxy.
                newProxyInstance(classLoader, interfaces, invocationHandler);
        return someInterfaceWithMethods;*/
//сервер


        final ReverseImpl service = new ReverseImpl();
/*создается реестр удаленных объектов */
        final Registry registry = LocateRegistry.createRegistry(2099);
        /*заглушка */
        Remote stub = UnicastRemoteObject.exportObject(service, 0);
        registry.bind(UNIC_BINDING_NAME, stub);

//        Thread.sleep(100000);
//        клиент
        Reverse service111 = (Reverse) registry.lookup(UNIC_BINDING_NAME);
        System.out.println(service111.reverse("123456"));


    }
    public static final String UNIC_BINDING_NAME = "server.reverse";

    interface Reverse extends Remote {
        public String reverse (String str) throws RemoteException;
    }
    static class ReverseImpl implements Reverse {

        @Override
        public String reverse(String str) throws RemoteException {
            return new StringBuffer(str).reverse().toString();
        }
    }
}