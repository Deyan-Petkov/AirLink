
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.awt.print.*;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Deyan
 */
public class GlobalATSReport extends javax.swing.JFrame {

    //Tables which will contain the result from the database queries
    private DefaultTableModel reportDefTabMod, totalsDeffTabMod;
    private String fromDate, toDate;//dates for the desired period of time for the report

    /**
     * Creates new form globalATSReport
     */
    public GlobalATSReport() {
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

                statement.addBatch("create temporary table gsr(\n"
                        + "n integer primary key autoincrement,\n"
                        + "agent integer (3),\n"
                        + "sold integer(3),\n"
                        + "fBase double(10),\n"
                        + "fBaseUSD double(10),\n"
                        + "taxes double (10),\n"
                        + "cashP double(10),\n"
                        + "cardP double(10),\n"
                        + "amount double (10),\n"
                        + "commission double(10)\n"
                        + ")");

                statement.addBatch("create view if not exists t as\n"
                        + "select Blank.blankNumber, Blank.isSold, Blank.StaffID,Blank.dateReceived,\n"
                        + "Itinerary.flightDeparture,Itinerary.flightDestination,Itinerary.flightArrivalTime,Itinerary.flightDepartureTime,Itinerary.FlightNum, Itinerary.CustomerID,Itinerary.ID,\n"
                        + "Payment.date,Payment.exchangeRate,Payment.expDate,Payment.isRefunded, Payment.taxes, Payment.otherTaxes, Payment.type,Payment.commissionRate,\n"
                        + "Flights.number,Flights.price, Flights.arrTime, Flights.depTime,\n"
                        + "commission.rate\n"
                        + "from Blank\n"
                        + "left join Itinerary on Blank.blankNumber = itinerary.BlankblankNumber\n"
                        + "left join Payment on Blank.blankNumber = Payment.BlankblankNumber\n"
                        + "left join Flights on Itinerary.FlightNum = Flights.number\n"
                        + "left join commission on Payment.date = commission.date");

                //create temporary veiw populated with data according to the current session
                statement.addBatch("create temp view if not exists glbrep as\n"
                        + "select * from t  WHERE (date >= '" + fromDate + "' AND\n"
                        + "  date <= '" + toDate + "') AND\n"
                        + "  (blankNumber LIKE '201%' OR\n"
                        + "   blankNumber LIKE '101%')");

                /*select all id's which sold domestic blanks during the selected period*/
                statement.addBatch("/*select all id's which sold domestic blanks during the selected period*/\n"
                        + "insert into gsr(agent) select distinct staffId from t where isSold = 1\n"
                        + "and (date >= '" + fromDate + "'\n"
                        + "and date <= '" + toDate + "')\n"
                        + "and (blankNumber like '101%' or blankNumber like '201%')\n"
                        + "order by staffID");
                /*How many blanks each advisor have sold*/
                statement.addBatch("update gsr set sold = (\n"
                        + "select count(distinct blankNumber) from glbrep where staffID = agent\n"
                        + ")");
                /*Total price for sold domestic tickets per advisor*/
                statement.addBatch("update gsr set fbase = (\n"
                        + "SELECT sum(price)\n"
                        + "FROM glbrep\n"
                        + "where StaffId = agent\n"
                        + ")");
                /*List the fare price in USD */
                statement.addBatch("UPDATE gsr\n"
                        + "   SET fbaseUSD = (\n"
                        + "    SELECT fbase * (\n"
                        + "         SELECT rate\n"
                        + "         FROM exchangeRates\n"
                        + "         WHERE date IN (\n"
                        + "             SELECT max(date)\n"
                        + "             FROM exchangeRates\n"
                        + "                  )\n"
                        + "             )\n"
                        + "       );");
                /*Set taxes payed by customer for all blanks sold by advisor*/
                statement.addBatch(" UPDATE gsr SET taxes = (\n"
                        + "  SELECT txs FROM (\n"
                        + "     SELECT sum(taxes) AS txs,staffID AS stf FROM\n"
                        + "         (SELECT DISTINCT blankNumber,taxes,staffId FROM glbrep) WHERE staffid IN\n"
                        + "             (SELECT agent FROM gsr) GROUP BY staffID) WHERE stf = agent);\n"
                        + "");
                /*set total amount payed*/
                statement.addBatch(" UPDATE gsr SET amount = (fbase + taxes)");
                /*Set commission for each advisor*/
                statement.addBatch(" update gsr set commission =\n"
                        + "(select sum((price*cr)-price) from\n"
                        + "(select distinct blankNumber as bn, staffID as id, price, commissionrate as cr from glbrep) where id = agent )\n"
                        + "");
                /*set cash payment amount*/
                statement.addBatch("update gsr set cashP =(\n"
                        + "select sum(price + taxes) from\n"
                        + "(select  sum(price) as price from glbrep where staffID = agent and type = 'cash'),\n"
                        + "(select sum (taxes) as taxes from\n"
                        + "(select distinct blankNumber, taxes from glbrep where staffID = agent and type = 'cash'))\n"
                        + ")");
                /*set card payment amount*/
                statement.addBatch("update gsr set cardP =(\n"
                        + "select sum(price + taxes) from\n"
                        + "(select  sum(price) as price from glbrep where staffID = agent and type is not 'cash'),\n"
                        + "(select sum (taxes) as taxes from\n"
                        + "(select distinct blankNumber, taxes from glbrep where staffID = agent and type is not 'cash'))\n"
                        + ")");

                /*Insert row total */
                statement.addBatch("insert into gsr (agent) values('TOTAL')");
                statement.addBatch("update gsr set sold = (select sum(sold) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set fbase = (select sum(fbase) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set fbaseUSD = (select sum(fbaseUSD) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set taxes = (select sum(taxes) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set cashP = (select sum(cashP) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set cardP = (select sum(cardP) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set amount = (select sum(amount) from gsr) where agent = 'TOTAL'");
                statement.addBatch("update gsr set commission = (select sum(commission) from gsr) where agent = 'TOTAL'");

                statement.executeBatch();

                PreparedStatement pst = con.prepareStatement("select * from gsr");

                ResultSet rs = pst.executeQuery();//contains the data returned from the database quiery
                ResultSetMetaData rsmd = rs.getMetaData();
                //controls the for loop for the assigning of values in the vector
                int column = rsmd.getColumnCount();
                //initialize this form table according to the database structure
                reportDefTabMod = (DefaultTableModel) reportjTable.getModel();
                reportDefTabMod.setRowCount(0);
                //loops over each row of the database
                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getString("n"));
                        v.add(rs.getString("agent"));
                        v.add(rs.getInt("sold"));
                        v.add(rs.getDouble("fBase"));
                        v.add(new BigDecimal(rs.getDouble("fBaseUSD")).setScale(4, RoundingMode.HALF_UP));
                        v.add(rs.getDouble("taxes"));
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
                statement.addBatch("insert into totals (netDebit,totalNetAmnt) values ((select fBase - commission from gsr where agent = 'TOTAL'),\n"
                        + " (select (amount - commission) from gsr where agent = 'TOTAL'))\n"
                        + "");
                statement.executeBatch();

