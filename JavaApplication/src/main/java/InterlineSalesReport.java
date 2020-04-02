
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xahna
 */
public class InterlineSalesReport extends javax.swing.JFrame {

    /**
     * Creates new form personalATSReport
     */
    //Holds copy of the database during the current session
    private DefaultTableModel defTabMod;
    
    static boolean isInstantiated;

    public InterlineSalesReport() {
        initComponents();
        isInstantiated = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personalATSBackground = new javax.swing.JPanel();
        personalATSBlueBackground = new javax.swing.JPanel();
        personalATSTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        GlobalButton = new javax.swing.JButton();
        IndividualButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        personalATSBackground.setBackground(new java.awt.Color(255, 255, 255));
        personalATSBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        personalATSBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        personalATSTitle.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        personalATSTitle.setText("INTERLINE SALES REPORT");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout personalATSBlueBackgroundLayout = new javax.swing.GroupLayout(personalATSBlueBackground);
        personalATSBlueBackground.setLayout(personalATSBlueBackgroundLayout);
        personalATSBlueBackgroundLayout.setHorizontalGroup(
            personalATSBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalATSBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(personalATSTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        personalATSBlueBackgroundLayout.setVerticalGroup(
            personalATSBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalATSBlueBackgroundLayout.createSequentialGroup()
                .addComponent(personalATSTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(personalATSBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GlobalButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        GlobalButton.setText("GLOBAL");
        GlobalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GlobalButtonActionPerformed(evt);
            }
        });

        IndividualButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        IndividualButton.setText("INDIVIDUAL");
        IndividualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IndividualButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout personalATSBackgroundLayout = new javax.swing.GroupLayout(personalATSBackground);
        personalATSBackground.setLayout(personalATSBackgroundLayout);
        personalATSBackgroundLayout.setHorizontalGroup(
            personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalATSBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(IndividualButton, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GlobalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        personalATSBackgroundLayout.setVerticalGroup(
            personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                .addComponent(personalATSBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GlobalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IndividualButton, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalATSBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalATSBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        isInstantiated = false;
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void GlobalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GlobalButtonActionPerformed
        // TODO add your handling code here:
        GlobalInterlineReport global = new GlobalInterlineReport();
        global.setVisible(true);
        global.setDefaultCloseOperation(global.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_GlobalButtonActionPerformed

    private void IndividualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IndividualButtonActionPerformed
        //if manager want to make report prompt him with advisors list to choose for which advisor
        //wants to make report
        if (LoginForm.role.equals("manager") | LoginForm.role.equals("Manager")) {
            AdvisorReportList list = new AdvisorReportList();
            list.setVisible(true);
            list.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {//else just open the report page because advisors ID is 
            //been already captured when login
            PersonalInterlineReport p = new PersonalInterlineReport();
            p.setVisible(true);
            p.setDefaultCloseOperation(p.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_IndividualButtonActionPerformed

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
            java.util.logging.Logger.getLogger(InterlineSalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterlineSalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterlineSalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterlineSalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterlineSalesReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GlobalButton;
    private javax.swing.JButton IndividualButton;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel personalATSBackground;
    private javax.swing.JPanel personalATSBlueBackground;
    private javax.swing.JLabel personalATSTitle;
    // End of variables declaration//GEN-END:variables
}
