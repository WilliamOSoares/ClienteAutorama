package ClienteAutorama.model;

import ClienteAutorama.controller.Comunication;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

/**
 * Thread que escuta o servidor.
 * 
 * @author Víctor César e William Soares.
 */
public class ThreadEscutaServidor implements Runnable{

    Comunication comunicacao;
    public MqttClient cliente;
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
            
            MqttCallback clienteCallBack = new MqttCallback() {
                @Override
                public void connectionLost(Throwable thrwbl) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void messageArrived(String string, MqttMessage mm) throws Exception {

                    System.out.println(string);
                    System.out.println(mm);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken imdt) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };

            try {
                cliente.setCallback(clienteCallBack);
                cliente.connect();
                cliente.subscribe("/#", 0);
            } catch (MqttException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao se conectar");
            }
            System.out.println("Rodando");
        }
    }
    
    /**
    * Invocação de inicio da thread.
    * 
    * @param cliente O cliente ADM.
    */
    public void start(MqttClient cliente){
        if(!online){
            this.cliente = cliente;
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
    }
    
}
