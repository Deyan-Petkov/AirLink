
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author xahna
 */
public class PersonalInterlineReport extends javax.swing.JFrame {

    //Tables which will contain the result from the database queries
    private DefaultTableModel reportDefTabMod, totalsDeffTabMod;
    private String fromDate, toDate;//dates for the desired period of time for the report
    private int staffId;

    /**
     * Creates new form personalATSReport
     */
    public PersonalInterlineReport() {
        initComponents();
    }

    //check if the input dates are in the correct format
    private boolean isValidDate(String date) {

        if (date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
            return true;
        }
        return false;
    }
    

    //Initialises the report
    private void initReportTable() {
        //if date fields are empty prompt a message and don't proceed any further 
        if (fromDate.length() < 1 | toDate.length() < 1  ) {
            JOptionPane.showMessageDialog(null, "Please fill in From Date and To Date fields");
        } else if (!isValidDate(fromDate) | !isValidDate(toDate)) {//check if the dates are entered in the correct format
            JOptionPane.showMessageDialog(null, "Date fields must contain date in format \"yyyy-mm-dd\"");
        } else {
            //estabblish connection with the database
            try ( Connection con = DbCon.getConnection()) {
                Statement statement = con.createStatement();
                /*Create table PInterline which holds the actual report*/
                statement.addBatch("CREATE TEMPORARY TABLE PInterline (\n"
                        + "    n               INTEGER primary key  autoincrement,\n"
                        + "    issuedN          INTEGER (15),\n"
                        + "    fBaseUSD         DOUBLE (10),\n"
                        + "    fBaseUSD/fBase   DOUBLE (10),\n"
                        + "    fBase            DOUBLE (10),\n"
                        + "    LZ               DOUBLE (10),\n"
                        + "    OTHS             DOUBLE (10),\n"
                        + "    pMethod          VARCHAR(10),\n"
                        + "    price            DOUBLE (10),\n"
                        + "    commission15%    DOUBLE (10),\n"
                        + "    commission12%    DOUBLE (10),\n"
                        + "    commission10%    DOUBLE (10),\n"
                        + "    commission9%     DOUBLE (10),\n"
                        + "    commission8.5%   DOUBLE (10),\n"
                        + "    commission7%     DOUBLE (10),\n"
                        + "    nonAssessAmounts DOUBLE (10),\n"
                        + ");");
                /*List all domestic blanks sold for the given period*/
                //TODO  substitude staffId
                statement.addBatch("INSERT INTO PInterline (\n"
                        + "      issuedN\n"
                        + "  )\n"
                        + "  SELECT DISTINCT blankNumber\n"
                        + "    FROM t1\n"
                        + "   WHERE StaffID = 2 AND \n"
                        + "         date >= '" + fromDate + "' AND \n"
                        + "         date <= '" + toDate + "' AND \n"
                        + "         (blankNumber LIKE '444%' OR \n"
                        + "          blankNumber LIKE '440%' OR \n"
                        + "          blankNumber LIKE '420%');");
                /*List the price for each blank */
                statement.addBatch("UPDATE PInterline\n"
                        + "SET fBase = (\n"
                        + "   SELECT sum (price)\n"
                        + "     FROM t1\n"
                        + "    WHERE blankNumber = issuedN\n"
                        + ");");
                /*List pMethod*/
                statement.addBatch("   UPDATE PInterline\n"
                        + "    SET pMethod = (\n"
                        + "       SELECT type\n"
                        + "         FROM Payment\n"
                        + "        WHERE BlankblankNumber = issuedN\n"
                        + "    );");

                /*set to "card" instead of the actual card number*/
                statement.addBatch("UPDATE PInterline\n"
                        + "        SET pMethod =\n"
                        + "           'card' where pmethod is not 'cash'");
                /*List LZ for each blank*/
                statement.addBatch(" UPDATE PInterline\n"
                        + "    SET LZ = (\n"
                        + "       SELECT LZ\n"
                        + "         FROM Taxes\n"
                        + "        WHERE BlankblankNumber = issuedN\n"
                        + "    )");
                /*List OTHS for each blank*/
                statement.addBatch("UPDATE PInterline\n"
                        + "     SET OTHS = (\n"
                        + "         SELECT OTHS\n"
                        + "         FROM Taxes\n"
                        + "         WHERE BlankblankNumber = issuedN\n"
                        + "     )");
                /*List price with taxes */
                statement.addBatch("  update PInterline set price =\n"
                        + "            LZ + OTHS + fbase");
                /*List the fare price in USD*/
                statement.addBatch("UPDATE PInterline\n"
                        + "               SET fbaseUSD = (\n"
                        + "                SELECT fbase * (\n"
                        + "                     SELECT rate\n"
                        + "                     FROM exchangeRates\n"
                        + "                     WHERE date IN (\n"
                        + "                         SELECT max(date)\n"
                        + "                         FROM exchangeRates\n"
                        + "                              )\n"
                        + "                         )\n"
                        + "                   );");
                /*List commissions charge rate *//*edit to implement each precentage*/
                statement.addBatch("update PInterline set commissionProc =(\n"
                        + "   SELECT  commissionRate\n"
                        + "     FROM Payment\n"
                        + "    WHERE BlankblankNumber = issuedN)");
                /*Add "total" row*/
                statement.addBatch("  insert into PInterline (issuedN) values ('TOTAL')");
                /*Set total for fBase*/
                statement.addBatch(" update PInterline set fBase=(\n"
                        + "               SELECT sum(fBase) from PInterline )\n"
                        + "                WHERE issuedN = 'TOTAL'");
                /*add new total for fBaseUSD/fBase*/
                
                /*Set total for fBaseUSD*/
                statement.addBatch("update PInterline set fBaseUSD=(\n"
                        + "                                   SELECT sum(fBaseUSD) from PInterline )\n"
                        + "                                    WHERE issuedN = 'TOTAL'");

                /*Total taxes*//*edit by making it LZ and OTHS*/
                statement.addBatch("update PInterline set taxes =(\n"
                        + "                   SELECT sum(taxes) from Interline )\n"
                        + "                    WHERE issuedN = 'TOTAL'");
                /*List total price*/
                statement.addBatch("update PInterline set price =(\n"
                        + "                       SELECT sum(price) from PInterline )\n"
                        + "                        WHERE issuedN = 'TOTAL'");
                /*List total commission*/
                statement.addBatch("update PInterline set commission =(\n"
                        + "             SELECT sum(commission) from PInterline )\n"
                        + "              WHERE issuedN = 'TOTAL'");

                statement.executeBatch();

                PreparedStatement pst = con.prepareStatement("select * from PInterlne");

                ResultSet rs = pst.executeQuery();//contains the data returned from the database quiery
                ResultSetMetaData rsmd = rs.getMetaData();
                //controls the for loop for the assigning of values in the vector
                int column = rsmd.getColumnCount();
                //initialize this form table according to the database structure
                reportDefTabMod = (DefaultTableModel) reportJTable.getModel();
                reportDefTabMod.setRowCount(0);
                //loops over each row of the database
                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getString("n"));
                        v.add(rs.getString("issuedN"));
                        v.add(new BigDecimal(rs.getDouble("fBaseUSD")).setScale(4, RoundingMode.HALF_UP));
                        v.add(new BigDecimal(rs.getDouble("fBaseUSD/fBase")).setScale(4, RoundingMode.HALF_UP));
                        v.add(rs.getDouble("fBase"));
                        v.add(rs.getString("pMethod"));
                        v.add(rs.getDouble("LZ"));
                        v.add(rs.getDouble("OTHS"));
                        v.add(rs.getDouble("price"));
                        v.add(rs.getDouble("commission15%"));
                        v.add(rs.getDouble("commission12%"));
                        v.add(rs.getDouble("commission10%"));
                        v.add(rs.getDouble("commission9%"));
                        v.add(rs.getDouble("commission8.5%"));
                        v.add(rs.getDouble("commission7%"));
                        v.add(rs.getDouble("nonAssessAmounts"));
                    }//inserts single row collected data from the databse into this form table
                    reportDefTabMod.addRow(v);
                }

