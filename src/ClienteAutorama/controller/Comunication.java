package ClienteAutorama.controller;

import ClienteAutorama.model.Arquivo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
            
            BufferedReader br = new BufferedReader(new FileReader(msg.getArquivo()));
            String line;
            OutputStream os = cliente.getOutputStream();
            while ((line = br.readLine()) != null) {
                // process the line.
                String rawString = line.concat("\n");
                ByteBuffer buffer = StandardCharsets.UTF_8.encode(rawString); 
                System.out.println(line);
                System.out.println(buffer.array().length);
                // A cada 8 bytes ele envia um 0x00 no final da mensagem, este if tira este caracter
                if(buffer.array().length <= 8){
                    os.write(buffer.array(), 0, buffer.array().length);
                } else {
                    os.write(buffer.array(), 0, buffer.array().length -1);
                }
               
                for(int i = 0; i<10000;i++){/*Wait*/}
                os.flush();
            }
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
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
           ous.writeObject(arquivo.getArquivo());
           return bao.toByteArray();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }
    
    
}
