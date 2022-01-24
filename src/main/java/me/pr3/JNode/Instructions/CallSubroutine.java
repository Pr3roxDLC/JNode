package me.pr3.JNode.Instructions;

import me.pr3.JNode.Interpreter.Program;

public class CallSubroutine extends Instruction {

    public int subroutineAddress = -1;

    public CallSubroutine(Program parent, int subroutineAddress) {
        super(parent);
        this.subroutineAddress = subroutineAddress;
    }

    public CallSubroutine(Program parent, String[] vars) {
        super(parent);
        this.subroutineAddress = Integer.parseInt(vars[0]);
    }

    @Override
    public void run() {
        parent.getReturnStack().push(parent.getCounter());
        parent.setCounter(subroutineAddress - 1);
    }

}