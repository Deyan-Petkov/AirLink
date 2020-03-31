
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
 * @author dhruv
 */
public class GlobalInterlineReport extends javax.swing.JFrame {

    //Tables which will contain the result from the database queries
    private DefaultTableModel reportDefTabMod, totalsDeffTabMod;
    private String fromDate, toDate;//dates for the desired period of time for the report
    private int staffId;

    /**
     * Creates new form globalInterlineReport
     */
    public GlobalInterlineReport() {
        initComponents();
    }

    private void initReportTable() {
        //if date fields are empty prompt a message and don't proceed any further 
        if (fromDate.length() < 1 | toDate.length() < 1) {
            JOptionPane.showMessageDialog(null, "Please fill in From Date and To Date fields");
        } else if (!PersonalATSReport.isValidDate(fromDate) | !PersonalATSReport.isValidDate(toDate)) {//check if the dates are entered in the correct format
            System.out.println("is valid fromdate : " + PersonalATSReport.isValidDate(fromDate));
            System.out.println("is valid to date : " + PersonalATSReport.isValidDate(toDate));

            JOptionPane.showMessageDialog(null, "Date fields must contain date in format \"yyyy-mm-dd\"");
        } else {
            //estabblish connection with the database
            try ( Connection con = DbCon.getConnection()) {
                Statement statement = con.createStatement();

                statement.addBatch("CREATE TEMPORARY table GInterline(\n" 
                        +"n integer primary key autoincrement,\n" 
                        +"agent integer (3),\n" 
                        +"sold integer(3),\n" 
                        +"fBase double(10),\n" 
                        +"taxes double (10),\n" 
                        +"otherTaxes double (10),\n" 
                        +"cashP double(10),\n" 
                        +"cardP double(10),\n" 
                        +"amount double (10),\n" 
                        +"commission double(10));");
                /*select all id's which sold domestic blanks during the selected period*/
                statement.addBatch("INSERT INTO GInterline(agent) SELECT DISTINCT staffID FROM glbrepI WHERE isSold = 1\n" 
                        +"AND date >= '2020-03-23'\n" 
                        +"AND date <= '2020-03-28'\n" 
                        +"AND blankNumber LIKE '444%' OR blankNumber LIKE '420%'\n" 
                        +"ORDER BY staffID;");
                /*How many blanks each advisor have sold*/
                statement.addBatch("UPDATE GInterline SET sold = (\n" 
                        +"SELECT count(DISTINCT blankNumber) FROM glbrepI WHERE staffID = agent);");
                /*Total price for sold domestic tickets per advisor*/
                statement.addBatch("UPDATE GInterline SET fBase = (\n" 
                        +"SELECT sum(price)\n" 
                        +"FROM glbrepI\n" 
                        +"WHERE staffID = agent);");
                /*Set taxes payed by customer for all blanks sold by advisor*/
                statement.addBatch("UPDATE GInterline SET taxes = (\n" 
                        +"SELECT txs FROM (\n" 
                        +"SELECT sum(taxes) AS txs,staffID AS stf FROM\n" 
                        +"(SELECT DISTINCT blankNumber,taxes,staffID FROM glbrepI) WHERE staffID IN\n" 
                        +"(SELECT agent FROM GInterline) GROUP BY staffID) WHERE stf = agent);");
                /*Set other taxes payed by customer for all blanks sold by advisor*/
                statement.addBatch("UPDATE GInterline SET otherTaxes = (\n" 
                        +"SELECT otxs FROM (\n" 
                        +"SELECT sum(otherTaxes) AS otxs,staffID AS stf FROM\n" 
                        +"(SELECT DISTINCT blankNumber,otherTaxes,staffID FROM glbrepI) WHERE staffID IN\n" 
                        +"(SELECT agent FROM GInterline) GROUP BY staffID) WHERE stf = agent);");
                /*set total amount payed*/
                statement.addBatch("UPDATE GInterline SET amount = (fBase + taxes + otherTaxes);");
                /*Set commission for each advisor*/
                statement.addBatch("UPDATE GInterline SET commission =\n" 
                        +"(SELECT sum((price*cr)-price) FROM\n" 
                        +"(SELECT DISTINCT blankNumber AS bn, staffID AS id, price, commissionrate AS cr FROM glbrepI) WHERE id = agent );");
                /*set cash payment amount*/
                statement.addBatch("UPDATE GInterline SET cashP =(\n" 
                        +"SELECT sum(price + taxes + otherTaxes) FROM\n" 
                        +"(SELECT sum(price) AS price FROM glbrepI WHERE staffID = agent AND type IS 'cash'),\n" 
                        +"(SELECT sum(taxes) AS taxes FROM\n" 
                        +"(SELECT DISTINCT blankNumber, taxes FROM glbrepI WHERE staffID = agent AND type IS 'cash')),\n"
                        +"(SELECT sum(otherTaxes) AS otherTaxes FROM\n" 
                        +"(SELECT DISTINCT blankNumber, otherTaxes FROM glbrepI WHERE staffID = agent AND type IS 'cash')));");
                /*set card payment amount*/
                statement.addBatch("UPDATE GInterline SET cardP =(\n" 
                        +"SELECT sum(price + taxes + otherTaxes) FROM\n" 
                        +"(SELECT sum(price) AS price FROM glbrepI WHERE staffID = agent AND type IS NOT 'cash'),\n" 
                        +"(SELECT sum(taxes) AS taxes FROM\n" 
                        +"(SELECT DISTINCT blankNumber, taxes FROM glbrepI WHERE staffID = agent AND type IS NOT 'cash')),\n" 
                        +"(SELECT sum(otherTaxes) AS otherTaxes FROM\n" 
                        +"(SELECT DISTINCT blankNumber, otherTaxes FROM glbrepI WHERE staffID = agent AND type IS NOT 'cash')));");
                
                /*Insert row total */
                statement.addBatch("INSERT INTO GInterline (agent) values('TOTAL');");
                statement.addBatch("UPDATE GInterline SET sold = (SELECT sum(sold) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET fBase = (SELECT sum(fBase) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET taxes = (SELECT sum(taxes) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET otherTaxes = (SELECT sum(otherTaxes) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET cashP = (SELECT sum(cashP) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET cardP = (SELECT sum(cardP) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET amount = (SELECT sum(amount) FROM GInterline) WHERE agent = 'TOTAL';");
                statement.addBatch("UPDATE GInterline SET commission = (SELECT sum(commission) FROM GInterline) WHERE agent = 'TOTAL';");

                statement.executeBatch();

                PreparedStatement pst = con.prepareStatement("SELECT * FROM GInterline");

                ResultSet rs = pst.executeQuery();//contains the data returned from the database quiery
                ResultSetMetaData rsmd = rs.getMetaData();
                //controls the for loop for the assigning of values in the vector
                int column = rsmd.getColumnCount();
                //initialize this form table according to the database structure
                reportDefTabMod = (DefaultTableModel) reportjTable.getModel();
                reportDefTabMod.setRowCount(0);
                //loops over each row of the database
                while (rs.next()) {
                    Vector v;
                    v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getString("n"));
                        v.add(rs.getString("agent"));
                        v.add(rs.getInt("sold"));
                        v.add(rs.getDouble("fBase"));
                        v.add(rs.getDouble("taxes"));
                        v.add(rs.getDouble("otherTaxes"));
                        v.add(rs.getDouble("cashP"));
                        v.add(rs.getDouble("cardP"));
                        v.add(rs.getDouble("amount"));
                        v.add(new BigDecimal(rs.getDouble("commission")).setScale(2, RoundingMode.HALF_UP));
                    }//inserts single row collected data from the databse into this form table
                    reportDefTabMod.addRow(v);
                }
                
                 /*We use this table to show only two values but otherways we'll
            have two empty columns from Idomestic and only those two
            values at the bottom*/
                statement.addBatch("CREATE TEMPORARY TABLE totals  (\n"
                        + "    netDebit         DOUBLE (10),\n"
                        + "    totalNetAmnt     DOUBLE (10)\n"
                        + ");");

                /*Calculate and insert netDebit and total net amount*/
                statement.addBatch("insert into totals (netDebit,totalNetAmnt) values ((select fBase - commission from GInterline where agent = 'TOTAL'),\n"
                        + "(select (amount - commission) from GInterline where agent = 'TOTAL'));");
                statement.executeBatch();

                pst = con.prepareStatement("SELECT * FROM totals");
                rs = pst.executeQuery();
                totalsDeffTabMod = (DefaultTableModel) totalsjTable.getModel();//create model
                rs.next();//go to first row of the reslut
                totalsDeffTabMod.addRow(new Object[2]);
                //pupulate first and second column from totals table 
                totalsDeffTabMod.setValueAt(new BigDecimal(rs.getDouble("netDebit")).setScale(2, RoundingMode.HALF_UP), 0, 0);
                totalsDeffTabMod.setValueAt(new BigDecimal(rs.getDouble("totalNetAmnt")).setScale(2, RoundingMode.HALF_UP), 0, 1);


            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GlobalInterlineReport.class.getName()).log(Level.SEVERE, null, ex);
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

        globalInterlineReportBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        globalInterlineTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        fromDateTextbox = new javax.swing.JTextField();
        toDateTextbox = new javax.swing.JTextField();
        printButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportjTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        totalsjTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        globalInterlineReportBackground.setBackground(new java.awt.Color(255, 255, 255));
        globalInterlineReportBackground.setPreferredSize(new java.awt.Dimension(1200, 900));

        bluePanel.setBackground(new java.awt.Color(120, 240, 240));

        globalInterlineTitle.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        globalInterlineTitle.setText("    GLOBAL INTERLINE REPORT");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bluePanelLayout = new javax.swing.GroupLayout(bluePanel);
        bluePanel.setLayout(bluePanelLayout);
        bluePanelLayout.setHorizontalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bluePanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(globalInterlineTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bluePanelLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(globalInterlineTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dateLabel.setText("Date:");

        toLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toLabel.setText("To:");

        fromDateTextbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fromDateTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromDateTextboxActionPerformed(evt);
            }
        });

        toDateTextbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        printButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        printButton.setText("PRINT");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        generateButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        generateButton.setText("GENERATE");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        reportjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Agent ID", "Sold", "Fare Base", "Taxes", "Other Taxes", "Cash Payment", "Card Payment", "Total Amount", "Commission"
            }
        ));
        jScrollPane1.setViewportView(reportjTable);

        totalsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Net Debit", "Total Net"
            }
        ));
        jScrollPane2.setViewportView(totalsjTable);

        javax.swing.GroupLayout globalInterlineReportBackgroundLayout = new javax.swing.GroupLayout(globalInterlineReportBackground);
        globalInterlineReportBackground.setLayout(globalInterlineReportBackgroundLayout);
        globalInterlineReportBackgroundLayout.setHorizontalGroup(
            globalInterlineReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(globalInterlineReportBackgroundLayout.createSequentialGroup()
                .addGroup(globalInterlineReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalInterlineReportBackgroundLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(dateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fromDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(toLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(globalInterlineReportBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(globalInterlineReportBackgroundLayout.createSequentialGroup()
                        .addGap(830, 830, 830)
                        .addGroup(globalInterlineReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(globalInterlineReportBackgroundLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        globalInterlineReportBackgroundLayout.setVerticalGroup(
            globalInterlineReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(globalInterlineReportBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(globalInterlineReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(fromDateTextbox)
                    .addComponent(toLabel)
                    .addComponent(toDateTextbox))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(globalInterlineReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton)
                    .addComponent(printButton))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalInterlineReportBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalInterlineReportBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        fromDate = fromDateTextbox.getText();
        toDate = toDateTextbox.getText();
        initReportTable();

    }//GEN-LAST:event_generateButtonActionPerformed

    private void fromDateTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromDateTextboxActionPerformed

    }//GEN-LAST:event_fromDateTextboxActionPerformed

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
            java.util.logging.Logger.getLogger(GlobalInterlineReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlobalInterlineReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlobalInterlineReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlobalInterlineReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GlobalInterlineReport().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField fromDateTextbox;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel globalInterlineReportBackground;
    private javax.swing.JLabel globalInterlineTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton printButton;
    private javax.swing.JTable reportjTable;
    private javax.swing.JTextField toDateTextbox;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTable totalsjTable;
    // End of variables declaration//GEN-END:variables
}
