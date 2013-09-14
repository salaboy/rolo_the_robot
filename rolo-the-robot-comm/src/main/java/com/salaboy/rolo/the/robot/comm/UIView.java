/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.the.robot.comm;

import org.hornetq.api.core.client.ClientSession;

/**
 *
 * @author salaboy
 */
public interface UIView {
    void messageReceived(ClientSession session, Object message, String producerId) throws Exception;
}
