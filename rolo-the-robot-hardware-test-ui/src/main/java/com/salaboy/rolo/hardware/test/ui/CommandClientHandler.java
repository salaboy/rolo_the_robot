/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.salaboy.rolo.hardware.test.ui;


import org.hornetq.api.core.client.ClientSession;

public class CommandClientHandler {
    
    
    private HardwareTestUIJFrame ui;
    
    
    
    public CommandClientHandler(HardwareTestUIJFrame ui) {
        this.ui = ui;
    }

   
    
    public void exceptionCaught(ClientSession session, Throwable cause) throws Exception {
//    	handler.exceptionCaught(new HornetQSessionWriter(session, message), cause);
    }
    
    public void messageReceived(ClientSession session, Object message, String producerId) throws Exception {
        
        System.out.println(">>> Client Handler Message Recieved = " + message);
        String[] values = message.toString().split(":");
        if(values[0].equals("DISTANCE_REPORT")){
            ui.getDistancejTextField().setText(values[1]); 
        }
        
        if(values[0].equals("LIGHT_REPORT")){
            ui.getLightjTextField().setText(values[1]);
        }
        
        if(values[0].equals("TOUCH_REPORT")){
            ui.getTouchjTextField().setText(values[1]);
        }
        
        ui.getNotificationjTextArea().setText(">>> Client Handler Message Recieved = " + message + "\n"+ui.getNotificationjTextArea().getText());
    }
}
