package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class AssignConstant extends Instruction{

    Var var = null;
    double constant = 0;

    //Constants/Variables shall always be of type Double,
    public AssignConstant(Program parent, Var var, double c) {
        super(parent);
        this.constant = c;
        this.var = var;
    }

    @Override
    public void run() {
            var.setNumber(constant);
    }
}