                /*We use this table to show only two values but otherways we'll
            have two empty columns from PInterline and only those two
            values at the bottom*/
                statement.addBatch("CREATE TEMPORARY TABLE totals  (\n"
                        + "    netDebit         DOUBLE (10),\n"
                        + "    totalNetAmnt     DOUBLE (10)\n"
                        + ");");

                /*Calculate and insert netDebit and total net amount*/
                statement.addBatch("insert into totals (netDebit,totalNetAmnt) values ((select fBase - commission from PInterline where issuedN = 'TOTAL'),\n"
                        + "                    (select (price - commission) from PInterline where issuedN = 'TOTAL'))");
                statement.executeBatch();

                pst = con.prepareStatement("select * from totals");
                rs = pst.executeQuery();
                totalsDeffTabMod = (DefaultTableModel) totalsJTable.getModel();//create model
                rs.next();//go to first row of the reslut
                totalsDeffTabMod.addRow(new Object[2]);
                //pupulate first and second column from totals table 
                totalsDeffTabMod.setValueAt(new BigDecimal(rs.getDouble("netDebit")).setScale(2, RoundingMode.HALF_UP), 0, 0);
                totalsDeffTabMod.setValueAt(new BigDecimal(rs.getDouble("totalNetAmnt")).setScale(2, RoundingMode.HALF_UP), 0, 1);

            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(CustomerRecords.class.getName()).log(Level.SEVERE, null, e);
            }
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

        personalATSBackground = new javax.swing.JPanel();
        personalATSBlueBackground = new javax.swing.JPanel();
        personalATSTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        fromDateTextbox = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        toDateTextbox = new javax.swing.JTextField();
        printButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        totalsJTable = new javax.swing.JTable();
        IDjTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        personalATSBackground.setBackground(new java.awt.Color(255, 255, 255));
        personalATSBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        personalATSBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        personalATSTitle.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        personalATSTitle.setText("PERSONAL INTERLINE REPORT");

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

        generateButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        generateButton.setText("GENERATE");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dateLabel.setText("Date:");

        fromDateTextbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        toLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toLabel.setText("To:");

        toDateTextbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        printButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        printButton.setText("PRINT");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        reportJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Issued Number", "USD", "USD/Local Currency", "Local Currency", "LZ", "OTHS", "Payment Method", "Price", "Commision 15%", "Commision 12%", "Commision 10%", "Commision 9%", "Commision 8.5%", "Commision 7%", "Non Assessed Amounts"
            }
        ));
        reportJTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(reportJTable);
        if (reportJTable.getColumnModel().getColumnCount() > 0) {
            reportJTable.getColumnModel().getColumn(5).setResizable(false);
            reportJTable.getColumnModel().getColumn(6).setResizable(false);
        }

        totalsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Net Debit", "Total Net"
            }
        ));
        jScrollPane2.setViewportView(totalsJTable);

        IDjTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        IDjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDjTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID");

        javax.swing.GroupLayout personalATSBackgroundLayout = new javax.swing.GroupLayout(personalATSBackground);
        personalATSBackground.setLayout(personalATSBackgroundLayout);
        personalATSBackgroundLayout.setHorizontalGroup(
            personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalATSBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalATSBackgroundLayout.createSequentialGroup()
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                        .addGap(814, 814, 814)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(dateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fromDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(toLabel)
                        .addGap(18, 18, 18)
                        .addComponent(toDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        personalATSBackgroundLayout.setVerticalGroup(
            personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                .addComponent(personalATSBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel)
                    .addComponent(fromDateTextbox)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton)
                    .addComponent(printButton))
                .addGap(105, 105, 105))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalATSBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(personalATSBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        fromDate = fromDateTextbox.getText();
        toDate = toDateTextbox.getText();
       // staffId = Integer.valueOf(IDjTextField.getText());
        initReportTable();
    }//GEN-LAST:event_generateButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void IDjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDjTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(PersonalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonalInterlineReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDjTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField fromDateTextbox;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel personalATSBackground;
    private javax.swing.JPanel personalATSBlueBackground;
    private javax.swing.JLabel personalATSTitle;
    private javax.swing.JButton printButton;
    private javax.swing.JTable reportJTable;
    private javax.swing.JTextField toDateTextbox;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTable totalsJTable;
    // End of variables declaration//GEN-END:variables
}
