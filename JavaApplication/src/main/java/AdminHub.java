/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhruv
 */
public class AdminHub extends javax.swing.JFrame {

    /**
     * Creates new form adminHub
     */
    public AdminHub() {
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

        adminHubBackground = new javax.swing.JPanel();
        adminBlueBackground = new javax.swing.JPanel();
        adminTitle = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        stockTurnoverReportButton = new javax.swing.JButton();
        restoreButton = new javax.swing.JButton();
        backupButton = new javax.swing.JButton();
        agencyInfoButton = new javax.swing.JButton();
        blankStockButton = new javax.swing.JButton();
        advisorListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        adminHubBackground.setBackground(new java.awt.Color(255, 255, 255));
        adminHubBackground.setPreferredSize(new java.awt.Dimension(1200, 900));

        adminBlueBackground.setBackground(new java.awt.Color(102, 240, 240));

        adminTitle.setFont(new java.awt.Font("Tahoma", 0, 110)); // NOI18N
        adminTitle.setText("ADMIN");

        logoutButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        logoutButton.setText("LOGOUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminBlueBackgroundLayout = new javax.swing.GroupLayout(adminBlueBackground);
        adminBlueBackground.setLayout(adminBlueBackgroundLayout);
        adminBlueBackgroundLayout.setHorizontalGroup(
            adminBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214)
                .addComponent(adminTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        adminBlueBackgroundLayout.setVerticalGroup(
            adminBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminBlueBackgroundLayout.createSequentialGroup()
                .addGroup(adminBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminBlueBackgroundLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(adminTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adminBlueBackgroundLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminBlueBackgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        stockTurnoverReportButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        stockTurnoverReportButton.setText("STOCK TURNOVER REPORT");
        stockTurnoverReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockTurnoverReportButtonstockTurnoverReportActionPerformed(evt);
            }
        });

        restoreButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        restoreButton.setText("RESTORE");
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonrestoreActionPerformed(evt);
            }
        });

        backupButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backupButton.setText("BACKUP");
        backupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupButtonbackupActionPerformed(evt);
            }
        });

        agencyInfoButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        agencyInfoButton.setText("AGENCY INFO");
        agencyInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agencyInfoButtonagencyInfoActionPerformed(evt);
            }
        });

        blankStockButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        blankStockButton.setText("BLANK STOCK");
        blankStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blankStockButtonblankStockActionPerformed(evt);
            }
        });

        advisorListButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        advisorListButton.setText("ADVISOR LIST");
        advisorListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advisorListButtonadvisorListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminHubBackgroundLayout = new javax.swing.GroupLayout(adminHubBackground);
        adminHubBackground.setLayout(adminHubBackgroundLayout);
        adminHubBackgroundLayout.setHorizontalGroup(
            adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminHubBackgroundLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(blankStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agencyInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(advisorListButton, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(backupButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockTurnoverReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(restoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        adminHubBackgroundLayout.setVerticalGroup(
            adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminHubBackgroundLayout.createSequentialGroup()
                .addComponent(adminBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addGroup(adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(advisorListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131)
                .addGroup(adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agencyInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143)
                .addGroup(adminHubBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blankStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockTurnoverReportButton))
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminHubBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminHubBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonlogoutActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginForm s = new LoginForm();
        s.setVisible(true);
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_logoutButtonlogoutActionPerformed

    private void stockTurnoverReportButtonstockTurnoverReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockTurnoverReportButtonstockTurnoverReportActionPerformed
        // TODO add your handling code here:
        StockTurnoverReport str= new StockTurnoverReport();
        str.setVisible(true);
        str.setDefaultCloseOperation(str.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_stockTurnoverReportButtonstockTurnoverReportActionPerformed

    private void restoreButtonrestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreButtonrestoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restoreButtonrestoreActionPerformed

    private void backupButtonbackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupButtonbackupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backupButtonbackupActionPerformed

    private void agencyInfoButtonagencyInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agencyInfoButtonagencyInfoActionPerformed
        // TODO add your handling code here:
        AgencyDetails agencydetails= new AgencyDetails();
        agencydetails.setVisible(true);
        agencydetails.setDefaultCloseOperation(agencydetails.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_agencyInfoButtonagencyInfoActionPerformed

    private void blankStockButtonblankStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blankStockButtonblankStockActionPerformed
        // TODO add your handling code here:
        AdminStockControl asc= new AdminStockControl();
        asc.setVisible(true);
        asc.setDefaultCloseOperation(asc.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_blankStockButtonblankStockActionPerformed

    private void advisorListButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advisorListButtonadvisorListActionPerformed
        // TODO add your handling code here:
        AdvisorsList advlist= new AdvisorsList();
        advlist.setVisible(true);
        advlist.setDefaultCloseOperation(advlist.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_advisorListButtonadvisorListActionPerformed

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
            java.util.logging.Logger.getLogger(AdminHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHub().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminBlueBackground;
    private javax.swing.JPanel adminHubBackground;
    private javax.swing.JLabel adminTitle;
    private javax.swing.JButton advisorListButton;
    private javax.swing.JButton agencyInfoButton;
    private javax.swing.JButton backupButton;
    private javax.swing.JButton blankStockButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton restoreButton;
    private javax.swing.JButton stockTurnoverReportButton;
    // End of variables declaration//GEN-END:variables
}
