package me.pr3.JNode.IO;

import me.pr3.JNode.Instructions.*;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Importer {

    //Much more readable compared to the hacky node tree reconstruction from before
    public static Program importProgramFromFile(String filename) {
        Program program = new Program();
        String code = getStringFromFile(filename);
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(code.split(System.lineSeparator())));
        ArrayList<Instruction> instructionList = new ArrayList<>();

        //TODO stop using huge if stacks
        for (String line : lines) {

            String end = "";
            String[] vars = new String[0];
            if (line.contains("[")) {
                end = line.split("\\[")[1];
                end = end.replace("]", "");
                end = end.replace(" ", "");
                vars = end.split(",");
            }

            if (line.startsWith(AssignConstant.class.getSimpleName().toUpperCase(Locale.ROOT))) {
                if(program.getVarPool().containsKey(vars[0])){
                    instructionList.add(new AssignConstant(program, program.getVarPool().get(vars[0]), Double.parseDouble(vars[1])));
                }else{
                    instructionList.add(new AssignConstant(program, new Var(0, vars[0]), Double.parseDouble(vars[1])));
                }
            }
            if(line.startsWith(Calculate.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new Calculate(program, program.getVarPool().get(vars[0]), program.getVarPool().get(vars[1]), program.getVarPool().get(vars[2]), Calculate.Operation.valueOf(vars[3])));
            }
            if(line.startsWith(CallSubroutine.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new CallSubroutine(program, Integer.parseInt(vars[0])));
            }
            if(line.startsWith(Compare.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new Compare(program, program.getVarPool().get(vars[0]), program.getVarPool().get(vars[1]), program.getVarPool().get(vars[2]), Compare.Operation.valueOf(vars[3])));
            }
            if(line.startsWith(CompareJumpIfNotEqual.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new CompareJumpIfNotEqual(program, program.getVarPool().get(vars[0]), program.getVarPool().get(vars[1]), Integer.parseInt(vars[2])));
            }
            if(line.startsWith(CompareJumpIfNotZero.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new CompareJumpIfNotZero(program, program.getVarPool().get(vars[0]), Integer.parseInt(vars[1])));
            }
            if(line.startsWith(Exit.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new Exit(program));
            }
            if(line.startsWith(IO.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new IO(program, program.getVarPool().get(vars[0])));
            }
            if(line.startsWith(Jump.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new Jump(program, Integer.parseInt(vars[0])));
            }
            if(line.startsWith(ReturnSubroutine.class.getSimpleName().toUpperCase(Locale.ROOT))){
                instructionList.add(new ReturnSubroutine(program));
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
            while (myReader.hasNextLine()) {
                str += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return str;
    }

}
