package me.pr3.JNode;

import me.pr3.JNode.Interpreter.Program;
import net.sourceforge.stripes.util.ResolverUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Instruction {

    protected Program parent = null;

    public Instruction(Program parent){
        this.parent = parent;
    }

    public static List<Class> getAllInstructions() {
        try {
            ResolverUtil resolver = new ResolverUtil();
            resolver.findImplementations(Instruction.class, Instruction.class.getPackage().getName());
            return new ArrayList<>(resolver.getClasses());
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load any Instructions");
        }
    }

    //Override this with whatever each instruction should do when its is executed
    public abstract void run();

}