package me.pr3.JNode.gui;


import me.pr3.JNode.gui.blocks.Block;
import me.pr3.JNode.gui.blocks.ControlBloks.WhileLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI implements Runnable {
    //GUI Parent
    public static JFrame frame;

    public static File loadedFile;

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
                if (result == JFileChooser.APPROVE_OPTION) {
                    loadedFile = fileChooser.getSelectedFile();
                }
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

        BlockManager.scripts.put("OnClientTick", new Script(Arrays.asList(new Block[]{new WhileLoop(50, 50, 0, new ArrayList<>())})));
        try {
            while (true) {
                BlockRenderer.render();
                Thread.sleep(16);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
