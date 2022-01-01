package me.pr3.JNode;

import me.pr3.JNode.Instructions.*;
import me.pr3.JNode.Interpreter.Program;
import me.pr3.JNode.Variable.Var;

public class Main {

    //Compared to 1.0, 1.1 will run on an assembly like instruction set that will be executed one by one, instead of having nodes with child nodes etc,
    //jumps will be used to skip/repeat certain parts of the code, as a result of this, each instruction in a program will have to be assigned a certain, unique
    //address. On top of this, a stack holding all the current return addresses will be needed for the implementation of subroutines as we need to store the
    //return address to where we need to jump back after the subroutine is done
    public static void main(String[] args) {
        Var var = new Var(7, "Test1");
        Program program = new Program();
        program.setInstructions(new Instruction[]{
                new Calculate(program, var, var , new Var(1), Calculate.Operation.ADD),
                new IO(program, var),
                new CompareJumpIfNotEqual(program, var, new Var(10), 0),
                new Exit(program)
        });
        program.runProgram();
    }
}
