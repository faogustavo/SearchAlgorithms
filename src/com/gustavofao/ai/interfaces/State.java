package com.gustavofao.ai.interfaces;

import java.util.List;

public interface State {

    /**
     * @return The cost of the movement
     */
    public abstract int cost();

    /**
     * @return If it is the final state
     */
    public abstract boolean isFinalState();

    /**
     * Generate all next states but should call
     * add next state to be actually add
     */
    public abstract List<State> generateNextStates();
}
