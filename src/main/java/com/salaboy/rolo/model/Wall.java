/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model;

/**
 *
 * @author salaboy
 */
public class Wall extends WorldShape {

    private String name;

    public Wall() {
    }

    public Wall(int height, int width, int positionX, int positionY) {
        super(height, width, positionX, positionY);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Wall{" + "name=" + name + "[ X= " + getPositionX() + " - Y= " + getPositionY() + " - W= " + getWidth() + " - H= " + getHeight() + " ]" + '}';
    }
}
