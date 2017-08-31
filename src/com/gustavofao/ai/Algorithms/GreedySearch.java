package com.gustavofao.ai.Algorithms;

import com.gustavofao.ai.Node;
import com.gustavofao.ai.Search;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.interfaces.State;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GreedySearch extends Search {

    public GreedySearch() {
    }

    public GreedySearch(Logger logger) {
        super(logger);
    }

    @Override
    protected Node execute(State state) throws Exception {
        Node currentNode;

        Queue<Node> openNodes = new PriorityQueue<>(heuristicNodeComparator);
        openNodes.add(new Node(state, null));

        while ((currentNode = openNodes.poll()) != null) {
            this.markAsVisited(currentNode, openNodes.size());

            if (currentNode.isFinalState()) {
                return currentNode;
            } else {
                List<State> nextStates = currentNode.getState().generateNextStates();
                for (State childState : nextStates) {
                    Node n = new Node(childState, currentNode);
                    if (!this.isNodeVisited(n) && !openNodes.contains(n)) {
                        openNodes.add(n);
                    }
                }
            }
        }

        return null;
    }

}
