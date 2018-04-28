package com.javarush.task.task30.task3004;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class Solution {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        /*в пул потоков ForkJoinPool добавляем новый экземпляр нашего класса*/
        String result = forkJoinPool.invoke(new RecursiveLine(5));
        System.out.println(result);
    }
    private static class RecursiveLine extends RecursiveTask<String> {
        private int i;
        public RecursiveLine(int i) {
            this.i = i;
        }
        @Override
        /*реализиция метода, возвращающего результат
        * можно сказать что, это что-то вроде аналога Callable*/
        protected String compute() {
            String result = String.valueOf(i);
            if (i > 0) /*пока i больше нуля, метод запускается снова*/{
                RecursiveLine task = new RecursiveLine(i - 1);/*создается новый объект с новым значение i*/
                task.fork();/*запускает compute() у объекта, созданного выше*/
                return result + task.join()/*результат выполнения задачи, он будет "накапливать"
                значения - сначала это 5, потом 4, потом 3 и тд*/;
            }
            return result;/*итоговый результат 0 плюс "накопленные" значения*/
        }
    }
}
