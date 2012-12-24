/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class Box extends WorldShape {

    private int weight;
    
    public Box() {
    }

    public Box(int height, int width, int positionX, int positionY) {
        super(height, width, positionX, positionY);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Box{" + "weight=" + weight + '}';
    }


}
