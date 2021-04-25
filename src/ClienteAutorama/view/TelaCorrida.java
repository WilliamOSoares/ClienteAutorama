package ClienteAutorama.view;

import ClienteAutorama.controller.GerenciadorTelas;
import ClienteAutorama.model.ModeloTabelaCorrida;
import ClienteAutorama.model.Piloto;
import java.awt.Toolkit;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Classe da tela da corrida.
 * 
 * @author Víctor César e William Soares.
 */
public class TelaCorrida extends javax.swing.JFrame {

    GerenciadorTelas gerenciador;
    ModeloTabelaCorrida modelo;
    public ArrayList<Piloto> pilotos; 
    public int voltas = 0, VoltasN =0;
    public boolean play = true;

    /**
     * Construtor da tela de corrida configurada com os dados recebidos da configuração de corrida e do qualificatório.
     */
    public TelaCorrida() {
        initComponents();
        this.setTitle("Autorama");  
        setIconImage(Toolkit.getDefaultToolkit().getImage("car.png"));
        gerenciador = GerenciadorTelas.getInstance();
        localCorrida.setText(gerenciador.corrida.pistaLocal.getNome()+" - "+gerenciador.corrida.pistaLocal.getPais());
        tempoRecord.setText(gerenciador.corrida.pistaLocal.getTempoRecordPista());
        autorRecord.setText(gerenciador.corrida.pistaLocal.getRecordista());
        nVoltas.setText("0 de "+Integer.toString(gerenciador.corrida.numVoltas)+" Voltas");
        this.pilotos = gerenciador.corrida.pilotos;
        voltas = gerenciador.corrida.getNumVoltas();
        for (int i = 0; i < this.pilotos.size(); i++) {
            pilotos.get(i).setMelhorMili(0);
            pilotos.get(i).setMelhorSec(0);
            pilotos.get(i).setVoltas(0);
            pilotos.get(i).setTempoSec(0);
            pilotos.get(i).setTempoMili(0);
            pilotos.get(i).setPrimeiraLeitura(true);
        }
        gerenciador.corrida.pilotos = this.pilotos;
        gerenciador.bancoDados.serealiza();
        gerenciador.corrida.setFimQuali(true);
        gerenciador.corrida.setFimCorrida(false);
        gerenciador.corrida.leitura.clear();
        this.modelo = new ModeloTabelaCorrida(pilotos);
        tabelaCorrida.setModel(modelo);
        gerenciador.setTelaCorrida(this);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nVoltas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        localCorrida = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        autorRecord = new javax.swing.JLabel();
        tempoRecord = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCorrida = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corrida", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ink Free", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Sessão de Corrida:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Local da corrida:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Record da pista:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Autor:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(localCorrida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nVoltas, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tempoRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(autorRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nVoltas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempoRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localCorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autorRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        jLabel3.setText("Classificação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tabelaCorrida.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaCorrida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    
    /**
    * @deprecated Metódo de execução de tela para depuração 
    * @param args Argumentos de entrada.
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
            java.util.logging.Logger.getLogger(TelaCorrida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCorrida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCorrida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCorrida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCorrida().setVisible(true);
            }
        });
    }
    
    /**
    * Thread de SwingWorker para alteração dos dados na tela corrida.
    */
    private void start(){
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
                GerenciadorTelas gerenciador;
                gerenciador = GerenciadorTelas.getInstance();
                while(gerenciador.play){gerenciador = GerenciadorTelas.getInstance();}
                Contagem cont;
                for (int i = 0; i < 5; i++) {
                    int s = 5 - i;
                    cont = new Contagem(Integer.toString(s));
                    cont.setLocationRelativeTo(gerenciador.telaQuali);
                    cont.setVisible(true);
                    cont.setEnabled(true);                    
                    Thread.sleep(1000);
                    cont.setVisible(false);
                    cont.setEnabled(false);
                }
                cont = new Contagem("VAI");
                cont.setLocationRelativeTo(gerenciador.telaQuali);
                cont.setVisible(true);
                cont.setEnabled(true);
                VoltasN = 0;
                int voltaPrim = pilotos.get(0).getVoltas();
                Thread.sleep(1000);
                while(voltas>=0){
                    cont.setVisible(false);
                    cont.setEnabled(false);
                    gerenciador.setPlay(true);
                    nVoltas.setText(Integer.toString(VoltasN)+ " de " + Integer.toString(gerenciador.corrida.getNumVoltas()) + " Voltas");
                    VoltasN++;                    
                    while(voltaPrim != VoltasN){
                        voltaPrim = gerenciador.corrida.pilotos.get(0).getVoltas();
                        gerenciador.telaCorrida.modelo.setArray(gerenciador.telaCorrida.pilotos);
                        gerenciador.telaCorrida.autorRecord.setText(gerenciador.corrida.pistaLocal.getRecordista());
                        gerenciador.telaCorrida.tempoRecord.setText(gerenciador.corrida.pistaLocal.getTempoRecordPista());
                    }                    
                    voltas--;                
                }
                //VoltasN++;  
                nVoltas.setText(Integer.toString(VoltasN)+ " de " + Integer.toString(gerenciador.corrida.getNumVoltas()) + " Voltas");
                nVoltas.setText("Acabou a corrida");
                
                while (!gerenciador.corrida.isFimCorrida()){
                   gerenciador.telaCorrida.modelo.setArray(gerenciador.telaCorrida.pilotos);
                   gerenciador.telaCorrida.autorRecord.setText(gerenciador.corrida.pistaLocal.getRecordista());
                   gerenciador.telaCorrida.tempoRecord.setText(gerenciador.corrida.pistaLocal.getTempoRecordPista());
                }

            return null;
            }
        };

        worker.execute();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autorRecord;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel localCorrida;
    private javax.swing.JLabel nVoltas;
    private javax.swing.JTable tabelaCorrida;
    private javax.swing.JLabel tempoRecord;
    // End of variables declaration//GEN-END:variables
}