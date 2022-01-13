package me.pr3.JNode.gui;

import me.pr3.JNode.gui.blocks.Block;

import java.util.ArrayList;
import java.util.Collection;

public class Script {

    public ArrayList<Block> blocks = new ArrayList<>();

    public Script(Collection<Block> blocks){
        this.blocks = new ArrayList<>(blocks);
    }

}
