package ClienteAutorama.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


public class Comunication implements Runnable{
    public static Socket cliente;  
    public String ip;
    public int door;    
    public BufferedWriter saida;
    public Scanner entrada;
    public JSONObject recebido;
    public boolean online = false;
    
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
            this.ip = ip;
            this.door = door;
            cliente = new Socket(ip, door); //augusto.ddns.net 
            saida = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            entrada = new Scanner(cliente.getInputStream());
            System.out.println("Conectado");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void POSTconfigLeitor(String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        
        JSONObject configLeitor = new JSONObject();
        configLeitor.put("METODO", "POST");
        configLeitor.put("URL", "configLeitor");
        configLeitor.put("portaSerial", portaSerial);
        configLeitor.put("baudrate", baudrate);
        configLeitor.put("regiao", regiao);
        configLeitor.put("antena", antena);
        configLeitor.put("protocolo", protocolo);
        configLeitor.put("power", power);
        
        try {
            saida.write(configLeitor.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public ArrayList<String> recebeMensagem(){
        ArrayList<String> arrayEPC = new ArrayList();        
        System.out.println("tentou");
        //cliente = new Socket(ip, door);
        //int i=0;
        while(entrada.hasNextLine()){
            String igual = entrada.nextLine();
            System.out.println(igual);
            arrayEPC.add(igual);
        }
        entrada.close();
        return arrayEPC;
    }    

    @Override
    public void run() {
        while(online){
            while(entrada.hasNextLine()){
                String chegou = entrada.nextLine();
                System.out.println(chegou);
                recebido = new JSONObject(chegou);
            }
            System.out.println("Rodando");
        }
    }
    
    public void start(){
        if(!online){
            this.online = true;
            new Thread(this).start();
        }
        
    }
    
    public void stop(){
        this.online = false;        
    }
    
    public void testando(){
        System.out.println(recebido.toString() + "aqui");
        System.out.println(recebido.toString() + "segundo");
        System.out.println(recebido.toString() + "kkkkkk");
        System.out.println(recebido.toString() + "Bora dormir");
    }
    
}
