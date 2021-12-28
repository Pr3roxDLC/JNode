package me.pr3.JNode.Nodes;

public class TopNode extends BodyNode{



    public TopNode(Node... nodes){
        super(nodes);
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
