package com.gustavofao.ai;

import com.gustavofao.ai.interfaces.Heuristic;
import com.gustavofao.ai.interfaces.State;

import java.util.Comparator;

public class Node {

    private final State state;
    private final Node parent;

    private int depth;
    private int cost;

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent;

        this.cost = state.cost();
        if (parent == null) {
            this.depth = 0;
        } else {
            this.depth = 1 + parent.getDepth();
        }
    }

    public int f() {
        return g() + h();
    }

    public int g() {
        return this.cost;
    }

    public int h() {
        if (this.state instanceof Heuristic) {
            return ((Heuristic) this.state).heuristicCost();
        }
        return 0;
    }

    public int getDepth() {
        return this.depth;
    }

    public State getState() {
        return this.state;
    }

    public boolean isFinalState() {
        return this.getState().isFinalState();
    }

    public Node getParent() {
        return this.parent;
    }

    public void recalculateDepth() {
        if (parent == null) {
            this.depth = 0;
        } else {
            parent.recalculateDepth();
            this.depth = 1 + parent.getDepth();
        }
    }

    public boolean isNewNodeInTree(Node node) {
        if (node == null) {
            return true;
        }

        if (this.equals(node)) {
            return false;
        }

        if (this.parent == null)
            return true;

        return this.parent.isNewNodeInTree(node);
    }

    public String printSolution() {
        String solution = "";
        if (this.parent != null) {
            solution = this.parent.printSolution();
        }

        solution += String.format("\t|-> %s\n", this.toString());
        return solution;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            return this.state.equals(((Node) obj).getState());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
