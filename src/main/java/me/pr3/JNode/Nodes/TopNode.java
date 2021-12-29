package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.GlobalPool;

public class TopNode extends BodyNode{

    public GlobalPool getVarPool() {
        return varPool;
    }

    private GlobalPool varPool = null;

    public TopNode(GlobalPool varPool, Node... nodes){
        super(nodes);
        this.varPool = varPool;
    }

    @Override
    public Object[] run() {

        for(Node node : nodes){
            if(node instanceof ReturnNode)break;
            node.run();
        }
        return new Object[0];
    }
}
