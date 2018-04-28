package com.javarush.task.task29.task2908;

import java.util.concurrent.*;

/* Argument and Value are generic types*/
public class CacheComputeManager<Argument, Value> implements Computable<Argument, Value> {
//    потокобезопасная мапа с каким-то аргументом и каким-то значением класса, поддерживающего интерфейс Future
    private final ConcurrentHashMap<Argument, Future<Value>> cache = new ConcurrentHashMap<>();
//    класс, поддерживающий интерфейс Computable
    private final Computable<Argument, Value> computable;
// в конструктор подается класс, поддерживающий интерфейс Computable
    public CacheComputeManager(Computable<Argument, Value> computable) {
        this.computable = computable;
    }

    @Override
//    метод, вычисляющий из аргумента какое-либо значение
    public Value compute(final Argument arg) throws InterruptedException {
//        значение берется из мапы
        Future<Value> f = cache.get(arg);
//        если значения в мапе нет, то оно вычисляется, кладется в мапу и запускается
        if (f == null) {
            FutureTask<Value> ft = createFutureTaskForNewArgumentThatHaveToComputeValue(arg);
            cache.putIfAbsent(arg, ft);
            f = ft;
            ft.run();
            System.out.print(arg + " will be cached  ");
        } else {
            System.out.print(arg + " taken from cache  ");
        }
        try {
            return f.get();
        } catch (CancellationException e) {
            cache.remove(arg, f);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
        return null;
    }

    public FutureTask<Value> createFutureTaskForNewArgumentThatHaveToComputeValue(final Argument arg) {

        FutureTask futureTask = new FutureTask<Value>(new Callable<Value>() {
            @Override
            public Value call() throws Exception {
                return computable.compute(arg);
            }
        });
        return futureTask;
    }
}
