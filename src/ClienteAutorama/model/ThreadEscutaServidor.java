package ClienteAutorama.model;

import ClienteAutorama.controller.Comunication;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class ThreadEscutaServidor extends Observable implements Runnable{

    Comunication comunicacao = Comunication.getInstance();
    public BufferedReader entrada;
    public boolean online = false;
    
    public ThreadEscutaServidor() {
        
    }
    
    
    @Override
    public void run() {
        while(online){
            try {
                //while(entrada.ready()){
                    String chegou = entrada.readLine();
                    if(chegou != null){    
                        System.out.println(chegou);
                        comunicacao.recebido = new JSONObject(chegou);
                        setChanged();
                        notifyObservers(chegou);
                    } else {
                        this.stop();
                    }
                //}
            } catch (IOException ex) {
                Logger.getLogger(ThreadEscutaServidor.class.getName()).log(Level.SEVERE, null, ex);
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
