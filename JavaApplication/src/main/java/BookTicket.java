
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.api.tree.ContinueTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xahna
 */
public class BookTicket extends javax.swing.JFrame {

    //buyer ID is set when advisor clicks on entry of the CustomerRecords table.
    //The value is assigned in CustomerRecords
    static int custID;// used with if statement for protection so we don't record sale without a customer assigned
    //returns true if BookTicket object was created.
    static boolean isInstantiated;//When is false clicking on CustomerRecords table doesn't assign value to custID
    private int typeBlank;//howds the blank type number chosen from the combobox in Advisor class
    private int blankAllowance;//how many legs can be added to the choosen blank
    DefaultTableModel flightsDftTblMdl, itinerearyDftTblMdl;
    int fTblSlctdRow;//when mouse clicked on flights table
    int itnrRowCount;// current itinenrary row count
    private long blankNo;//holds unsold blank number
    boolean delayed;//if payment is delayed
    private double price;//holds the final blank price
    private double exchangeRate;//last captured exchange rate - 1Local = ?USD
    private double commissionRate;//the commision rate for the chosen blank
    private CardInfo cardInfo;

    public BookTicket() {
        initComponents();
        isInstantiated = true;
        initFlightsTable();
        delayed = false;
        exchangeRate = 0;
        price = 0;

    }

