package me.pr3.JNode.Instructions;

import com.sun.org.apache.xpath.internal.operations.Variable;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class IO extends Instruction{

    Var output = null;

    public IO(Program parent, Var output){
        super(parent);
        this.output = output;

    }

    @Override
    public void run() {
        System.out.println(output.getNumber().toString());
    }
}
