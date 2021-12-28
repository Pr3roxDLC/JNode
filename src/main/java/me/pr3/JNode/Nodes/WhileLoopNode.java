package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class WhileLoopNode extends BodyNode{

    Var var = null;


    public WhileLoopNode(Var var, Node ... bodyNodes){
        this.var = var;
        this.nodes = bodyNodes;
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
