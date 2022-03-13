package me.pr3.JNode.Instructions;


import me.pr3.JNode.Interpreter.Program;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Instruction {

    protected Program parent;

    public static ArrayList<Class<?>> instructionSubTypes =  new ArrayList<>(Arrays.asList(AssignConstant.class,
                Calculate.class,
                CallSubroutine.class,
                Compare.class,
                CompareJumpIfNotEqual.class,
                CompareJumpIfNotZero.class,
                Exit.class,
                Jump.class,
                ReturnSubroutine.class,
                SOUT.class));

    public Instruction(Program parent){
        this.parent = parent;
    }

    public static List<Class<?>> getAllInstructions() {
       return instructionSubTypes;
    }

    //Override this with whatever each instruction should do when its is executed
    public abstract void run();

}
