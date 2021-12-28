package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class IfNode extends Node{

    ComparatorNode comparatorNode = null;
    Node[] nodes = null;

    public IfNode(ComparatorNode comparatorNode, Node... bodyNodes){
        this.comparatorNode = comparatorNode;
        this.nodes = bodyNodes;
    }

    @Override
    public Object[] run() {

        if(((Var)comparatorNode.run()[0]).getValue() == 1){
            for(Node node : nodes){
                node.run();
            }
        }

        return new Object[0];
    }
}
