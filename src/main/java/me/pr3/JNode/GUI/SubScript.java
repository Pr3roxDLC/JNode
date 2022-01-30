package me.pr3.JNode.GUI;

import me.pr3.JNode.GUI.Util.GuiUtil;
import me.pr3.JNode.GUI.blocks.Block;
import me.pr3.JNode.GUI.blocks.ControlBloks.ControlBlock;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SubScript {

    private final ArrayList<Block> blocks = new ArrayList<>();
    private int x,y,layer = 0;
    private int width, height = 0;
    private boolean isBound = false;

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

    public void handleMouseEvent(MouseEvent event, @Nullable EventType eventType){
        if(eventType == null)return;
        //System.out.println(eventType.toString());
        if(eventType == EventType.PRESSED){
            if(getBoundingBox().contains(event.getPoint())){
                isBound = true;
                //System.out.println("Bound to script");
            }
        }
        if(eventType == EventType.RELEASED){
            isBound = false;
        }
    }

    public Rectangle2D getBoundingBox(){
        if(isBoundingBoxOutdated){
            height = GuiUtil.getSubScriptHeight(this);
            width = GuiUtil.getSubScriptWidth(this);
            //System.out.println("Calculated " + height + " " + width);
            isBoundingBoxOutdated = false;
        }
        return new Rectangle2D.Double(x , y, width, height);
    }

    public boolean isBound() {
        return isBound;
    }

    public enum EventType{
        PRESSED,
        CLICKED,
        RELEASED
    }

}
