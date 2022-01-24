package me.pr3.JNode;

import me.pr3.JNode.IO.Exporter;
import me.pr3.JNode.IO.Importer;
import me.pr3.JNode.Instructions.*;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;
import me.pr3.JNode.gui.GUI;

import java.lang.reflect.InvocationTargetException;

public class Main {

    //Compared to legacy, this new version will run on an assembly like instruction set that will be executed one by one, instead of having nodes with child nodes etc,
    //jumps will be used to skip/repeat certain parts of the code, as a result of this, each instruction in a program will have to be assigned a certain, unique
    //address. On top of this, a stack holding all the current return addresses will be needed for the implementation of subroutines as we need to store the
    //return address to where we need to jump back after the subroutine is done
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Var var = new Var(0);
        Var var2 = new Var(0);
        Var var3 = new Var(0);
        Var var4 = new Var(0);
        Program program = new Program();
        program.setInstructions(new Instruction[]{
                new AssignConstant(program, var, 5),
                new SOUT(program, var),
                new CallSubroutine(program, 4),
                new Exit(program),

                new AssignConstant(program, var2, 1),
                new Calculate(program, var, var, var2, Calculate.Operation.ADD),
                new SOUT(program, var),
                new CallSubroutine(program, 11),
                new AssignConstant(program, var4, 0),
                new SOUT(program, var4),
                new ReturnSubroutine(program),

                new AssignConstant(program, var3, 100),
                new SOUT(program, var3),
                new ReturnSubroutine(program)
        });

        program.runProgram();

        Exporter.exportProgram(program, "test");

        Exporter.exportProgram(Importer.importProgramFromFile("test"), "test2");

        //GUI.initGUI();

    }

}