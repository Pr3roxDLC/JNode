package me.pr3.JNode.GUI.Util;

import me.pr3.JNode.GUI.SubScript;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ControlBloks.ControlBlock;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GuiUtil {


    public static void mergeScripts(SubScript thisScript, SubScript intoThatScript, Block belowBlock) {
        //1. Get the Block that belowBlock is a parent to in intoThatScript
        //TODO add top level checks
        if(intoThatScript.getBlocks().contains(belowBlock)){
            int belowBlockIndex = intoThatScript.getBlocks().indexOf(belowBlock);
            for(Block block : thisScript.getBlocks()){
                intoThatScript.getBlocks().add(belowBlockIndex, block);
                belowBlockIndex++;
            }
            thisScript.setToDispose(true);
            return;
        }

        ControlBlock parent = getParentOfBlockInScript(belowBlock, intoThatScript);
        if(parent != null) {
            int belowBlockIndex = parent.getChildren().indexOf(belowBlock);
            for(Block block : thisScript.getBlocks()){
                parent.getChildren().add(belowBlockIndex, block);
                //belowBlockIndex++;
            }
        }
        thisScript.setToDispose(true);
        //Add all of thisScripts blocks between belowBlock and the following block ( if existing)
    }


    private static ControlBlock getParentOfBlockInScript(Block child, SubScript script) {
        for (Block block : script.getBlocks()) {
            if (block == child) {
                //child is a Top Level Block => has no parent, return null
                return null;
            }
        }
        for (Block block : script.getBlocks()) {
            return (ControlBlock) getParentOfBlockInScriptRecursively(block, child);
        }
        throw new Error("Unable to locate a block in SubScript");
    }

    private static Block getParentOfBlockInScriptRecursively(Block block, Block child) {
        if (block instanceof ControlBlock) {
            if(((ControlBlock) block).getChildren().contains(child))return block;
            for (Block block1 : ((ControlBlock) block).getChildren()) {
                return getParentOfBlockInScriptRecursively(block1, child);
            }
        }
        return null;
    }

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


    public static Collection<Map.Entry<Block, Rectangle>> getBoundingBoxes(SubScript script) {
        ArrayList<Map.Entry<Block, Rectangle>> boundingBoxes = new ArrayList<>();
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

    public static Collection<Block> getBlocksBelowBlock(Block block, SubScript script) {
        ArrayList<Block> blocks = new ArrayList<>();
        if (script.getBlocks().contains(block)) {
            boolean found = false;
            for (Block scriptBlock : script.getBlocks()) {
                if (scriptBlock == block) {
                    found = true;
                }
                if (found) {
                    blocks.add(scriptBlock);
                }
            }
            return blocks;
        }
        for (Block scriptBlock : script.getBlocks()) {
            blocks.addAll(getBlocksBelowRecursively(block, scriptBlock));
        }
        return blocks;
    }

    private static Collection<Block> getBlocksBelowRecursively(Block block, Block scriptBlock) {
        ArrayList<Block> foundBlocks = new ArrayList<>();
        if (scriptBlock instanceof ControlBlock) {
            boolean found = false;
            for (Block child : ((ControlBlock) scriptBlock).getChildren()) {
                if (child == block) {
                    found = true;
                }
                if (found) {
                    foundBlocks.add(child);
                }
            }
            if (!found) {
                ((ControlBlock) scriptBlock).getChildren().forEach(child -> {
                    foundBlocks.addAll(getBlocksBelowRecursively(block, child));
                });
            }
        }
        return foundBlocks;
    }

    public static void deleteBlocksFromScript(Collection<Block> blocksToRemove, SubScript script) {
        script.getBlocks().forEach(block -> findBlocksAndRemoveRecursively(blocksToRemove, block, block));
        //TODO Remove Blocks if they are on the highest level
        ArrayList<Block> toRemove = script.getBlocks().stream().filter(blocksToRemove::contains).collect(Collectors.toCollection(ArrayList::new));
        script.getBlocks().removeAll(toRemove);
    }

    private static void findBlocksAndRemoveRecursively(Collection<Block> blocks, Block block, Block parent) {
        if (block instanceof ControlBlock) {
            ((ControlBlock) block).getChildren().forEach(n -> findBlocksAndRemoveRecursively(blocks, n, block));
            ArrayList<Block> toRemove = ((ControlBlock) block).getChildren().stream().filter(blocks::contains).collect(Collectors.toCollection(ArrayList::new));
            ((ControlBlock) block).getChildren().removeAll(toRemove);
        }

    }


}
