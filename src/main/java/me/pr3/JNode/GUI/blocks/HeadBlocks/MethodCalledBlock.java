package me.pr3.JNode.GUI.blocks.HeadBlocks;

import me.pr3.JNode.GUI.blocks.Block;

import java.awt.*;

public class MethodCalledBlock extends Block {

    public MethodCalledBlock(int layer) {
        super( Color.CYAN.darker(), layer);
        setText("OnScriptCall");
    }

}
