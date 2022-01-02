package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;

public class Jump extends Instruction {

    public int pointer = -1;

    //Anything that points to an address must be of type int as it's used for the instruction array index
    public Jump(Program parent, int pointer) {
        super(parent);
        this.pointer = pointer;
    }

    @Override
    public void run() {
        parent.setCounter(pointer - 1);
    }
}
