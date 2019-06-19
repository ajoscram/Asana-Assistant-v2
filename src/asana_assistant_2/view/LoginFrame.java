/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asana_assistant_2.view;

import asana_assistant_1.control.ControlException;
import asana_assistant_1.control.IRouter;
import asana_assistant_1.model.User;
import asana_assistant_1.view.View;
import javax.swing.ImageIcon;

/**
 *
 * @author Gabriel
 */
public class LoginFrame extends javax.swing.JFrame {
    private View source;
    private IRouter router;
    
    public LoginFrame(View source) {
        initComponents();
        this.source = source;
        this.router = source.getRouter();
        ImageIcon icon = new ImageIcon("logo.png");
        this.setIconImage(icon.getImage());
        this.setTitle("Asana Assistant");
    }
    /**
     * Creates new form LoginFrame
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoginPanelBackground = new javax.swing.JPanel();
        EmailLabel = new javax.swing.JLabel();
        EmailTextField = new javax.swing.JTextField();
        PasswordLabel = new javax.swing.JLabel();
        PasswordTextField = new javax.swing.JPasswordField();
        LoginButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        SignUpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        LoginPanelBackground.setBackground(new java.awt.Color(29, 38, 52));

        EmailLabel.setFont(new java.awt.Font("Proxima Nova Lt", 0, 20)); // NOI18N
        EmailLabel.setForeground(new java.awt.Color(255, 255, 255));
        EmailLabel.setText("Email:");

        EmailTextField.setFont(new java.awt.Font("Proxima Nova Rg", 0, 16)); // NOI18N

        PasswordLabel.setFont(new java.awt.Font("Proxima Nova Lt", 0, 20)); // NOI18N
        PasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        PasswordLabel.setText("Password:");

        PasswordTextField.setFont(new java.awt.Font("Proxima Nova Rg", 0, 16)); // NOI18N
        PasswordTextField.setText("password");

        LoginButton.setBackground(new java.awt.Color(255, 102, 0));
        LoginButton.setFont(new java.awt.Font("Proxima Nova Rg", 0, 16)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setBorder(null);
        LoginButton.setBorderPainted(false);
        LoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LoginButton.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        LoginButton.setFocusPainted(false);
        LoginButton.setLabel("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_long_clear (2).png"))); // NOI18N

        SignUpButton.setFont(new java.awt.Font("Proxima Nova Rg", 0, 16)); // NOI18N
        SignUpButton.setForeground(new java.awt.Color(0, 153, 255));
        SignUpButton.setText("Sign Up");
        SignUpButton.setBorder(null);
        SignUpButton.setBorderPainted(false);
        SignUpButton.setContentAreaFilled(false);
        SignUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SignUpButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SignUpButtonMouseExited(evt);
            }
        });
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginPanelBackgroundLayout = new javax.swing.GroupLayout(LoginPanelBackground);
        LoginPanelBackground.setLayout(LoginPanelBackgroundLayout);
        LoginPanelBackgroundLayout.setHorizontalGroup(
            LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(411, 411, 411))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelBackgroundLayout.createSequentialGroup()
                .addGroup(LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LoginPanelBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SignUpButton))
                    .addGroup(LoginPanelBackgroundLayout.createSequentialGroup()
                        .addGap(472, 472, 472)
                        .addGroup(LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PasswordLabel)
                            .addComponent(EmailLabel))
                        .addGap(18, 18, 18)
                        .addGroup(LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PasswordTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(EmailTextField)
                            .addComponent(LoginButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(463, 463, 463))
        );
        LoginPanelBackgroundLayout.setVerticalGroup(
            LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelBackgroundLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel2)
                .addGap(135, 135, 135)
                .addGroup(LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailLabel)
                    .addComponent(EmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(LoginPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SignUpButton)
                .addGap(135, 135, 135))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LoginPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1298, 732));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SignUpButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUpButtonMouseEntered
        SignUpButton.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_SignUpButtonMouseEntered

    private void SignUpButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignUpButtonMouseExited
        SignUpButton.setForeground(java.awt.Color.decode("#0099FF"));
    }//GEN-LAST:event_SignUpButtonMouseExited

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        try {
            String email = EmailTextField.getText();
            String password = new String(PasswordTextField.getPassword());
            User user = router.login(email, password);
            new UserFrame(source, this, user).setVisible(true);
            this.EmailTextField.setText("");
            this.PasswordTextField.setText("");
            this.setVisible(false);
        } catch(ControlException ex){
            NewView.displayError(this, ex);
        }
        
        /*this.setVisible(false);
        new UserFrame(this).setVisible(true);*/
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        new SignUpDialog(source,this).setVisible(true);
    }//GEN-LAST:event_SignUpButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField EmailTextField;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel LoginPanelBackground;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}