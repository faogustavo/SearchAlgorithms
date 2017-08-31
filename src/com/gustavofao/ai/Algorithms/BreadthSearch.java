package com.gustavofao.ai.Algorithms;

import com.gustavofao.ai.Node;
import com.gustavofao.ai.Search;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.interfaces.State;

import java.util.*;

public class BreadthSearch extends Search {

    public BreadthSearch() {
        super();
    }

    public BreadthSearch(Logger logger) {
        super(logger);
    }

    @Override
    protected Node execute(State state) throws Exception {
        Queue<Node> openNodes = new LinkedList<>();
        openNodes.add(new Node(state, null));

        Node currentNode;
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
