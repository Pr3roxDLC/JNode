package me.pr3.JNode;

import me.pr3.JNode.Nodes.*;
import me.pr3.JNode.Var.Var;

public class Main {

    public static void main(String[] args) {

        simpleCounter();

    }

    public static void simpleCounter(){
        Var var = new Var(10);
        Var var2 = new Var(0);
        Var boolVar = new Var(1);

        WhileLoopNode node = new WhileLoopNode(boolVar,
                new IfNode(new ComparatorNode(boolVar, var, var2, ComparatorNode.Operation.UNQEUAL),
                        new MathNode(var2, var2, new Var(1), MathNode.Operation.ADDITION),
                        new IONode(IONode.Operation.IN, var2))
        );
        node.run();
    }


}
