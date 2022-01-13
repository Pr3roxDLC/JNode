package me.pr3.JNode.gui.blocks.ControlBloks;

import me.pr3.JNode.gui.GUI;
import me.pr3.JNode.gui.blocks.Block;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class ControlBlock extends Block {

    private ArrayList<Block> children = new ArrayList<>();

    public ArrayList<Block> getChildren() {
        return children;
    }

    public void drawChildren(){
        children.forEach(n->n.draw((Graphics2D) GUI.frame.getGraphics()));
    }

    public ControlBlock(int x, int y, Color color, int layer) {
        super(x, y, color, layer);
    }
}
