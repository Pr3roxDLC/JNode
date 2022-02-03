package me.pr3.JNode.GUI.blocks.ControlBloks;

import me.pr3.JNode.GUI.blocks.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



//Width and height only affect the Header of the Control Block, side and bottom part are unaffected
public abstract class ControlBlock extends Block {

    private List<Block> children = new ArrayList<>();

    public List<Block> getChildren() {
        return children;
    }

    public ControlBlock(Color color, int layer) {
        super( color, layer);
    }

}
