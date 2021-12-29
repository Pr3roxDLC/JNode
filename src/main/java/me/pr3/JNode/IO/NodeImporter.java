package me.pr3.JNode.IO;

import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import me.pr3.JNode.Nodes.*;
import me.pr3.JNode.Var.GlobalPool;
import me.pr3.JNode.Var.Var;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NodeImporter {

    public static ArrayList<String> lines = new ArrayList<>();

    public static Node importFromFile(String fileName) {
        String string = getStringFromFile(fileName);
        //remove whitespace
        string = string.replace(" ", "");
        //System.out.println(string);
        lines = new ArrayList<>(Arrays.asList(string.split("\n")));
        if (!lines.get(0).startsWith("TopNode")) {
            throw new SyntaxException("Unexpected Token at: 0 : " + lines.get(0) + ", expected TopNode");
        }

        //Will probably be recursive
        int level = 0;

        Stack<NodeConstructor> nodeStack = new Stack<>();

        GlobalPool pool = new GlobalPool();

        //System.out.println(Arrays.toString(lines.get(0).split("\\{")[1].substring(1, lines.get(0).split("\\{")[1].length() - 2).replace("],[", "*").split("\\*")));
        String[] poolVars = lines.get(0).split("\\{")[1].substring(1, lines.get(0).split("\\{")[1].length()-2).replace("],[", "*").split("\\*");
        for(String var : poolVars){
            //var = var.substring(1, var.length() -1 );
            String key = var.split(",")[0];
            int value = Integer.parseInt(var.split(",")[1]);
            pool.getVars().put(key, new Var(value, key));
        }

        for (int i = 0; i < lines.size(); i++) {

            String currentLine = lines.get(i);
            //if isNodeWithBody => Put all following nodes into its body, until we encounter a ReturnNode, if we encounter another bodyNode on the way,
            //add all the nodes to the last encountered bodyNode and so on
            //System.out.println(level);
            //levelLists.get(level).addAll(currentLevelList);
            if (isNodeWithBody(currentLine)) {
                level++;
                nodeStack.push(new NodeConstructor(getClassFromLine(currentLine), new ArrayList<Node>(), new ArrayList<Var>(), ""));
                for(String var : getVarsFromLine(currentLine)){
                    nodeStack.peek().vars.add(pool.getVars().get(var));
                }
                getOperationFromLine(currentLine).ifPresent(n -> nodeStack.peek().operation = n);
            }else{
                //Node has no Body and therefor can be constructed and added to the Body right away
                //Will still use the NodeConstructor for the easier class management
                NodeConstructor bodylessNodeConstructor = new NodeConstructor(getClassFromLine(currentLine), new ArrayList<Node>(), new ArrayList<Var>(), "");
                for(String var : getVarsFromLine(currentLine)){
                    bodylessNodeConstructor.vars.add(pool.getVars().get(var));
                }
                getOperationFromLine(currentLine).ifPresent(n -> bodylessNodeConstructor.operation = n);
                bodylessNodeConstructor.assemble();
                nodeStack.peek().children.add(bodylessNodeConstructor.node);
            }

            if (lines.get(i).startsWith("ReturnNode")) {

                nodeStack.peek().assemble();
                NodeConstructor parent = nodeStack.pop();

                nodeStack.peek().children.add(parent.node);
                //Whenever we Reach a ReturnNode we know that the BodyNode previously worked on must be finished, therefor we can pop it from the stack, asseble the Node and then add it to its parent Node
                level--;

            }
        }


        //A Collection of all nodes will be added to the constructor of this TopNode At the very end
        //TopNode topNode = new TopNode();

        return null;
    }

    public static Optional<String> getOperationFromLine(String line){
        if(!line.contains("{"))return Optional.empty();
         Optional<String> opString = Arrays.stream(line.split("\\{")[1].split(",")).filter(n -> n.startsWith("OP[")).findAny();
         if(opString.isPresent()){
             return Optional.of(opString.get().substring(3, opString.get().length() - 2));
         }
         return Optional.empty();
    }

    public static boolean isNodeWithBody(String line) {
        return line.startsWith("IfNode") || line.startsWith("WhileLoopNode") || line.startsWith("TopNode");
    }

    public static String[] getVarsFromLine(String line){
        if(!line.contains("{"))return new String[]{};
        return Arrays.stream(line.split("\\{")[1].split(",")).filter(n -> !n.startsWith("OP[")).toArray(String[]::new);
    }

    public static ArrayList<String> getLinesTillNextReturnNode(int i) {
        return null;
    }

    public static Class getClassFromLine(String line) {
        if (line.startsWith(ComparatorNode.class.getSimpleName())) return ComparatorNode.class;
        if (line.startsWith(ExpressionNode.class.getSimpleName())) return ExpressionNode.class;
        if (line.startsWith(IfNode.class.getSimpleName())) return IfNode.class;
        if (line.startsWith(IONode.class.getSimpleName())) return IONode.class;
        if (line.startsWith(LogicNode.class.getSimpleName())) return LogicNode.class;
        if (line.startsWith(MathNode.class.getSimpleName())) return MathNode.class;
        if (line.startsWith(ReturnNode.class.getSimpleName())) return ReturnNode.class;
        if (line.startsWith(TopNode.class.getSimpleName())) return TopNode.class;
        if (line.startsWith(WhileLoopNode.class.getSimpleName())) return WhileLoopNode.class;
        return null;
    }


    public static String getStringFromFile(String fileName) {
        String str = "";
        try {
            File myObj = new File(fileName + ".jn");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                str += myReader.nextLine() + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return str;
    }

}
