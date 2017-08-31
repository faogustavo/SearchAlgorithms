package com.gustavofao.ai.samples;

import com.gustavofao.ai.Algorithms.DepthSearch;
import com.gustavofao.ai.Node;
import com.gustavofao.ai.Utils.Logger;
import com.gustavofao.ai.Utils.NonNullList;
import com.gustavofao.ai.interfaces.State;

import java.util.List;

public class Jars implements State {

    final int jar1;
    final int jar2;
    final String operation;

    public static void main(String[] args) {
        Jars jars = new Jars(0,0, "Estado inicial");
        Node result = new DepthSearch(new Logger()).start(jars);

        if (result == null) {
            System.out.println("Sem solucao!");
        } else {
            System.out.println("Solução:\n" + result.printSolution() + "\n\n");
        }
    }

    public Jars(int jar1, int jar2, String operation) {
        this.jar1 = jar1;
        this.jar2 = jar2;
        this.operation = operation;
    }


    @Override
    public int cost() {
        return 1;
    }

    @Override   
    public boolean isFinalState() {
        return (jar1 == 2 && jar2 == 0) || (jar1 == 0 && jar2 == 2);
    }

    @Override
    public List<State> generateNextStates() {
        List<State> nextStates = new NonNullList<>();

        nextStates.add(fillFirstJar());
        nextStates.add(fillSecondJar());
        nextStates.add(dumpFirstJar());
        nextStates.add(dumpSecondJar());
        nextStates.add(dumpFirstJarIntoSecond());
        nextStates.add(dumoSecondJardIntoFirst());

        return nextStates;
    }

    @Override
    public String toString() {
        return String.format("%s - %s\n", asString(), operation);
    }

    @Override
    public int hashCode() {
        return asString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jars) {
            Jars other = (Jars) obj;
            return this.jar1 == other.jar1 && this.jar2 == other.jar2;
        }

        return false;
    }

    private String asString() {
        return String.format("(%d, %d)", jar1, jar2);
    }

    private State fillFirstJar() {
        if (jar1 < 4) {
            return new Jars(4, jar2, "Enche jarra 1");
        }
        
        return null;
    }

    private State fillSecondJar() {
        if (jar2 < 3) {
            return new Jars(jar1, 3, "Enche jarra 2");
        }

        return null;
    }

    private State dumpFirstJar() {
        if (jar1 > 0) {
            return new Jars(0, this.jar2, "Esvaziar jarra 1");
        }

        return null;
    }

    private State dumpSecondJar() {
        if (jar2 > 0) {
            return new Jars(jar1, 0, "Esvaziar jarra 2");
        }

        return null;
    }

    private State dumpFirstJarIntoSecond() {
        if (jar1 < 4) {
            int jar1 = this.jar1;
            int jar2 = this.jar2;

            while (jar1 > 0 && jar2 < 3) {
                jar1 -= 1;
                jar2 += 1;
            }

            return new Jars(jar1, jar2, "Despejando jarra 1 em jarra 2");
        }

        return null;
    }

    private State dumoSecondJardIntoFirst() {
        if (jar1 < 4) {
            int jar1 = this.jar1;
            int jar2 = this.jar2;

            while (jar1 < 4 && jar2 > 0) {
                jar1 += 1;
                jar2 -= 1;
            }

            return new Jars(jar1, jar2, "Despejando jarra 2 em jarra 1");
        }
        return null;
    }
}
