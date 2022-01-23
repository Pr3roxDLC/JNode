package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class CompareJumpIfNotEqual extends Instruction {

    public Var in1, in2 = null;
    public int pointer = -1;

    public CompareJumpIfNotEqual(Program parent, Var in1, Var in2, int pointer) {
        super(parent);
        this.in1 = in1;
        this.in2 = in2;
        this.pointer = pointer;
    }

    @Override
    public void run() {
        if (in1.getNumber().doubleValue() != in2.getNumber().doubleValue())
            parent.setCounter(pointer - 1);
    }

}