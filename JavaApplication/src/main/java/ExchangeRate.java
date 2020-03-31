
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xahna
 */
public class ExchangeRate extends javax.swing.JFrame {

    PreparedStatement pst = null;
    LocalDateTime localDateTime;
    String result;
   
    /**
     * Creates new form exchangeRate
     */
    public ExchangeRate() {
        initComponents();
        try ( Connection con = DbCon.getConnection()) {//gets the currency type this office is using
            pst = con.prepareStatement("select currency from AgencyDetails");
            ResultSet rs = pst.executeQuery();
            rs.next();
            LocalCLabel.setText("1 " + rs.getString("currency") + ":");//sets the label to the according type of currency

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExchangeRate.class.getName()).log(Level.SEVERE, null, ex);
        }
        //sets local date and time variable according to the current date and time
        localDateTime = LocalDateTime.now().withNano(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createCustomerBackground = new javax.swing.JPanel();
        exchangeRateBackground = new javax.swing.JPanel();
        exchangeRateTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        LocalCLabel = new javax.swing.JLabel();
        exchangeRateTextbox = new javax.swing.JTextField();
        USDjLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createCustomerBackground.setBackground(new java.awt.Color(255, 255, 255));
        createCustomerBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        exchangeRateBackground.setBackground(new java.awt.Color(102, 255, 255));

        exchangeRateTitle.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        exchangeRateTitle.setText("EXCHANGE RATE");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exchangeRateBackgroundLayout = new javax.swing.GroupLayout(exchangeRateBackground);
        exchangeRateBackground.setLayout(exchangeRateBackgroundLayout);
        exchangeRateBackgroundLayout.setHorizontalGroup(
            exchangeRateBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exchangeRateBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exchangeRateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        exchangeRateBackgroundLayout.setVerticalGroup(
            exchangeRateBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exchangeRateBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(exchangeRateBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exchangeRateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        LocalCLabel.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        LocalCLabel.setText("Local");

        exchangeRateTextbox.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        exchangeRateTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exchangeRateTextboxActionPerformed(evt);
            }
        });

        USDjLabel.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        USDjLabel.setText("USD");

        javax.swing.GroupLayout createCustomerBackgroundLayout = new javax.swing.GroupLayout(createCustomerBackground);
        createCustomerBackground.setLayout(createCustomerBackgroundLayout);
        createCustomerBackgroundLayout.setHorizontalGroup(
            createCustomerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exchangeRateBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(createCustomerBackgroundLayout.createSequentialGroup()
                .addGroup(createCustomerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createCustomerBackgroundLayout.createSequentialGroup()
                        .addGap(0, 738, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createCustomerBackgroundLayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(LocalCLabel)
                        .addGap(51, 51, 51)
                        .addComponent(exchangeRateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(USDjLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        createCustomerBackgroundLayout.setVerticalGroup(
            createCustomerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createCustomerBackgroundLayout.createSequentialGroup()
                .addComponent(exchangeRateBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214)
                .addGroup(createCustomerBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeRateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LocalCLabel)
                    .addComponent(USDjLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createCustomerBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createCustomerBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        String sql = "INSERT INTO ExchangeRates(Date, rate) Values(?,?)";
        try ( Connection con = DbCon.getConnection();) {//Get connection to the database

            //sets date and rate in ExchangeRates table
            pst = con.prepareStatement(sql);
            pst.setString(1, localDateTime.toString());
            pst.setString(2, exchangeRateTextbox.getText());

            pst.execute();
            JOptionPane.showMessageDialog(null, "inserted successfully");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void exchangeRateTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exchangeRateTextboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exchangeRateTextboxActionPerformed

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
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExchangeRate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExchangeRate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LocalCLabel;
    private javax.swing.JLabel USDjLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel createCustomerBackground;
    private javax.swing.JPanel exchangeRateBackground;
    private javax.swing.JTextField exchangeRateTextbox;
    private javax.swing.JLabel exchangeRateTitle;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
