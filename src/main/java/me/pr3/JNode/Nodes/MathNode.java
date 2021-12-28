package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class MathNode extends Node {

    Operation operation;
    Var out;
    Var in1;

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

    Var in2;

    public enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION,
        MODULO
    }

    public MathNode(Var out, Var in1, Var in2, Operation operation) {
        this.in1 = in1;
        this.in2 = in2;
        this.out = out;
        this.operation = operation;
    }

    @Override
    public Object[] run() {


        switch (operation) {
            case ADDITION:
                out.setValue(in1.getValue() + in2.getValue());
                break;
            case SUBTRACTION:
                out.setValue(in1.getValue() - in2.getValue());
                break;
            case MULTIPLICATION:
                out.setValue(in1.getValue() * in2.getValue());
                break;
            case DIVISION:
                out.setValue(in1.getValue() / in2.getValue());
                break;
            case MODULO:
                out.setValue(in1.getValue() % in2.getValue());
                break;
        }

        return new Object[0];
    }
}
