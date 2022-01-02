package me.pr3.JNode.IO;

import me.pr3.JNode.Instructions.AssignConstant;
import me.pr3.JNode.Instructions.Instruction;
import me.pr3.JNode.Interpreter.Program;

import java.util.Locale;

public class Exporter {

    public static void exportProgram(Program program) {
        String output = "";
        for (Instruction instruction : program.instructions) {
            if(instruction instanceof AssignConstant){
                AssignConstant assignConstant = (AssignConstant) instruction;
                output += assignConstant.getClass().getSimpleName().toUpperCase(Locale.ROOT) + "[" + assignConstant.var.getName() + " , " + assignConstant.constant + " ]";
            }
        }
        System.out.println(output);
    }

}
