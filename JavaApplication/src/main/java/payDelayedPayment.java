import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhruv
 */
public class payDelayedPayment extends javax.swing.JFrame {
 //Holds copy of the database during the current session
    private DefaultTableModel defTabMod;
    //holds the row number selected by the user
    private int selectedRow;
   static int customerID; 
      private CardInfo cardInfo;
    PreparedStatement pst= null; 
    ResultSet rs=null; 
    Connection con=null;
  
    /**
     * Creates new form payDelayedPayment
     */
    public payDelayedPayment() {
        //   paymentjComboBox.setVisible(false);
        initComponents();
     initPaymentRecords("SELECT *  FROM Payment WHERE delayed=1; ");
     
     
    }
 private void initPaymentRecords(String sqlStatement) {
        //estabblish connection with the database
        try ( PreparedStatement ps = DbCon.getConnection().prepareStatement(sqlStatement);) {
            
            
            
            ResultSet rs = ps.executeQuery();//contains the data returned from the database quiery
            ResultSetMetaData rsmd = rs.getMetaData();
            //controls the for loop for the assigning of values in the vector
            int column = rsmd.getColumnCount();
            //initialize this form table according to the database structure
            defTabMod = (DefaultTableModel) paymentTable.getModel();
            defTabMod.setRowCount(0);
            //loops over each row of the database
            while (rs.next()) {
                Vector v = new Vector();

                for (int i = 0; i <= column; i++) {
                    
                    v.add(rs.getString("BlankblankNumber"));
                    v.add(rs.getBoolean("delayed"));
                    v.add(rs.getString("exchangeRate"));
                   v.add(rs.getString("type"));
                   v.add(rs.getString("date"));
                   v.add(rs.getString("taxes"));
                 v.add(rs.getBoolean("isRefunded"));
                 v.add(rs.getString("name"));
             
                         
                    
                    
                }//inserts single row collected data from the databse into this form table
                defTabMod.addRow(v);
            }

        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        delayedPaymentBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        cardDetailsTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        cardNumberLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        paymentjComboBox = new javax.swing.JComboBox<>();
        paymentPane = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        delayedPaymentBackground.setBackground(new java.awt.Color(255, 255, 255));
        delayedPaymentBackground.setPreferredSize(new java.awt.Dimension(1100, 800));

        bluePanel.setBackground(new java.awt.Color(125, 240, 240));

        cardDetailsTitle.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        cardDetailsTitle.setText("DELAYED PAYMENT");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonadvisorListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bluePanelLayout = new javax.swing.GroupLayout(bluePanel);
        bluePanel.setLayout(bluePanelLayout);
        bluePanelLayout.setHorizontalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cardDetailsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addGroup(bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bluePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bluePanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(cardDetailsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        cardNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonadvisorListActionPerformed(evt);
            }
        });

        paymentjComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        paymentjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "CASH", "CARD" }));
        paymentjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentjComboBoxActionPerformed(evt);
            }
        });

        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Blank Number", "Delayed", "Exchange Rate", "Type", "Date", "Taxes", "Is Refunded", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        paymentTable.setColumnSelectionAllowed(true);
        paymentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentTableMouseClicked(evt);
            }
        });
        paymentPane.setViewportView(paymentTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Select Blank:");

        javax.swing.GroupLayout delayedPaymentBackgroundLayout = new javax.swing.GroupLayout(delayedPaymentBackground);
        delayedPaymentBackground.setLayout(delayedPaymentBackgroundLayout);
        delayedPaymentBackgroundLayout.setHorizontalGroup(
            delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delayedPaymentBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delayedPaymentBackgroundLayout.createSequentialGroup()
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delayedPaymentBackgroundLayout.createSequentialGroup()
                            .addComponent(paymentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)))))
            .addGroup(delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(delayedPaymentBackgroundLayout.createSequentialGroup()
                    .addGap(358, 358, 358)
                    .addComponent(cardNumberLabel)
                    .addContainerGap(742, Short.MAX_VALUE)))
        );
        delayedPaymentBackgroundLayout.setVerticalGroup(
            delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delayedPaymentBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addComponent(paymentjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(delayedPaymentBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(delayedPaymentBackgroundLayout.createSequentialGroup()
                    .addContainerGap(381, Short.MAX_VALUE)
                    .addComponent(cardNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(374, 374, 374)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(delayedPaymentBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(delayedPaymentBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonadvisorListActionPerformed
        //assigns values to the variables according to the user input

 try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = null;
           
            String type= paymentjComboBox.getSelectedItem().toString();
        
            if (paymentjComboBox.getSelectedItem().toString().equals("CARD")) {
                    pst = con.prepareStatement("update Payment set type = '"+ cardInfo.getCardNo() + "',   name = '" + cardInfo.getCardHldrName() + "', expDate = '" + cardInfo.getCardExpDate() + "', delayed=0 where BlankblankNumber = '" + defTabMod.getValueAt(selectedRow, 0) + "'"
                    );
                    pst.execute();

                } else {//write to payment if cash paymment is being used
                    pst = con.prepareStatement("update Payment set type = 'cash',delayed=0 where BlankblankNumber = '" + defTabMod.getValueAt(selectedRow, 0) + "'");
                 
                    pst.execute();
                }
         
            
           JOptionPane.showMessageDialog(null,"Payment made");
           
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_saveButtonadvisorListActionPerformed

    private void backButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonadvisorListActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonadvisorListActionPerformed

    private void paymentjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentjComboBoxActionPerformed
        if (paymentjComboBox.getSelectedItem().toString().equals("CARD")) {
              cardInfo = new CardInfo();
           
          cardInfo.setVisible(true);
            cardInfo.setDefaultCloseOperation(cardInfo.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_paymentjComboBoxActionPerformed

    private void paymentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentTableMouseClicked
        // TODO add your handling code here:
          selectedRow = paymentTable.getSelectedRow();
        String blanknumber= defTabMod.getValueAt(selectedRow, 0).toString();
       // paymentjComboBox.setVisible(true);
        
        
    }//GEN-LAST:event_paymentTableMouseClicked

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
            java.util.logging.Logger.getLogger(payDelayedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payDelayedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payDelayedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payDelayedPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new payDelayedPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel cardDetailsTitle;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JPanel delayedPaymentBackground;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane paymentPane;
    private javax.swing.JTable paymentTable;
    private javax.swing.JComboBox<String> paymentjComboBox;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
