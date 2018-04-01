/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agricultureproject;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dead
 */
public class FormPlantSetup extends javax.swing.JFrame {

    /**
     * Creates new form FormPlantSetup
     */
    String id = "0";
    HashMap hm = new HashMap();
    DatabaseConnection dc = new DatabaseConnection();
    Border empty = BorderFactory.createLineBorder(Color.red, 1);
    Border notEmpty = BorderFactory.createLineBorder(Color.gray, 1);

    public FormPlantSetup() {
        initComponents();
    }
    
    public FormPlantSetup(String a) {
        initComponents();
        this.id = a;
        btnSave.setText("Update");
        updateDiseaseCureForm();
    }
    
    public void updateDiseaseCureForm()
    {
        System.out.println("Update : " +id);
        HashMap<Integer,HashMap> plant_mst_setup = new HashMap<>();

        plant_mst_setup = dc.getAllInformation("plant_setup_mst_tb"," where id = "+id);
      
        for (Object key : plant_mst_setup.keySet())
        {
            txtPlantName.setText(plant_mst_setup.get(key).get("plant_name").toString());
            txtPlantDescription.setText(plant_mst_setup.get(key).get("description").toString());        
            //model.addRow(new Object[]{plant_setup.get(key).get("id"),plant_setup.get(key).get("plant_name"),plant_setup.get(key).get("description")});
        } 
        
        DefaultTableModel model = (DefaultTableModel) tablePlantSetup.getModel();
        model.setRowCount(0);
        HashMap<Integer,HashMap> plant_chd_setup = new HashMap<>();

        plant_chd_setup = dc.getAllInformation("plant_setup_chd_tb"," where plant_id = "+id);
      
        for (Object key : plant_chd_setup.keySet())
        {
            //comboPlantName.addItem(plant_setup.get(key).get("plant_name").toString());
            model.addRow(new Object[]{plant_chd_setup.get(key).get("id"),plant_chd_setup.get(key).get("days"),plant_chd_setup.get(key).get("steps_desc")});
        }
    }
    
    public boolean checkInput()
    {        
        //System.out.println(jTable2.getColumnCount()-1);
        if(txtPlantName.getText().trim().equals(""))
        {
            txtPlantName.setBorder(empty);
            return false;     
        }
        else
        {
            txtPlantName.setBorder(notEmpty);
        }
        int row=0,column=0;
        try
        {
            for(row=0; row<tablePlantSetup.getRowCount();row++)
            {
                for(column=0; column<tablePlantSetup.getColumnCount();column++)
                {
                    if(tablePlantSetup.getValueAt(row, column).toString().trim().equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"Found Empty cell at row = "+row + " column =  "+ column);
                        return false;
                    }
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Found Empty cell at row = "+row + " column =  "+ column);
            tablePlantSetup.editCellAt(row, column);
            return false;
        }
        
        
        System.out.println("All true");
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPlantName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPlantDescription = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePlantSetup = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Plant Name : ");

        txtPlantName.setToolTipText("Enter Plant Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Plant Description");

        txtPlantDescription.setColumns(20);
        txtPlantDescription.setRows(5);
        jScrollPane1.setViewportView(txtPlantDescription);

        tablePlantSetup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablePlantSetup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "id", "Day", "Work Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePlantSetup.setColumnSelectionAllowed(true);
        tablePlantSetup.setGridColor(new java.awt.Color(204, 204, 204));
        tablePlantSetup.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tablePlantSetup);
        tablePlantSetup.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablePlantSetup.getColumnModel().getColumnCount() > 0) {
            tablePlantSetup.getColumnModel().getColumn(0).setMinWidth(0);
            tablePlantSetup.getColumnModel().getColumn(0).setMaxWidth(0);
            tablePlantSetup.getColumnModel().getColumn(1).setMinWidth(40);
            tablePlantSetup.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        jButton2.setText("add row");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("remove row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                        .addComponent(jScrollPane4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPlantName, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPlantName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tablePlantSetup.getModel();
        model.addRow(new Object[]{1,null,null});
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try
        {
            DefaultTableModel model = (DefaultTableModel)tablePlantSetup.getModel();       
            int i= tablePlantSetup.getSelectedRow();
            if(i>=0)
            {
                model.removeRow(i);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please select a row from the table");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error in removing row");
        } 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        //System.out.println(dc.maxId("plant_setup_mst_tb", "id"));
        boolean check = checkInput();
        System.out.println(check);
        if(check == true)
        {
            // Create a hash map
            hm.clear();
            // Put elements to the map
            hm.put("plant_name", txtPlantName.getText());
            hm.put("description", txtPlantDescription.getText());

            boolean mstSuccess = dc.insertData("plant_setup_mst_tb",hm);
            
            boolean chdSuccess = false;            
            for(int row=0; row<tablePlantSetup.getRowCount();row++)
            {
                hm.clear();
                hm.put("plant_id", dc.maxId("plant_setup_mst_tb", "id"));
                hm.put("days", tablePlantSetup.getValueAt(row, 0).toString().trim());
                hm.put("steps_desc", tablePlantSetup.getValueAt(row, 1).toString().trim());
                chdSuccess = dc.insertData("plant_setup_chd_tb",hm);
            }

            if(mstSuccess == true && chdSuccess==true)
            {
                JOptionPane.showMessageDialog(null,"Data Saved");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Error: Inserting Plant Information");
        }
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(FormPlantSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPlantSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPlantSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPlantSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPlantSetup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablePlantSetup;
    private javax.swing.JTextArea txtPlantDescription;
    private javax.swing.JTextField txtPlantName;
    // End of variables declaration//GEN-END:variables
}