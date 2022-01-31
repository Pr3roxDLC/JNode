package me.pr3.JNode.GUI;


import me.pr3.JNode.GUI.blocks.ControlBloks.IfBlock;
import me.pr3.JNode.GUI.blocks.ControlBloks.WhileLoop;
import me.pr3.JNode.GUI.blocks.HeadBlocks.OnEventBlock;
import me.pr3.JNode.GUI.blocks.VariableBlocks.SetVarBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI extends JPanel implements Runnable {
    //Internal Varialbles
    boolean shouldClose = false;
    //GUI Parent
    public static JFrame frame;
    Image dbImage = null;
    Graphics dbg = null;
    int width = 1920;
    int height = 1080;


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

        //Looks ugly, maybe find a better way of doing this, custom MouseListener Factory class?
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, SubScript.EventType.CLICKED));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, SubScript.EventType.PRESSED));
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, SubScript.EventType.RELEASED));
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, null));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, null));
            }
        });

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        Thread f = new Thread(new GUI());
        f.start();
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
        while (!shouldClose) {
            onUpdate();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void paint(final Graphics g) {
        //System.out.println("Painted");
        super.paint(g);
        final Dimension d = getSize();
        if (dbImage == null) {
            // Double-buffer: clear the offscreen image.
            dbImage = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
            dbg = dbImage.getGraphics();

        }
        dbg.setColor(Color.WHITE);
        dbg.fillRect(0, 0 , 1920, 1080);

        script.drawScriptBlocks((Graphics2D) dbg);

        g.drawImage(dbImage, 0, 0, null);
    }

    private void onUpdate() {
        try {
            paint(frame.getGraphics());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onLoad() {
        script = new Script(new ArrayList<>());
        SubScript subScript = new SubScript();
        subScript.getBlocks().add(new OnEventBlock(0));
        subScript.getBlocks().add(new WhileLoop(0, new ArrayList<>(Arrays.asList(new SetVarBlock(0),
                new IfBlock(0, new ArrayList<>(Collections.singletonList(new SetVarBlock(0))))))));
        subScript.getBlocks().add(new SetVarBlock(0));
        script.subScripts.add(subScript);
    }

}
