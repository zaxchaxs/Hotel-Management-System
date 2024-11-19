/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kamar;

import DatabaseInstance.Database;
import DatabaseInstance.Database.ambilKamar;
import DatabaseInstance.DatabaseResultResponse;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

/**
 *
 * @author USER
 */
public class PesanKamar extends javax.swing.JFrame {
    private Database db;
    DefaultTableModel tableKmr;

    /**
     * Creates new form DashboardAdmin
     */
    public PesanKamar() {
        initComponents();
        setTitle("Hotel Management System");
        setVisible(false);
        setLocationRelativeTo(null);
        setBackgroundMenu("/Images/menu background.jpg");
        db = new Database();
        initializeTableModel();
        try {
            dataKamar(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void initializeTableModel() {
        tableKmr = new DefaultTableModel();
        tableKmr.setColumnIdentifiers(new String[]{"Room Number", "Room Name", "Price", "Room Type","Status"});
        tableKamar.setModel(tableKmr);
    }
    
    public final void dataKamar() throws SQLException{
        if (db == null) {
            db = new Database();
        }
        
        try {
        ambilKamar dataKamar = db.new ambilKamar(db.getConnection());
        ResultSet rs = dataKamar.getKamars();
        
        while(rs.next()){
            Object[] row = new Object[5];
                row[0] = rs.getString("id");
                row[1] = rs.getString("name");
                row[2] = rs.getString("price");
                row[3] = rs.getString("type");
                tableKmr.addRow(row);
        }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        bgImage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Harga = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKamar = new javax.swing.JTable();
        roomField = new javax.swing.JTextField();
        Harga1 = new javax.swing.JLabel();
        Harga3 = new javax.swing.JLabel();
        cIField = new javax.swing.JTextField();
        Harga4 = new javax.swing.JLabel();
        cOField = new javax.swing.JTextField();
        day = new javax.swing.JLabel();
        dayField = new javax.swing.JTextField();
        Harga2 = new javax.swing.JLabel();
        customerField = new javax.swing.JTextField();
        paymentField = new javax.swing.JComboBox<>();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new Dimension(1080, 720));

        jButton1.setText("Pesan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Harga.setText("Nomor Kamar");

        tableKamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nomor", "Tipe", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableKamar);

        roomField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomFieldActionPerformed(evt);
            }
        });

        Harga1.setText("add payment method");

        Harga3.setText("Check-in-date");

        cIField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cIFieldActionPerformed(evt);
            }
        });

        Harga4.setText("Check-out-date");

        cOField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cOFieldActionPerformed(evt);
            }
        });
        cOField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cOFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cOFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cOFieldKeyTyped(evt);
            }
        });

        day.setText("day");

        dayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayFieldActionPerformed(evt);
            }
        });

        Harga2.setText("Customer Id");

        customerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerFieldActionPerformed(evt);
            }
        });

        paymentField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Livin Mandiri", "BCA", "BRI", "BNI" }));
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bgImage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roomField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Harga2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Harga4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Harga3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cIField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cOField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roomField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Harga1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Harga3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cIField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Harga2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Harga4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cOField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
        // Validate all required fields first
        String roomId = roomField.getText();
        if (roomId == null || roomId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon masukkan nomor kamar dengan benar");
            return;
        }
        
        String customerText = customerField.getText();
        int customerId;
        try {
            customerId = Integer.parseInt(customerText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mohon masukkan angka yang valid untuk Customer ID");
            return;
        }
        
        String dayText = dayField.getText();
        int dayReserved;
        try {
            dayReserved = Integer.parseInt(dayText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mohon masukkan angka yang valid untuk Lama hari");
            return;
        }
        
        // Validate dates
        String cI = cIField.getText();
        String cO = cOField.getText();
        if (cI == null || cO == null || cI.isEmpty() || cO.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon masukkan cek in dan cek out");
            return;
        }
        
        try {
            LocalDate checkInDate = LocalDate.parse(cI); 
            LocalDate checkOutDate = LocalDate.parse(cO);
            if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
                JOptionPane.showMessageDialog(this, "Check-out date must be after the check-in date.");
                cOField.setText("");
                return;
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Please enter dates in the format YYYY-MM-DD.");
            return;
        }
        
        // Calculate total price
        double priceTotal = 0.0;
        String roomType = null;
        String roomPrice = null;
        
         try {
            ambilKamar dataKamar = db.new ambilKamar(db.getConnection());
            ResultSet rs = dataKamar.getKamars();
            boolean roomFound = false;
            
            while (rs.next()) {
                String noDb = rs.getString("id");
                if (roomId.equals(noDb)) { 
                    roomType = rs.getString("type");
                    roomPrice = rs.getString("price");
                    try {
                        double pricePerDay = Double.parseDouble(roomPrice);
                        priceTotal = pricePerDay * dayReserved;
                        roomFound = true;
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid price format in database");
                        return;
                    }
                }
            }
            
            if (!roomFound) {
                JOptionPane.showMessageDialog(this, "Room not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error accessing room data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String paymentDate = LocalDate.now().toString();
        String paymentMethod = paymentField.getSelectedItem().toString().toLowerCase();
        String status = "pending";
        String paymentId = UUID.randomUUID().toString();
        
        DatabaseResultResponse paymentResponse = db.addPayment(paymentId, priceTotal, paymentDate, paymentMethod, status);
        if (paymentResponse.status == 201) {
            // Create reservation
            DatabaseResultResponse reservationResponse = db.postReserved(
                paymentId, 
                customerId, 
                roomId, 
                cI,  
                cO,  
                dayReserved,
                priceTotal,
                status
            );
            
            if (reservationResponse.status == 201) {
                JOptionPane.showMessageDialog(this, 
                    "Reservation created successfully!\nTotal Price: " + priceTotal, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                Confirm confirmPage = new Confirm(roomType, roomPrice);
                confirmPage.setVisible(true);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to create reservation: " + reservationResponse.message, 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Payment failed: " + paymentResponse.message, 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "An error occurred: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearFields() {
    roomField.setText("");
    customerField.setText("");
    cIField.setText("");
    cOField.setText("");
    dayField.setText("");
}
    private void roomFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomFieldActionPerformed

    private void cIFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cIFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cIFieldActionPerformed

    private void cOFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cOFieldActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_cOFieldActionPerformed

    private void dayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayFieldActionPerformed

    private void customerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerFieldActionPerformed

    private void cOFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cOFieldKeyTyped
    
    }//GEN-LAST:event_cOFieldKeyTyped

    private void cOFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cOFieldKeyReleased

    }//GEN-LAST:event_cOFieldKeyReleased

    private void cOFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cOFieldKeyPressed

    }//GEN-LAST:event_cOFieldKeyPressed

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentFieldKeyReleased

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
            java.util.logging.Logger.getLogger(PesanKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesanKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesanKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesanKamar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new PesanKamar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Harga;
    private javax.swing.JLabel Harga1;
    private javax.swing.JLabel Harga2;
    private javax.swing.JLabel Harga3;
    private javax.swing.JLabel Harga4;
    private javax.swing.JLabel bgImage;
    private javax.swing.JTextField cIField;
    private javax.swing.JTextField cOField;
    private javax.swing.JTextField customerField;
    private javax.swing.JLabel day;
    private javax.swing.JTextField dayField;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> paymentField;
    private javax.swing.JTextField roomField;
    private javax.swing.JTable tableKamar;
    // End of variables declaration//GEN-END:variables
}
