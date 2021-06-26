package clientetelespectador;

import java.awt.Toolkit;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Classe controlador da Tela do Qualificatório.
 * 
 * @author Víctor César e William Soares.
 */
public class TelaQuali extends javax.swing.JFrame {
    
    ClienteTelespectador gerenciador;
    public ModeloTabelaQuali modelo;
    public int tempoDoQualiMin;
    public int tempoDoQualiSec;
    public ArrayList<Piloto> pilotos;
    
    public TelaQuali() {
        initComponents();
        this.setTitle("Autorama");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("car.png")));
        setResizable(false);
        gerenciador = ClienteTelespectador.getInstance();
        localCorrida.setText(gerenciador.getNomePista());
        tempoRecord.setText(gerenciador.getRecord());
        autorRecord.setText(gerenciador.getRecordista());
        tempoDoQualiMin = Integer.parseInt(gerenciador.getTempoQualiMin());
        tempoDoQualiSec = Integer.parseInt(gerenciador.getTempoQualiSec());
        tempoQuali.setText(gerenciador.getTempoQualiMin()+":"+gerenciador.getTempoQualiSec());
        this.pilotos = gerenciador.getPilotos();
        this.modelo = new ModeloTabelaQuali(gerenciador.getPilotos());
        tabelaQuali.setModel(modelo);
        for (int i = 0; i < pilotos.size(); i++) {
            if(i==0){
                piloto1.setText(pilotos.get(i).getNome());
            } else if(i==1){
                piloto2.setText(pilotos.get(i).getNome());
            } else if(i==2){
                piloto3.setText(pilotos.get(i).getNome());
            } else if(i==3){
                piloto4.setText(pilotos.get(i).getNome());
            } else if(i==4){
                piloto5.setText(pilotos.get(i).getNome());
            }
        }   
        gerenciador.setQuali(this);
        this.start();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tempoQuali = new javax.swing.JLabel();
        localCorrida = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        autorRecord = new javax.swing.JLabel();
        tempoRecord = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaQuali = new javax.swing.JTable();
        voltar = new javax.swing.JButton();
        corrida = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        piloto1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        piloto2 = new javax.swing.JButton();
        piloto3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        piloto4 = new javax.swing.JButton();
        piloto5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qualificatório", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ink Free", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Sessão de qualificação:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Local da corrida:");

        tempoQuali.setText("aa");

        localCorrida.setText("a");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Record da pista:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Autor:");

        autorRecord.setText("a");

        tempoRecord.setText("a");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(localCorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(autorRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(tempoQuali, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tempoRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tempoRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tempoQuali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(autorRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(localCorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        jLabel3.setText("Classificação");

        tabelaQuali.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Lewis Hamilton", "Mercedes", "1:15:024", "10"},
                {"2", "Valtteri Bottas", "Mercedes", "1:20:030", "8"},
                {"3", "Sebastian Vettel", "Ferrari", "1:25:400", "7"},
                {"4", "Felipe Massa", "Ferrari", "1:25:800", "20"},
                {"5", "Airton Sena", "N/A", "1:30:500", "1"}
            },
            new String [] {
                "Pos", "Piloto", "Equipe", "Tempo de volta", "Voltas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaQuali.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaQuali);

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        corrida.setText("Corrida");
        corrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corridaActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("1º");

        piloto1.setText("Sem Piloto");
        piloto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piloto1ActionPerformed(evt);
            }
        });

        jLabel7.setText("2º");

        piloto2.setText("Sem Piloto");
        piloto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piloto2ActionPerformed(evt);
            }
        });

        piloto3.setText("Sem Piloto");
        piloto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piloto3ActionPerformed(evt);
            }
        });

        jLabel8.setText("3º");

        piloto4.setText("Sem Piloto");
        piloto4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piloto4ActionPerformed(evt);
            }
        });

        piloto5.setText("Sem Piloto");
        piloto5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piloto5ActionPerformed(evt);
            }
        });

        jLabel9.setText("4º");

        jLabel10.setText("5º");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(piloto1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piloto2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piloto3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piloto4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piloto5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel6)
                .addGap(114, 114, 114)
                .addComponent(jLabel7)
                .addGap(116, 116, 116)
                .addComponent(jLabel8)
                .addGap(115, 115, 115)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(piloto1)
                    .addComponent(piloto2)
                    .addComponent(piloto3)
                    .addComponent(piloto4)
                    .addComponent(piloto5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(voltar)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(corrida))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(corrida)
                        .addComponent(voltar))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        gerenciador.telaQualiParaInicio();
    }//GEN-LAST:event_voltarActionPerformed

    private void corridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corridaActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(gerenciador.getEtapa()==null){
            JOptionPane.showMessageDialog(null, "Nem o qualificatório e nem a corrida começou. Espere!!!", null, WIDTH); 
        }else if(gerenciador.getEtapa().equals("quali")){
            JOptionPane.showMessageDialog(null, "O qualificatório ainda não acabou!!!", null, WIDTH);            
        } else if(gerenciador.getEtapa().equals("corrida")){
            for (int i = 0; i < gerenciador.pilotos.size(); i++) {
                gerenciador.pilotos.get(i).setVoltas("0");
                gerenciador.pilotos.get(i).setTempoVolta("0:00.000");
                gerenciador.pilotos.get(i).setTempoMelhor("0:00.000");
                gerenciador.pilotos.get(i).setTempoGeral("00:00.000");
                gerenciador.pilotos.get(i).setTempoCima("00.000");
                gerenciador.pilotos.get(i).setTempoBaixo("00.000");
            }            
            gerenciador.telaQualiParaCorrida();
        }
    }//GEN-LAST:event_corridaActionPerformed

    private void piloto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piloto1ActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(gerenciador.getPilotos().size()>=1){    
            TelaPiloto piloto = new TelaPiloto(gerenciador.getPilotos(),0, "Quali");
            gerenciador.setPilot(piloto);
            gerenciador.telaQualiParaPiloto();
        } else{
            JOptionPane.showMessageDialog(null, "Só existem "+ gerenciador.getPilotos().size() +" pilotos!!", null, WIDTH); 
        }
    }//GEN-LAST:event_piloto1ActionPerformed

    private void piloto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piloto2ActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(gerenciador.getPilotos().size()>=2){ 
            TelaPiloto piloto = new TelaPiloto(gerenciador.getPilotos(),1, "Quali");
            gerenciador.setPilot(piloto);
            gerenciador.telaQualiParaPiloto();
        } else{
            JOptionPane.showMessageDialog(null, "Só existem "+ gerenciador.getPilotos().size() +" pilotos!!", null, WIDTH); 
        }    
    }//GEN-LAST:event_piloto2ActionPerformed

    private void piloto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piloto3ActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(gerenciador.getPilotos().size()>=3){ 
            TelaPiloto piloto = new TelaPiloto(gerenciador.getPilotos(),2, "Quali");
            gerenciador.setPilot(piloto);
            gerenciador.telaQualiParaPiloto();
        } else{
            JOptionPane.showMessageDialog(null, "Só existem "+ gerenciador.getPilotos().size() +" pilotos!!", null, WIDTH); 
        }
    }//GEN-LAST:event_piloto3ActionPerformed

    private void piloto4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piloto4ActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(gerenciador.getPilotos().size()>=4){ 
            TelaPiloto piloto = new TelaPiloto(gerenciador.getPilotos(),3, "Quali");
            gerenciador.setPilot(piloto);
            gerenciador.telaQualiParaPiloto();
        } else{
            JOptionPane.showMessageDialog(null, "Só existem "+ gerenciador.getPilotos().size() +" pilotos!!", null, WIDTH); 
        }
    }//GEN-LAST:event_piloto4ActionPerformed

    private void piloto5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piloto5ActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(gerenciador.getPilotos().size()>=5){ 
            TelaPiloto piloto = new TelaPiloto(gerenciador.getPilotos(),4, "Quali");
            gerenciador.setPilot(piloto);
            gerenciador.telaQualiParaPiloto();
        } else{
            JOptionPane.showMessageDialog(null, "Só existem "+ gerenciador.getPilotos().size() +" pilotos!!", null, WIDTH); 
        }
    }//GEN-LAST:event_piloto5ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaQuali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaQuali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaQuali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaQuali.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaQuali().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autorRecord;
    private javax.swing.JButton corrida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel localCorrida;
    private javax.swing.JButton piloto1;
    private javax.swing.JButton piloto2;
    private javax.swing.JButton piloto3;
    private javax.swing.JButton piloto4;
    private javax.swing.JButton piloto5;
    private javax.swing.JTable tabelaQuali;
    private javax.swing.JLabel tempoQuali;
    private javax.swing.JLabel tempoRecord;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables

    /**
    * Thread de SwingWorker para alteração dos dados na tela do qualificatório.
    */
    private void start(){
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
                ClienteTelespectador gerenciador;
                gerenciador = ClienteTelespectador.getInstance();
                Thread.sleep(1000);
                tempoDoQualiMin = Integer.parseInt(gerenciador.getTempoQualiMin());
                Boolean flag = false;
                int sec = Integer.parseInt(gerenciador.getTempoQualiSec());
                while(tempoDoQualiMin>=0 || tempoDoQualiSec>0){
                    gerenciador = ClienteTelespectador.getInstance();          
                    tempoDoQualiMin = Integer.parseInt(gerenciador.getTempoQualiMin());
                    sec = Integer.parseInt(gerenciador.getTempoQualiSec()); 
                    if(sec<10){
                        gerenciador.quali.tempoQuali.setText(tempoDoQualiMin + ":0" + sec);
                    } else{
                        gerenciador.quali.tempoQuali.setText(tempoDoQualiMin + ":" + sec);
                    } 
                    gerenciador.quali.modelo.setArray(gerenciador.getPilotos());
                    gerenciador.quali.tempoRecord.setText(gerenciador.getRecord());
                    gerenciador.quali.autorRecord.setText(gerenciador.getRecordista());
                    for (int i = 0; i < gerenciador.pilotos.size(); i++) {
                        if(i==0){
                            gerenciador.quali.piloto1.setText(gerenciador.pilotos.get(i).getNome());
                        } else if(i==1){
                            gerenciador.quali.piloto2.setText(gerenciador.pilotos.get(i).getNome());
                        } else if(i==2){
                            gerenciador.quali.piloto3.setText(gerenciador.pilotos.get(i).getNome());
                        } else if(i==3){
                            gerenciador.quali.piloto4.setText(gerenciador.pilotos.get(i).getNome());
                        } else if(i==4){
                            gerenciador.quali.piloto5.setText(gerenciador.pilotos.get(i).getNome());
                        }
                    }
                    if(gerenciador.etapa.equals("corrida")){
                        break;
                    }
                }               
                while (tempoDoQualiMin==0){
                    tempoQuali.setText("Qualificatório finalizado");
                    gerenciador.quali.modelo.setArray(gerenciador.quali.pilotos);
                    if(gerenciador.etapa.equals("corrida")){
                        tempoDoQualiMin--;
                    }
                    gerenciador.quali.tempoRecord.setText(gerenciador.getRecord());
                    gerenciador.quali.autorRecord.setText(gerenciador.getRecordista());
                }
            return null;
            }
        };

        worker.execute();
    }
}
