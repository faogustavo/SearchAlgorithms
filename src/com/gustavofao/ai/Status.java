package com.gustavofao.ai;

public class Status {

    private int visitedNodes = 0;
    private int currentDepth = 0;
    private int openNodes = 0;

    private long startTime = -1;
    private long stopTime = -1;

    public void start() {
        if (this.startTime > 0)
            return;

        this.reset();
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        if (this.startTime < 0) {
            System.out.println("You didn't called start().");
            return;
        }

        this.stopTime = System.currentTimeMillis();
    }

    public void reset() {
        this.startTime = -1;
        this.stopTime = -1;
        this.visitedNodes = 0;
        this.currentDepth = 0;
        this.openNodes = 0;
    }

    public long runningTime() {
        if (startTime > 0) {
            if (this.stopTime > 0) {
                return this.stopTime - this.startTime;
            }

            return System.currentTimeMillis() - this.startTime;
        }
        return 0;
    }

    public void exploring(Node node, int openNodes) {
        this.visitedNodes += 1;
        this.openNodes = openNodes;

        if (this.currentDepth != node.getDepth()) {
            this.currentDepth = node.getDepth();
        }
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }

    public int getOpenNodes() {
        return openNodes;
    }
}
