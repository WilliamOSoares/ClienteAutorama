package ClienteAutorama.controller;

import ClienteAutorama.model.ThreadEscutaServidor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


public class Comunication{
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
            this.ip = ip;
            this.door = Integer.parseInt(porta);
            cliente = new Socket(ip, door);
            saida = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            entrada = new BufferedReader (new InputStreamReader(cliente.getInputStream()));
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
  
    public void recebeMensagem(JSONObject msg){
        if(msg != null){
            this.recebido = msg;
        } else {
            System.out.println("null");
        }
        
    }    
    /*
    public static void main(String[] args) {
        String s = "{" + "Protocolo" + ":" + "POST"+ "}";
        System.out.println(s);
        JSONObject t = new JSONObject(s);
        System.out.println(t.toString());
        
    }
    */
    
    public void testando(){
        System.out.println(recebido.toString());
    }
    
}
