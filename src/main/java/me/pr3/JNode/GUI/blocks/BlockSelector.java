package me.pr3.JNode.GUI.blocks;

import me.pr3.JNode.GUI.GUI;
import me.pr3.JNode.GUI.SubScript;
import me.pr3.JNode.GUI.Util.EventType;
import me.pr3.JNode.GUI.Util.GuiUtil;
import me.pr3.JNode.GUI.blocks.ControlBloks.ControlBlock;
import me.pr3.JNode.GUI.blocks.ControlBloks.IfBlock;
import me.pr3.JNode.GUI.blocks.ControlBloks.WhileLoop;
import me.pr3.JNode.GUI.blocks.ExpressionBlocks.BooleanCalculationBlock;
import me.pr3.JNode.GUI.blocks.ExpressionBlocks.CalculationBlock;
import me.pr3.JNode.GUI.blocks.HeadBlocks.MethodCalledBlock;
import me.pr3.JNode.GUI.blocks.HeadBlocks.OnEventBlock;
import me.pr3.JNode.GUI.blocks.VariableBlocks.SetVarBlock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class BlockSelector {

    public static Rectangle selectorBoundingBox = new Rectangle(0, 0, 400, GUI.height);

    public static ArrayList<Block> blockSelection = new ArrayList<>(Arrays.asList(
        new IfBlock(0, new ArrayList<>()),
        new WhileLoop(0, new ArrayList<>()),
        new BooleanCalculationBlock(0),
        new CalculationBlock(0),
        new MethodCalledBlock(0),
        new OnEventBlock(0),
        new SetVarBlock(0)
    ));

    public static void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 400, GUI.height);
        int x = 50, y = 0;
        for (Block block : blockSelection) {
            block.setX(x);
            block.setY(y);
            block.setExtraWidth(0);
            if (block instanceof ControlBlock) {
                g.setColor(block.getColor());
                g.fillRect(x, y, block.getWidth(), block.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(block.getText(), block.getX() + 10, (int) ((block.getY() + block.getHeight() / 2) + g.getFont().getStringBounds(block.getText(), g.getFontRenderContext()).getHeight() / 2));
                block.setBoundingBox(new Rectangle(x, y, block.getWidth(), block.getHeight()));

                g.setColor(block.getColor());
                g.fillRect(x, y, block.getWidth(), 10);
                //Increase the yOffset for the footer of the Control Block
            } else {
                g.setColor(block.getColor());
                block.setBoundingBox(new Rectangle(x, y, block.getWidth(), block.getHeight()));
                g.fillRect(x, y, block.getWidth(), block.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(block.getText(), block.getX() + 10, (int) ((block.getY() + block.getHeight() / 2) + g.getFont().getStringBounds(block.getText(), g.getFontRenderContext()).getHeight() / 2));
            }
            if (GUI.DEBUG) {
                g.setColor(Color.RED);
                GuiUtil.getSubBoundingBoxes(block).forEach((s, rectangle) -> {
                    g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                });
            }
            y += 200;
        }
    }

    //TODO RELEASE TO DELETE, ADD grabOffset LOGIC
    public static void handleMouseEvent(MouseEvent mouseEvent, EventType eventType) {
        if (eventType == EventType.PRESSED) {
            if (selectorBoundingBox.contains(mouseEvent.getPoint())) {
                blockSelection.stream().filter(n -> n.getBoundingBox().contains(mouseEvent.getPoint())).findFirst().ifPresent(block -> {
                    SubScript newScript = new SubScript();
                    newScript.getBlocks().add(GuiUtil.getNewBlockByType(block.getClass()));
                    newScript.setBound(true);
                    GUI.script.subScripts.add(newScript);
                });
            }
        } else if (eventType == EventType.RELEASED) {

        }
    }

}
