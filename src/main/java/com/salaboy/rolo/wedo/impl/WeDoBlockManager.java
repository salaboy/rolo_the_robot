/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.wedo.impl;

import com.codeminders.hidapi.ClassPathLibraryLoader;
import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDDeviceNotFoundException;
import com.codeminders.hidapi.HIDManager;
import com.salaboy.rolo.wedo.api.BlockManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salaboy
 */
public class WeDoBlockManager implements BlockManager {

    private HIDDevice dev;
    public static String arch = "arm7";
    static {
        
        
       

    }

    public WeDoBlockManager() {
        if(arch.equals("pc")){
            ClassPathLibraryLoader.loadNativeHIDLibrary();
        }
        if(arch.equals("arm7")){
            System.loadLibrary("hidapi-jni"); 
        }
        try {
            dev = HIDManager.getInstance().openById(VENDOR_ID, PRODUCT_ID, null);
        } catch (HIDDeviceNotFoundException ex) {
            Logger.getLogger(WeDoBlockManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException(" No Device Found! " + ex);
        } catch (IOException ex) {
            Logger.getLogger(WeDoBlockManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException(" No Device Found! " + ex);
        }
    }

    public HIDDevice getDevice() {
        return this.dev;
    }

    public synchronized void write(byte[] data) {
        try {
            dev.write(data);
        } catch (IOException ex) {
            Logger.getLogger(WeDoBlockManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized int read(byte[] buff) {
        try {
            return dev.read(buff);
        } catch (IOException ex) {
            Logger.getLogger(WeDoBlockManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
