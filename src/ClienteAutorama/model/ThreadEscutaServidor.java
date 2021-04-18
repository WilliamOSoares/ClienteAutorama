package ClienteAutorama.model;

import ClienteAutorama.controller.Comunication;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class ThreadEscutaServidor implements Runnable{

    Comunication comunicacao;
    public BufferedReader entrada;
    public boolean online = false;
    public JSONObject msg;
    
    public ThreadEscutaServidor() {
        
    }
    
    @Override
    public void run() {
        
        while(online){
            
            try {
                String chegou = entrada.readLine();
                if(chegou != null){
                    msg = new JSONObject(chegou);
                    comunicacao = Comunication.getInstance();
                    comunicacao.recebeMensagem(msg);
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadEscutaServidor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
            System.out.println("Rodando");
        }
    }
    
    public void start(BufferedReader s){
        if(!online){
            entrada = s;
            this.online = true;
            new Thread(this).start();
        }
        
    }
    
    public void stop(){
        this.online = false;        
    }
    
}
