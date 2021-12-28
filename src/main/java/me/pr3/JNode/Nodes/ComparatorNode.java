package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class ComparatorNode extends Node {

    Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public Var getOut() {
        return out;
    }

    public Var getIn1() {
        return in1;
    }

    public Var getIn2() {
        return in2;
    }

    Var out, in1, in2;

    public enum Operation {
        GREATER,
        GREATEROREQUAL,
        EQUAL,
        SMALLEROREQUAL,
        SMALLER,
        UNQEUAL
    }

    public ComparatorNode(Var out, Var in1, Var in2, Operation operation) {
        this.operation = operation;
        this.out = out;
        this.in1 = in1;
        this.in2 = in2;
    }

    @Override
    public Object[] run() {

        switch (operation) {
            case EQUAL:
                if (in1.getValue() == in2.getValue()) {
                    out.setValue(1);
                } else {
                    out.setValue(0);
                }
                break;
            case GREATER:
                if (in1.getValue() > in2.getValue()) {
                    out.setValue(1);
                } else {
                    out.setValue(0);
                }
                break;
            case GREATEROREQUAL:
                if (in1.getValue() >= in2.getValue()) {
                    out.setValue(1);
                } else {
                    out.setValue(0);
                }
                break;
            case SMALLER:
                if (in1.getValue() < in2.getValue()) {
                    out.setValue(1);
                } else {
                    out.setValue(0);
                }
                break;
            case SMALLEROREQUAL:
                if (in1.getValue() <= in2.getValue()) {
                    out.setValue(1);
                } else {
                    out.setValue(0);
                }
                break;
            case UNQEUAL:
                if (in1.getValue() != in2.getValue()) {
                    out.setValue(1);
                } else {
                    out.setValue(0);
                }
                break;


        }

        return new Object[]{out};
    }
}
