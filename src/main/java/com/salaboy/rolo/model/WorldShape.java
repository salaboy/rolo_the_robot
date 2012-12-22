/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class WorldShape {
    private int height;
    private int width;
    private int positionX;
    private int positionY;

    public WorldShape() {
    }

    public WorldShape(int height, int width, int positionX, int positionY) {
        this.height = height;
        this.width = width;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int weight) {
        this.width = weight;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "WorldShape{" + "height=" + height + ", weight=" + width + ", positionX=" + positionX + ", positionY=" + positionY + '}';
    }
    
    
}
