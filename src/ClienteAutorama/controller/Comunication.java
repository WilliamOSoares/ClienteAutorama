package ClienteAutorama.controller;

import ClienteAutorama.model.ThreadEscutaServidor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Comunication implements Observer{//implements Runnable{
    public static Socket cliente;  
    public String ip;
    public int door;    
    public BufferedWriter saida;
    public BufferedReader entrada;
    public JSONObject recebido = new JSONObject();
    public boolean online = false;
    public JSONObject teste;
    public ThreadEscutaServidor thread = new ThreadEscutaServidor();
    
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
            entrada = new BufferedReader (new InputStreamReader(cliente.getInputStream()));
            //thread.start(entrada);
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
        teste = new JSONObject(configLeitor.toString());
        try {
            saida.write(configLeitor.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void update(Observable o, Object o1) {
        ThreadEscutaServidor servidor = (ThreadEscutaServidor)o;
        String str = String.valueOf(o1);
        recebido = new JSONObject(str);
    }
    
    /*
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
            if(flag){
                while(entrada.hasNextLine()){
                    String chegou = entrada.nextLine();
                    System.out.println(chegou);
                    if(chegou.equals(teste.toString())){
                        recebido = new JSONObject(chegou);
                    } else{
                        System.out.println("é nulo");
                        System.out.println(chegou.length());
                        System.out.println(chegou.isEmpty());
                    }                
                }
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
    
    public static void main(String[] args) {
        String s = "{" + "Protocolo" + ":" + "POST"+ "}";
        System.out.println(s);
        JSONObject t = new JSONObject(s);
        System.out.println(t.toString());
        
    }
    */
    
    public void testando(){
        //if(!recebido.isEmpty()){
        //    System.out.println(recebido.toString() + "aqui");
        //}
        //System.out.println("Recebido está vazio");
        System.out.println(recebido.toString());
    }

}
