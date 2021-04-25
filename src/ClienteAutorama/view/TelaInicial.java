package ClienteAutorama.view;

import ClienteAutorama.controller.GerenciadorTelas;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
* Classe da tela inicial.
* 
* @author Víctor César e William Soares.
*/
public class TelaInicial extends javax.swing.JFrame {

    GerenciadorTelas gerenciador;
    
    /**
    * Construtor da tela inicial e onde executa a musica do famoso jogo Top Gear.
    * 
    */
    public TelaInicial() {
        initComponents();
        this.setTitle("Autorama");
        try {
            File music = new File("src//audio//TopGear.wav");
            
            if(music.exists()){
                AudioInputStream audio = AudioSystem.getAudioInputStream(music);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);
                
            } else {
                System.out.println("Sem musica");
            }
            
        } catch (Exception e) {
            System.out.println(e);
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

        imagem1 = new ClienteAutorama.view.Imagem();
        jLabel1 = new javax.swing.JLabel();
        cadastro = new javax.swing.JButton();
        botaoCorrida = new javax.swing.JButton();
        sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 430));

        imagem1.setImg(new ImageIcon("src/imagens/autorama.jpg"));

        jLabel1.setFont(new java.awt.Font("Ink Free", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("TELA INICIAL");

        cadastro.setText("CADASTRO");
        cadastro.setToolTipText("");
        cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroActionPerformed(evt);
            }
        });

        botaoCorrida.setText("CORRIDA");
        botaoCorrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCorridaActionPerformed(evt);
            }
        });

        sair.setText("SAIR");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout imagem1Layout = new javax.swing.GroupLayout(imagem1);
        imagem1.setLayout(imagem1Layout);
        imagem1Layout.setHorizontalGroup(
            imagem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagem1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(imagem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(imagem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(sair, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCorrida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                .addContainerGap(493, Short.MAX_VALUE))
        );
        imagem1Layout.setVerticalGroup(
            imagem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagem1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(72, 72, 72)
                .addComponent(cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCorrida, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Abre a tela cadastro.
    * 
    */
    private void cadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroActionPerformed
        gerenciador = GerenciadorTelas.getInstance();
        gerenciador.setTelaInicial(this);
        gerenciador.abrirTelaCadastro();
    }//GEN-LAST:event_cadastroActionPerformed

    /**
    * Encerra a execução do programa.
    * 
    */
    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_sairActionPerformed

    /**
    * Abre a tela de configuração da corrida.
    * 
    */
    private void botaoCorridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCorridaActionPerformed
        gerenciador = GerenciadorTelas.getInstance();
        if(gerenciador.flag == 2){
            JOptionPane.showMessageDialog(null, "O banco de dados está vazio, por favor faça os cadastros", null, WIDTH);
        } else {
            gerenciador.setTelaInicial(this);
            gerenciador.abrirTelaConfigCorrida();
        }
            
    }//GEN-LAST:event_botaoCorridaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
             
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCorrida;
    private javax.swing.JButton cadastro;
    private ClienteAutorama.view.Imagem imagem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
