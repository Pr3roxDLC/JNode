package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class SOUT extends Instruction{

    public Var output = null;

    public SOUT(Program parent, Var output){
        super(parent);
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println(output.getNumber().toString());
    }

}