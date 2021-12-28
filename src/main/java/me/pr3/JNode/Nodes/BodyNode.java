package me.pr3.JNode.Nodes;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BodyNode extends Node{
    public Node[] nodes;

    public Node[] getNodes(){
        return  nodes;
    }

    public BodyNode(Node[] nodes){
        if(!(nodes[nodes.length-1] instanceof ReturnNode)){
            ArrayList<Node> nodeList = new ArrayList<>(Arrays.asList(nodes));
            nodeList.add(new ReturnNode());
           this.nodes = nodeList.toArray(new Node[0]);
        }else this.nodes = nodes;
    }
}
