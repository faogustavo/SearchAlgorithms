package com.gustavofao.ai.Algorithms;

import com.gustavofao.ai.Node;
import com.gustavofao.ai.Search;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.interfaces.State;

import java.util.Comparator;

public class AStarSearch extends Search {

    public AStarSearch() {
    }

    public AStarSearch(Logger logger) {
        super(logger);
    }

    public static final Comparator<Node> aStartNodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node left, Node right) {
            if (left.f() < right.f())
                return 1;
            else if (left.f() > right.f())
                return -1;
            return 0;
        }
    };

    @Override
    protected Node execute(State state) throws Exception {
        return null;
    }

}
