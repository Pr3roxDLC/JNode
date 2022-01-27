package me.pr3.JNode.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Script {

    public ArrayList<SubScript> subScripts = new ArrayList<>();

    public Script(Collection<SubScript> subScripts){
        this.subScripts = new ArrayList<>(subScripts);
    }

    public void drawScriptBlocks(Graphics2D g) {
        subScripts.forEach(n -> {
            int offsetX = 0;
            AtomicInteger offsetY = new AtomicInteger();
            n.getBlocks().forEach(block -> {
                g.setColor(block.getColor());
                g.fillRect(n.getX() + offsetX, n.getY() + offsetY.get(), block.getWidth(), block.getHeight());
                offsetY.addAndGet(block.getHeight());
            });
        });
    }
}