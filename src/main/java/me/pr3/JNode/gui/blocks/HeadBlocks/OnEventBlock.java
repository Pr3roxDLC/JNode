package me.pr3.JNode.gui.blocks.HeadBlocks;

import java.awt.*;

public class OnEventBlock extends HeadBlock{
    public OnEventBlock(int x, int y, int layer) {
        super(x, y, Color.CYAN, layer);
        setWidth(200);
        setHeight(100);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}