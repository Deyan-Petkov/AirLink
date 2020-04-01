
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
public class AdvisorReportList extends javax.swing.JFrame {
    //Holds copy of the database during the current session

    private DefaultTableModel defTabMod;
    //holds the row number selected by the user
    private int selectedRow;
    static boolean select = false;//When is false clicking on CustomerRecords table doesn't assign value to custID
    static String advisorName;
    static int addvisorID;

    /**
     * Creates new form advisorsList
     */
    public AdvisorReportList() {
        initComponents();
        initAdvisorsList("select * from staff where role='advisor'");
        selectedRow = -1;

    }
    //populates the customerTable table with the relevant data from tha databse

    private void initAdvisorsList(String sqlStatement) {
        //estabblish connection with the database
        try ( PreparedStatement ps = DbCon.getConnection().prepareStatement(sqlStatement);) {
            ResultSet rs = ps.executeQuery();//contains the data returned from the database quiery
            ResultSetMetaData rsmd = rs.getMetaData();
            //controls the for loop for the assigning of values in the vector
            int column = rsmd.getColumnCount();
            //initialize this form table according to the database structure
            defTabMod = (DefaultTableModel) advisorsListTable.getModel();
            defTabMod.setRowCount(0);
            //loops over each row of the database
            while (rs.next()) {
                Vector v = new Vector();

                for (int i = 1; i <= column; i++) {
                    v.add(rs.getInt("ID"));
                    v.add(rs.getString("role"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("address"));
                    v.add(rs.getString("email"));
                    v.add(rs.getInt("phoneNum"));

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

        advisorsListBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        advisorsListTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        advisorsListPane = new javax.swing.JScrollPane();
        advisorsListTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        advisorsListBackground.setBackground(new java.awt.Color(255, 255, 255));
        advisorsListBackground.setPreferredSize(new java.awt.Dimension(1100, 800));
        advisorsListBackground.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                advisorsListBackgroundMouseMoved(evt);
            }
        });

        bluePanel.setBackground(new java.awt.Color(125, 240, 240));

        advisorsListTitle.setFont(new java.awt.Font("Tahoma", 0, 80)); // NOI18N
        advisorsListTitle.setText("ADVISORS LIST");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
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
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(advisorsListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bluePanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(advisorsListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        advisorsListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ROLE", "NAME", "ADDRESS", "EMAIL", "PHONE NUMBER"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        advisorsListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                advisorsListTableMouseClicked(evt);
            }
        });
        advisorsListPane.setViewportView(advisorsListTable);

        javax.swing.GroupLayout advisorsListBackgroundLayout = new javax.swing.GroupLayout(advisorsListBackground);
        advisorsListBackground.setLayout(advisorsListBackgroundLayout);
        advisorsListBackgroundLayout.setHorizontalGroup(
            advisorsListBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorsListBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(advisorsListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        advisorsListBackgroundLayout.setVerticalGroup(
            advisorsListBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advisorsListBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(advisorsListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorsListBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorsListBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonadvisorListActionPerformed
        // TODO add your handling code here:

        dispose();
    }//GEN-LAST:event_backButtonadvisorListActionPerformed


    private void advisorsListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advisorsListTableMouseClicked
        // TODO add your handling code here:
        //holds teh row number selected with the mouse
        selectedRow = advisorsListTable.getSelectedRow();
        if (DomesticSalesReport.isInstantiated) {
            // Set custID in BookTicket according to the mouse selected row ID
//            DomesticSalesReport.advisorID = (int) defTabMod.getValueAt(selectedRow, 0);
//            DomesticSalesReport.advisorname = (String) defTabMod.getValueAt(selectedRow, 2).toString();
            addvisorID = (int) defTabMod.getValueAt(selectedRow, 0);
            advisorName = (String) defTabMod.getValueAt(selectedRow, 2).toString();

            PersonalATSReport personal = new PersonalATSReport();
            personal.setVisible(true);
            personal.setDefaultCloseOperation(personal.DISPOSE_ON_CLOSE);
        } else if (InterlineSalesReport.isInstantiated) {
//            InterlineSalesReport.advisorID = (int) defTabMod.getValueAt(selectedRow, 0);
//            InterlineSalesReport.advisorname = (String) defTabMod.getValueAt(selectedRow, 2).toString();
            addvisorID = (int) defTabMod.getValueAt(selectedRow, 0);
            advisorName = (String) defTabMod.getValueAt(selectedRow, 2).toString();

            PersonalInterlineReport p = new PersonalInterlineReport();
            p.setVisible(true);
            p.setDefaultCloseOperation(p.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_advisorsListTableMouseClicked

    private void advisorsListBackgroundMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advisorsListBackgroundMouseMoved
        // TODO add your handling code here:
        if (Advisor.buttonPressed() == true) {
            initAdvisorsList("select * from staff where role='advisor'");

        }

    }//GEN-LAST:event_advisorsListBackgroundMouseMoved

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdvisorsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdvisorsList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel advisorsListBackground;
    private javax.swing.JScrollPane advisorsListPane;
    private javax.swing.JTable advisorsListTable;
    private javax.swing.JLabel advisorsListTitle;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bluePanel;
    // End of variables declaration//GEN-END:variables
}
