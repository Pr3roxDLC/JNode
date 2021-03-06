package me.pr3.JNode.GUI;

import me.pr3.JNode.GUI.Util.EventType;
import me.pr3.JNode.GUI.Util.GuiUtil;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ControlBlocks.ControlBlock;
import me.pr3.JNode.GUI.blocks.HeadBlocks.HeadBlock;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class SubScript {

    private final ArrayList<Block> blocks = new ArrayList<>();
    private int x, y, layer = 0;
    private int width, height = 0;

    public void setBound(boolean bound) {
        isBound = bound;
    }

    private boolean isBound = false;
    private int grabOffsetX = 0;
    private int grabOffsetY = 0;
    private Rectangle boundingBox = null;
    private boolean toDispose = false;

    private boolean isBoundingBoxOutdated = true;

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public SubScript() {
        isBoundingBoxOutdated = true;
    }

    public void handleMouseEvent(MouseEvent event, @Nullable EventType eventType) {
        this.isBoundingBoxOutdated = true;
        if (eventType == null) return;
        //System.out.println(eventType.toString());
        if (eventType == EventType.PRESSED) {
            if (getBoundingBox().contains(event.getPoint())) {


                Optional<Map.Entry<Block, Rectangle>> clickedOn = GuiUtil.getBoundingBoxes(this).stream()
                    .filter(n -> n.getValue().contains(event.getPoint()))
                    .findFirst();
                clickedOn.ifPresent(n -> {
                    Block block = clickedOn.get().getKey();

                    //If we clicked on the Top Block, dont split the script
                    if (block == getBlocks().get(0)) {
                        System.out.println("Binding Script: " + event.getX() + " - " + block.getX() + " = " + (event.getX() - block.getX()));
                        System.out.println("Binding Script: " + event.getY() + " - " + block.getY() + " = " + (event.getY() - block.getY()));
                        this.setGrabOffsetX(event.getX() - block.getX());
                        this.setGrabOffsetY(event.getY() - block.getY());
                        this.isBound = true;
                        return;
                    }

                    ArrayList<Block> blocksBelow = new ArrayList<>(GuiUtil.getBlocksBelowBlock(block, this));
                    SubScript newSubScript = new SubScript();
                    newSubScript.getBlocks().addAll(blocksBelow);
                    newSubScript.setX(0);
                    newSubScript.setY(0);
                    GUI.script.subScripts.add(newSubScript);
                    newSubScript.setGrabOffsetX(event.getX() - block.getX());
                    newSubScript.setGrabOffsetY(event.getY() - block.getY());
                    newSubScript.isBound = true;
                    System.out.println(block.getClass().getSimpleName());
                    GuiUtil.deleteBlocksFromScript(blocksBelow, this);
                });


                //System.out.println("Bound to script");
            }
        }
        if (eventType == EventType.RELEASED) {
            if (this.isBound) {
                isBound = false;
                if(this.getBlocks().get(0) instanceof HeadBlock)return;
                for (SubScript script : GUI.script.subScripts) {
                    if (script == this) continue;
                    if (script.getBoundingBox().intersects(this.getBoundingBox())) {
                        Point intersectPoint = new Point((int) this.getBoundingBox().getCenterX(), (int) (this.getBoundingBox().getY()));
                        for(Map.Entry<Block, Rectangle> pair : GuiUtil.getBoundingBoxes(script)){
                            if(pair.getKey() instanceof ControlBlock){
                                GuiUtil.getSubBoundingBoxes(pair.getKey()).entrySet().stream().filter(stringRectangleEntry -> stringRectangleEntry.getValue().contains(intersectPoint)).findFirst().ifPresent(n -> {
                                    String name = n.getKey();
                                    Rectangle rect = n.getValue();
                                    switch (name){
                                        case"Head":
                                            GuiUtil.mergeScripts(this, script, pair.getKey(), 0);
                                            this.setToDispose(true);
                                            script.isBoundingBoxOutdated = true;
                                            script.getBoundingBox();
                                            break;
                                        case "Foot":
                                            GuiUtil.mergeScripts(this, script, pair.getKey());
                                            this.setToDispose(true);
                                            script.isBoundingBoxOutdated = true;
                                            script.getBoundingBox();
                                            break;
                                    }
                                });
                            }else if(pair.getValue().contains(intersectPoint)){
                                GuiUtil.mergeScripts(this, script, pair.getKey());
                                this.setToDispose(true);
                                script.isBoundingBoxOutdated = true;
                                script.getBoundingBox();
                            }
                        }
                        break;
                    }
                }
            }
        }
        this.isBoundingBoxOutdated = true;
    }

    public Rectangle2D getBoundingBox() {
        if (isBoundingBoxOutdated) {
            height = GuiUtil.getSubScriptHeight(this);
            width = GuiUtil.getSubScriptWidth(this);
            //System.out.println("Calculated " + height + " " + width);
            isBoundingBoxOutdated = false;
        }
        boundingBox = new Rectangle(x, y, width, height);
        return boundingBox;
    }

    public boolean isBound() {
        return isBound;
    }

    public int getGrabOffsetY() {
        return grabOffsetY;
    }

    public void setGrabOffsetY(int grabOffsetY) {
        this.grabOffsetY = grabOffsetY;
    }

    public int getGrabOffsetX() {
        return grabOffsetX;
    }

    public void setGrabOffsetX(int grabOffsetX) {
        this.grabOffsetX = grabOffsetX;
    }

    public boolean isToDispose() {
        return toDispose;
    }

    public void setToDispose(boolean toDispose) {
        this.toDispose = toDispose;
    }



}
