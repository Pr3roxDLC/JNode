package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class AssignConstant extends Instruction {

    public Var var = null;
    public double constant = 0;

    //Constants/Variables shall always be of type Double,
    public AssignConstant(Program parent, Var var, double c) {
        super(parent);
        this.constant = c;
        //this feels wrong to do
        if(!parent.getVarPool().containsKey(var.getName()))
            parent.getVarPool().put(var.getName(), var);
        this.var = var;
    }

    public AssignConstant(Program parent, String[] vars) {
        super(parent);
        this.constant = Double.parseDouble(vars[1]);

        if(!parent.getVarPool().containsKey(vars[0]))
            parent.getVarPool().put(vars[0], new Var((Number) 0, vars[0]));

        this.var = parent.getVarPool().get(vars[0]);
    }

    @Override
    public void run() {
        if(!parent.getVarPool().containsKey(var.getName()))
            parent.getVarPool().put(var.getName(), var);
        var.setNumber(constant);
    }

}
