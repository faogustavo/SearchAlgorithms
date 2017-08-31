package com.gustavofao.ai.Utils;

import java.util.*;

public class CollectionsUtils {

    public static <T> Queue<T> listAsQueue(List<T> list) {
        Queue<T> q = new LinkedList<T>();
        q.addAll(list);

        return q;
    }

    public static String ToString(Stack<Integer> s) {
        if (s.isEmpty())
            return "[ ]";

        StringBuilder buffer = new StringBuilder();

        buffer.append("[");
        for (Integer i : s) {
            buffer.append(" ");
            buffer.append(String.valueOf(i));
            buffer.append(" ");
        }
        buffer.append("]");

        return buffer.toString();
    }

}
