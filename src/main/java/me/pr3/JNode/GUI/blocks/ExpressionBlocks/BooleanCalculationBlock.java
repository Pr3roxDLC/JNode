package me.pr3.JNode.GUI.blocks.ExpressionBlocks;

import me.pr3.JNode.GUI.blocks.Block;

import java.awt.*;

public class BooleanCalculationBlock extends Block {

    //This returns a boolean aka a Variable with value 1 or 2 (Might change in the future)

    public BooleanCalculationBlock(int layer) {
        super(Color.BLUE, layer);
    }

    public enum Operation{
        EQUAL,
        UNEQUAL,
        GREATER,
        SMALLER,
        GREATER_OR_EQUAL,
        SMALLER_OR_EQUAL
    }

}
