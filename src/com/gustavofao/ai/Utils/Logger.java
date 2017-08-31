package com.gustavofao.ai.Utils;

import com.gustavofao.ai.Search;
import com.gustavofao.ai.Status;

public class Logger extends Thread {

    private Status status;
    private boolean isExecuting = false;
    private long delay = 1000;

    public Logger() {
        this.delay = 1000;
    }

    public Logger(int logDelay) {
        this.delay = logDelay;
    }

    @Override
    public synchronized void start() {
        this.isExecuting = true;
        super.start();
    }

    public void logStop() {
        this.isExecuting = false;
        System.out.println("Search ended");
        System.out.println("Running Time: " + getRunningTime());
        System.out.println("Visited Nodes: " + this.status.getVisitedNodes());
        System.out.println("\n\n");
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private String getRunningTime() {
        return (this.status.runningTime()) + "ms";
    }

    private void showStatusOnConsole() {
        if (status != null) {
            System.out.println("\n\n");
            System.out.println("Running Time: " + getRunningTime());
            System.out.println("Current Dephth: " + this.status.getCurrentDepth());
            System.out.println("Visited Nodes: " + this.status.getVisitedNodes());
            System.out.println("Opened Nodes: " + this.status.getOpenNodes());
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.delay);

            while (isExecuting) {
                showStatusOnConsole();
                Thread.sleep(this.delay);
            }
        } catch (InterruptedException e) {
            Search.getEx().handleException(e);
        }
    }
}
