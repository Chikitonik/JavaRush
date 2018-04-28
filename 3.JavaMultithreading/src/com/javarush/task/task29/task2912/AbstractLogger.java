package com.javarush.task.task29.task2912;

/**
 * В программе реализован паттерн "цепочка ответственности". Изучи его внимательно. В классах FileLogger, ConsoleLogger,
 * SmsLogger, PhoneLogger есть много повторяющегося кода. Подними весь повторяющийся код в абстрактный класс AbstractLogger.
 Подъемом в рефакторинге называется перенос полей, методов, конструкторов из всех наследников в одного общего предка.
 Из наследников, при этом, удаляется код, который перенесен в класс предка.
 */
public abstract class AbstractLogger implements Logger {
    int level;
    Logger next;

    public AbstractLogger(int level) {
        this.level = level;
    }

    public void inform (String message, int level){
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }

    public void setNext(Logger next) {
        this.next = next;
    }
    public abstract void info(String message);
}
