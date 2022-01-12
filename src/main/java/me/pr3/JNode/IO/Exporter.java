package me.pr3.JNode.IO;

import me.pr3.JNode.Instructions.*;
import me.pr3.JNode.Interpreter.Program;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Exporter {
    //TODO stop using huge if stacks
    public static void exportProgram(Program program, String name) {
        boolean containsExitInstruction = false;
        StringBuilder output = new StringBuilder();
        for (Instruction instruction : program.instructions) {
            if (instruction instanceof AssignConstant) {
                AssignConstant assignConstant = (AssignConstant) instruction;
                output.append(assignConstant.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(assignConstant.var.getName()).append(" , ").append(assignConstant.constant).append(" ]");
            }
            if (instruction instanceof Calculate) {
                Calculate calculate = (Calculate) instruction;
                output.append(calculate.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(calculate.out.getName()).append(" , ").append(calculate.in1.getName()).append(" , ").append(calculate.in2.getName()).append(" , ").append(calculate.operation.name()).append(" ]");
            }
            if (instruction instanceof CallSubroutine) {
                CallSubroutine callSubroutine = (CallSubroutine) instruction;
                output.append(callSubroutine.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(callSubroutine.subroutineAddress).append("]");
            }
            if (instruction instanceof Compare) {
                Compare compare = (Compare) instruction;
                output.append(compare.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(compare.out.getName()).append(" , ").append(compare.in1.getName()).append(" , ").append(compare.in2.getName()).append(" , ").append(compare.operation.name()).append("]");
            }
            if (instruction instanceof CompareJumpIfNotEqual) {
                CompareJumpIfNotEqual compareJumpIfNotEqual = (CompareJumpIfNotEqual) instruction;
                output.append(compareJumpIfNotEqual.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(compareJumpIfNotEqual.in1.getName()).append(" , ").append(compareJumpIfNotEqual.in2).append(" , ").append(compareJumpIfNotEqual.pointer).append("]");
            }
            if (instruction instanceof CompareJumpIfNotZero) {
                CompareJumpIfNotZero compareJumpIfNotZero = (CompareJumpIfNotZero) instruction;
                output.append(compareJumpIfNotZero.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(compareJumpIfNotZero.in.getName()).append(" , ").append(compareJumpIfNotZero.in).append("]");
            }
            if (instruction instanceof Exit) {
                Exit exit = (Exit) instruction;
                containsExitInstruction = true;
                output.append(exit.getClass().getSimpleName().toUpperCase(Locale.ROOT));
            }
            if(instruction instanceof SOUT){
                SOUT SOUT = (SOUT) instruction;
                output.append(SOUT.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(SOUT.output.getName()).append("]");
            }
            if(instruction instanceof Jump){
                Jump jump = (Jump) instruction;
                output.append(jump.getClass().getSimpleName().toUpperCase(Locale.ROOT)).append("[").append(jump.pointer).append("]");
            }
            if(instruction instanceof ReturnSubroutine){
                ReturnSubroutine returnSubroutine = (ReturnSubroutine) instruction;
                output.append(returnSubroutine.getClass().getSimpleName().toUpperCase(Locale.ROOT));
            }
            output.append(System.lineSeparator());
        }
        if (!containsExitInstruction) {
            System.out.println("Exported Program with no Exit instruction");
        }
        createFile(output.toString(), name);
    }


    public static void createFile(String text, String name) {
        try {
            FileWriter myWriter = new FileWriter(name + ".jn");
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully exported " + name + ".jn");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
