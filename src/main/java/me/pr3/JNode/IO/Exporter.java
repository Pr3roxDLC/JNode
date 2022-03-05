package me.pr3.JNode.IO;

import me.pr3.JNode.Instructions.Instruction;
import me.pr3.JNode.Instructions.*;
import me.pr3.JNode.Interpreter.Program;

import java.io.*;

public class Exporter {

    public static void exportProgram(Program program, String name) {
        boolean containsExitInstruction = false;
        StringBuilder output = new StringBuilder();
        for (Instruction instruction : program.instructions) {
            output.append(instruction);
            if (instruction instanceof Exit)
                containsExitInstruction = true;
            output.append(System.lineSeparator());
        }
        if (!containsExitInstruction)
            System.out.println("Exported Program with no Exit instruction");
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
