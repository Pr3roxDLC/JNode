package me.pr3.JNode.Var;

public class Var {

    public String getName() {
        return name;
    }

    private String name = "";

    private int value = 0;

    public Var(int value){
        this.value = value;
        this.name =""+this.hashCode();
    }

    public Var(int value, String name){
        this.value = value;
        this.name = name;
    }



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
