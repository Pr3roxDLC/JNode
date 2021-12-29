package me.pr3.JNode.IO;

import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import me.pr3.JNode.Nodes.*;
import me.pr3.JNode.Var.GlobalPool;
import me.pr3.JNode.Var.Var;

import java.util.ArrayList;
import java.util.Collection;

public class NodeConstructor {
    public Node node = null;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Class getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(Class typeOf) {
        this.typeOf = typeOf;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Var> getVars() {
        return vars;
    }

    public void setVars(ArrayList<Var> vars) {
        this.vars = vars;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Class typeOf = null;
    public ArrayList<Node> children = new ArrayList<Node>();
    public ArrayList<Var> vars = new ArrayList<>();
    public String operation = "";

    public NodeConstructor(Class typeOf, Collection<Node> subnodes, Collection<Var> vars, String operation) {
        System.out.println("Creating NodeConstructor with type of: " + typeOf.getSimpleName());
        children = new ArrayList<>(subnodes);
        this.vars = new ArrayList<>(vars);
        this.operation = operation;
        this.typeOf = typeOf;
    }

    public void assemble() {
        switch (typeOf.getSimpleName()) {
            case "ComparatorNode":
                node = new ComparatorNode(vars.get(0), vars.get(1), vars.get(2), ComparatorNode.Operation.valueOf(operation));
                break;
            case "IfNode":
                node = new IfNode(new ComparatorNode(vars.get(0), vars.get(1), vars.get(2), ComparatorNode.Operation.valueOf(operation)), children.toArray(new Node[0]));
                break;
            case "IONode":
                node = new IONode(IONode.Operation.valueOf(operation), vars.get(0));
                break;
            case "LogicNode":
                node = new LogicNode(vars.get(0), vars.get(1), vars.get(2), LogicNode.Operation.valueOf(operation));
                break;
            case "MathNode":
                node = new MathNode(vars.get(0), vars.get(1), vars.get(2), MathNode.Operation.valueOf(operation));
                break;
            case "ReturnNode":
                node = new ReturnNode();
                break;
            case "TopNode":
                node = new TopNode(new GlobalPool(),children.toArray(new Node[0]));
                break;
            case "WhileLoopNode":
                node = new WhileLoopNode(vars.get(0), children.toArray(new Node[0]));
                break;
            default:
                throw new SyntaxException("Tried constructing Node of wrong class type: " + typeOf.getSimpleName());
        }
    }

}
