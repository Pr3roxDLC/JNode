package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class Compare extends Instruction{

    public Var out, in1, in2 = null;
    public Operation operation = Operation.SMALLER;

    //Sets out to 1 if true, 0 if false
    public Compare(Program parent, Var out, Var in1, Var in2, Operation operation) {
        super(parent);
        this.out = out;
        this.in1 = in1;
        this.in2 = in2;
        this.operation = operation;
    }

    @Override
    public void run() {
        switch (operation){
            case SMALLER:
                if(in1.getNumber().doubleValue() < in2.getNumber().doubleValue()){
                    out.setNumber(1);
                }else{
                    out.setNumber(0);
                }
                break;
            case SMALLER_OR_EQUAL:
                if(in1.getNumber().doubleValue() <= in2.getNumber().doubleValue()){
                    out.setNumber(1);
                }else{
                    out.setNumber(0);
                }
                break;
            case EQUAL:
                if(in1.getNumber().doubleValue() == in2.getNumber().doubleValue()){
                    out.setNumber(1);
                }else{
                    out.setNumber(0);
                }
                break;
            case GREATER_OR_EQUAL:
                if(in1.getNumber().doubleValue() >= in2.getNumber().doubleValue()){
                    out.setNumber(1);
                }else{
                    out.setNumber(0);
                }
                break;
            case GREATER:
                if(in1.getNumber().doubleValue() > in2.getNumber().doubleValue()){
                    out.setNumber(1);
                }else{
                    out.setNumber(0);
                }
                break;
            case UNEQUAL:
                if(in1.getNumber().doubleValue() != in2.getNumber().doubleValue()){
                    out.setNumber(1);
                }else{
                    out.setNumber(0);
                }
                break;
        }
    }

    public enum Operation{
        SMALLER,
        SMALLER_OR_EQUAL,
        EQUAL,
        GREATER_OR_EQUAL,
        GREATER,
        UNEQUAL
    }
}
