package me.pr3.JNode.GUI.blocks.VariableBlocks;

import me.pr3.JNode.GUI.Variable;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ExpressionBlocks.CalculationBlock;

import java.awt.*;
import java.util.Optional;

public class SetVarBlock extends Block {

    //Shall be able to take either a Direct Value, Other Variable or a Calculation Block returning a Variable for copying from one to another variable

    public Variable variable;
    public Optional<CalculationBlock> calculationBlock;
    //Can either be a previously used Variable, or a constant
    public Optional<Variable> setTo;

    public SetVarBlock(int layer) {
        super(Color.LIGHT_GRAY, layer);
        setHeight(50);
        setWidth(200);
    }

}
