package com.gustavofao.ai.Utils;

import java.util.Collection;
import java.util.LinkedList;

public class NonNullList<T> extends LinkedList<T> {

    @Override
    public boolean add(T o) {
        return o != null && super.add(o);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return super.addAll(removeNullables(c));
    }

    public static <E> NonNullList<E> removeNullables(Collection<? extends E> data) {
        NonNullList<E> newObject = new NonNullList<E>();

        for (E item : data) {
            newObject.add(item);
        }

        return newObject;
    }

}
