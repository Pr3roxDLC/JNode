package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class CompareJumpIfNotZero extends Instruction{

    public Var in = null;
    public int pointer = -1;

    public CompareJumpIfNotZero(Program parent, Var in, int pointer) {
        super(parent);
        this.in = in;
        this.pointer = pointer;
    }

    @Override
    public void run() {
        if(in.getNumber().doubleValue() != 0){
            parent.setCounter(pointer - 1);
        }
    }
}
