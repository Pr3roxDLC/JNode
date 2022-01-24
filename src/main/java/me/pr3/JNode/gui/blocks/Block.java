package me.pr3.JNode.gui.blocks;

import java.awt.*;

public abstract class Block {

    private int x,y = 0;
    private int width, height = 50;
    private int layer = 0;
    private Color color = Color.CYAN;

    protected int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    protected int getLayer() {
        return this.layer;
    }

    protected void setLayer(int layer) {
        this.layer = layer;
    }

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    public Block(int x, int y, Color color, int layer){
        this.color = color;
        this.layer = layer;
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics2D g);

}