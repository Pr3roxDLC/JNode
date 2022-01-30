package me.pr3.JNode.GUI;

import me.pr3.JNode.GUI.Util.GuiUtil;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ControlBloks.ControlBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Script {

    public ArrayList<SubScript> subScripts = new ArrayList<>();

    public Script(Collection<SubScript> subScripts){
        this.subScripts = new ArrayList<>(subScripts);
    }

    private int xOffset, yOffset = 0;


    public void drawScriptBlocks(Graphics2D g) {


        //Iterate over all blocks first to get the max x offset to be expected to adjust the extra width every block will be given

        xOffset = 0;
        yOffset = 0;
        subScripts.forEach(n -> {
            AtomicInteger extraWidth = new AtomicInteger();
            n.getBlocks().forEach(block -> {
                int extraWidthFromThisBlock = getExtraWidthFromThisBLockAndItsChildren(block);
                if(extraWidth.get() <= extraWidthFromThisBlock){
                    extraWidth.set(extraWidthFromThisBlock);
                }
            });

            if(n.isBound()){
               n.setX(GUI.frame.getMousePosition().x);
               n.setY(GUI.frame.getMousePosition().y);
            }

            n.getBlocks().forEach(block -> {
                drawBlock2(block, g, n.getX(), n.getY(), extraWidth.get());
                g.setColor(Color.BLACK);
            });

            GuiUtil.getBoundingBoxes(n).forEach(box -> {
                g.drawRect(box.getValue().x, box.getValue().y, box.getValue().width, box.getValue().height);
            });

        });
    }


    private void drawBlock2(Block block, Graphics2D g, int x, int y, int extraWidth){
        if(block instanceof ControlBlock){
            g.setColor(block.getColor());
            g.fillRect(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight());
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
        }else{
            g.setColor(block.getColor());
            block.setBoundingBox(new Rectangle(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight()));
            g.fillRect(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight());
            yOffset += block.getHeight();
        }
    }


    //Recursive Methods are the shit, not, why is everything recursive in this project i hate it
    public int getExtraWidthFromThisBLockAndItsChildren(Block block){
        AtomicInteger i = new AtomicInteger();
        if(block instanceof ControlBlock){
            i.addAndGet(10);
            ((ControlBlock) block).getChildren().forEach(child -> {
                i.addAndGet(getExtraWidthFromThisBLockAndItsChildren(child));
            });
        }
        return i.get();
    }
}