                pst = con.prepareStatement("select * from totals");
                rs = pst.executeQuery();
                totalsDeffTabMod = (DefaultTableModel) totalsjTable.getModel();//create model
                rs.next();//go to first row of the reslut
                totalsDeffTabMod.addRow(new Object[2]);
                //pupulate first and second column from totals table 
                totalsDeffTabMod.setValueAt(new BigDecimal(rs.getDouble("netDebit")).setScale(2, RoundingMode.HALF_UP), 0, 0);
                totalsDeffTabMod.setValueAt(new BigDecimal(rs.getDouble("totalNetAmnt")).setScale(2, RoundingMode.HALF_UP), 0, 1);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(GlobalATSReport.class.getName()).log(Level.SEVERE, null, ex);
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

        globalATSReportBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        globalATSTitle = new javax.swing.JLabel();
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

        globalATSReportBackground.setBackground(new java.awt.Color(255, 255, 255));
        globalATSReportBackground.setPreferredSize(new java.awt.Dimension(1200, 900));

        bluePanel.setBackground(new java.awt.Color(120, 240, 240));

        globalATSTitle.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        globalATSTitle.setText("    GLOBAL ATS REPORT");

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
                .addComponent(globalATSTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(globalATSTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "No", "Agent ID", "Sold", "Local Fare Base", "USD Fare Base", "Taxes", "Cash Payment", "Card Payment", "Total Amount", "Commission"
            }
        ));
        jScrollPane1.setViewportView(reportjTable);
        if (reportjTable.getColumnModel().getColumnCount() > 0) {
            reportjTable.getColumnModel().getColumn(3).setMinWidth(120);
            reportjTable.getColumnModel().getColumn(4).setMinWidth(120);
        }

        totalsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Net Debit", "Total Net"
            }
        ));
        jScrollPane2.setViewportView(totalsjTable);

        javax.swing.GroupLayout globalATSReportBackgroundLayout = new javax.swing.GroupLayout(globalATSReportBackground);
        globalATSReportBackground.setLayout(globalATSReportBackgroundLayout);
        globalATSReportBackgroundLayout.setHorizontalGroup(
            globalATSReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(globalATSReportBackgroundLayout.createSequentialGroup()
                .addGroup(globalATSReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalATSReportBackgroundLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(dateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fromDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(toLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(globalATSReportBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(globalATSReportBackgroundLayout.createSequentialGroup()
                        .addGap(830, 830, 830)
                        .addGroup(globalATSReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(globalATSReportBackgroundLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        globalATSReportBackgroundLayout.setVerticalGroup(
            globalATSReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(globalATSReportBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(globalATSReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(fromDateTextbox)
                    .addComponent(toLabel)
                    .addComponent(toDateTextbox))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(globalATSReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateButton)
                    .addComponent(printButton))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalATSReportBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalATSReportBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Report Print");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        
        try{
            reportjTable.print(JTable.PrintMode.NORMAL, header, footer);
            totalsjTable.print(JTable.PrintMode.NORMAL, header, footer);
            
        }catch(java.awt.print.PrinterException e){
            System.err.format("Cannot print %s%n", e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(GlobalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlobalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlobalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlobalATSReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlobalATSReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField fromDateTextbox;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel globalATSReportBackground;
    private javax.swing.JLabel globalATSTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton printButton;
    private javax.swing.JTable reportjTable;
    private javax.swing.JTextField toDateTextbox;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTable totalsjTable;
    // End of variables declaration//GEN-END:variables
}
