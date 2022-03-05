package me.pr3.JNode.GUI.blocks.ControlBlocks;

import me.pr3.JNode.GUI.blocks.Block;

import java.awt.*;
import java.util.Collection;

public class IfBlock extends  ControlBlock{

    public IfBlock(int layer, Collection<Block> children) {
        super(Color.LIGHT_GRAY.darker(), layer);
        setText("If");
        setHeight(50);
        getChildren().addAll(children);
    }

}
