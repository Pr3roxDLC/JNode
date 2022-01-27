package me.pr3.JNode.GUI;

import me.pr3.JNode.GUI.blocks.Block;

import java.util.ArrayList;

public class SubScript {

    private final ArrayList<Block> blocks = new ArrayList<>();
    private int x,y,layer = 0;

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}
