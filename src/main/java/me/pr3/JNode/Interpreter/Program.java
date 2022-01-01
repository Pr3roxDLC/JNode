package me.pr3.JNode.Interpreter;

import me.pr3.JNode.Instructions.Instruction;

public class Program {

    private boolean stop = false;
    private int counter = 0;

    public void setCounter(int counter){
        this.counter = counter;
    }

    public int getCounter(){
        return counter;
    }


    public Program(){

    }

    public void stop(){
        stop = true;
    }

    public Instruction[] instructions = new Instruction[0];

    public void setInstructions(Instruction[] instructions){
        this.instructions = instructions;
    }

    public void runProgram(){
        counter = 0;
        while(!stop){
            instructions[counter].run();
            //increment the counter after executing each instruction
            counter++;
        }
    }

}
