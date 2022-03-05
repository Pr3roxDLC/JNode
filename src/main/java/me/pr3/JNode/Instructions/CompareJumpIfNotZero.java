package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

import java.util.Locale;

public class CompareJumpIfNotZero extends Instruction {

    public Var in = null;
    public int pointer = -1;

    public CompareJumpIfNotZero(Program parent, Var in, int pointer) {
        super(parent);
        this.in = in;
        this.pointer = pointer;
    }

    public CompareJumpIfNotZero(Program parent, String[] vars) {
        super(parent);
        this.in = parent.getVarPool().get(vars[0]);
        this.pointer = Integer.parseInt(vars[1]);
    }

    @Override
    public void run() {
        if (in.getNumber().doubleValue() != 0)
            parent.setCounter(pointer - 1);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().toUpperCase(Locale.ROOT) + "[" + this.in.getName() + " , " + this.in + "]";
    }

}
