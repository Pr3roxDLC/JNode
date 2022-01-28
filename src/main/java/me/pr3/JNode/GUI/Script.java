package me.pr3.JNode.GUI;

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

            n.getBlocks().forEach(block -> {
                yOffset+= block.getHeight();
                drawBlock(block, g, n.getX(), n.getY(), extraWidth.get());
            });
        });
    }

    //TODO Find out why Nested Loops are not rendered correctly
    private void drawBlock(Block block, Graphics2D g, int x, int y, int extraWidth) {
        System.out.println("Drawing: " + block.getClass().getSimpleName());
        if(block instanceof ControlBlock){
            //Draw the top part of the Control Block
            g.setColor(block.getColor());
            g.fillRect(x + xOffset, y + yOffset, block.getWidth() + extraWidth - xOffset, block.getHeight());
            xOffset += 10;
            ((ControlBlock) block).getChildren().forEach(child -> {
                //Draw Children + Side Part
                g.setColor(block.getColor());
                g.fillRect(x + xOffset - 10, y + yOffset, 10, child.getHeight());
                yOffset += child.getHeight();
                System.out.println("Drawing Children with xOffset: " + xOffset);
                drawBlock(child, g, x, y, extraWidth);
            });
            xOffset -= 10;
            //Draw the end of it
            g.setColor(block.getColor());
            g.fillRect(x + xOffset, y + yOffset, block.getWidth()  + extraWidth - xOffset, block.getHeight());
        }else{
            //do the actual drawing of all non control blocks
            g.setColor(block.getColor());
            g.fillRect(x + xOffset, y + yOffset, block.getWidth()  + extraWidth - xOffset, block.getHeight());

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