package me.pr3.JNode.GUI;

import me.pr3.JNode.GUI.Util.GuiUtil;
import me.pr3.JNode.GUI.Util.RenderUtil;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ControlBloks.ControlBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Script {

    public CopyOnWriteArrayList<SubScript> subScripts = new CopyOnWriteArrayList<>();

    public Script(Collection<SubScript> subScripts) {
        this.subScripts = new CopyOnWriteArrayList<>(subScripts);
    }

    private int xOffset, yOffset = 0;


    public void drawScriptBlocks(Graphics2D g) {


        //Iterate over all blocks first to get the max x offset to be expected to adjust the extra width every block will be given


        subScripts.forEach(n -> {
            //Important: Always reset the xOffset or collision will get fucked up
            xOffset = 0;
            yOffset = 0;
            AtomicInteger extraWidth = new AtomicInteger();
            n.getBlocks().forEach(block -> {
                int extraWidthFromThisBlock = getExtraWidthFromThisBLockAndItsChildren(block);
                if (extraWidth.get() <= extraWidthFromThisBlock) {
                    extraWidth.set(extraWidthFromThisBlock);
                }
            });

            if (n.isBound()) {
                n.setX(GUI.frame.getMousePosition().x - n.getGrabOffsetX());
                n.setY(GUI.frame.getMousePosition().y - n.getGrabOffsetY());
            }

            n.getBlocks().forEach(block -> {
                drawBlock2(block, g, n.getX(), n.getY(), extraWidth.get());
            });

            g.setColor(Color.BLACK);
            GuiUtil.getBoundingBoxes(n).forEach(box -> {
                g.drawRect(box.getValue().x, box.getValue().y, box.getValue().width, box.getValue().height);
            });

        });
        g.setColor(Color.RED);
        if(GUI.DEBUG) {
            subScripts.forEach(n -> {
                n.getBoundingBox();
                g.fillRect(n.getX(), n.getY(), 10, 10);
                g.drawRect((int) n.getBoundingBox().getX(), (int) n.getBoundingBox().getY(), (int) n.getBoundingBox().getWidth(), (int) n.getBoundingBox().getHeight());
            });
        }
    }


    private void drawBlock2(Block block, Graphics2D g, int x, int y, int extraWidth) {
        block.setX(x + xOffset);
        block.setY(y + yOffset);
        block.setExtraWidth(extraWidth - xOffset);
        if (block instanceof ControlBlock) {
            g.setColor(block.getColor());
            g.fillRect(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight());
            g.setColor(Color.BLACK);
            g.drawString(block.getText(), block.getX() + 10, (int) ((block.getY() + block.getHeight()/2) + g.getFont().getStringBounds(block.getText(), g.getFontRenderContext()).getHeight() / 2));
            block.setBoundingBox(new Rectangle(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight()));
            //Increase the yOffset For the Header of the Control Block
            yOffset += block.getHeight();

            int oldYOffset = yOffset;
            int oldXOffset = xOffset;

            xOffset += 10;
            ((ControlBlock) block).getChildren().forEach(child -> {
                drawBlock2(child, g, x, y, extraWidth);
            });
            xOffset -= 10;

            //Draw the vertical line from the footer to the header
            g.setColor(block.getColor());
            g.fillRect(x + oldXOffset, y + oldYOffset, 10, yOffset - oldYOffset);

            g.setColor(block.getColor());
            g.fillRect(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, 10);
            //Increase the yOffset for the footer of the Control Block
            yOffset += 10;
        } else {
            g.setColor(block.getColor());
            block.setBoundingBox(new Rectangle(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight()));
            g.fillRect(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight());
            g.setColor(Color.BLACK);
            g.drawString(block.getText(), block.getX() + 10, (int) ((block.getY() + block.getHeight()/2) + g.getFont().getStringBounds(block.getText(), g.getFontRenderContext()).getHeight() / 2));
            yOffset += block.getHeight();
        }
        if(GUI.DEBUG) {
            g.setColor(Color.RED);
            GuiUtil.getSubBoundingBoxes(block).forEach((s, rectangle) -> {
                g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            });
        }
    }


    //Recursive Methods are the shit, not, why is everything recursive in this project i hate it
    public int getExtraWidthFromThisBLockAndItsChildren(Block block) {
        AtomicInteger i = new AtomicInteger();
        if (block instanceof ControlBlock) {
            i.addAndGet(10);
            ((ControlBlock) block).getChildren().forEach(child -> {
                i.addAndGet(getExtraWidthFromThisBLockAndItsChildren(child));
            });
        }
        return i.get();
    }
}
