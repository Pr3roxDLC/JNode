package me.pr3.JNode.GUI.blocks.ControlBlocks;

import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ExpressionBlocks.BooleanCalculationBlock;

import java.awt.*;
import java.util.Collection;

public class WhileLoop extends ControlBlock {

    public BooleanCalculationBlock condition;

    public WhileLoop(int layer, Collection<Block> children){
        super(Color.DARK_GRAY, layer);
        setText("While");
        setWidth(200);
        setHeight(50);
        getChildren().addAll(children);
    }
}
