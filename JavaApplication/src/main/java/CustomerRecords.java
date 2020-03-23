
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
 * @author xahna
 */
public class CustomerRecords extends javax.swing.JFrame {

    //Holds copy of the database during the current session
    private DefaultTableModel defTabMod;
    //holds the row number selected by the user
    private int selectedRow;
    

    public CustomerRecords() {
        initComponents();
        initCustomerRecords("select * from Customer");
        selectedRow = -1;
    }

    //returns  number bigger with 1 from the biggest current ID
    public static int nextID(String sqlString) {
        int result = 0;
        try ( Connection con = DbCon.getConnection()) {
            //find the greatest ID in the database
            PreparedStatement pst = con.prepareStatement("select max(ID) from '" + sqlString + "'");
            ResultSet rs = pst.executeQuery();//collecet the result
            //move to the first and only row frow the result
            rs.next();
            result = rs.getInt("max(ID)");//asing the result so we can return it

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++result;
    }

    //populates the customerTable table with the relevant data from tha databse
    private void initCustomerRecords(String sqlStatement) {
        //estabblish connection with the database
        try ( PreparedStatement ps = DbCon.getConnection().prepareStatement(sqlStatement);) {
            ResultSet rs = ps.executeQuery();//contains the data returned from the database quiery
            ResultSetMetaData rsmd = rs.getMetaData();
            //controls the for loop for the assigning of values in the vector
            int column = rsmd.getColumnCount();
            //initialize this form table according to the database structure
            defTabMod = (DefaultTableModel) customerTable.getModel();
            defTabMod.setRowCount(0);
            //loops over each row of the database
            while (rs.next()) {
                Vector v = new Vector();

                for (int i = 1; i <= column; i++) {
                    v.add(rs.getInt("ID"));
                    v.add(rs.getString("firstName"));
                    v.add(rs.getString("lastName"));
                    v.add(rs.getString("phoneNum"));
                    v.add(rs.getString("email"));
                    v.add(rs.getString("type"));
                    v.add(rs.getString("discountType"));
                    v.add(rs.getDouble("discountRate"));
                    v.add(rs.getString("address"));
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

        customerRecordsBackground = new javax.swing.JPanel();
        customerRecordsBlueBackground = new javax.swing.JPanel();
        customerRecordsTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        findButton = new javax.swing.JButton();
        findComboBox = new javax.swing.JComboBox<>();
        findTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        ticketButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerRecordsBackground.setBackground(new java.awt.Color(255, 255, 255));
        customerRecordsBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));
        customerRecordsBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerRecordsBackgroundMouseClicked(evt);
            }
        });

        customerRecordsBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        customerRecordsTitle.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        customerRecordsTitle.setText("CUSTOMER RECORDS");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerRecordsBlueBackgroundLayout = new javax.swing.GroupLayout(customerRecordsBlueBackground);
        customerRecordsBlueBackground.setLayout(customerRecordsBlueBackgroundLayout);
        customerRecordsBlueBackgroundLayout.setHorizontalGroup(
            customerRecordsBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerRecordsBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(customerRecordsTitle)
                .addGap(79, 79, 79)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        customerRecordsBlueBackgroundLayout.setVerticalGroup(
            customerRecordsBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerRecordsBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerRecordsBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(customerRecordsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Phone", "Email", "Type", "Discount Type", "Discount Rate", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        customerTable.setColumnSelectionAllowed(true);
        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(customerTable);
        customerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        updateButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        findButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        findButton.setText("FIND");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        findComboBox.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        findComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIND BY ID", "FIND BY NAME" }));
        findComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findComboBoxActionPerformed(evt);
            }
        });

