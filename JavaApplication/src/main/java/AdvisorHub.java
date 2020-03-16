/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xahna
 */
public class AdvisorHub extends javax.swing.JFrame {

    /**
     * Creates new form advisorHub
     */
    public AdvisorHub() {
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
        returnTicketButton = new javax.swing.JButton();
        logsButton = new javax.swing.JButton();
        CustomerButton = new javax.swing.JButton();
        exchangeRateButton = new javax.swing.JButton();
        makeATSComboBox = new javax.swing.JComboBox<>();
        sellTicketComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1200, 900));

        advisorBackground.setBackground(new java.awt.Color(255, 255, 255));
        advisorBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        advisorBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        advisorTitle.setFont(new java.awt.Font("Tahoma", 0, 110)); // NOI18N
        advisorTitle.setText("ADVISOR");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(advisorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        advisorBlueBackgroundLayout.setVerticalGroup(
            advisorBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advisorBlueBackgroundLayout.createSequentialGroup()
                .addGroup(advisorBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advisorBlueBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(advisorBlueBackgroundLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(advisorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        returnTicketButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        returnTicketButton.setText("RETURN TICKET");
        returnTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnTicketButtonActionPerformed(evt);
            }
        });

        logsButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        logsButton.setText("LOGS");
        logsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsButtonActionPerformed(evt);
            }
        });

        CustomerButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        CustomerButton.setText(" CUSTOMER");
        CustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerButtonActionPerformed(evt);
            }
        });

        exchangeRateButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        exchangeRateButton.setText("EXCHANGE RATE");
        exchangeRateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exchangeRateButtonActionPerformed(evt);
            }
        });

        makeATSComboBox.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        makeATSComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAKE ATS", "International", "Domestic" }));
        makeATSComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeATSComboBoxActionPerformed(evt);
            }
        });

        sellTicketComboBox.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        sellTicketComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELL TICKET", "444\t", "440", "420", "201", "101", "451", "452" }));
        sellTicketComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellTicketComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout advisorBackgroundLayout = new javax.swing.GroupLayout(advisorBackground);
        advisorBackground.setLayout(advisorBackgroundLayout);
        advisorBackgroundLayout.setHorizontalGroup(
            advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeATSComboBox, 0, 353, Short.MAX_VALUE)
                    .addComponent(CustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sellTicketComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(exchangeRateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(returnTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        advisorBackgroundLayout.setVerticalGroup(
            advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                .addComponent(advisorBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advisorBackgroundLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(returnTicketButton))
                    .addGroup(advisorBackgroundLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(CustomerButton)))
                .addGap(110, 110, 110)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(makeATSComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(sellTicketComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorBackgroundLayout.createSequentialGroup()
                        .addComponent(logsButton)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorBackgroundLayout.createSequentialGroup()
                        .addComponent(exchangeRateButton)
                        .addGap(131, 131, 131))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(advisorBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void returnTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnTicketButtonActionPerformed
        ReturnTicket s = new ReturnTicket();
        s.setVisible(true);     
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);// TODO add your handling code here:
    }//GEN-LAST:event_returnTicketButtonActionPerformed

    private void logsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logsButtonActionPerformed

    private void CustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerButtonActionPerformed
        CustomerForm s = new CustomerForm();
        s.setVisible(true);
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_CustomerButtonActionPerformed

    private void exchangeRateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exchangeRateButtonActionPerformed
        // TODO add your handling code here:
        ExchangeRate s = new ExchangeRate();
        s.setVisible(true);
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_exchangeRateButtonActionPerformed

    private void makeATSComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeATSComboBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_makeATSComboBoxActionPerformed

    private void logoutButtonlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonlogoutActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginForm s = new LoginForm();
        s.setVisible(true);
    }//GEN-LAST:event_logoutButtonlogoutActionPerformed

    private void sellTicketComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellTicketComboBoxActionPerformed
        // TODO add your handling code here:
         BookTicket s = new BookTicket();
        s.setVisible(true);     
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_sellTicketComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(AdvisorHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdvisorHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdvisorHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdvisorHub.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdvisorHub().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CustomerButton;
    private javax.swing.JPanel advisorBackground;
    private javax.swing.JPanel advisorBlueBackground;
    private javax.swing.JLabel advisorTitle;
    private javax.swing.JButton exchangeRateButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton logsButton;
    private javax.swing.JComboBox<String> makeATSComboBox;
    private javax.swing.JButton returnTicketButton;
    private javax.swing.JComboBox<String> sellTicketComboBox;
    // End of variables declaration//GEN-END:variables
}
