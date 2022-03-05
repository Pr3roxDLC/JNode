package me.pr3.JNode.GUI;

import me.pr3.JNode.GUI.Util.EventType;
import me.pr3.JNode.GUI.blocks.BlockSelector;
import me.pr3.JNode.GUI.blocks.ControlBlocks.IfBlock;
import me.pr3.JNode.GUI.blocks.ControlBlocks.WhileLoop;
import me.pr3.JNode.GUI.blocks.HeadBlocks.OnEventBlock;
import me.pr3.JNode.GUI.blocks.VariableBlocks.SetVarBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUI extends JPanel implements Runnable {

    //Internal Variables
    boolean shouldClose = false;
    public static boolean DEBUG = true;
    //GUI Parent
    public static JFrame frame;
    Image dbImage = null;
    Graphics dbg = null;
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    //FileIO
    public static File loadedFile;

    //Script
    public static Script script;

    public static void initGUI() {
        frame = new JFrame("JNode Editor");
        frame.setSize(width, height);
        frame.setLayout(null);
        //Initialize All Components

        //Initialize the Menu Bar
        frame.setMenuBar(getMenu());

        //Looks ugly, maybe find a better way of doing this, custom MouseListener Factory class?
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, EventType.CLICKED));
                BlockSelector.handleMouseEvent(mouseEvent, EventType.CLICKED);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, EventType.PRESSED));
                BlockSelector.handleMouseEvent(mouseEvent, EventType.PRESSED);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                script.subScripts.forEach(n -> n.handleMouseEvent(mouseEvent, EventType.RELEASED));
                BlockSelector.handleMouseEvent(mouseEvent, EventType.RELEASED);
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

        frame.addMouseWheelListener(BlockSelector::handleMouseScrollEvent);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(new GUI()).start();
    }

    public static MenuBar getMenu() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem loadItem = new MenuItem("Load");

        loadItem.addActionListener(actionEvent -> {
            //Open File Load Dialog
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION)
                loadedFile = fileChooser.getSelectedFile();
        });

        fileMenu.add(loadItem);
        MenuItem saveItem = new MenuItem("Save");
        saveItem.addActionListener(actionEvent -> {
            //TODO doesn't save it yet
            System.out.println("Saving File");
        });

        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    @Override
    public void run() {
        onLoad();
        while (!shouldClose)
            onUpdate();
    }


    @Override
    public void paint(final Graphics g) {
        //System.out.println("Painted");
        super.paint(g);
        if (dbImage == null) {
            //Double-buffer: clear the offscreen image.
            dbImage = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
            dbg = dbImage.getGraphics();
        }
        dbg.setColor(Color.LIGHT_GRAY);
        dbg.fillRect(0, 0 , 1920, 1080);

        BlockSelector.draw((Graphics2D) dbg);
        script.drawScriptBlocks((Graphics2D) dbg);

        g.drawImage(dbImage, 0, 0, null);
    }

    private void onUpdate() {
        try {
            script.subScripts.forEach(n -> {if(n.isToDispose()){script.subScripts.remove(n);}});
            paint(frame.getGraphics());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onLoad() {
        script = new Script(new ArrayList<>());
        SubScript subScript = new SubScript();
        subScript.setX(500);
        subScript.setY(50);
        subScript.getBlocks().add(new OnEventBlock(0));
        subScript.getBlocks().add(new WhileLoop(0, new ArrayList<>(Arrays.asList(new SetVarBlock(0),
                new IfBlock(0, new ArrayList<>(Collections.singletonList(new SetVarBlock(0))))))));
        subScript.getBlocks().add(new SetVarBlock(0));
        script.subScripts.add(subScript);
    }

}
