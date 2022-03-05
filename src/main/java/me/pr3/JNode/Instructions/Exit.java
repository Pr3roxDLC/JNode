package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;

import java.util.Locale;

/*
 * Very important to tell the program where to stop, not including this will result in the program to either throw an ArrayIndexOutOfBoundsExcpetion
 * or read and execute "garbage" instructions belonging to subroutines etc
 */
public class Exit extends Instruction {

    public Exit(Program parent) {
        super(parent);
    }

    public Exit(Program parent, String[] vars) {
        super(parent);
    }

    @Override
    public void run() {
        System.out.println("Exited Program");
        parent.stop();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().toUpperCase(Locale.ROOT);
    }

}
