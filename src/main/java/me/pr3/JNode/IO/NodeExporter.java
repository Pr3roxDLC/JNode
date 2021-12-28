package me.pr3.JNode.IO;

import me.pr3.JNode.Nodes.BodyNode;
import me.pr3.JNode.Nodes.IfNode;
import me.pr3.JNode.Nodes.Node;
import me.pr3.JNode.Nodes.TopNode;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class NodeExporter {

    public static void exportNode(Node parentNode, Path exportPath, String fileName){
        //Always start at level 0
        exportNode(parentNode, exportPath,fileName, 0);
    }

    private static void exportNode(Node parentNode, Path exportPath, String fileName, int level) {
        //Print out the current Node
        String indent = "";
        for(int i = 0; i < level; i++){
            indent += "    ";
        }
        System.out.println( indent + exportSingleNode(parentNode));
        //Handle all Child Nodes of the current Node
        if(parentNode instanceof BodyNode){
            BodyNode bodyNode = (BodyNode) parentNode;
            level++;
            for(Node node : bodyNode.getNodes()){
                exportNode(node, exportPath, fileName, level);
            }
            level--;
        }

    }

    public static String exportSingleNode(Node node){
            return node.getClass().getSimpleName();
    }



    }
