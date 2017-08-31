package com.gustavofao.ai;

import com.gustavofao.ai.Utils.Exceptions.DebugExceptionHandler;
import com.gustavofao.ai.Utils.ExceptionHandler;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.interfaces.State;

import java.util.Comparator;
import java.util.HashSet;

public abstract class Search {

    /**
     * Statics
     */
    private static ExceptionHandler ex;

    public static ExceptionHandler getEx() {
        if (ex == null) {
            ex = new DebugExceptionHandler();
        }
        return ex;
    }

    public static void setEx(ExceptionHandler ex) {
        Search.ex = ex;
    }

    public static final Comparator<Node> normalNodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node left, Node right) {
            if (left.g() < right.g())
                return 1;
            else if (left.g() > right.g())
                return -1;
            return 0;
        }
    };

    public static final Comparator<Node> heuristicNodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node left, Node right) {
            if (left.h() < right.h())
                return 1;
            else if (left.h() > right.h())
                return -1;
            return 0;
        }
    };

    /**
     * Class Methods
     */
    private HashSet<State> visited;
    private Logger logger;
    private Status status;

    public Search() {
        this.visited = new HashSet<>();
        this.status = new Status();
    }

    public Search(Logger logger) {
        this();
        this.logger = logger;
        this.logger.setStatus(status);
    }

    public final Node start(State state) {
        status.start();
        if (this.logger != null) {
            this.logger.start();
        }

        Node n = null;

        try {
            n = execute(state);
        } catch (Exception e) {
            getEx().handleException(e);
        }

        status.stop();
        if (this.logger != null) {
            this.logger.logStop();
        }
        return n;
    }

    protected abstract Node execute(State state) throws Exception;

    public boolean isNodeVisited(Node node) {
        return this.visited.contains(node.getState());
    }

    public void markAsVisited(Node node, int openNodes) {
        this.visited.add(node.getState());
        this.status.exploring(node, openNodes);
    }

    public void unmarkAsVisited(Node node) {
        this.visited.remove(node.getState());
    }

}
