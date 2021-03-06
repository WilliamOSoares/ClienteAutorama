package ClienteAutorama.view;

import ClienteAutorama.controller.GerenciadorTelas;
import java.awt.Toolkit;

/**
 * Classe da tela do botão virtual.
 * 
 * @author Víctor César e William Soares.
 */
public class ApertouBotao extends javax.swing.JFrame {

    private int code;
    GerenciadorTelas gerenciador;
    
    /**
     * Construtor da tela do botão de acordo com o código de invocação.
     * 
     * @param code código de invocação do botão, que muda o redirecionamento de execução de acordo com ele.
     */
    public ApertouBotao(int code) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/car.png")));
        setResizable(false);
        this.code = code;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        simulaBotao = new javax.swing.JButton();
        mensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        simulaBotao.setText("Botão virtual");
        simulaBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulaBotaoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 101;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 110, 34, 0);
        getContentPane().add(simulaBotao, gridBagConstraints);

        mensagem.setFont(new java.awt.Font("Ink Free", 1, 24)); // NOI18N
        mensagem.setForeground(new java.awt.Color(204, 0, 0));
        mensagem.setText("Esperando o aperto do botão!!!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 44, 0, 56);
        getContentPane().add(mensagem, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Repasse de tela e execução de acordo com o código de invocação da tela.
    * 
    */
    private void simulaBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulaBotaoActionPerformed
        if(code == 0){
            gerenciador = GerenciadorTelas.getInstance();
            gerenciador.setPlay(false);
            gerenciador.mostrarQualificatorio();            
        } else if(code == 1){
            gerenciador = GerenciadorTelas.getInstance();
            gerenciador.mostrarCorrida();
        } else if(code == 2){
            gerenciador = GerenciadorTelas.getInstance();
            gerenciador.setPlay(false);
            gerenciador.iniciarCorrida();
        } else if (code == 3){
            gerenciador = GerenciadorTelas.getInstance();
            gerenciador.voltarParaTelaInicial();
        }
        
    }//GEN-LAST:event_simulaBotaoActionPerformed

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
            java.util.logging.Logger.getLogger(ApertouBotao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApertouBotao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApertouBotao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApertouBotao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApertouBotao(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel mensagem;
    private javax.swing.JButton simulaBotao;
    // End of variables declaration//GEN-END:variables
}
