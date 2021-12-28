package me.pr3.JNode.Nodes;

import me.pr3.JNode.Var.Var;

public class IfNode extends BodyNode{

    public ComparatorNode getComparatorNode() {
        return comparatorNode;
    }

    ComparatorNode comparatorNode = null;

    public IfNode(ComparatorNode comparatorNode, Node... bodyNodes){
        super(bodyNodes);
        this.comparatorNode = comparatorNode;
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
