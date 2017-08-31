package com.gustavofao.ai.Algorithms;

import com.gustavofao.ai.Node;
import com.gustavofao.ai.Search;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.interfaces.State;

import java.util.PriorityQueue;
import java.util.Queue;

public class HillClimbing extends Search {

    public HillClimbing() {
    }

    public HillClimbing(Logger logger) {
        super(logger);
    }

    @Override
    protected Node execute(State state) throws Exception {
        return recursiveSearch(new Node(state, null));
    }

    private Node recursiveSearch(Node node) {
        if (node == null || this.isNodeVisited(node)) {
            return null;
        }

        this.markAsVisited(node, 0);
        if (node.isFinalState()) {
            return node;
        }

        Queue<Node> nextStates = new PriorityQueue<>(normalNodeComparator);
        for (State child : node.getState().generateNextStates()) {
            nextStates.add(new Node(child, node));
        }

        Node nextChild;
        while ((nextChild = nextStates.poll()) != null) {
            Node childResult = recursiveSearch(nextChild);
            if (childResult != null) {
                return childResult;
            }
        }

        return null;
    }

}
