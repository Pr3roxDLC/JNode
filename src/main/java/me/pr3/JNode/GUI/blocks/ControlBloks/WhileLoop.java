package me.pr3.JNode.GUI.blocks.ControlBloks;

import me.pr3.JNode.GUI.blocks.Block;

import java.awt.*;
import java.util.Collection;

public class WhileLoop extends ControlBlock {

    public WhileLoop(int layer, Collection<Block> children){
        super(Color.DARK_GRAY, layer);
        setWidth(200);
        setHeight(100);
        getChildren().addAll(children);
    }


}
