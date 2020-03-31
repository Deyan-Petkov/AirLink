

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SalesRecords extends javax.swing.JFrame {
  //Holds copy of the database during the current session
    private DefaultTableModel defTabMod, defTabMod2;
    //holds the row number selected by the user
    private int selectedRow;
   static int customerID; 
   
   
   PreparedStatement pst= null; 
    ResultSet rs=null; 
    Connection con=null;
   
    static boolean isInstantiated;//When is false clicking on CustomerRecords table doesn't assign value to custID
    /**
     * Creates new form salesRecord
     */
    public SalesRecords() {
         isInstantiated = true;
        initComponents();
         initSalesRecords("select * from Itinerary WHERE CustomerID='"+customerID+"' ");
    }
//populates the customerTable table with the relevant data from tha databse
    private void initSalesRecords(String sqlStatement) {
        //estabblish connection with the database
        try ( PreparedStatement ps = DbCon.getConnection().prepareStatement(sqlStatement);) {
            ResultSet rs = ps.executeQuery();//contains the data returned from the database quiery
            ResultSetMetaData rsmd = rs.getMetaData();
            //controls the for loop for the assigning of values in the vector
            int column = rsmd.getColumnCount();
            //initialize this form table according to the database structure
            defTabMod = (DefaultTableModel) ticketsTable.getModel();
            defTabMod.setRowCount(0);
            //loops over each row of the database
            while (rs.next()) {
                Vector v = new Vector();

                for (int i = 1; i <= column; i++) {
                    v.add(rs.getInt("ID"));
                    v.add(rs.getString("flightDeparture"));
                    v.add(rs.getString("flightDestination"));
                    v.add(rs.getString("flightArrivalTime"));
                    v.add(rs.getString("flightDepartureTime"));
                    v.add(rs.getString("FlightNum"));
                    v.add(rs.getString("BlankblankNumber"));
                    v.add(rs.getDouble("CustomerID"));
                  
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

        salesRecordBackground = new javax.swing.JPanel();
        paymentPane = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        ticketsPane = new javax.swing.JScrollPane();
        ticketsTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        salesBlueBackground = new javax.swing.JPanel();
        salesRecordTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        salesRecordBackground.setBackground(new java.awt.Color(255, 255, 255));
        salesRecordBackground.setPreferredSize(new java.awt.Dimension(1100, 800));

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
        paymentTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        ticketsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Flight Departure", "Flight Destination", "Arrival Time", "Departure Time", "Flight Number", "Blank Number", "Customer ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ticketsTable.setColumnSelectionAllowed(true);
        ticketsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ticketsTableMouseClicked(evt);
            }
        });
        ticketsPane.setViewportView(ticketsTable);
        ticketsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton1.setText("EDIT SALE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout salesRecordBackgroundLayout = new javax.swing.GroupLayout(salesRecordBackground);
        salesRecordBackground.setLayout(salesRecordBackgroundLayout);
        salesRecordBackgroundLayout.setHorizontalGroup(
            salesRecordBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesRecordBackgroundLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(salesRecordBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ticketsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
            .addGroup(salesRecordBackgroundLayout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        salesRecordBackgroundLayout.setVerticalGroup(
            salesRecordBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesRecordBackgroundLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(ticketsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(paymentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        salesBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        salesRecordTitle.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        salesRecordTitle.setText("SALES RECORD");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout salesBlueBackgroundLayout = new javax.swing.GroupLayout(salesBlueBackground);
        salesBlueBackground.setLayout(salesBlueBackgroundLayout);
        salesBlueBackgroundLayout.setHorizontalGroup(
            salesBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salesBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salesRecordTitle)
                .addGap(158, 158, 158)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        salesBlueBackgroundLayout.setVerticalGroup(
            salesBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesBlueBackgroundLayout.createSequentialGroup()
                .addGroup(salesBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(salesBlueBackgroundLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(salesRecordTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(salesBlueBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salesRecordBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(salesBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(salesBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(salesRecordBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ticketsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketsTableMouseClicked
         selectedRow = ticketsTable.getSelectedRow();
        String blanknumber= defTabMod.getValueAt(selectedRow, 6).toString();
        
            try ( PreparedStatement ps = DbCon.getConnection().prepareStatement("SELECT *  FROM Payment WHERE BlankblankNumber ='"+blanknumber+"' ");) {
            ResultSet rs = ps.executeQuery();//contains the data returned from the database quiery
            ResultSetMetaData rsmd = rs.getMetaData();
            //controls the for loop for the assigning of values in the vector
            int column = rsmd.getColumnCount();
            //initialize this form table according to the database structure
            defTabMod2 = (DefaultTableModel)   paymentTable.getModel();
            defTabMod2.setRowCount(0);
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
                //v.add(rs.getString("expDate"));
                         
                    
                    
                }//inserts single row collected data from the databse into this form table
                defTabMod2.addRow(v);
            }

        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, e);
        }    
    }//GEN-LAST:event_ticketsTableMouseClicked

    
    
    
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void paymentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
             // Implements the updatate button updating the whole row chosen with the mouse.
        //By double clicking you can change the entry. After finishing click update.
        try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = null;
          
            pst = con.prepareStatement("update Itinerary set flightDeparture = '"
                    + defTabMod.getValueAt(selectedRow, 1)
                    + "', flightDestination = '"
                    + defTabMod.getValueAt(selectedRow, 2)
                    + "', flightArrivalTime = '"
                    + defTabMod.getValueAt(selectedRow, 3)
                    + "', flightDepartureTime = '"
                    + defTabMod.getValueAt(selectedRow, 4)
                    + "', FlightNum = '"
                    + defTabMod.getValueAt(selectedRow, 5)
                    + "', BlankblankNumber = '"
                    + defTabMod.getValueAt(selectedRow, 6) 
                    +  "', CustomerID = '"
                    + defTabMod.getValueAt(selectedRow, 7)
                   
                    + "' where ID = '" + defTabMod.getValueAt(selectedRow, 0) + "'");

            pst.execute();
            initSalesRecords("select * from Itinerary WHERE CustomerID='"+customerID+"'");

             PreparedStatement pst2 = null; 
            
              pst2 = con.prepareStatement("update Payment set delayed= '"
                    + defTabMod2.getValueAt(selectedRow, 1)
                    + "', exchangeRate = '"
                    + defTabMod2.getValueAt(selectedRow, 2)
                    + "',type = '"
                    + defTabMod2.getValueAt(selectedRow, 3)
                    + "', date = '"
                    + defTabMod2.getValueAt(selectedRow, 4)
                    + "', taxes = '"
                    + defTabMod2.getValueAt(selectedRow, 5)
                    + "', isRefunded = '"
                    + defTabMod2.getValueAt(selectedRow, 6) 
                    +  "', name = '"
                    + defTabMod2.getValueAt(selectedRow, 7)
                   
                    + "' where BlankblankNumber = '" + defTabMod2.getValueAt(selectedRow, 0) + "'");

            pst2.execute();
            
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                            

   

                                          

                                              

                                             

    private void customerRecordsBackgroundMouseClicked(java.awt.event.MouseEvent evt) {                                                       

                                                         
    //creates new row initialising the ID and leaving rest of the columns to the user
                                              
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SalesRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane paymentPane;
    private javax.swing.JTable paymentTable;
    private javax.swing.JPanel salesBlueBackground;
    private javax.swing.JPanel salesRecordBackground;
    private javax.swing.JLabel salesRecordTitle;
    private javax.swing.JScrollPane ticketsPane;
    private javax.swing.JTable ticketsTable;
    // End of variables declaration//GEN-END:variables
}
