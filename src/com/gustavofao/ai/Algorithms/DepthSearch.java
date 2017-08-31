package com.gustavofao.ai.Algorithms;

import com.gustavofao.ai.Node;
import com.gustavofao.ai.Search;
import com.gustavofao.ai.Utils.CollectionsUtils;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.interfaces.State;

import java.util.*;

public class DepthSearch extends Search {

    private int maxDepth = 1024;
    private boolean removeOnPop = false;

    public DepthSearch() {
        super();
    }

    public DepthSearch(int maxDepth) {
        super();
        this.maxDepth = maxDepth;
    }

    public DepthSearch(Boolean removeOnPop) {
        super();
        this.removeOnPop = removeOnPop;
    }

    public DepthSearch(Logger logger) {
        super(logger);
    }

    public DepthSearch(Logger logger, int maxDepth) {
        super(logger);
        this.maxDepth = maxDepth;
    }

    public DepthSearch(Logger logger, Boolean removeOnPop) {
        super(logger);
        this.removeOnPop = removeOnPop;
    }

    public DepthSearch(int maxDepth, Boolean removeOnPop) {
        super();
        this.maxDepth = maxDepth;
        this.removeOnPop = removeOnPop;
    }

    public DepthSearch(Logger logger, int maxDepth, Boolean removeOnPop) {
        super(logger);
        this.maxDepth = maxDepth;
        this.removeOnPop = removeOnPop;
    }

    public DepthSearch setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    public DepthSearch setRemoveOnPop(boolean remove) {
        this.removeOnPop = remove;
        return this;
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

        if (node.getDepth() >= this.maxDepth) {
            if (this.removeOnPop) {
                this.unmarkAsVisited(node);
            }

            return null;
        }

        Queue<State> states = CollectionsUtils.listAsQueue(node.getState().generateNextStates());
        State next;
        while ((next = states.poll()) != null) {

            Node childResult = recursiveSearch(new Node(next, node));
            if (childResult != null) {
                return childResult;
            }
        }

        if (this.removeOnPop) {
            this.unmarkAsVisited(node);
        }

        return null;
    }

}
