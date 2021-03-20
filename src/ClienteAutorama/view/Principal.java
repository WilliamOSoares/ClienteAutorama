package ClienteAutorama.view;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Principal extends javax.swing.JFrame {

    private static Socket cliente;
    
    public Principal() {
        initComponents();
        iniciaCliente();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mensagemText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        resultado = new javax.swing.JLabel();
        msgServer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        msgCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mensagemText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mensagemTextActionPerformed(evt);
            }
        });
        getContentPane().add(mensagemText, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 170, 30));

        jButton1.setText("Enviar Mensagem");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 140, 30));
        getContentPane().add(resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 270, 30));
        getContentPane().add(msgServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 280, 30));

        jLabel1.setText("MetaZap");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));
        getContentPane().add(msgCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 270, 30));

        setSize(new java.awt.Dimension(416, 221));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mensagemTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mensagemTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mensagemTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        msgCliente.setText("Cliente: ''" + mensagemText.getText()+"''");
        enviaMensagem(mensagemText.getText());
        resultado.setText("Servidor: ''recebi''");
        msgServer.setText(recebeMensagem());
    }//GEN-LAST:event_jButton1ActionPerformed

    
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

    private static void iniciaCliente(){
        try {
            cliente = new Socket("augusto.ddns.net", 5021); //augusto.ddns.net 
            System.out.println("Conectado");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private static void enviaMensagem(String msg){
        PrintStream saida;
        try{
            saida = new PrintStream(cliente.getOutputStream());
            saida.println(msg);
        }catch(IOException ex){
            System.out.println("Deu merda pra enviar mensagem");
        }
          
    }
    
    private static String recebeMensagem(){
        Scanner entrada;
        try {
            entrada = new Scanner(cliente.getInputStream());
            while(entrada.hasNextLine()){
                String igual = entrada.nextLine();
                System.out.println(igual);
                return igual;
            }
        } catch (IOException ex) {
            System.out.println("Ferrou, recebeu não");
            return "Servidor recusou";
        }
        return "Servidor recusou";     
        /*
        PrintStream entrada;
        try {
            entrada = new PrintStream(cliente.getOutputStream());
            String retorno = entrada.toString();
            entrada.close();
            return retorno;            
        } catch (IOException ex) {
            System.out.println("Ferrou, recebeu não");
            return "Não foi";
        } */    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField mensagemText;
    private javax.swing.JLabel msgCliente;
    private javax.swing.JLabel msgServer;
    private javax.swing.JLabel resultado;
    // End of variables declaration//GEN-END:variables
}