        findTextField.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        findTextField.setText("...");
        findTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findTextFieldMouseClicked(evt);
            }
        });
        findTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findTextFieldActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        ticketButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        ticketButton.setText("TICKETS");
        ticketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerRecordsBackgroundLayout = new javax.swing.GroupLayout(customerRecordsBackground);
        customerRecordsBackground.setLayout(customerRecordsBackgroundLayout);
        customerRecordsBackgroundLayout.setHorizontalGroup(
            customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerRecordsBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerRecordsBackgroundLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(customerRecordsBackgroundLayout.createSequentialGroup()
                        .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(findComboBox, 0, 300, Short.MAX_VALUE)
                            .addComponent(findTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ticketButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(findButton, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addGap(62, 62, 62)
                        .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(82, 82, 82))
        );
        customerRecordsBackgroundLayout.setVerticalGroup(
            customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerRecordsBackgroundLayout.createSequentialGroup()
                .addComponent(customerRecordsBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerRecordsBackgroundLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(findButton)))
                    .addGroup(customerRecordsBackgroundLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(findComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(customerRecordsBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ticketButton))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerRecordsBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerRecordsBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void customerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTableMouseClicked
        //holds teh row number selected with the mouse
        selectedRow = customerTable.getSelectedRow();
        //If BookTicket object was instantiated that means 
        //we are inside CustomerRecords to choose customer.
         if(BookTicket.isInstantiated){
             //Set custID in BookTicket according to the mouse selected row ID
             BookTicket.custID = (int) defTabMod.getValueAt(selectedRow, 0);
             this.dispose();
         }

    }//GEN-LAST:event_customerTableMouseClicked
    //Update entry from the databse
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // Implements the updatate button updating the whole row chosen with the mouse.
        //By double clicking you can change the entry. After finishing click update.
        try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = null;

            pst = con.prepareStatement("update Customer set firstName = '"
                    + defTabMod.getValueAt(selectedRow, 1)
                    + "', lastName = '"
                    + defTabMod.getValueAt(selectedRow, 2)
                    + "', phoneNum = '"
                    + defTabMod.getValueAt(selectedRow, 3)
                    + "', email = '"
                    + defTabMod.getValueAt(selectedRow, 4)
                    + "', type = '"
                    + defTabMod.getValueAt(selectedRow, 5)
                    + "', discountType = '"
                    + defTabMod.getValueAt(selectedRow, 6)
                    + "', discountRate = '"
                    + defTabMod.getValueAt(selectedRow, 7)
                    + "', address = '"
                    + defTabMod.getValueAt(selectedRow, 8)
                    + "' where ID = '" + defTabMod.getValueAt(selectedRow, 0) + "'");

            pst.execute();
            initCustomerRecords("select * from Customer");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

   

//Triggers search related to the option chosen in findComboBox
    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed

        try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = null;
            if (findTextField.getText().equals("")) {//To see again all entryes from the database 
                initCustomerRecords("select * from Customer");//just clear the findTextField and press SELECT again
            } else if (findComboBox.getSelectedItem().toString().equals("FIND BY ID")) {//Triggers FIND BY ID 
                initCustomerRecords("select * from Customer where ID = '" + findTextField.getText() + "'");//or FIND BY NAME search
            } else if (findComboBox.getSelectedItem().toString().equals("FIND BY NAME")) {// taking the data from findTextField
                initCustomerRecords("select * from Customer where firstName = '" + findTextField.getText() + "'");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_findButtonActionPerformed

    private void findComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_findComboBoxActionPerformed

    private void findTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_findTextFieldActionPerformed

    private void findTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findTextFieldMouseClicked
        findTextField.setText("");//clears the textField once you click on it
    }//GEN-LAST:event_findTextFieldMouseClicked

    private void customerRecordsBackgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerRecordsBackgroundMouseClicked

    }//GEN-LAST:event_customerRecordsBackgroundMouseClicked
    //creates new row initialising the ID and leaving rest of the columns to the user
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try ( Connection con = DbCon.getConnection()) {

            PreparedStatement pst = con.prepareStatement("INSERT INTO Customer Values (?,'-','-','-','-','-','-','-','-')");
            pst.setInt(1, nextID("Customer"));//set ID column
            pst.execute();
            initCustomerRecords("select * from Customer");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void ticketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketButtonActionPerformed
        // TODO add your handling code here:
        SalesRecords s = new SalesRecords();
        s.setVisible(true);     
        s.setDefaultCloseOperation(s.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_ticketButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRecords().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel customerRecordsBackground;
    private javax.swing.JPanel customerRecordsBlueBackground;
    private javax.swing.JLabel customerRecordsTitle;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton findButton;
    private javax.swing.JComboBox<String> findComboBox;
    private javax.swing.JTextField findTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ticketButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
