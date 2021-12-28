package me.pr3.JNode.IO;

import me.pr3.JNode.Nodes.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NodeImporter {
    public static Node importFromFile(String fileName){
        String string = getStringFromFile(fileName);
        //remove whitespace
        string = string.replace(" ", "");
        System.out.println(string);
        return null;
    }

    public static String getStringFromFile(String fileName){
        String str = "";
        try {
            File myObj = new File( fileName +".jn");
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
