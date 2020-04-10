
import static java.lang.System.gc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class AdminStockControl extends javax.swing.JFrame {
  
   
    PreparedStatement pst= null; 
    ResultSet rs=null; 
    Connection con=null; 
    
    // decleration for values in date
     LocalDateTime localDateTime;
    
 ; 
  
 
    /**
     * Creates new form adminStockControl
     */
    public AdminStockControl() {
       initComponents();
  
     //below is assignation of date 
     
             localDateTime = LocalDateTime.now().withNano(0);
   
      

    }
        
    
   

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminStockControlBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        adminStockControlTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        addtButton1 = new javax.swing.JButton();
        quantityTextField = new javax.swing.JTextField();
        blankComboBox = new javax.swing.JComboBox<>();
        orderTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        quantityTextbox = new javax.swing.JTextField();
        returnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        adminStockControlBackground.setBackground(new java.awt.Color(255, 255, 255));
        adminStockControlBackground.setPreferredSize(new java.awt.Dimension(1100, 800));

        bluePanel.setBackground(new java.awt.Color(125, 240, 240));

        adminStockControlTitle.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        adminStockControlTitle.setText("       ADMIN STOCK CONTROL");

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
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(adminStockControlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminStockControlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addGroup(bluePanelLayout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonadvisorListActionPerformed(evt);
            }
        });

        addtButton1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        addtButton1.setText("ADD");
        addtButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtButton1advisorListActionPerformed(evt);
            }
        });

        quantityTextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        quantityTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantityTextFieldMouseClicked(evt);
            }
        });
        quantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextFieldActionPerformed(evt);
            }
        });

        blankComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        blankComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Blank", "444", "440", "420", "201", "101", "451", "452" }));
        blankComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blankComboBoxActionPerformed(evt);
            }
        });

        orderTextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        orderTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTextFieldMouseClicked(evt);
            }
        });
        orderTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Place Order:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Current Quantity:");

        quantityTextbox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        quantityTextbox.setText("Enter return Quantity...");
        quantityTextbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quantityTextboxMouseClicked(evt);
            }
        });
        quantityTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextboxActionPerformed(evt);
            }
        });

        returnButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        returnButton.setText("DELETE");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adminStockControlBackgroundLayout = new javax.swing.GroupLayout(adminStockControlBackground);
        adminStockControlBackground.setLayout(adminStockControlBackgroundLayout);
        adminStockControlBackgroundLayout.setHorizontalGroup(
            adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                        .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(blankComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adminStockControlBackgroundLayout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(orderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(131, 131, 131)
                        .addComponent(quantityTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(addtButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(143, 143, 143)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                    .addGap(166, 166, 166)
                    .addComponent(jLabel2)
                    .addContainerGap(850, Short.MAX_VALUE)))
        );
        adminStockControlBackgroundLayout.setVerticalGroup(
            adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(blankComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(158, 158, 158)
                .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addtButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnButton))
                .addContainerGap())
            .addGroup(adminStockControlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(adminStockControlBackgroundLayout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(426, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminStockControlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminStockControlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonadvisorListActionPerformed
        // TODO add your handling code here:
      
            dispose(); 
    }//GEN-LAST:event_backButtonadvisorListActionPerformed

       
    private void saveButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonadvisorListActionPerformed
        // new blanks will be added to the database 
        
        String s=blankComboBox.getSelectedItem().toString();  //takes the value of blank number that is selected 
        String quantity= orderTextField.getText(); //takes the value of new number of blanks that are ordered by the advisor
        int row= Integer.parseInt(quantity);  // new rows that need to be added depending on the number of blanks ordered 
        String blanka=null; // just done to create keep first 3 digits of blank numebr same 
        String commission=null;
        String result=null;
        
       try (   Connection con = DbCon.getConnection();) {
     

           //write else ifs 
        if(s=="444"){blanka="444";  ;} //these stms are for diffirent cases of first 3 digits of uniqu blank number and fetching th commission rate from the Db
        if(s=="440"){blanka="440"; ;}
        if(s=="420"){blanka="420"; ;}
        if(s=="201"){blanka="201";  ;}
        if(s=="101"){blanka="101"; ;} 
        if(s=="451"){blanka="451"; ;} 
        if(s=="452"){blanka="452";  }    
        
         for(int i=0;i<row;i++){   //for how ever many blanks ordered(however many new rows)
              long number = (long) Math.floor(Math.random() * 9_000_000_0L) + 1_000_000_0L;  //random number generated for last 8 digits of uniques blank number
              String blanknum=blanka+number;  //blank number = first 3 digit of blank number + last 8 difits of random number
          long blanknums=  Long.parseLong(blanknum);
              
              PreparedStatement pst = con.prepareStatement("INSERT INTO Blank Values (?,?,?,?,?)");
         
  
         
          
          //going to new rows and just inserting the values. 
          
            pst.setLong(1,blanknums);
            pst.setString(2, null);
            pst.setBoolean(3, false);
          
          pst.setString(4, null);
            pst.setString(5, localDateTime.toString());
           
            pst.execute();
           }
         JOptionPane.showMessageDialog(null,"inserted");
            
        
            
        } catch (SQLException | ClassNotFoundException e) {
          JOptionPane.showMessageDialog(null,"error");
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_saveButtonadvisorListActionPerformed

    private void addtButton1advisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtButton1advisorListActionPerformed
        // This just shows the new quantity of blanks after its ordered by (old quantity+new orered amount) only front end, this finciton has no back end implications
        String orderquantity=orderTextField.getText(); 
        int oq=Integer.parseInt(orderquantity);
       
        String oldq=   quantityTextField.getText();
          int x=Integer.parseInt(oldq);
        
          int newq=oq+x; 
          
        String newStock=Integer.toString(newq);
        
        quantityTextField.setText(newStock);
        
        
        
    }//GEN-LAST:event_addtButton1advisorListActionPerformed

    private void quantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextFieldActionPerformed

    private void blankComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blankComboBoxActionPerformed
        // this is showing the number of specific type of blanks in teh database 
       
     try (   Connection con = DbCon.getConnection();) {
           String s=blankComboBox.getSelectedItem().toString();  //takes the type of blank that is chose in combo box 
            String sql=null;
        
         
          
        //diffiret stms for diffirent blank types, done in orer to count how many there are in the Db 
        if(s=="444"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '444%';\n" +"]  ";
          
         }
        if(s=="440"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '440%';\n" +"]  ";
       
         }    
        if(s=="420"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '420%';\n" +"]  ";
            
         }  
            
       if(s=="201"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '201%';\n" +"]  ";
         
         }
       if(s=="101"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '101%';\n" +"]  ";
         
         }
       if(s=="451"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '451%';\n" +"]  ";
          
         } 
       if(s=="452"){
            sql="select count(blankNumber) from Blank where blankNumber \n" +"LIKE '452%';\n" +"]  ";
           
         } 
        
        //this just shows in the text fied the quantity of blanks that are available in the stock.
        
        pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String sum=rs.getString("count(blankNumber)");
            quantityTextField.setText(sum);
          
        }
         
        }catch (SQLException | ClassNotFoundException e) {
          JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_blankComboBoxActionPerformed

    private void orderTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderTextFieldActionPerformed

    private void quantityTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantityTextFieldMouseClicked
        // TODO add your handling code here:
          quantityTextField.setText("");//clears the textField once you click on it
    }//GEN-LAST:event_quantityTextFieldMouseClicked

    private void orderTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTextFieldMouseClicked
        // TODO add your handling code here:
        orderTextField.setText("");//clears the textField once you click on it
    }//GEN-LAST:event_orderTextFieldMouseClicked

    private void quantityTextboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quantityTextboxMouseClicked
        // TODO add your handling code here:
        quantityTextbox.setText("");//clears the textField once you click on it
    }//GEN-LAST:event_quantityTextboxMouseClicked

    private void quantityTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextboxActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        // TODO add your handling code here:
        String s=blankComboBox.getSelectedItem().toString();  //choosen blanks

        String quantity= quantityTextbox.getText(); //input of quantity of blanks
        int blanks=  Integer.parseInt(quantity);

        try (   Connection con = DbCon.getConnection();) {
            PreparedStatement rst = null;
            rst=con.prepareStatement(" DELETE FROM Blank WHERE blankNumber IN(select blankNumber from(select blankNumber FROM Blank where blankNumber like '"+s+"%' AND isSold = 0 LIMIT "+blanks+")x)");
            rst.execute();

        //    JOptionPane.showMessageDialog(null,"Deleted successfully"); //comment this when u are deleting 101 

            dispose();

        }catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"error");
        }

        //get blanks where isSOld is false and staffID is null and blankID==combobox
        //delete that row and do it untill the quantity.

    }//GEN-LAST:event_returnButtonActionPerformed
        
     
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
            java.util.logging.Logger.getLogger(AdminStockControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminStockControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminStockControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminStockControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminStockControl().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addtButton1;
    private javax.swing.JPanel adminStockControlBackground;
    private javax.swing.JLabel adminStockControlTitle;
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> blankComboBox;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField orderTextField;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JTextField quantityTextbox;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
