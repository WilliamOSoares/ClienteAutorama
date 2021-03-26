package ClienteAutorama.view;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClienteAutorama.controller.Comunication;
import ClienteAutorama.controller.Corrida;
import ClienteAutorama.controller.GerenciadorArquivo;
import java.io.File;


public class Principal extends javax.swing.JFrame {

    private static Socket cliente;
    Comunication comunicacao = Comunication.getInstance();
    GerenciadorArquivo arquivo = GerenciadorArquivo.getInstance();
    Corrida corrida = Corrida.getInstance();
    File mensagem;
    
    public Principal() {
        //comunicacao.iniciaCliente();
        
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textIP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        resultado = new javax.swing.JLabel();
        msgServer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        msgCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textPorta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textPortaSerial = new javax.swing.JTextField();
        textAntena = new javax.swing.JTextField();
        textBaudrate = new javax.swing.JTextField();
        textProtocolo = new javax.swing.JTextField();
        textRegiao = new javax.swing.JTextField();
        textPower = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIPActionPerformed(evt);
            }
        });
        getContentPane().add(textIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 170, 30));

        jButton1.setText("Configurar Leitor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 140, 30));
        getContentPane().add(resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 270, 30));
        getContentPane().add(msgServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 280, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("AUTORAMA COM RFID");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 190, 40));
        getContentPane().add(msgCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 270, 30));

        jLabel2.setText("IP");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jLabel3.setText("Configuração da conexão");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jLabel4.setText("Configuração do leitor");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        textPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPortaActionPerformed(evt);
            }
        });
        getContentPane().add(textPorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 120, 30));

        jLabel5.setText("Porta");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        jLabel6.setText("Porta Serial");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jLabel7.setText("Baudrate");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, 20));

        jLabel8.setText("Região");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, -1));

        jLabel9.setText("Número de antenas");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jLabel10.setText("Protocolo");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, -1, -1));

        jLabel11.setText("Potência da leitura");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, -1, -1));

        textPortaSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPortaSerialActionPerformed(evt);
            }
        });
        getContentPane().add(textPortaSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 170, 30));

        textAntena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAntenaActionPerformed(evt);
            }
        });
        getContentPane().add(textAntena, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 170, 30));
        getContentPane().add(textBaudrate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 150, 30));

        textProtocolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textProtocoloActionPerformed(evt);
            }
        });
        getContentPane().add(textProtocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 150, 30));
        getContentPane().add(textRegiao, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 130, 30));

        textPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPowerActionPerformed(evt);
            }
        });
        getContentPane().add(textPower, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 130, 30));

        setSize(new java.awt.Dimension(716, 469));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIPActionPerformed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (!textIP.getText().isEmpty() && !textPorta.getText().isEmpty() && !textPortaSerial.getText().isEmpty() && !textBaudrate.getText().isEmpty() && !textRegiao.getText().isEmpty() && !textAntena.getText().isEmpty() && !textProtocolo.getText().isEmpty() && !textPower.getText().isEmpty()){
            textIP.setText("augusto.ddns.net");
            textPorta.setText("2020");
            textPortaSerial.setText("tmr:///dev/ttyUSB0");
            textBaudrate.setText("230400");
            textRegiao.setText("NA2");
            textAntena.setText("1");
            textProtocolo.setText("GEN2");
            textPower.setText("1500");
            
            comunicacao.iniciaCliente(textIP.getText(), textPorta.getText());
            comunicacao.start();
            arquivo.configInicialLeitor(textPortaSerial.getText(), textBaudrate.getText(), textRegiao.getText(), textAntena.getText(), textProtocolo.getText(), textPower.getText());
            comunicacao.POSTconfigLeitor(textPortaSerial.getText(), textBaudrate.getText(), textRegiao.getText(), textAntena.getText(), textProtocolo.getText(), textPower.getText());
            while(true){
                Scanner s = new Scanner(System.in);
                System.out.printf("fala:");
                int k = s.nextInt();
                if(k == 1){
                    break;
                }
            }
            comunicacao.testando();
            
        }
        
        msgCliente.setText("Cliente: ''" + textIP.getText()+"''");
        //comunicacao.enviaMensagem(textIP.getText());
        resultado.setText("Servidor: ''recebi''");
        msgServer.setText("AAAAAAAAAAAAAAH");
        //corrida.getEPC(comunicacao.recebeMensagem());
        //this.setEnabled(false);
        //this.setVisible(false);
        //TelaInicial telaInicial = new TelaInicial();
        //telaInicial.setLocationRelativeTo(this);
        //telaInicial.setVisible(true);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPortaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPortaActionPerformed

    private void textProtocoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textProtocoloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textProtocoloActionPerformed

    private void textPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPowerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPowerActionPerformed

    private void textAntenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAntenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAntenaActionPerformed

    private void textPortaSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPortaSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPortaSerialActionPerformed

    
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel msgCliente;
    private javax.swing.JLabel msgServer;
    private javax.swing.JLabel resultado;
    private javax.swing.JTextField textAntena;
    private javax.swing.JTextField textBaudrate;
    private javax.swing.JTextField textIP;
    private javax.swing.JTextField textPorta;
    private javax.swing.JTextField textPortaSerial;
    private javax.swing.JTextField textPower;
    private javax.swing.JTextField textProtocolo;
    private javax.swing.JTextField textRegiao;
    // End of variables declaration//GEN-END:variables
}
