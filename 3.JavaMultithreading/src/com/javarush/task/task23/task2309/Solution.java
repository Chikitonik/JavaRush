package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.List;

/* 
Анонимность иногда так приятна!
1. В пакете vo создайте public классы User, Location, Server, Subject, Subscription, которые наследуются от NamedItem
2. В классе Solution для каждого класса создайте свой метод, который
возвращает список экземпляров класса.

Например, для класса User это будет - public List<User> getUsers()
Для класса Location это будет - public List<Location> getLocations()

3. Внутри каждого такого метода создайте анонимный класс от AbstractDbSelectExecutor
и вызовите его нужный метод.
Подсказка:
тело метода должно начинаться так: return new AbstractDbSelectExecutor

4. Пример вывода для User и Location:
Id=5, name='User-5', description=Got by executing 'SELECT * FROM USER'
Id=1, name='Location-1', description=Got by executing 'SELECT * FROM LOCATION'

5. Проанализируйте пример вывода и сформируйте правильный query для всех классов.
*/
public class Solution {
    public List<User> getUsers(){
        return new AbstractDbSelectExecutor<User>(){
            @Override
            public String getQuery() {
                String name = Thread.currentThread().getStackTrace()[3].getMethodName();
                return String.format("SELECT * FROM %s",
                        name.substring(3, name.length()-1).toUpperCase());
            }
        }.execute();
    }
    public List<Location> getLocations(){
        return new AbstractDbSelectExecutor<Location>(){
            @Override
            public String getQuery() {
                String name = Thread.currentThread().getStackTrace()[3].getMethodName();
                return String.format("SELECT * FROM %s",
                        name.substring(3, name.length()-1).toUpperCase());
            }
        }.execute();
    }
    public List<Server> getServers(){
        return new AbstractDbSelectExecutor<Server>(){
            @Override
            public String getQuery() {
                String name = Thread.currentThread().getStackTrace()[3].getMethodName();
                return String.format("SELECT * FROM %s",
                        name.substring(3, name.length()-1).toUpperCase());
            }
        }.execute();
    }
    public List<Subject> getSubjects() {
        return new AbstractDbSelectExecutor<Subject>(){
            @Override
            public String getQuery() {
                String name = Thread.currentThread().getStackTrace()[3].getMethodName();
                return String.format("SELECT * FROM %s",
                        name.substring(3, name.length()-1).toUpperCase());
            }
        }.execute();
    }
    public List<Subscription> getSubscriptions(){
        return new AbstractDbSelectExecutor<Subscription>(){
            @Override
            public String getQuery() {
                String name = Thread.currentThread().getStackTrace()[3].getMethodName();
                return String.format("SELECT * FROM %s",
                        name.substring(3, name.length()-1).toUpperCase());
            }
        }.execute();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
        print(solution.getServers());
        print(solution.getSubjects());
        print(solution.getSubscriptions());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }
}
