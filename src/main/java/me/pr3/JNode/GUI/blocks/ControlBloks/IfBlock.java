package me.pr3.JNode.GUI.blocks.ControlBloks;

import me.pr3.JNode.GUI.blocks.Block;

import java.awt.*;
import java.util.Collection;

public class IfBlock extends  ControlBlock{
    public IfBlock(int layer, Collection<Block> children) {
        super(Color.LIGHT_GRAY.darker(), layer);
        setHeight(50);
        getChildren().addAll(children);
    }
}