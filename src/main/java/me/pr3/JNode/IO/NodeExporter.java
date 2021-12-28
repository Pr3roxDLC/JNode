package me.pr3.JNode.IO;

import me.pr3.JNode.Nodes.*;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class NodeExporter {

    public static String out = "";

    public static void exportNode(Node parentNode, String fileName) {
        //Always start at level 0
        exportNode(parentNode, fileName, 0);
        System.out.println(out);
        try {
            createFile(out, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void exportNode(Node parentNode, String fileName, int level) {
        //Print out the current Node
        String indent = "";
        for (int i = 0; i < level; i++) {
            indent += "    ";
        }
        out += indent + exportSingleNode(parentNode) + "\n";
        //Handle all Child Nodes of the current Node
        if (parentNode instanceof BodyNode) {
            BodyNode bodyNode = (BodyNode) parentNode;
            level++;
            for (Node node : bodyNode.getNodes()) {
                exportNode(node, fileName, level);
            }
            level--;
        }

    }

    public static String exportSingleNode(Node node) {
        String outputString = node.getClass().getSimpleName() + " ";
        if (node instanceof IfNode) {
            IfNode ifNode = (IfNode) node;
            ComparatorNode comparatorNode = ifNode.getComparatorNode();
            outputString += "{ " + comparatorNode.getOut().getName() + " , " + comparatorNode.getIn1().getName() + " , " + comparatorNode.getIn2().getName() + " , " + comparatorNode.getOperation().toString() + " }";
            return outputString;
        }
        if (node instanceof MathNode) {
            MathNode mathNode = (MathNode) node;
            outputString += "{ " + mathNode.getOut().getName() + " , " + mathNode.getIn1().getName() + " , " + mathNode.getIn2().getName() + " , " + mathNode.getOperation().toString() + " }";
            return outputString;
        }
        if (node instanceof WhileLoopNode) {
            WhileLoopNode whileLoopNode = (WhileLoopNode) node;
            outputString += "{ " + whileLoopNode.getVar().getName() + " }";
            return outputString;
        }
        if (node instanceof IONode) {
            IONode ioNode = (IONode) node;
            outputString += "{ " + ioNode.getVar().getName() + " }";
            return outputString;
        }
        return node.getClass().getSimpleName();

    }

    public static void createFile(String text, String name) throws FileNotFoundException {
        try {
            FileWriter myWriter = new FileWriter(name + ".jn");
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully exported " + name + ".jn");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
