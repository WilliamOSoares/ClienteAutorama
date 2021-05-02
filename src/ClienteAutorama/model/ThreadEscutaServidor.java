package ClienteAutorama.model;

import ClienteAutorama.controller.Comunication;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * Thread que escuta o servidor.
 * 
 * @author Víctor César e William Soares.
 */
public class ThreadEscutaServidor implements Runnable{

    Comunication comunicacao;
    public BufferedReader entrada;
    public boolean online = false;
    public JSONObject msg;
    
    /**
    * Construtor da classe ThreadEscutaServidor.
    * 
    */
    public ThreadEscutaServidor() {
        //Criação da thread
    }
    
    /**
    * Código da execução da thread que espera a entrada de um JSON do servidor.
    * 
    */
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
    
    /**
    * Invocação de inicio da thread.
    * 
    * @param escuta Buffer de entrada de mensagem do servidor.
    */
    public void start(BufferedReader escuta){
        if(!online){
            entrada = escuta;
            this.online = true;
            new Thread(this).start();
        }
        
    }
    
    /**
    * Para a thread.
    * 
    */
    public void stop(){
        this.online = false;
        try {
            entrada.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadEscutaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
