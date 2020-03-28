
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
public class PersonalATSReport extends javax.swing.JFrame {

    private DefaultTableModel defTabMod;

    /**
     * Creates new form personalATSReport
     */
    public PersonalATSReport() {
        initComponents();
        initReportTable();
    }

    private void initReportTable() {
        //estabblish connection with the database
        try ( Connection con = DbCon.getConnection()) {
            Statement statement = con.createStatement();
            String s1 = "CREATE TEMPORARY TABLE IDomestic (\n"
                    + "    issuedN          VARCHAR (15),\n"
                    + "    fBase            DOUBLE (10),\n"
                    + "    fBaseUSD         DOUBLE (10),\n"
                    + "    pMethod          VARCHAR(10),\n"
                    + "    taxes            DOUBLE (10),\n"
                    + "    price            DOUBLE (10),\n"
                    + "    commission1      DOUUBLE (10),\n"
                    + "    commission2      DOUBLE(10),\n"
                    + "    notes            VARCHAR(200)\n"
                    + ");";
            String s2 = "CREATE TEMPORARY TABLE totals (\n"
                    + "    totalCommissions DOUBLE (10),\n"
                    + "    netDebit         DOUBLE (10),\n"
                    + "    totalNetAmnt     DOUBLE (10)\n"
                    + ");";
            String s3 = "INSERT INTO IDomestic (\n"
                    + "      issuedN\n"
                    + "  )\n"
                    + "  SELECT blankNumber\n"
                    + "        FROM blank\n"
                    + "       where  isSold = 1 and StaffID = 2\n"
                    + "       and ( blankNumber like '201%'\n"
                    + "       or blankNumber like '101%');";

            String s4 = "insert into IDomestic (issuedN) values ('TOTAL')";

            String s5 = "UPDATE IDomestic\n"
                    + "SET fBase = (\n"
                    + "   SELECT sum (price)\n"
                    + "     FROM t\n"
                    + "    WHERE blankNumber = issuedN\n"
                    + ");";
            String s6 = "update Idomestic set fBase=(\n"
                    + "   SELECT sum(fBase) from IDomestic )\n"
                    + "    WHERE issuedN = 'TOTAL'";
            String s7 = "    UPDATE Idomestic\n"
                    + "    SET pMethod = (\n"
                    + "       SELECT type\n"
                    + "         FROM Payment\n"
                    + "        WHERE BlankblankNumber = issuedN\n"
                    + "    );";

            String s8 = "    UPDATE IDomestic\n"
                    + "    SET taxes = (\n"
                    + "       SELECT taxes\n"
                    + "         FROM Payment\n"
                    + "        WHERE BlankblankNumber = issuedN\n"
                    + "    )";
            String s9 = "  update Idomestic set taxes =(\n"
                    + "       SELECT sum(taxes) from IDomestic )\n"
                    + "        WHERE issuedN = 'TOTAL'";
            String s10 = "update Idomestic set price =\n"
                    + "            taxes + fbase\n"
                    + "           \n"
                    + "";
            statement.addBatch(s1);
            statement.addBatch(s2);
            statement.addBatch(s3);
            statement.addBatch(s4);
            statement.addBatch(s5);
            statement.addBatch(s6);
            statement.addBatch(s7);
            statement.addBatch(s8);
            statement.addBatch(s9);
            statement.addBatch(s10);
            statement.executeBatch();
            PreparedStatement pst = con.prepareStatement("select * from Idomestic");

            ResultSet rs = pst.executeQuery();//contains the data returned from the database quiery
            ResultSetMetaData rsmd = rs.getMetaData();
            //controls the for loop for the assigning of values in the vector
            int column = rsmd.getColumnCount();
            //initialize this form table according to the database structure
            defTabMod = (DefaultTableModel) reportJTable.getModel();
            defTabMod.setRowCount(0);
            //loops over each row of the database
            while (rs.next()) {
                Vector v = new Vector();

                for (int i = 1; i <= column; i++) {
                    v.add(rs.getString("issuedN"));
                    v.add(rs.getDouble("fBase"));
                    v.add(rs.getDouble("fBaseUSD"));
                    v.add(rs.getString("pMethod"));
                    v.add(rs.getDouble("taxes"));
                    v.add(rs.getDouble("price"));
                    v.add(rs.getDouble("commission1"));
                    v.add(rs.getDouble("commission2"));
                    v.add(rs.getString("notes"));
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

        personalATSBackground = new javax.swing.JPanel();
        personalATSBlueBackground = new javax.swing.JPanel();
        personalATSTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        dateStartTextbox = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        dateFinishTextbox = new javax.swing.JTextField();
        printButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportJTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        personalATSBackground.setBackground(new java.awt.Color(255, 255, 255));
        personalATSBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        personalATSBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        personalATSTitle.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        personalATSTitle.setText("PERSONAL ATS REPORT");

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

        toLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toLabel.setText("To");

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
                "Issued Number", "Local Fare Base", "USD Fare Base", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        reportJTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(reportJTable);

        javax.swing.GroupLayout personalATSBackgroundLayout = new javax.swing.GroupLayout(personalATSBackground);
        personalATSBackground.setLayout(personalATSBackgroundLayout);
        personalATSBackgroundLayout.setHorizontalGroup(
            personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalATSBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(dateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateStartTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(toLabel)
                        .addGap(18, 18, 18)
                        .addComponent(dateFinishTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalATSBackgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        personalATSBackgroundLayout.setVerticalGroup(
            personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalATSBackgroundLayout.createSequentialGroup()
                .addComponent(personalATSBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFinishTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel)
                    .addComponent(dateStartTextbox)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addGroup(personalATSBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton)
                    .addComponent(printButton))
                .addContainerGap())
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

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

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
                new PersonalATSReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField dateFinishTextbox;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateStartTextbox;
    private javax.swing.JButton generateButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel personalATSBackground;
    private javax.swing.JPanel personalATSBlueBackground;
    private javax.swing.JLabel personalATSTitle;
    private javax.swing.JButton printButton;
    private javax.swing.JTable reportJTable;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
}
