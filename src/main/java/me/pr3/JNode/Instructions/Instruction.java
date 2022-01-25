package me.pr3.JNode.Instructions;

import javassist.tools.reflect.Reflection;
import jdk.nashorn.internal.codegen.CompilerConstants;
import me.pr3.JNode.Interpreter.Program;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class Instruction {

    protected Program parent = null;

    public static ArrayList<Class> instructionSubTypes = new ArrayList<>();

    static {
        //IK this is bad but there is no better way of doing this without using hacky reflections
        instructionSubTypes = new ArrayList(Arrays.asList(AssignConstant.class,
                Calculate.class,
                CallSubroutine.class,
                Compare.class,
                CompareJumpIfNotEqual.class,
                CompareJumpIfNotZero.class,
                Exit.class,
                Jump.class,
                ReturnSubroutine.class,
                SOUT.class));
    }

    public Instruction(Program parent){
        this.parent = parent;
    }

    public static List<Class> getAllInstructions() {
       return instructionSubTypes;
    }

    //Override this with whatever each instruction should do when its is executed
    public abstract void run();

}