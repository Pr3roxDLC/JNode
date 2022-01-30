package me.pr3.JNode.GUI.Util;

import jdk.internal.util.xml.impl.Pair;
import me.pr3.JNode.GUI.SubScript;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ControlBloks.ControlBlock;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GuiUtil {

    public static int getSubScriptHeight(SubScript script) {
        AtomicInteger height = new AtomicInteger();
        script.getBlocks().forEach(block -> {
            height.addAndGet(getBlockHeight(block));
        });
        return height.get();
    }

    //Yesss, more recursive brain fuckery
    public static int getBlockHeight(Block block) {
        AtomicInteger height = new AtomicInteger();
        if (block instanceof ControlBlock) {
            height.addAndGet(block.getHeight());
            ((ControlBlock) block).getChildren().forEach(child -> {
                height.addAndGet(getBlockHeight(child));
            });
            height.addAndGet(10);
        } else {
            height.addAndGet(block.getHeight());
        }
        return height.get();
    }

    public static int getSubScriptWidth(SubScript script) {
        AtomicInteger width = new AtomicInteger();
        script.getBlocks().forEach(block -> {
            int blockWidth = getExtraWidthFromThisBLockAndItsChildren(block) + 200;
            width.set(Math.max(width.get(), blockWidth));
        });
        return width.get();
    }

    public static int getExtraWidthFromThisBLockAndItsChildren(Block block) {
        AtomicInteger i = new AtomicInteger();
        if (block instanceof ControlBlock) {
            i.addAndGet(10);
            ((ControlBlock) block).getChildren().forEach(child -> {
                i.addAndGet(getExtraWidthFromThisBLockAndItsChildren(child));
            });
        }
        return i.get();
    }


    public static Collection<Map.Entry<Block,Rectangle>> getBoundingBoxes(SubScript script) {
        ArrayList<Map.Entry<Block,Rectangle>> boundingBoxes = new ArrayList<>();
        script.getBlocks().forEach(n -> boundingBoxes.addAll(getBoundingBoxesRecursively(n)));
        return boundingBoxes;
    }

    public static Collection<Map.Entry<Block, Rectangle>> getBoundingBoxesRecursively(Block block) {
        ArrayList<Map.Entry<Block, Rectangle>> boundingBoxes = new ArrayList<>();
        if (block instanceof ControlBlock) {
            boundingBoxes.add(new AbstractMap.SimpleEntry<>(block, block.getBoundingBox()));
            ((ControlBlock) block).getChildren().forEach(n -> boundingBoxes.addAll(getBoundingBoxesRecursively(n)));
        } else {
            boundingBoxes.add(new AbstractMap.SimpleEntry<>(block, block.getBoundingBox()));
        }
        return boundingBoxes;
    }


}
