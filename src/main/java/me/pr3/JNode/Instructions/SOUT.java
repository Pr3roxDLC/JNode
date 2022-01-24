package me.pr3.JNode.Instructions;

import me.pr3.JNode.Instruction;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class SOUT extends Instruction {

    public Var output = null;

    public SOUT(Program parent, Var output){
        super(parent);
        this.output = output;
    }

    public SOUT(Program parent, String[] vars){
        super(parent);
        this.output = parent.getVarPool().get(vars[0]);
    }

    @Override
    public void run() {
        System.out.println(output.getNumber().toString());
    }

}