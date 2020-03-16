
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhruv
 */
public class Advisor extends javax.swing.JFrame {
       PreparedStatement pst= null; 
    ResultSet rs=null; 
    /**
     * Creates new form advisor
     */
    public Advisor() {
        initComponents();
    }
  
     public static final String encrypt(String md5) {

        try {//This class provides the encrypting algorithm (MD5) 
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            //encrypt the input and return it as a array of bytes
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            //convert the byte array to hex value
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }//return encrypted password
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        advisorBackground = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        advisorTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        passwordTextbox = new javax.swing.JTextField();
        surnameLabel = new javax.swing.JLabel();
        addressTextbox1 = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        phoneTextbox = new javax.swing.JTextField();
        emailTextbox = new javax.swing.JTextField();
        surnameTextbox1 = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        nameLabel1 = new javax.swing.JLabel();
        nameTextbox2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        advisorBackground.setBackground(new java.awt.Color(255, 255, 255));
        advisorBackground.setPreferredSize(new java.awt.Dimension(1100, 800));

        bluePanel.setBackground(new java.awt.Color(125, 240, 240));

        advisorTitle.setFont(new java.awt.Font("Tahoma", 0, 60)); // NOI18N
        advisorTitle.setText("     ADVISOR");

        backButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                .addContainerGap(335, Short.MAX_VALUE)
                .addComponent(advisorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bluePanelLayout.setVerticalGroup(
            bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bluePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(advisorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        passwordTextbox.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        passwordTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextboxActionPerformed(evt);
            }
        });

        surnameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        surnameLabel.setText("Surname:");

        addressTextbox1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        addressTextbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextbox1ActionPerformed(evt);
            }
        });

        addressLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        addressLabel.setText("Address:");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        phoneLabel.setText("Phone:");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        emailLabel.setText("Email:");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        passwordLabel.setText("Password:");

        phoneTextbox.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        phoneTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTextboxActionPerformed(evt);
            }
        });

        emailTextbox.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        emailTextbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextboxActionPerformed(evt);
            }
        });

        surnameTextbox1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        surnameTextbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surnameTextbox1ActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonadvisorListActionPerformed(evt);
            }
        });

        nameLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nameLabel1.setText("Name: ");

        nameTextbox2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        nameTextbox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextbox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout advisorBackgroundLayout = new javax.swing.GroupLayout(advisorBackground);
        advisorBackground.setLayout(advisorBackgroundLayout);
        advisorBackgroundLayout.setHorizontalGroup(
            advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(advisorBackgroundLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(surnameTextbox1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(phoneTextbox))
                        .addGap(57, 57, 57)
                        .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advisorBackgroundLayout.createSequentialGroup()
                                .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)))
                        .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordTextbox, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                            .addComponent(addressTextbox1)
                            .addComponent(emailTextbox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(advisorBackgroundLayout.createSequentialGroup()
                    .addGap(257, 257, 257)
                    .addComponent(nameTextbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(613, Short.MAX_VALUE)))
        );
        advisorBackgroundLayout.setVerticalGroup(
            advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advisorBackgroundLayout.createSequentialGroup()
                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(advisorBackgroundLayout.createSequentialGroup()
                            .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12))
                        .addComponent(surnameTextbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emailTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTextbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(advisorBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(advisorBackgroundLayout.createSequentialGroup()
                    .addGap(226, 226, 226)
                    .addComponent(nameTextbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(517, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advisorBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonadvisorListActionPerformed
        // TODO add your handling code here:
           dispose(); 
    }//GEN-LAST:event_backButtonadvisorListActionPerformed

    private void passwordTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextboxActionPerformed

    private void addressTextbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextbox1ActionPerformed

    private void phoneTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTextboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTextboxActionPerformed

    private void emailTextboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextboxActionPerformed

    private void surnameTextbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surnameTextbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_surnameTextbox1ActionPerformed

    private void saveButtonadvisorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonadvisorListActionPerformed
        




         // TODO add your handling code here:
           String sql= "INSERT INTO Staff(ID, password, role, name, address,email,phoneNum) Values(?,?,?,?,?,?,?)";
        try (//Get connection to the database
            Connection con = DbCon.getConnection();
            ){
            
           
            
          pst=con.prepareStatement(sql);
          
          pst.setString(1, null);
          pst.setString(2, passwordTextbox.getText());
          pst.setString(3, "advisor");
          pst.setString(4,nameTextbox2.getText() );
         pst.setString(5, addressTextbox1.getText() );
        pst.setString(6, emailTextbox.getText() ); 
          pst.setString(7, phoneTextbox.getText() );   
        
        
        pst.execute();
          JOptionPane.showMessageDialog(null,"inserted successfully" );
          dispose();
        
        }
        catch (Exception e) {
        JOptionPane.showMessageDialog(null,"error");
        }
        
        
        
    }//GEN-LAST:event_saveButtonadvisorListActionPerformed

    private void nameTextbox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextbox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextbox2ActionPerformed

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
            java.util.logging.Logger.getLogger(Advisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Advisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Advisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Advisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Advisor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextbox1;
    private javax.swing.JPanel advisorBackground;
    private javax.swing.JLabel advisorTitle;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextbox;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JTextField nameTextbox2;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextbox;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextbox;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField surnameTextbox1;
    // End of variables declaration//GEN-END:variables
}

