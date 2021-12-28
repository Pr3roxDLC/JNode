package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class IONode extends Node{

    public Var getVar() {
        return var;
    }

    Var var = null;

    public enum Operation{
        IN,
        OUT
    }

    public IONode(Operation op, Var var){
        this.var = var;
    }

    @Override
    public Object[] run() {
        System.out.println(var.getValue());
        return (Object[]) null;
    }
}
