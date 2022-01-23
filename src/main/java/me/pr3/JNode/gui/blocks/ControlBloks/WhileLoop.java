package me.pr3.JNode.gui.blocks.ControlBloks;

import me.pr3.JNode.gui.blocks.Block;

import java.awt.*;
import java.util.Collection;

public class WhileLoop extends ControlBlock {

    public WhileLoop(int x, int y, int layer, Collection<Block> children){
        super(x, y, Color.CYAN, layer);
        setWidth(200);
        setHeight(100);
        getChildren().addAll(children);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}
