package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/*это комментарий для тестирования GIT*/

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int) Math.ceil(collection.size() / .75f));
        map = new HashMap<>(capacity);
        for (E e : collection)
            map.put(e, PRESENT);
    }

    @Override
    public boolean add(Object o) {
        return map.put((E) o, PRESENT) == null;
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            AmigoSet amigoSet = (AmigoSet) super.clone();
            amigoSet.map = (HashMap) map.clone();
            return amigoSet;
        } catch (Exception e) {
            throw new InternalError();
        }
    }
    private void writeObject(ObjectOutputStream s) {
        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        int size = map.size();
        try {
            s.defaultWriteObject();
            s.writeInt(capacity);
            s.writeFloat(loadFactor);
            s.writeInt(size);
            for (E e: map.keySet()) {
                s.writeObject(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readObject(ObjectInputStream s) {
        /*Для десериализации:
         * вычитай все данные
         * создай мапу используя конструктор с capacity и loadFactor*/
        try {
            s.defaultReadObject();
            int capacity = s.readInt();
            float loadFactor = s.readFloat();
            int size = s.readInt();
            map = new HashMap<>(capacity, loadFactor);
            for (int i = 0; i < size; i++) {
                map.put((E)s.readObject(),PRESENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
