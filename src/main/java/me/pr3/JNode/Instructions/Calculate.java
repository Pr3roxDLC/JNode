package me.pr3.JNode.Instructions;

import me.pr3.JNode.Instruction;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class Calculate extends Instruction {

    public Var out, in1, in2 = null;
    public Operation operation = Operation.ADD;

    public Calculate(Program parent, Var out, Var in1, Var in2, Operation operation) {
        super(parent);
        this.out = out;
        this.in1 = in1;
        this.in2 = in2;
        this.operation = operation;
    }

    public Calculate(Program parent, String[] vars) {
        super(parent);
        this.out = parent.getVarPool().get(vars[0]);
        this.in1 = parent.getVarPool().get(vars[1]);
        this.in2 = parent.getVarPool().get(vars[2]);
        this.operation = Calculate.Operation.valueOf(vars[3]);
    }

    @Override
    public void run() {
        switch (operation) {
            case ADD:
                out.setNumber(in1.getNumber().doubleValue() + in2.getNumber().doubleValue());
                break;
            case SUBTRACT:
                out.setNumber(in1.getNumber().doubleValue() - in2.getNumber().doubleValue());
                break;
            case MULTIPLY:
                out.setNumber(in1.getNumber().doubleValue() * in2.getNumber().doubleValue());
                break;
            case DIVIDE:
                out.setNumber(in1.getNumber().doubleValue() / in2.getNumber().doubleValue());
                break;
            case MODULO:
                out.setNumber(in1.getNumber().doubleValue() % in2.getNumber().doubleValue());
                break;
            default:
                throw new Error("Unsupported Operation received for Calculate Instruction at: " + parent.getCounter());
        }
    }

    public enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        MODULO
    }

}