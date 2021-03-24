package ClienteAutorama.controller;

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
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    
    
    public void enviaMensagem(File msg){
        try {
            BufferedReader br = new BufferedReader(new FileReader(msg));
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
    
    
    public ArrayList<String> recebeMensagem(){
        Scanner entrada;
        try {
            entrada = new Scanner(cliente.getInputStream());
            ArrayList<String> arrayEPC = new ArrayList();
            int i=0;
            while(entrada.hasNextLine()){
                String igual = entrada.nextLine();
                System.out.println(igual);
                arrayEPC.add(igual);
            }
            return arrayEPC;
        } catch (IOException ex) {
            System.out.println("Ferrou, recebeu não");
            System.out.println(ex);
        }
        return null;
        /*
        try {
            InputStream os = cliente.getInputStream();
            while (os.) {
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
               
                for(int i = 0; i<10000;i++){ }
                os.flush();
            }
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */
        
    }    
    
}
