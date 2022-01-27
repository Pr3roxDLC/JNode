package me.pr3.JNode.gui.blocks.ControlBloks;

import me.pr3.JNode.gui.GUI;
import me.pr3.JNode.gui.blocks.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ControlBlock extends Block {

    private List<Block> children = new ArrayList<>();

    public List<Block> getChildren() {
        return children;
    }

    public ControlBlock(Color color, int layer) {
        super( color, layer);
    }

}