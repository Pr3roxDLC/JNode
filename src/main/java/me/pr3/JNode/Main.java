package me.pr3.JNode;

import me.pr3.JNode.IO.NodeExporter;
import me.pr3.JNode.IO.NodeImporter;
import me.pr3.JNode.Nodes.*;
import me.pr3.JNode.Var.GlobalPool;
import me.pr3.JNode.Var.Var;

import java.io.File;
import java.util.logging.FileHandler;

public class Main {

    public static void main(String[] args) {


        //simpleCounter();
        fibonaci(12);

    }

    public static void fibonaci(int n) {

        Var counter = new Var(n);
        Var x = new Var(0, "x");
        Var y = new Var(1, "y");
        Var i = new Var(1, "i");
        Var z = new Var(0, "z");
        Var boolVar = new Var(1);
        Var zero = new Var(0, "zero");
        Var dump = new Var(0, "dump");
        Var one = new Var(1, "one");

        GlobalPool globalPool = new GlobalPool(counter, x, y, i, z, zero, dump, one);

        TopNode fibonacci = new TopNode(globalPool,
                new IfNode(
                        new ComparatorNode(dump, counter, zero, ComparatorNode.Operation.EQUAL),
                        new IONode(IONode.Operation.OUT, zero),
                        new ReturnNode()
                ),
                new IfNode(
                        new ComparatorNode(dump, counter, zero, ComparatorNode.Operation.GREATER),
                        new MathNode(counter, counter, one, MathNode.Operation.SUBTRACTION),
                        new WhileLoopNode(boolVar,
                                new IfNode(new ComparatorNode(boolVar, i, counter, ComparatorNode.Operation.SMALLER),
                                        new MathNode(z, x, y, MathNode.Operation.ADDITION),
                                        new MathNode(x, y, zero, MathNode.Operation.ADDITION),
                                        new MathNode(y, z, zero, MathNode.Operation.ADDITION),
                                        new MathNode(i, i, one, MathNode.Operation.ADDITION),
                                        new IONode(IONode.Operation.OUT, y)
                                )
                        )
                )
        );

        NodeExporter.exportNode(fibonacci, "Fibonacci");

        fibonacci.run();


        NodeImporter.importFromFile("Fibonacci");

    }

    public static void simpleCounter() {
        System.out.println("test");
        Var var = new Var(10);
        Var var2 = new Var(0);
        Var boolVar = new Var(1);


        new TopNode(new GlobalPool(),new WhileLoopNode(boolVar,
                new IfNode(new ComparatorNode(boolVar, var, var2, ComparatorNode.Operation.UNQEUAL),
                        new MathNode(var2, var2, new Var(1), MathNode.Operation.ADDITION),
                        new IONode(IONode.Operation.IN, var2))
        )).run();

    }


}
