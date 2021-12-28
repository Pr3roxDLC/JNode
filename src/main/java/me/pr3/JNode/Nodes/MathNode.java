package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class MathNode extends Node {

    Operation operation;
    Var out, in1, in2;

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
                System.out.println("Adding " + in1.getValue() + " and " + in2.getValue() + " and storing to out");
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
