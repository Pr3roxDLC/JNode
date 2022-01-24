package me.pr3.JNode.Variable;

public class Var {

    private Number number = null;
    private String name = "";

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Var(Number number, String name){
        this.number = number;
        this.name = name;
    }

    public Var(Number number){
        this.number = number;
        this.name = "v"+String.valueOf(Integer.toHexString(this.hashCode()));
    }

}