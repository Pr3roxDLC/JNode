package me.pr3.JNode.gui;

import java.awt.*;

public class BlockRenderer {

    public static void render(){
            BlockManager.scripts.values().forEach(n -> {
                n.blocks.forEach(block -> {
                    block.draw((Graphics2D) GUI.frame.getGraphics());
                });
            });
    }

}
