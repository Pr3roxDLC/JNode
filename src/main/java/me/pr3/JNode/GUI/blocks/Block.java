package me.pr3.JNode.GUI.blocks;

import java.awt.*;

public abstract class Block {

    private int width, height = 50;
    private int layer;
    private Color color;
    private int x,y = 0;
    private Rectangle boundingBox = null;
    private int extraWidth = 0;

    public Block(Color color, int layer){
        this.color = color;
        this.layer = layer;
        this.width = 200;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text = "Error";

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLayer() {
        return this.layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getExtraWidth() {
        return extraWidth;
    }

    public void setExtraWidth(int extraWidth) {
        this.extraWidth = extraWidth;
    }

}
