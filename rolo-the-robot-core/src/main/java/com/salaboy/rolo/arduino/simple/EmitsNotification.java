/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.arduino.simple;

import com.salaboy.rolo.transport.HornetQSessionWriter;

/**
 *
 * @author salaboy
 */
public interface EmitsNotification {
    void setNotifications(HornetQSessionWriter notifications);
}
