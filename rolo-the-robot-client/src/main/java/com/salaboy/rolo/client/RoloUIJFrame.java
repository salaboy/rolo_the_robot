/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.client;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salaboy
 */
public class RoloUIJFrame extends javax.swing.JFrame {

    private RoloClientConnector roloClientConnector;
    private boolean connected = false;
    /**
     * Creates new form RoloUIJFrame
     */
    public RoloUIJFrame() {
        initComponents();
        roloClientConnector = new RoloClientConnector("rolo-ui", new CommandClientHandler(notificationjTextArea));
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        backwardjButton = new javax.swing.JButton();
        leftjButton = new javax.swing.JButton();
        rightjButton = new javax.swing.JButton();
        forwardjButton = new javax.swing.JButton();
        rotateLeftjButton = new javax.swing.JButton();
        rotateRightjButton = new javax.swing.JButton();
        stopjButton = new javax.swing.JButton();
        headRightjButton = new javax.swing.JButton();
        headLeftjButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        notificationjTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        serverAddressjTextField = new javax.swing.JTextField();
        connectjButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        statusjLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Controls"));

        backwardjButton.setText("Backward");
        backwardjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backwardjButtonActionPerformed(evt);
            }
        });

        leftjButton.setText("Left");
        leftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftjButtonActionPerformed(evt);
            }
        });

        rightjButton.setText("Right");
        rightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightjButtonActionPerformed(evt);
            }
        });

        forwardjButton.setText("Forward");
        forwardjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardjButtonActionPerformed(evt);
            }
        });

        rotateLeftjButton.setText("Rotate Left");
        rotateLeftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateLeftjButtonActionPerformed(evt);
            }
        });

        rotateRightjButton.setText("Rotate Right");
        rotateRightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateRightjButtonActionPerformed(evt);
            }
        });

        stopjButton.setText("Stop");
        stopjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopjButtonActionPerformed(evt);
            }
        });

        headRightjButton.setText("Head Right");
        headRightjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headRightjButtonActionPerformed(evt);
            }
        });

        headLeftjButton.setText("Head Left");
        headLeftjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headLeftjButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(rotateLeftjButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(rotateRightjButton))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(80, 80, 80)
                        .add(forwardjButton))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(71, 71, 71)
                        .add(backwardjButton))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(90, 90, 90)
                        .add(stopjButton)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(leftjButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(rightjButton)
                        .add(16, 16, 16))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(headLeftjButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(headRightjButton)
                        .add(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(headLeftjButton)
                    .add(headRightjButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(stopjButton)
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rotateLeftjButton)
                    .add(rotateRightjButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(forwardjButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(leftjButton)
                    .add(rightjButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(backwardjButton)
                .add(15, 15, 15))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Notifications"));

        notificationjTextArea.setColumns(20);
        notificationjTextArea.setRows(5);
        jScrollPane1.setViewportView(notificationjTextArea);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        serverAddressjTextField.setText("192.168.0.x");

        connectjButton.setText("Connect");
        connectjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectjButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Status:");

        statusjLabel.setBackground(new java.awt.Color(153, 0, 0));
        statusjLabel.setForeground(new java.awt.Color(153, 0, 0));
        statusjLabel.setText("Disconnected");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(serverAddressjTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(connectjButton)
                .add(73, 73, 73)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(statusjLabel)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(serverAddressjTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(connectjButton)
                    .add(jLabel1)
                    .add(statusjLabel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void forwardjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardjButtonActionPerformed
        // TODO add your handling code here:
        
        roloClientConnector.write("FORWARD");
    }//GEN-LAST:event_forwardjButtonActionPerformed

    private void rightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightjButtonActionPerformed
        // TODO add your handling code here:
        roloClientConnector.write("RIGHT");
    }//GEN-LAST:event_rightjButtonActionPerformed

    private void leftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftjButtonActionPerformed
        
        roloClientConnector.write("LEFT");
    }//GEN-LAST:event_leftjButtonActionPerformed

    private void backwardjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backwardjButtonActionPerformed
        
        roloClientConnector.write("BACKWARD");
    }//GEN-LAST:event_backwardjButtonActionPerformed

    private void rotateRightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateRightjButtonActionPerformed
        
        roloClientConnector.write("ROTATE RIGHT");
    }//GEN-LAST:event_rotateRightjButtonActionPerformed

    private void rotateLeftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateLeftjButtonActionPerformed
        
        roloClientConnector.write("ROTATE LEFT");
    }//GEN-LAST:event_rotateLeftjButtonActionPerformed

    private void connectjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectjButtonActionPerformed
        
        if(!connected){
            connected = roloClientConnector.connect(serverAddressjTextField.getText(), 5445);
            if(connected){
                statusjLabel.setText("Connected");
                statusjLabel.setForeground(Color.green);
                connectjButton.setText("Disconnect");
            }
        }else{
            try {
                roloClientConnector.disconnect();
            } catch (Exception ex) {
                Logger.getLogger(RoloUIJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectjButton.setText("Connect");
            statusjLabel.setText("Disconnected");
            statusjLabel.setForeground(Color.red);
        }
        
    }//GEN-LAST:event_connectjButtonActionPerformed

    private void stopjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopjButtonActionPerformed
        // TODO add your handling code here:
        roloClientConnector.write("STOP");
    }//GEN-LAST:event_stopjButtonActionPerformed

    private void headRightjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headRightjButtonActionPerformed
        // TODO add your handling code here:
        roloClientConnector.write("ROTATE HEAD RIGHT");
    }//GEN-LAST:event_headRightjButtonActionPerformed

    private void headLeftjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headLeftjButtonActionPerformed
        // TODO add your handling code here:
        roloClientConnector.write("ROTATE HEAD LEFT");
    }//GEN-LAST:event_headLeftjButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoloUIJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoloUIJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoloUIJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoloUIJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoloUIJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backwardjButton;
    private javax.swing.JButton connectjButton;
    private javax.swing.JButton forwardjButton;
    private javax.swing.JButton headLeftjButton;
    private javax.swing.JButton headRightjButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leftjButton;
    private javax.swing.JTextArea notificationjTextArea;
    private javax.swing.JButton rightjButton;
    private javax.swing.JButton rotateLeftjButton;
    private javax.swing.JButton rotateRightjButton;
    private javax.swing.JTextField serverAddressjTextField;
    private javax.swing.JLabel statusjLabel;
    private javax.swing.JButton stopjButton;
    // End of variables declaration//GEN-END:variables
}
