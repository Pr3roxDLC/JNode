package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;

public class ReturnSubroutine extends Instruction{
    public ReturnSubroutine(Program parent) {
        super(parent);
    }

    @Override
    public void run() {
            parent.setCounter(parent.getReturnStack().pop());
    }
}
