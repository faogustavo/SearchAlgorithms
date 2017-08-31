package com.gustavofao.ai.samples;

import com.gustavofao.ai.Algorithms.DepthSearch;
import com.gustavofao.ai.Node;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.Utils.NonNullList;
import com.gustavofao.ai.Utils.CollectionsUtils;
import com.gustavofao.ai.interfaces.State;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Stack;

/**
 * Results with size 13 on breadth search
 * Running Time: 130459ms (2:10 minutes)
 * Visited Nodes: 1586132
 *
 * Results with size 14 on breadth search
 * Running Time: 743798ms (12:23 minutes)
 * Visited Nodes: 4772047
 */
public class Hanoi implements State {

    private static final int SIZE = 3;

    public static void main(String[] a) {
        Stack<Integer> t1 = new Stack<>();
        Stack<Integer> t2 = new Stack<>();
        Stack<Integer> t3 = new Stack<>();

        int add = SIZE;
        while (add > 0) {
            t1.add(add--);
        }

        Hanoi inicial = new Hanoi(t1, t2, t3, "Estado inicial");

        Node n = new DepthSearch(new Logger(), (int) Math.pow(2, SIZE) - 1, true).start(inicial);
//        Node n = new BreadthSearch(new Logger()).start(inicial);
        if (n == null) {
            System.out.println("sem solucao!");
        } else {
            System.out.println("Solucao:\n" + n.printSolution());
            System.out.println("Movimentos necessários: " + n.getDepth());
        }
    }

    private final Stack<Integer> t1;
    private final Stack<Integer> t2;
    private final Stack<Integer> t3;
    private final String op;

    public Hanoi(Stack<Integer> t1, Stack<Integer> t2, Stack<Integer> t3, String op) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.op = op;
    }

    @Override
    public int cost() {
        return 1;
    }

    @Override
    public boolean isFinalState() {
        return this.t1.isEmpty() && this.t2.isEmpty();
    }

    @Override
    public List<State> generateNextStates() {
        List<State> states = new NonNullList<>();

        states.add(move(this.t1, this.t3, 2));
        states.add(move(this.t1, this.t2, 1));
        states.add(move(this.t2, this.t1, 3));
        states.add(move(this.t2, this.t3, 4));
        states.add(move(this.t3, this.t1, 5));
        states.add(move(this.t3, this.t2, 6));

        return states;
    }

    public boolean equals(Object o) {
        if (o instanceof Hanoi) {
            Hanoi e = (Hanoi)o;
            return this.t1.equals(e.t1) && this.t2.equals(e.t2) && this.t3.equals(e.t3);
        }
        return false;
    }

    public int hashCode() {
        return this.asString().hashCode();
    }

    public String asString() {
        return String.format(
                "(%s, %s, %s)", CollectionsUtils.ToString(this.t1), CollectionsUtils.ToString(this.t2), CollectionsUtils.ToString(this.t3)
        );
    }

    public String toString() {
        return String.format("%s -- %s", this.op, this.asString());
    }

    @Nullable
    private State move(Stack<Integer> from, Stack<Integer> to, int step) {
        if (isValid(from, to)) {
            Stack<Integer> tFrom = (Stack<Integer>) from.clone();
            Stack<Integer> tTo = (Stack<Integer>) to.clone();

            tTo.add(tFrom.pop());
            switch(step) {
                case 1:
                    return new Hanoi(tFrom, tTo, this.t3, "mover da t1 para t2");
                case 2:
                    return new Hanoi(tFrom, this.t2, tTo, "mover da t1 para t3");
                case 3:
                    return new Hanoi(tTo, tFrom, this.t3, "mover da t2 para t1");
                case 4:
                    return new Hanoi(this.t1, tFrom, tTo, "mover da t2 para t3");
                case 5:
                    return new Hanoi(tTo, this.t2, tFrom, "mover da t3 para t1");
                case 6:
                    return new Hanoi(this.t1, tTo, tFrom, "mover da t3 para t2");
            }
        }

        return null;
    }

    private boolean isValid(Stack<Integer> from, Stack<Integer> to) {
        if ( from.empty() || (!to.empty() && from.peek() > to.peek()))
            return false;

        return true;
    }

}
