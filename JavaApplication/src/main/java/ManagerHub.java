

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xahna
 */
public class ManagerHub extends javax.swing.JFrame {

    /**
     * Creates new form managerHub
     */
    public ManagerHub() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        advisorBackground = new javax.swing.JPanel();
        advisorBlueBackground = new javax.swing.JPanel();
        advisorTitle = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        commissionButton = new javax.swing.JButton();
        logsButton = new javax.swing.JButton();
        blankStockButton = new javax.swing.JButton();
        exchangeRateButton = new javax.swing.JButton();
        viewReportsComboBox = new javax.swing.JComboBox<>();
        stockTurnoverReportButton = new javax.swing.JButton();
        statusButton = new javax.swing.JButton();
        viewAlerts = new javax.swing.JButton();
        customerRecords = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        advisorBackground.setBackground(new java.awt.Color(255, 255, 255));
        advisorBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        advisorBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        advisorTitle.setFont(new java.awt.Font("Tahoma", 0, 110)); // NOI18N
        advisorTitle.setText("MANAGER");

        logoutButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        logoutButton.setText("LOGOUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout advisorBlueBackgroundLayout = new javax.swing.GroupLayout(advisorBlueBackground);
        advisorBlueBackground.setLayout(advisorBlueBackgroundLayout);
        advisorBlueBackgroundLayout.setHorizontalGroup(
            advisorBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advisorBlueBackgroundLayout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(advisorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        advisorBlueBackgroundLayout.setVerticalGroup(
            advisorBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(advisorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(advisorBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        commissionButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        commissionButton.setText("COMMISSION");
        commissionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commissionButtonActionPerformed(evt);
            }
        });

        logsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        logsButton.setText("LOGS");
        logsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsButtonActionPerformed(evt);
            }
        });

        blankStockButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        blankStockButton.setText("BLANK STOCK");
        blankStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blankStockButtonActionPerformed(evt);
            }
        });

        exchangeRateButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        exchangeRateButton.setText("EXCHANGE RATE");
        exchangeRateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exchangeRateButtonActionPerformed(evt);
            }
        });

        viewReportsComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        viewReportsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIEW REPORTS", "Interline", "Domestic" }));
        viewReportsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportsComboBoxActionPerformed(evt);
            }
        });

        stockTurnoverReportButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        stockTurnoverReportButton.setText("STOCK TURNOVER REPORT");
        stockTurnoverReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockTurnoverReportButtonActionPerformed(evt);
            }
        });

        statusButton.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        statusButton.setText("ASSIGN STATUS");
        statusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusButtonActionPerformed(evt);
            }
        });

        viewAlerts.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        viewAlerts.setText("VIEW ALERTS");
        viewAlerts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAlertsActionPerformed(evt);
            }
        });

        customerRecords.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        customerRecords.setText("CUSTOMER RECORDS");
        customerRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRecordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout advisorBackgroundLayout = new javax.swing.GroupLayout(advisorBackground);
        advisorBackground.setLayout(advisorBackgroundLayout);
        advisorBackgroundLayout.setHorizontalGroup(
            advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(commissionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blankStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockTurnoverReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewReportsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(201, 201, 201)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewAlerts, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exchangeRateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        advisorBackgroundLayout.setVerticalGroup(
            advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                .addComponent(advisorBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blankStockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTurnoverReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeRateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commissionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewReportsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewAlerts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(logsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(customerRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(118, 118, 118))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void commissionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commissionButtonActionPerformed
        // TODO add your handling code here:
        Commission s = new Commission();
        s.setVisible(true);
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_commissionButtonActionPerformed

    private void logsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsButtonActionPerformed
        // TODO add your handling code here:
        try{
              
               
           String filename= "manager's"+" "+"logfile";
     
           ProcessBuilder pb = new ProcessBuilder("Notepad.exe", filename);
        pb.start();
      
        
        }
        catch(Exception e){  System.out.println("error");}
    }//GEN-LAST:event_logsButtonActionPerformed

    private void blankStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blankStockButtonActionPerformed

        // TODO add your handling code here:
        ManagerStock s = new ManagerStock();
        s.setVisible(true);
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_blankStockButtonActionPerformed

    private void exchangeRateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exchangeRateButtonActionPerformed
        ExchangeRate s = new ExchangeRate();
        s.setVisible(true);
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_exchangeRateButtonActionPerformed

    private void stockTurnoverReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockTurnoverReportButtonActionPerformed
        StockTurnoverReport s = new StockTurnoverReport();
        s.setVisible(true);// TODO add your handling code here:
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_stockTurnoverReportButtonActionPerformed

    private void statusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusButtonActionPerformed
        // TODO add your handling code here:
        CustomerStatus s = new CustomerStatus();
        s.setVisible(true);// TODO add your handling code here:
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_statusButtonActionPerformed

    private void viewAlertsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAlertsActionPerformed
        Alerts s = new Alerts();
        s.setVisible(true);// TODO add your handling code here:
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_viewAlertsActionPerformed

    private void logoutButtonlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonlogoutActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginForm s = new LoginForm();
        s.setVisible(true);
    }//GEN-LAST:event_logoutButtonlogoutActionPerformed

    private void viewReportsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportsComboBoxActionPerformed
        // TODO add your handling code here:
     switch (viewReportsComboBox.getSelectedItem().toString()) {
                case "Interline":
                    InterlineSalesReport interlineSalesReport = new InterlineSalesReport();
                    interlineSalesReport.setVisible(true);
                    interlineSalesReport.setDefaultCloseOperation(interlineSalesReport.DISPOSE_ON_CLOSE);
                    break;
                case "Domestic":
                    DomesticSalesReport domesticSalesReport = new DomesticSalesReport();
                    domesticSalesReport.setVisible(true);
                    domesticSalesReport.setDefaultCloseOperation(domesticSalesReport.DISPOSE_ON_CLOSE);
                    break;
        }
    }//GEN-LAST:event_viewReportsComboBoxActionPerformed

    private void customerRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerRecordsActionPerformed
        // TODO add your handling code here:
         managerCustomerRecords mcr= new managerCustomerRecords();
         mcr.setVisible(true);
        
        
        
    }//GEN-LAST:event_customerRecordsActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerHub().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel advisorBackground;
    private javax.swing.JPanel advisorBlueBackground;
    private javax.swing.JLabel advisorTitle;
    private javax.swing.JButton blankStockButton;
    private javax.swing.JButton commissionButton;
    private javax.swing.JButton customerRecords;
    private javax.swing.JButton exchangeRateButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton logsButton;
    private javax.swing.JButton statusButton;
    private javax.swing.JButton stockTurnoverReportButton;
    private javax.swing.JButton viewAlerts;
    private javax.swing.JComboBox<String> viewReportsComboBox;
    // End of variables declaration//GEN-END:variables
}
