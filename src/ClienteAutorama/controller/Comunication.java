package ClienteAutorama.controller;

import ClienteAutorama.model.Arquivo;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Comunication {
    public static Socket cliente;

    private Comunication() {
    }
    
    private static Comunication uniqueInstance = new Comunication();

    public static Comunication getInstance() {
	return uniqueInstance;
    }  
    
    //Inicia a conexão com o ip dado e a porta para se conectar
    public void iniciaCliente(String ip, String porta){
        try {
            int door = Integer.parseInt(porta);
            cliente = new Socket(ip, door); //augusto.ddns.net 
            System.out.println("Conectado");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
    public void enviaMensagem(Arquivo msg){
        try {
            BufferedOutputStream bf = new BufferedOutputStream(cliente.getOutputStream());
            byte[] bytea = new byte[5120];
            bytea = serializarArquivo(msg);
            bf.write(bytea);
            bf.flush();
            bf.close();
            //socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
            PrintStream saida;
            try{
            saida = new PrintStream(cliente.getOutputStream());
            saida.println(msg);
            }catch(IOException ex){
            System.out.println(ex);
            }
        */
    }
    
    
    public String recebeMensagem(){
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
    }
    
    private byte[] serializarArquivo(Arquivo arquivo){
        try {
           ByteArrayOutputStream bao = new ByteArrayOutputStream();
           ObjectOutputStream ous;
           ous = new ObjectOutputStream(bao);
           ous.writeObject(arquivo);
           return bao.toByteArray();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }
    
    
}
