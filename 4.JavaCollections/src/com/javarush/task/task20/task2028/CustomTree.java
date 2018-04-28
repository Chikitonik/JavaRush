package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("0");
    ;
    Entry<String> nextEntry;


    public CustomTree() {
//        this.root = root;
//        this.root.lineNumber = 0;
    }

    @Override
    public boolean add(String s) {
        /*горизонтальный обход*/
        /*очередь*/
        Queue<Entry> queue = new LinkedList<>();
        Entry<String> entry = new Entry<>(s);
        this.nextEntry = root;
        while (!this.nextEntry.isAvailableToAddChildren()) {
            if (this.nextEntry.leftChild != null) {
                queue.add(this.nextEntry.leftChild);
            }
            if (this.nextEntry.rightChild != null) {
                queue.add(this.nextEntry.rightChild);
            }
            if (!queue.isEmpty()) {
                this.nextEntry = queue.poll();
            }
        }
        if (this.nextEntry.isAvailableToAddChildren()) {
            if (this.nextEntry.availableToAddLeftChildren) {
                entry.parent = this.nextEntry;
                this.nextEntry.leftChild = entry;
                this.nextEntry.availableToAddLeftChildren = false;
//                System.out.printf("добавил left entry %s и присвоил номер линии %d\n", s, entry.lineNumber);

            } else {
                entry.parent = this.nextEntry;
                this.nextEntry.rightChild = entry;
                this.nextEntry.availableToAddRightChildren = false;
//                System.out.printf("добавил right entry %s и присвоил номер линии %d\n", s, entry.lineNumber);
            }
        }

        return true;
    }

    @Override
    public int size() {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        System.out.println("Корень");
        int size = 0;
        while (!queue.isEmpty()) {
            Entry<String> leaf = queue.poll();
            System.out.println("Ветка сына " + leaf.elementName);
            if (leaf.leftChild != null) {
                size++;
                queue.offer(leaf.leftChild);
                System.out.println("left: " + leaf.leftChild.elementName);
            }
            if (leaf.rightChild != null) {
                size++;
                queue.offer(leaf.rightChild);
                System.out.println("right: " + leaf.rightChild.elementName);

            }
        }
        return size;
    }

    public String getParent(String s) {
        String result = null;
        Queue<Entry> queue = new LinkedList<>();
        List<Entry> entryList = new ArrayList<>();
        this.nextEntry = root;
        while (!this.nextEntry.isAvailableToAddChildren()) {
            if (this.nextEntry.leftChild != null) {
                queue.add(this.nextEntry.leftChild);
                entryList.add(this.nextEntry.leftChild);
            }
            if (this.nextEntry.rightChild != null) {
                queue.add(this.nextEntry.rightChild);
                entryList.add(this.nextEntry.rightChild);
            }
            if (!queue.isEmpty()) {
                this.nextEntry = queue.poll();
            }
        }
        for (Entry entry : entryList) {
            if (entry.elementName.equals(s)) {
                result = entry.parent.elementName;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        String s = new String();
        if (o.getClass()!= s.getClass()) {
            throw new UnsupportedOperationException();
        }

        String removed = (String)(o);
        Queue<Entry> queue = new LinkedList<>();
        List<Entry> entryList = new ArrayList<>();
        this.nextEntry = root;
        while (!this.nextEntry.isAvailableToAddChildren()) {
            if (this.nextEntry.leftChild != null) {
                queue.add(this.nextEntry.leftChild);
                entryList.add(this.nextEntry.leftChild);
            }
            if (this.nextEntry.rightChild != null) {
                queue.add(this.nextEntry.rightChild);
                entryList.add(this.nextEntry.rightChild);
            }
            if (!queue.isEmpty()) {
                this.nextEntry = queue.poll();
            }
        }
        for (Entry entry : entryList) {
            if (entry.elementName.equals(removed)) {
                if (entry.parent.rightChild == entry) {
                    entry.parent.availableToAddRightChildren = true;
                    entry = null;
                    return true;
                } else {
                    entry.parent.availableToAddLeftChildren = true;
                    entry = null;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }


    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }


    public static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;

        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren ? availableToAddLeftChildren : availableToAddRightChildren;
        }
    }
}