    //used to assign blank to the current session. Takes the smallest number from the available once
    private long findNextBlankNo() {
        try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select min(blankNumber) from Blank where isSold = 0 and blankNumber like '"
                    + typeBlank + "%'");
            ResultSet rs = pst.executeQuery();
            rs.next();
            blankNo = rs.getLong("min(blankNumber)");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blankNo;
    }

    //returns the commission rate assigned to the current session blank
    public double getCommissionRate() {
        if (commissionRate > 0) {
            return commissionRate;
        }
        try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select commissionRate from Blank where blankNumber = '" + blankNo + "'");
            ResultSet rs = pst.executeQuery();
            rs.next();

            commissionRate = rs.getDouble("commissionRate");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commissionRate;
    }

    //populates the flight table with available flights from the databse table Flights
    private void initFlightsTable() {
        try ( Connection con = DbCon.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from Flights");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();
            //create a model to of the flights table to be populated later
            flightsDftTblMdl = (DefaultTableModel) flightsjTable.getModel();
            flightsDftTblMdl.setRowCount(0);

            while (rs.next()) {
                //holds all valauese from table Flights
                Object[] a = new Object[6];
                //assigning of the values from Flight
                for (int i = 0; i < columnCount; i++) {
                    a[0] = rs.getString("departure");
                    a[1] = rs.getString("destination");
                    a[2] = rs.getString("depTime");
                    a[3] = rs.getString("arrTime");
                    a[4] = rs.getInt("number");
                    a[5] = rs.getDouble("price");
                }// add new row to the model
                flightsDftTblMdl.addRow(a);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
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

        bookTicketBackground = new javax.swing.JPanel();
        bookTicketBlueBackground = new javax.swing.JPanel();
        bookTicketTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        voidBlankButton = new javax.swing.JButton();
        selectCustomerButton = new javax.swing.JButton();
        delayPaymentButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itineraryTable = new javax.swing.JTable();
        itineraryLabel = new javax.swing.JLabel();
        paymentjComboBox = new javax.swing.JComboBox<>();
        amountTextbox = new javax.swing.JTextField();
        currencyComboBox = new javax.swing.JComboBox<>();
        taxesTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        flightsjTable = new javax.swing.JTable();
        flightsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        otherTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        blankNojLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        amountjLabel = new javax.swing.JLabel();
        PricejButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bookTicketBackground.setBackground(new java.awt.Color(255, 255, 255));
        bookTicketBackground.setPreferredSize(new java.awt.Dimension(1200, 1539));

        bookTicketBlueBackground.setBackground(new java.awt.Color(102, 255, 255));

        bookTicketTitle.setFont(new java.awt.Font("Tahoma", 0, 72)); // NOI18N
        bookTicketTitle.setText("BOOK TICKET");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bookTicketBlueBackgroundLayout = new javax.swing.GroupLayout(bookTicketBlueBackground);
        bookTicketBlueBackground.setLayout(bookTicketBlueBackgroundLayout);
        bookTicketBlueBackgroundLayout.setHorizontalGroup(
            bookTicketBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookTicketBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookTicketTitle)
                .addGap(217, 217, 217)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bookTicketBlueBackgroundLayout.setVerticalGroup(
            bookTicketBlueBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookTicketBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookTicketBlueBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookTicketTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        voidBlankButton.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        voidBlankButton.setText("VOID BLANK");
        voidBlankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voidBlankButtonActionPerformed(evt);
            }
        });

        selectCustomerButton.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        selectCustomerButton.setText("SELECT CUSTOMER");
        selectCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCustomerButtonActionPerformed(evt);
            }
        });

        delayPaymentButton.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        delayPaymentButton.setText("DELAY PAYMENT");
        delayPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayPaymentButtonActionPerformed(evt);
            }
        });

        itineraryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight Departure", "Flight Destination", "Arrival Time", "Departure Time", "Flight Number", "Price", "Customer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        itineraryTable.setColumnSelectionAllowed(true);
        itineraryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itineraryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(itineraryTable);
        itineraryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        itineraryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        itineraryLabel.setText("ITINERARY");

        paymentjComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        paymentjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH", "CARD" }));
        paymentjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentjComboBoxActionPerformed(evt);
            }
        });

        amountTextbox.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        amountTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountTextboxActionPerformed(evt);
            }
        });

        currencyComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currencyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CURRENCY", "LOCAL", "USD" }));
        currencyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currencyComboBoxActionPerformed(evt);
            }
        });

        taxesTextField.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        taxesTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taxesTextFieldMouseClicked(evt);
            }
        });
        taxesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxesTextFieldActionPerformed(evt);
            }
        });
        taxesTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taxesTextFieldKeyTyped(evt);
            }
        });

        flightsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flight Departure", "Flight Destination", "Arrival TIme", "Departure Time", "Flight Number", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        flightsjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flightsjTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(flightsjTable);

        flightsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        flightsLabel.setText("AVAILABLE FLIGHTS");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("TAXES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setText("OTHER");

        otherTextField.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        otherTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherTextFieldMouseClicked(evt);
            }
        });
        otherTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherTextFieldActionPerformed(evt);
            }
        });
        otherTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                otherTextFieldKeyTyped(evt);
            }
        });

        blankNojLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Blank No:");

        amountjLabel.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N

        PricejButton.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        PricejButton.setText("PRICE");
        PricejButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PricejButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PricejButtonMousePressed(evt);
            }
        });
        PricejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PricejButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bookTicketBackgroundLayout = new javax.swing.GroupLayout(bookTicketBackground);
        bookTicketBackground.setLayout(bookTicketBackgroundLayout);
        bookTicketBackgroundLayout.setHorizontalGroup(
            bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookTicketBlueBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                        .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(itineraryLabel)
                                .addGap(337, 337, 337)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blankNojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookTicketBackgroundLayout.createSequentialGroup()
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(flightsLabel)
                                    .addComponent(voidBlankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(delayPaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paymentjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95)
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PricejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(taxesTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                    .addComponent(otherTextField)
                                    .addComponent(amountjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(110, Short.MAX_VALUE))
                    .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(amountTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );
        bookTicketBackgroundLayout.setVerticalGroup(
            bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                .addComponent(bookTicketBlueBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(itineraryLabel)
                        .addComponent(jLabel5))
                    .addComponent(jLabel4)
                    .addComponent(blankNojLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(flightsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                        .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(currencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookTicketBackgroundLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(taxesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(otherTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(amountTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(selectCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                                .addComponent(delayPaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(voidBlankButton)
                                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PricejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(bookTicketBackgroundLayout.createSequentialGroup()
                                .addGroup(bookTicketBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paymentjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(29, 29, 29)
                                .addComponent(amountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookTicketBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1323, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookTicketBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        custID = 0;
        dispose();
        isInstantiated = false;
    }//GEN-LAST:event_backButtonActionPerformed


    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        //if the price was calculaated all fields are sent and we can save
        if (amountjLabel.getText().length() > 0) {
            try ( Connection con = DbCon.getConnection()) {
                PreparedStatement pst;
                //write to itinerary table
                while (itinerearyDftTblMdl.getRowCount() > 0) {
                    pst = con.prepareStatement("insert into Itinerary values(?,?,?,?,?,?,?,?)");
                    pst.setInt(1, CustomerRecords.nextID("Itinerary"));//ID
                    pst.setString(2, (String) itinerearyDftTblMdl.getValueAt(0, 0));//Flight Departure
                    pst.setString(3, (String) itinerearyDftTblMdl.getValueAt(0, 1));//Flight Destination
                    pst.setString(4, (String) itinerearyDftTblMdl.getValueAt(0, 2));//Flight Arrival Time
                    pst.setString(5, (String) itinerearyDftTblMdl.getValueAt(0, 3));//Fligght Departure Time
                    pst.setInt(6, (int) itinerearyDftTblMdl.getValueAt(0, 4));//Flight Number
                    pst.setLong(7, blankNo);//Blank Number
                    pst.setInt(8, (int) itinerearyDftTblMdl.getValueAt(0, 6));//Customer

                    itinerearyDftTblMdl.removeRow(0);

                    pst.execute();

                }
                //write to payment table
                pst = con.prepareStatement("insert into payment (BlankblankNumber,delayed,exchangeRate,"
                        + "date,taxes,isRefunded) values(?,?,?,?,?,?)");
                pst.setLong(1, blankNo);
                pst.setBoolean(2, delayed);
                pst.setDouble(3, exchangeRate);
                pst.setString(4, LocalDate.now().toString());//date
                pst.setDouble(5, Double.valueOf(taxesTextField.getText()));//taxes
                pst.setBoolean(6, false);//is refunded
                pst.execute();
                //add card details if card option is selected
                if (paymentjComboBox.getSelectedItem().toString().equals("CARD")) {
                    pst = con.prepareStatement("update payment set type = '"
                            + cardInfo.getCardNo() + "',"
                            + " name = '" + cardInfo.getCardHldrName() + "',"
                            + " expDate = '" + cardInfo.getCardExpDate() + "'"
                            + " where BlankblankNumber = '" + blankNo + "'"
                    );
                    pst.execute();

                } else {//write to payment if cash paymment is being used
                    pst = con.prepareStatement("update payment set type = 'cash' where BlankblankNumber = '" + blankNo + "'");
                    pst.execute();
                }
                //if blank is 444 or 420 write to otherTaxes inside payment 
                if (typeBlank == 444 | typeBlank == 420) {
                    pst = con.prepareStatement("update payment set otherTaxes = '"
                            + Double.valueOf(otherTextField.getText())
                            + "' where BlankblankNumber = '" + blankNo + "'");
                    pst.execute();
                }
                //change this blank to sold in Blank
                pst = con.prepareStatement("update Blank set isSold = 1 where blankNumber = '" + blankNo + "'");
                pst.execute();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
            custID = 0;

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "First calculate the price",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed
    //sets isSold in Blank table to void
    private void voidBlankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voidBlankButtonActionPerformed

        int dialogButton = JOptionPane.showConfirmDialog(null, "Do you want to VOID this blank?", "WARNING", JOptionPane.YES_NO_OPTION);

        if (dialogButton == JOptionPane.YES_OPTION) {
            try ( Connection con = DbCon.getConnection()) {
                PreparedStatement ps = con.prepareStatement("update blank set isSold = 'VOID' where blankNumber = " + blankNo);
                ps.execute();
                this.dispose();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_voidBlankButtonActionPerformed

    private void selectCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCustomerButtonActionPerformed
        // TODO add your handling code here:
        CustomerRecords cr = new CustomerRecords();
        cr.setVisible(true);
        cr.setDefaultCloseOperation(cr.DISPOSE_ON_CLOSE);
        blankNojLabel.setText(Long.toString(findNextBlankNo()));
        //if the blank is not 444 or 440 don't allow writing into "Other" text field
        if (typeBlank == 444 | typeBlank == 420) {
            otherTextField.setEditable(true);
        } else {
            otherTextField.setEditable(false);
        }
    }//GEN-LAST:event_selectCustomerButtonActionPerformed
    //sets variable delayed which later will be passed to Payment table
    private void delayPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayPaymentButtonActionPerformed
        if (delayPaymentButton.getText().equals("DELAY PAYMENT")) {
            delayPaymentButton.setText("DELAYED");
            delayPaymentButton.setBackground(Color.red);
            delayed = true;
        } else if (delayPaymentButton.getText().equals("DELAYED")) {
            delayPaymentButton.setText("DELAY PAYMENT");
            delayPaymentButton.setBackground(selectCustomerButton.getBackground());
            delayed = false;
        }
    }//GEN-LAST:event_delayPaymentButtonActionPerformed

    private void amountTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountTextboxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_amountTextboxActionPerformed
    //if user choose card payment cardInfo window will pop up to take card details
    private void paymentjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentjComboBoxActionPerformed
        if (paymentjComboBox.getSelectedItem().toString().equals("CARD")) {
            cardInfo = new CardInfo();
            cardInfo.setVisible(true);
            cardInfo.setDefaultCloseOperation(cardInfo.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_paymentjComboBoxActionPerformed

    private void currencyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currencyComboBoxActionPerformed
        //if we work with blank types 420 or 444 and currency combobox is set to USD
        if ((typeBlank == 444 | typeBlank == 420) & currencyComboBox.getSelectedItem().toString().equals("USD")) {
            //set exchange rate
            try ( Connection con = DbCon.getConnection()) {
                PreparedStatement pst = con.prepareStatement("select rate from ExchangeRates where date =(select max(date) from ExchangeRates)");
                ResultSet rs = pst.executeQuery();
                rs.next();
                exchangeRate = rs.getDouble("rate");

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //if the chosen blank is not 444 or 420 don't allow payment in USD
        if (typeBlank != 444 & typeBlank != 420) {
            if (currencyComboBox.getSelectedItem().toString().equals("USD")) {
                JOptionPane.showMessageDialog(null,
                        "This blank type doesn't allow payments in USD",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                currencyComboBox.setSelectedItem("CURRENCY");
            }
        }


    }//GEN-LAST:event_currencyComboBoxActionPerformed

    private void otherTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherTextFieldActionPerformed

    }//GEN-LAST:event_otherTextFieldActionPerformed

    private void flightsjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flightsjTableMouseClicked
        //if user tries to add more flights than allowed for the blank show meessage instead
        if (itnrRowCount >= blankAllowance) {
            JOptionPane.showMessageDialog(null,
                    "Maximum allowed flights for this type of blank is " + blankAllowance,
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } else {//else allow to add more flights
            if (custID == 0) {
                JOptionPane.showMessageDialog(null,
                        "To add flights, please first \"SELECT CUSTOMER\"",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {//copy the selected row from flyghts to itinereary 
                itinerearyDftTblMdl = (DefaultTableModel) itineraryTable.getModel();
                fTblSlctdRow = flightsjTable.getSelectedRow();
                itinerearyDftTblMdl.addRow(new Object[5]);
                itnrRowCount = itineraryTable.getRowCount();
                price += (double) flightsjTable.getValueAt(fTblSlctdRow, 5);
                itinerearyDftTblMdl.setValueAt(flightsjTable.getValueAt(fTblSlctdRow, 0), itnrRowCount - 1, 0);//Flight Departure
                itinerearyDftTblMdl.setValueAt(flightsjTable.getValueAt(fTblSlctdRow, 1), itnrRowCount - 1, 1);//Flight Destination
                itinerearyDftTblMdl.setValueAt(flightsjTable.getValueAt(fTblSlctdRow, 2), itnrRowCount - 1, 2);//Arrival Time
                itinerearyDftTblMdl.setValueAt(flightsjTable.getValueAt(fTblSlctdRow, 3), itnrRowCount - 1, 3);//Departure Time
                itinerearyDftTblMdl.setValueAt(flightsjTable.getValueAt(fTblSlctdRow, 4), itnrRowCount - 1, 4);//Fight Number
                itinerearyDftTblMdl.setValueAt((double) flightsjTable.getValueAt(fTblSlctdRow, 5), itnrRowCount - 1, 5);//Price
                itinerearyDftTblMdl.setValueAt(custID, itnrRowCount - 1, 6);//Customer

            }
        }

    }//GEN-LAST:event_flightsjTableMouseClicked

    private void taxesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxesTextFieldActionPerformed

    }//GEN-LAST:event_taxesTextFieldActionPerformed

    private void taxesTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taxesTextFieldKeyTyped
    }//GEN-LAST:event_taxesTextFieldKeyTyped

    private void otherTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_otherTextFieldKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_otherTextFieldKeyTyped

    private void itineraryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itineraryTableMouseClicked
        // subrtract the value of ticket from the blank price before deleting it
        price -= (double) itinerearyDftTblMdl.getValueAt(itineraryTable.getSelectedRow(), 5);
        //delete ticket from the blank
        itinerearyDftTblMdl.removeRow(itineraryTable.getSelectedRow());
    }//GEN-LAST:event_itineraryTableMouseClicked

    private void taxesTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxesTextFieldMouseClicked
        //makes sure customer has been chosen 
        if (blankNojLabel.getText().length() <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Please select customer",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_taxesTextFieldMouseClicked

    private void otherTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherTextFieldMouseClicked

    }//GEN-LAST:event_otherTextFieldMouseClicked

    private void PricejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PricejButtonActionPerformed

    }//GEN-LAST:event_PricejButtonActionPerformed

    private void PricejButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PricejButtonMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_PricejButtonMouseClicked

    private void PricejButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PricejButtonMousePressed
        //prevent from making calculation with null values
        // if flyght has not been chosen yet
        if (itinerearyDftTblMdl == null) {
            JOptionPane.showMessageDialog(null,
                    "Please select flight/s",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

        }//If currency is not set send message to the user
        else if (currencyComboBox.getSelectedItem().toString().equals("CURRENCY")) {
            JOptionPane.showMessageDialog(null,
                    "Please select currency",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

        } else {
            //if local is set but taxes is empty
            if (currencyComboBox.getSelectedItem().toString().equals("LOCAL") && (taxesTextField.getText() == null | taxesTextField.getText().length() <= 0)) {
                JOptionPane.showMessageDialog(null,
                        "Please fill the \"TAXES\" field!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                //if USD is set but other is empty
            } else if (currencyComboBox.getSelectedItem().toString().equals("USD") && (otherTextField.getText() == null | taxesTextField.getText().length() <= 0)) {
                JOptionPane.showMessageDialog(null,
                        "Please fill the \"OTHER\" and \"TAXES\" fields!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }//if local is set - calculate total price of all (flights * commission) + taxes 
            else if (currencyComboBox.getSelectedItem().toString().equals("LOCAL")) {

                double taxes = Double.valueOf(taxesTextField.getText());
                double finalPrice = (price * getCommissionRate()) + taxes;
                //for debugging purposes
//                System.out.println("INSIDE LOCAL");
//                System.out.println("price: " + price);
//                System.out.println("commission: " + getCommissionRate());
//                System.out.println("taxes: " + taxes);
//                System.out.println("final price: " + finalPrice);
                  //
//               
                amountjLabel.setText(String.valueOf(new BigDecimal(finalPrice).setScale(2, RoundingMode.HALF_UP)));
            } else if (currencyComboBox.getSelectedItem().toString().equals("USD")) {
                System.out.println("INSIDE USD");

                double taxes = Double.valueOf(taxesTextField.getText());
                double otherTaxes = Double.valueOf(otherTextField.getText());
                double totalTaxes = taxes + otherTaxes;
                // final price
                double finalPrice = ((price * getCommissionRate()) + totalTaxes) * exchangeRate;
                //for debugging purposes
//                System.out.println("price" + price);
//                System.out.println("taxes: " + taxes);
//                System.out.println("other taxes: " + otherTaxes);
//                System.out.println("exchange rate: " + exchangeRate);
//                System.out.println("commission: " + getCommissionRate());
//                System.out.println("final price: " + finalPrice);

                amountjLabel.setText(String.valueOf(new BigDecimal(finalPrice).setScale(2, RoundingMode.HALF_UP)));
            }
        }
    }//GEN-LAST:event_PricejButtonMousePressed

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
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookTicket().setVisible(true);
            }
        });
    }

    //used in AdvisorHub.java. With this values set we can search for the 
    //right type of blank e.g 444...... 420.......
    public void setComboBoxIndex(int comboBoxIndex, int blankAllowance) {
        this.typeBlank = comboBoxIndex;
        this.blankAllowance = blankAllowance;
    }

    public int getComboboxIndex() {
        return typeBlank;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PricejButton;
    private javax.swing.JTextField amountTextbox;
    private javax.swing.JLabel amountjLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel blankNojLabel;
    private javax.swing.JPanel bookTicketBackground;
    private javax.swing.JPanel bookTicketBlueBackground;
    private javax.swing.JLabel bookTicketTitle;
    private javax.swing.JComboBox<String> currencyComboBox;
    private javax.swing.JButton delayPaymentButton;
    private javax.swing.JLabel flightsLabel;
    private javax.swing.JTable flightsjTable;
    private javax.swing.JLabel itineraryLabel;
    private javax.swing.JTable itineraryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField otherTextField;
    private javax.swing.JComboBox<String> paymentjComboBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton selectCustomerButton;
    private javax.swing.JTextField taxesTextField;
    private javax.swing.JButton voidBlankButton;
    // End of variables declaration//GEN-END:variables
}
