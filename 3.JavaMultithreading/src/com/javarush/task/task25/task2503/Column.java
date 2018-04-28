package com.javarush.task.task25.task2503;

import java.util.*;

public enum Column implements Columnable{

    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        for (int i = 0; i < realOrder.length; i++) {
                Column  column = Column.values()[i];
                if (column.isShown()) {
                    result.add(column);
                }
        }
        Collections.sort(result, new Comparator<Column>() {
            @Override
            public int compare(Column o1, Column o2) {
                Integer i1 = realOrder[o1.ordinal()];
                Integer i2 = realOrder[o2.ordinal()];
                return i1.compareTo(i2);
            }
        });

//        ///////////////////////////////////
//        public static void sort(List<Stock> list) {
//            Collections.sort(list, new Comparator<Stock>() {
//                public int compare(Stock stock1, Stock stock2) {
//                    String s = (String)stock1.get("name");
//                    String s2 = (String)stock2.get("name");
//                    return s.compareTo(s2);//сортировка по имени
//                }
//            }.thenComparing(new Comparator<Stock>() {
//                public int compare(Stock stock1, Stock stock2) {
//                    Calendar c = new GregorianCalendar();
//                    Calendar c2 = new GregorianCalendar();
//                    c.setTime((Date)stock1.get("date"));
//                    c2.setTime((Date)stock2.get("date"));
//                    return Integer.compare(c.get(1),c2.get(1))*-1;//сортировка по году
//                }
//            }.thenComparing(new Comparator<Stock>() {
//                public int compare(Stock stock1, Stock stock2) {
//                    Calendar c = new GregorianCalendar();
//                    Calendar c2 = new GregorianCalendar();
//                    c.setTime((Date)stock1.get("date"));
//                    c2.setTime((Date)stock2.get("date"));
//                    return Integer.compare(c.get(6),c2.get(6))*-1;//сортировка по номеру дня в году
//                }
//            }.thenComparing(new Comparator<Stock>() {
//                @Override
//                public int compare(Stock o1, Stock o2) {
//                    double change,change2;
//                    if (o1.get("change")!=null) {
//                        change = (double) o1.get("change");
//                    } else {
//                        change = (double) o1.get("last")-(double)o1.get("open");
//                    }
//                    if (o2.get("change")!=null) {
//                        change2 = (double) o2.get("change");
//                    } else {
//                        change2 = (double) o2.get("last")-(double)o2.get("open");
//                    }
//
//                    return Double.compare(change,change2 )*-1;//сортировка по прибыли
//                }
//            }))));
//        }
        //////////////////////////////////
        return result;
    }
    /**
     * @return полное имя колонки
     */
    @Override
    public String getColumnName() {
        return this.columnName;
    }
    /**
     * Возвращает true, если колонка видимая, иначе false
     */
    @Override
    public boolean isShown() {
        return realOrder[this.ordinal()]==-1? false : true;
    }
    /**
     * Скрывает колонку - маркирует колонку -1 в массиве realOrder.
     * Сдвигает индексы отображаемых колонок, которые идут после скрытой
     */
    @Override
    public void hide() {
        realOrder[this.ordinal()]=-1;
    }

}
