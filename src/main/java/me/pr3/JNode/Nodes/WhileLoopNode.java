package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class WhileLoopNode extends BodyNode{

    public Var getVar() {
        return var;
    }

    Var var = null;


    public WhileLoopNode(Var var, Node ... bodyNodes){
        super(bodyNodes);
        this.var = var;
    }

    @Override
    public Object[] run() {
        if(var.getValue() == 1){
            for(Node node : nodes){
                node.run();
            }
            run();
        }
        return (Object[])null;
    }
}
