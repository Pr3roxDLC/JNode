package me.pr3.JNode.Nodes;


import me.pr3.JNode.Var.Var;

public class LogicNode extends Node{

    Operation operation;
    Var out, in1, in2;

    public enum Operation{
        AND,OR,NOT
    }

    //IF operation is NOT, value of in2 will be ignored
    public LogicNode(Var out, Var in1, Var in2, Operation operation){
        this.in1 = in1;
        this.in2 = in2;
        this.operation = operation;
        this.out = out;
    }

    @Override
    public Object[] run() {

        switch (operation){
            case AND:
                if(in1.getValue() == 1 && in2.getValue() == 1){
                    out.setValue(1);
                }else{
                    out.setValue(0);
                }
                break;
            case OR:
                if(in1.getValue() == 1 || in2.getValue() == 1){
                    out.setValue(1);
                }else{
                    out.setValue(0);
                }
                break;
            case NOT:
                if(in1.getValue() == 1){
                    out.setValue(0);
                }else{
                    out.setValue(1);
                }
                break;
        }


        return new Object[]{out};
    }
}
