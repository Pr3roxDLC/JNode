package me.pr3.JNode.IO;

import me.pr3.JNode.Instruction;
import me.pr3.JNode.Instructions.*;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Importer {

    //Much more readable compared to the hacky node tree reconstruction from before
    public static Program importProgramFromFile(String filename) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Program program = new Program();
        String code = getStringFromFile(filename);
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(code.split(System.lineSeparator())));
        ArrayList<Instruction> instructionList = new ArrayList<>();

        for (String line : lines) {
            String end = "";
            String[] vars = new String[0];
            if (line.contains("[")) {
                end = line.split("\\[")[1];
                end = end.replace("]", "");
                end = end.replace(" ", "");
                vars = end.split(",");
            }
            for(Class inst : Instruction.getAllInstructions()) {
                if(line.startsWith(inst.getSimpleName().toUpperCase(Locale.ROOT)))
                    instructionList.add((Instruction) inst.getDeclaredConstructor(Program.class, String[].class).newInstance(program, vars));
            }
            if (line.startsWith(AssignConstant.class.getSimpleName().toUpperCase(Locale.ROOT))) {
                if(program.getVarPool().containsKey(vars[0]))
                    instructionList.add(new AssignConstant(program, program.getVarPool().get(vars[0]), Double.parseDouble(vars[1])));
                else instructionList.add(new AssignConstant(program, new Var(0, vars[0]), Double.parseDouble(vars[1])));
            }
        }

        program.setInstructions(instructionList.toArray(new Instruction[0]));
        return program;
    }

    private static String getStringFromFile(String fileName) {
        String str = "";
        try {
            File myObj = new File(fileName + ".jn");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
                str += myReader.nextLine() + System.lineSeparator();

            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
        return str;
    }

}