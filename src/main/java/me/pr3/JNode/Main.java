package me.pr3.JNode;

import me.pr3.JNode.IO.NodeExporter;
import me.pr3.JNode.Nodes.*;
import me.pr3.JNode.Var.Var;

import java.io.File;
import java.util.logging.FileHandler;

public class Main {

    public static void main(String[] args) {

        new File("Test.jn");

        simpleCounter();
        fibonaci(12);

    }

    public static void fibonaci(int n) {

        Var counter = new Var(n);
        Var x = new Var(0);
        Var y = new Var(1);
        Var i = new Var(1);
        Var z = new Var(0);
        Var boolVar = new Var(1);

        TopNode fibonacci = new TopNode(
                new IfNode(
                        new ComparatorNode(new Var(0), counter, new Var(0), ComparatorNode.Operation.EQUAL),
                        new IONode(IONode.Operation.IN, new Var(0)),
                        new ReturnNode()
                ),
                new IfNode(
                        new ComparatorNode(new Var(0), counter, new Var(0), ComparatorNode.Operation.GREATER),
                        new MathNode(counter, counter, new Var(1), MathNode.Operation.SUBTRACTION),
                        new WhileLoopNode(boolVar,
                                new IfNode(new ComparatorNode(boolVar, i, counter, ComparatorNode.Operation.SMALLER),
                                        new MathNode(z, x, y, MathNode.Operation.ADDITION),
                                        new MathNode(x, y, new Var(0), MathNode.Operation.ADDITION),
                                        new MathNode(y, z, new Var(0), MathNode.Operation.ADDITION),
                                        new MathNode(i, i, new Var(1), MathNode.Operation.ADDITION),
                                        new IONode(IONode.Operation.OUT, y)
                                )
                        )
                )
        );

        fibonacci.run();

        NodeExporter.exportNode(fibonacci, "Fibonacci");

    }

    public static void simpleCounter() {
        System.out.println("test");
        Var var = new Var(10);
        Var var2 = new Var(0);
        Var boolVar = new Var(1);

        new TopNode(new WhileLoopNode(boolVar,
                new IfNode(new ComparatorNode(boolVar, var, var2, ComparatorNode.Operation.UNQEUAL),
                        new MathNode(var2, var2, new Var(1), MathNode.Operation.ADDITION),
                        new IONode(IONode.Operation.IN, var2))
        )).run();

    }


}
