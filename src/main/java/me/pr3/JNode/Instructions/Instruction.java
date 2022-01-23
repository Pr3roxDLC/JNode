package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;

public abstract class Instruction {

    protected Program parent = null;

    public Instruction(Program parent){
        this.parent = parent;
    }

    //Override this with whatever each instruction should do when its is executed
    public abstract void run();

}