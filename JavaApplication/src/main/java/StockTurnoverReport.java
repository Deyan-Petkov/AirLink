
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
public class StockTurnoverReport extends javax.swing.JFrame {

    private String fromDate, toDate;//dates for the desired period of time for the report
    private int staffId;
    private DefaultTableModel t1, t2, t3, t4, t5, t6;

    /**
     * Creates new form stockTurnoverReport
     */
    public StockTurnoverReport() {
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
            try (Connection con = DbCon.getConnection()) {
                Statement statement = con.createStatement();

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
                statement.addBatch("create temp view if not exists trep as\n"
                        + "select Blank.BlankNumber as bn, Blank.issold, Blank.staffID , Blank.dateReceived as date,\n"
                        + "Payment.date as pdate\n"
                        + "from Blank\n"
                        + "left join\n"
                        + "Payment on Payment.BlankblankNumber = blank.blankNumber\n"
                        + "where datereceived >= '" + fromDate + "'and datereceived <= '" + toDate + "'");
                statement.addBatch("create temporary table t1(\n"
                        + "n integer primary key autoincrement,\n"
                        + "ftblank integer (10),\n"
                        + "amnt integer (10)\n"
                        + ");");
                statement.addBatch("create temporary table t2(\n"
                        + "n integer primary key autoincrement,\n"
                        + "code integer    (3),\n"
                        + "ft1 integer    (3),\n"
                        + "amnt1 integer (10)\n"
                        + "); ");
                statement.addBatch("create temporary table t3(\n"
                        + "n integer primary key autoincrement,\n"
                        + "code1 integer   (10),\n"
                        + "assigned integer    (3),\n"
                        + "amnt2 integer (3)\n"
                        + ");");
                statement.addBatch("create temporary table t4(\n"
                        + "n integer primary key autoincrement,\n"
                        + "used integer (10),\n"
                        + "amnt3 integer (10)\n"
                        + ");");
                statement.addBatch("create temporary table t5(\n"
                        + "n integer primary key autoincrement,\n"
                        + "ft2 integer (3),\n"
                        + "amnt4 integer (10)\n"
                        + ");");
                statement.addBatch("create temporary table t6(\n"
                        + "n integer primary key autoincrement,\n"
                        + "code2 integer (10),\n"
                        + "ft3 integer (3),\n"
                        + "amnt5 integer (10)\n"
                        + ")");

                statement.executeBatch();
                //list all newly received blanks
                statement.addBatch("insert into t1 (ftblank,amnt)\n"
                        + "\n"
                        + "  select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '444%'\n"
                        + "  union\n"
                        + "  select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '440%'\n"
                        + "  union\n"
                        + " select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '420%'\n"
                        + "  union\n"
                        + "   select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '201%'\n"
                        + "  union\n"
                        + "   select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '101%'\n"
                        + "  union\n"
                        + "   select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '451%'\n"
                        + "  union\n"
                        + "   select min(bn) || ' - ' ||  max(bn),count(bn) from trep where bn like '452%';");  // amount of new blanks 
                statement.addBatch("insert into t1 (ftblank,amnt) values ('TOTAL',(select sum(amnt) from t1))");
                /*How many of each type blanks are newly received and assigned*/
                statement.addBatch("insert into t2 (code,ft1, amnt1)\n"
                        + "\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,bn from trep  where bn like'444%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,bn from trep  where bn like'440%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,bn from trep  where bn like'420%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,bn from trep  where bn like'201%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,bn from trep  where bn like'101%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,bn from trep  where bn like'451%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,bn from trep  where bn like'452%' and staffid not null) group by staffid;");
                statement.addBatch("insert into t2 (ft1,amnt1) values ('TOTAL',(select sum(amnt1) from t2))");
                /*Existing (NOT newly received) blanks assigned to advisors (by advisor’s code);   */
                statement.addBatch("insert into t3 (code1,assigned, amnt2)\n"
                        + "\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'444%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'440%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'420%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'201%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'101%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'451%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from t  where  blankNumber not in(select bn from trep) and bn like'452%' and staffid not null) group by staffid;");
                statement.addBatch("insert into t3 (assigned,amnt2) values ('TOTAL',(select sum(amnt2) from t3))\n"
                        + "");
                /*Blanks used (new or existing) by the agent (no matter the advisor) within the given period;*/
                statement.addBatch("insert into t4 (used, amnt3)\n"
                        + "\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'444%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'))\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'440%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'))\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'420%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'))\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'201%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'))\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'101%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'))\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'451%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'))\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select blankNumber as bn from Blank  where blankNumber like'452%' and bn in (select BlankBlankNumber from Payment where date >= '" + fromDate + "'and date <= '" + toDate + "'));");
                statement.addBatch("insert into t4 (used,amnt3) values ('TOTAL',(select sum(amnt3) from t4))\n"
                        + "");
                /* blanks available in Agent’s stock at the end of period;*/
                statement.addBatch("insert into t5 (ft2, amnt4)\n"
                        + "\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'444%' and isSold = 0 )\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'440%' and isSold = 0 )\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'420%' and isSold = 0 )\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'201%' and isSold = 0 )\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'101%' and isSold = 0 )\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'451%' and isSold = 0 )\n"
                        + "union\n"
                        + "select min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'452%' and isSold = 0 );");
                statement.addBatch("insert into t5 (ft2,amnt4) values ('TOTAL',(select sum(amnt4) from t5))");
                /*Amount of blanks of a given type assigned to an advisor at the end of period*/
                statement.addBatch("insert into t6 (code2,ft3, amnt5)\n"
                        + "\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'444%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'440%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn),count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'420%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'201%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'101%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'451%' and staffid not null) group by staffid\n"
                        + "union\n"
                        + "select staffID,min(bn)|| '- ' || max(bn), count(bn) from (select distinct staffid,blankNumber as bn from Blank  where blankNumber like'452%' and staffid not null) group by staffid;");
                statement.addBatch("insert into t6 (ft3,amnt5) values ('TOTAL',(select sum(amnt5) from t6))");

                statement.executeBatch();

                PreparedStatement pst = con.prepareStatement("select * from t1");

                ResultSet rs = pst.executeQuery();//contains the data returned from the database quiery
                ResultSetMetaData rsmd = rs.getMetaData();
                //controls the for loop for the assigning of values in the vector
                int column = rsmd.getColumnCount();
                //initialize this form table according to the database structure
                t1 = (DefaultTableModel) table1.getModel();
                t1.setRowCount(0);
                //loops over each row of the database
                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getInt("n"));
                        v.add(rs.getString("ftblank"));
                        v.add(rs.getInt("amnt"));

                    }//inserts single row collected data from the databse into this form table
                    t1.addRow(v);
                }

                pst = con.prepareStatement("select * from t2");
                rs = pst.executeQuery();
                rsmd = rs.getMetaData();
                column = rsmd.getColumnCount();

                t2 = (DefaultTableModel) table2.getModel();
                t2.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getInt("n"));
                        v.add(rs.getInt("code"));
                        v.add(rs.getString("ft1"));
                        v.add(rs.getInt("amnt1"));

                    }
                    t2.addRow(v);
                }

                pst = con.prepareStatement("select * from t3");
                rs = pst.executeQuery();
                rsmd = rs.getMetaData();
                column = rsmd.getColumnCount();

                t3 = (DefaultTableModel) table3.getModel();
                t3.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getInt("n"));
                        v.add(rs.getInt("code1"));
                        v.add(rs.getString("assigned"));
                        v.add(rs.getInt("amnt2"));

                    }
                    t3.addRow(v);
                }

                pst = con.prepareStatement("select * from t4");
                rs = pst.executeQuery();
                rsmd = rs.getMetaData();
                column = rsmd.getColumnCount();

                t4 = (DefaultTableModel) table4.getModel();
                t4.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getInt("n"));
                        v.add(rs.getString("used"));
                        v.add(rs.getInt("amnt3"));

                    }
                    t4.addRow(v);
                }

                pst = con.prepareStatement("select * from t5");
                rs = pst.executeQuery();
                rsmd = rs.getMetaData();
                column = rsmd.getColumnCount();

                t5 = (DefaultTableModel) table5.getModel();
                t5.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getInt("n"));
                        v.add(rs.getString("ft2"));
                        v.add(rs.getInt("amnt4"));

                    }
                    t5.addRow(v);
                }

                pst = con.prepareStatement("select * from t6");
                rs = pst.executeQuery();
                rsmd = rs.getMetaData();
                column = rsmd.getColumnCount();

                t6 = (DefaultTableModel) table6.getModel();
                t6.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= column; i++) {
                        v.add(rs.getInt("n"));
                        v.add(rs.getInt("code2"));
                        v.add(rs.getString("ft3"));
                        v.add(rs.getInt("amnt5"));

                    }
                    t6.addRow(v);
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(StockTurnoverReport.class.getName()).log(Level.SEVERE, null, ex);
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

        stockTurnoverReportBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        stockTurnoverReportTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        fromDatetTextbox = new javax.swing.JTextField();
        toDateTextbox = new javax.swing.JTextField();
        generateButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table5 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        table6 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        stockTurnoverReportBackground.setBackground(new java.awt.Color(255, 255, 255));
        stockTurnoverReportBackground.setPreferredSize(new java.awt.Dimension(1100, 800));

        bluePanel.setBackground(new java.awt.Color(125, 240, 240));

        stockTurnoverReportTitle.setFont(new java.awt.Font("Tahoma", 0, 90)); // NOI18N
        stockTurnoverReportTitle.setText("STOCK REPORT");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stockTurnoverReportTitle)
                .addGap(117, 117, 117)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockTurnoverReportTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton))
                .addGap(22, 22, 22))
        );

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dateLabel.setText("Date:");

        toLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toLabel.setText("To:");

        generateButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        generateButton.setText("GENERATE");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        printButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        printButton.setText("PRINT");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Used Blanks", "Amnt"
            }
        ));
        jScrollPane1.setViewportView(table4);
        if (table4.getColumnModel().getColumnCount() > 0) {
            table4.getColumnModel().getColumn(1).setMinWidth(200);
        }

        table5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Available", "Amnt"
            }
        ));
        jScrollPane2.setViewportView(table5);
        if (table5.getColumnModel().getColumnCount() > 0) {
            table5.getColumnModel().getColumn(1).setMinWidth(200);
        }

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "New Blanks", "Amnt"
            }
        ));
        jScrollPane3.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(1).setMinWidth(200);
        }

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Code", "New Blanks Assigned", "Amnt"
            }
        ));
        jScrollPane4.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(2).setMinWidth(200);
        }

        table6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Code", "Assigned", "Amnt"
            }
        ));
        jScrollPane5.setViewportView(table6);
        if (table6.getColumnModel().getColumnCount() > 0) {
            table6.getColumnModel().getColumn(2).setMinWidth(200);
        }

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Code", "Assigned Existing Blanks", "Amnt"
            }
        ));
        jScrollPane6.setViewportView(table3);
        if (table3.getColumnModel().getColumnCount() > 0) {
            table3.getColumnModel().getColumn(2).setMinWidth(200);
        }

        javax.swing.GroupLayout stockTurnoverReportBackgroundLayout = new javax.swing.GroupLayout(stockTurnoverReportBackground);
        stockTurnoverReportBackground.setLayout(stockTurnoverReportBackgroundLayout);
        stockTurnoverReportBackgroundLayout.setHorizontalGroup(
            stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                        .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(dateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromDatetTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(toLabel)
                                .addGap(18, 18, 18)
                                .addComponent(toDateTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        stockTurnoverReportBackgroundLayout.setVerticalGroup(
            stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(toLabel)
                    .addComponent(fromDatetTextbox)
                    .addComponent(toDateTextbox))
                .addGap(38, 38, 38)
                .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stockTurnoverReportBackgroundLayout.createSequentialGroup()
                        .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(77, 77, 77)
                .addGroup(stockTurnoverReportBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printButton)
                    .addComponent(generateButton))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(stockTurnoverReportBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 1246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stockTurnoverReportBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed
    //initializes the variables according to user input and populates the tables
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        fromDate = fromDatetTextbox.getText();
        toDate = toDateTextbox.getText();
        initReportTable();
    }//GEN-LAST:event_generateButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonActionPerformed

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
            java.util.logging.Logger.getLogger(StockTurnoverReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockTurnoverReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockTurnoverReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockTurnoverReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockTurnoverReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField fromDatetTextbox;
    private javax.swing.JButton generateButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton printButton;
    private javax.swing.JPanel stockTurnoverReportBackground;
    private javax.swing.JLabel stockTurnoverReportTitle;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTable table4;
    private javax.swing.JTable table5;
    private javax.swing.JTable table6;
    private javax.swing.JTextField toDateTextbox;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
}
