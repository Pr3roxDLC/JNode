package me.pr3.JNode.Instructions;

import com.sun.istack.internal.Nullable;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class AssignConstant extends Instruction {

    public Var var = null;
    public double constant = 0;

    //Constants/Variables shall always be of type Double,
    public AssignConstant(Program parent, @Nullable Var var, double c) {
        super(parent);
        this.constant = c;
        //this feels wrong to do
        if(!parent.getVarPool().containsKey(var.getName()))
            parent.getVarPool().put(var.getName(), var);

        this.var = var;
    }

    @Override
    public void run() {
        var.setNumber(constant);
    }

}