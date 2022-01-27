package me.pr3.JNode.GUI;


import me.pr3.JNode.GUI.blocks.ControlBloks.WhileLoop;
import me.pr3.JNode.GUI.blocks.HeadBlocks.OnEventBlock;
import me.pr3.JNode.GUI.blocks.VariableBlocks.SetVarBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI implements Runnable {
    //Internal Varialbles
    boolean shouldclose = false;
    //GUI Parent
    public static JFrame frame;

    //FileIO
    public static File loadedFile;

    //Script
    public static Script script;

    public static void initGUI() {
        frame = new JFrame("JNode Editor");
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        //Inititalize All Components

        //Initialize the Menu Bar
        frame.setMenuBar(getMenu());

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        Thread f = new Thread(new GUI());
        f.run();
    }

    public static MenuBar getMenu() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem loadItem = new MenuItem("Load");

        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Open File Load Dialog
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION)
                    loadedFile = fileChooser.getSelectedFile();
            }
        });

        fileMenu.add(loadItem);
        MenuItem saveItem = new MenuItem("Save");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Save File
                System.out.println("Saving File");
            }
        });

        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    @Override
    public void run() {
        onLoad();
        while(!shouldclose){
            onUpdate();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void onUpdate() {
        script.drawScriptBlocks((Graphics2D) frame.getGraphics());
    }

    private void onLoad() {
        script = new Script(new ArrayList<>());
        SubScript subScript = new SubScript();
        subScript.getBlocks().add(new OnEventBlock(0));
        subScript.getBlocks().add(new WhileLoop(0, new ArrayList<>()));
        subScript.getBlocks().add(new SetVarBlock(0));
        script.subScripts.add(subScript);
    }

}