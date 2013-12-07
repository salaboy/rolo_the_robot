/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.model.reports;

/**
 *
 * @author salaboy
 */
public class SonarsReport {

    private int front;
    private int right;
    private int left;
    private int back;
    
    private boolean processed = false;

    public SonarsReport(int front, int right, int left, int back) {
        this.front = front;
        this.right = right;
        this.left = left;
        this.back = back;
    }

    public int getFront() {
        return front;
    }

    public int getRight() {
        return right;
    }

    public int getLeft() {
        return left;
    }

    public int getBack() {
        return back;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return "SonarsReport{" + "front=" + front + ", right=" + right + ", left=" + left + ", back=" + back + ", processed=" + processed + '}';
    }

    
}
