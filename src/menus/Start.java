/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package menus;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Irzi Rhmtllh
 */
public class Start extends javax.swing.JFrame {

    /**
     * Creates new form Start2
     */
    public Start() {
        initComponents();
        setTitle("Hotel Management System");
        setLocationRelativeTo(null); 
        setBackgroundMenu("/Images/menu background.jpg");
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        jProgressBar1.setValue(i);
                        Thread.sleep(50);
//                        if(true) {
                        if(jProgressBar1.getString().equals("100%")) {
                            new SignIn().setVisible(true);
                            setVisible(false);
                            return;
                        }
                        if(jProgressBar1.getString().equals("50%")){
                            loading.setText("Loading Modules.....");
                            
                        }
                        if(jProgressBar1.getString().equals("25%")){
                            loading.setText("Connecting Database....");
                            }
                        if(jProgressBar1.getString().equals("95%"))
                            loading.setText("Launching Aplication....");
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();        

    }
    private void setBackgroundMenu(String urlImg) {

        Dimension screenSize = new Dimension(1080, 720);

        // background menu
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(urlImg));
        Image scaledImage = originalIcon.getImage().getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        bgImage.setIcon(scaledIcon);
        bgImage.setText("");
        bgImage.setSize(screenSize);
        getContentPane().setLayout(null);
        getContentPane().add(bgImage);
        bgImage.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
    };
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        bgImage = new javax.swing.JLabel();
        loading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1080, 720));
        setPreferredSize(new Dimension(1080, 720));

        bgImage.setText("  ");

        loading.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        loading.setForeground(new java.awt.Color(255, 255, 255));
        loading.setText("Loading...");
        loading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Handycheera", 1, 56)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GoLiv Hotel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgImage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(404, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(374, 374, 374))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(loading)
                .addGap(277, 277, 277))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel loading;
    // End of variables declaration//GEN-END:variables
}
