/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.table.DefaultTableModel;
import simplex.Simplex;

/**
 *
 * @author murilo
 */

public class main extends javax.swing.JFrame {
    private Object lista;

    /**
     * Creates new form main
     */
    
    // -- Sobreescrita da função isCellEditable para poder tornar uma celula não editavel
    public class MyTableModel extends DefaultTableModel {

       public MyTableModel(Object[][] tableData, Object[] colNames) {
          super(tableData, colNames);
       }

       public boolean isCellEditable(int row, int column) {
          if(row == 0){
              if(column == 0){
                  return false;
              } else if(column == this.getColumnCount()-2){
                  return false;
              }else if(column == this.getColumnCount()-1){
                  return false;
              }
          }
          return true;
       }
    }
    // ----------------------------------------------------------------------
    
    public main() {
        initComponents(); 
        tblElementos.setVisible(false); //torna a tabela invisivel
        // -- vetor de strings que contem os nomes das colunas
        String[] columnNames = {"",
            "Op",
            "Res"};
        Object[][] data = null; //vetor de objetos que contem o conteudo da tabela
        MyTableModel dtm = new MyTableModel(data, columnNames); // novo tablemodel
        Object[] linha = {"Z"}; 
        dtm.addRow(linha); //adiciona a linha Z ao modelo criado

        tblElementos.setModel(dtm); //atribui o modelo a tabela tblElementos
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
        nVar = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblElementos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        addRestricoes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero de Variaveis:");

        nVar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nVarStateChanged(evt);
            }
        });

        tblElementos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblElementos);

        jButton1.setText("Calcular Simplex");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addRestricoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add.png"))); // NOI18N
        addRestricoes.setText("Restricão");
        addRestricoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRestricoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(nVar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addRestricoes, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nVar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRestricoes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nVarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nVarStateChanged
        // TODO add your handling code here:
        Integer n = (Integer) nVar.getValue(); //pega o valor do componente nVar
        if(n>0){ // se for maior que 0
            tblElementos.setVisible(true); //habilita a tabela de elementos
            
            // -- fará a inserção de uma nova coluna antes da coluna "Op"
            MyTableModel dtm = (MyTableModel)tblElementos.getModel(); 
            String aux = ",";
            for (int i=0; i<n; i++){
                aux += "X"+i;
                aux += ",";
            }
            aux += "Op, Res";
            String[] columnNames = aux.split(",");
            // --------------------------------------------------------
            // -- Varrerá a matriz e salvará seus dados em um vetor de objetos 
            Object[][] data;
            data = new Object[dtm.getRowCount()][dtm.getColumnCount()];
            for(int i=0; i<dtm.getRowCount(); i++){
                for(int j=0; j<dtm.getColumnCount(); j++){
                    Object obj = tblElementos.getValueAt(i, j);
                    data[i][j] = obj;
                }
            }
            MyTableModel novaDtm = new MyTableModel(data, columnNames);
            //dtm.isCellEditable(0, dtm.getColumnCount()-2);

            tblElementos.setModel(novaDtm);
        } else{
            tblElementos.setVisible(false);
            String[] columnNames = {"",
                "Op",
                "Res"};
            Object[][] data = null;
            MyTableModel dtm = new MyTableModel(data, columnNames);
            Object[] linha = {"Z"};
            dtm.addRow(linha);

            tblElementos.setModel(dtm);
        }
    }//GEN-LAST:event_nVarStateChanged

    private void addRestricoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRestricoesActionPerformed
        // TODO add your handling code here:
        
        MyTableModel dtm = (MyTableModel)tblElementos.getModel();
        int n = dtm.getRowCount()-1;
        Object[] linha = {"Rest. "+n};
        dtm.addRow(linha);
        tblElementos.setModel(dtm);
    }//GEN-LAST:event_addRestricoesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addRestricoes.setEnabled(false);
        nVar.setEnabled(false);
        Simplex calculo = new Simplex((int)nVar.getValue(), tblElementos);
        calculo.impressao();
        Integer[] pivo = calculo.buscaPivo();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                main m = new main();
                m.setTitle("Simplex");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRestricoes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner nVar;
    private javax.swing.JTable tblElementos;
    // End of variables declaration//GEN-END:variables
}
