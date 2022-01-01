package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;

public class Exit extends Instruction {
    public Exit(Program parent) {
        super(parent);
    }

    @Override
    public void run() {
        System.out.println("Exited Program");
        parent.stop();
    }
}
