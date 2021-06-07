package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.ThreadEscutaServidor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.json.JSONObject;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Classe de comunicação com o servidor.
 * 
 * @author Víctor César e William Soares.
 */
public class Comunication{
    public MqttClient cliente;  
    public String ip, respostaConfig = " ", respostaConfirm = " ", respostaQuali = " ", respostaCorrida = " ";   
    public BufferedWriter saida;
    public BufferedReader entrada;
    public JSONObject recebido = new JSONObject();
    public boolean online = false;
    public JSONObject teste;
    public ThreadEscutaServidor thread = new ThreadEscutaServidor();
    public Semaphore semaforo = new Semaphore(1);
    public ArrayList<Carro> carros = new ArrayList();
    
    /**
    * Construtor da classe de comunicação.
    * 
    */
    private Comunication() {
    }
    
    //Criação da única instância.
    private static Comunication uniqueInstance = new Comunication();

    /**
    * Pega a única instância da classe existente no código, com isso faz a implementação do padrão de criação Singleton.
    * 
    * @return Retorna a única instância.
    */
    public static Comunication getInstance() {
	return uniqueInstance;
    }  
    
    /**
    * Inicia a conexão através do ip e a porta fornecidas para se conectar com o outro computador.
    * 
    * @param ip ip do outro computador.
    * @param usuario Usuário de acesso da comunicação entre o servidor MQTT.
    * @param senha Senha do usuário do servidor MQTT
    */
    public void iniciaCliente(String ip, String usuario, String senha){
        /*
        try {
            this.ip = ip;
            cliente = new MqttClient(ip, "Cam1");//"Autorama2");
            
            cliente.setCallback(new MqttCallback() {

                @Override
                public void messageArrived(String string, MqttMessage mm) throws Exception {
                    
                    System.out.println(string);
                    System.out.println(mm);
                                       
                    if("Resposta/Config".equals(string)){
                        respostaConfig = mm.toString();
                    } else if("Resposta/Confirm".equals(string)){
                        respostaConfirm = mm.toString();
                    } else if (string.contains("LeitorRFID")){
                        String s[] = string.split("/");
                        if(carros.isEmpty()){
                            carros.add(new Carro(mm.toString()));                         
                        } else {
                            boolean tem = false;
                            for (int i = 0; i < carros.size(); i++) {
                                if(carros.get(i).getEPC().contains(s[1])){
                                    tem = true;
                                }
                            }
                            if(!tem){
                                carros.add(new Carro(mm.toString())); 
                            }
                        }
                        if(string.contains("Tempo")){
                            for (int i = 0; i < carros.size(); i++) {
                                if(carros.get(i).getEPC().contains(s[1])){
                                    carros.get(i).setTempo(mm.toString());
                                }    
                            }
                        }
                        if(string.contains("Ciclo")){
                            for (int i = 0; i < carros.size(); i++) {
                                if(carros.get(i).getEPC().contains(s[1])){
                                    carros.get(i).setCiclo(mm.toString());
                                }    
                            }
                        }                        
                    } else if("Resposta/Quali".equals(string)){
                        respostaQuali = mm.toString();
                    } else if("Resposta/Corrida".equals(string)){
                        respostaCorrida = mm.toString();
                    }
                }

                @Override
                public void connectionLost(Throwable thrwbl) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken imdt) {
                    try {
                        System.out.println("Delivery complete");
                        System.out.println(imdt.getClient());
                        System.out.println(imdt.getMessage());
                        System.out.println(imdt.getException());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
            
            MqttConnectOptions mqttOptions = new MqttConnectOptions();
            mqttOptions.setUserName(usuario);
            mqttOptions.setPassword(senha.toCharArray());
            mqttOptions.setAutomaticReconnect(true);
            mqttOptions.setCleanSession(false);
            
            cliente.connect(mqttOptions);
            cliente.subscribe("Resposta/#", 0);
            cliente.subscribe("Qualificatorio/#", 0); 
            cliente.subscribe("Corrida/#", 0); 
            cliente.subscribe("LeitorRFID/#", 0);
            cliente.subscribe("Config/Botao", 0);
            System.out.println("Conectado");
        } catch (MqttException ex) {
            System.out.println(ex);
        }
        */
        try{
            this.ip = ip;
            cliente = new MqttClient(ip, "Autorama2");//"Cam1");//"Autorama2");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();
            mqttOptions.setUserName(usuario);
            mqttOptions.setPassword(senha.toCharArray());
            mqttOptions.setAutomaticReconnect(true);
            mqttOptions.setCleanSession(false);            
            cliente.connect(mqttOptions);
            System.out.println("Conectado");
        } catch (MqttException ex) {
            System.out.println(ex);
        }
        
        try{
            cliente = new MqttClient(ip, "Autorama3");
            cliente.setCallback(new MqttCallback() {

                @Override
                public void messageArrived(String string, MqttMessage mm) throws Exception {
                    
                    System.out.println(string);
                    System.out.println(mm);
                                       
                    if("Resposta/Config".equals(string)){
                        respostaConfig = mm.toString();
                    } else if("Resposta/Confirm".equals(string)){
                        respostaConfirm = mm.toString();
                    } else if (string.contains("LeitorRFID")){
                        String s[] = string.split("/");
                        if(respostaQuali.equals("OK") || respostaCorrida.equals("OK")){
                             
                        } else{
                            if(carros.isEmpty()){
                                carros.add(new Carro(mm.toString()));                         
                            } else {
                                boolean tem = false;
                                for (int i = 0; i < carros.size(); i++) {
                                    if(carros.get(i).getEPC().contains(s[1])){
                                        tem = true;
                                    }
                                }
                                if(!tem){
                                    carros.add(new Carro(mm.toString())); 
                                }
                            }
                            if(string.contains("Tempo")){
                                for (int i = 0; i < carros.size(); i++) {
                                    if(carros.get(i).getEPC().contains(s[1])){
                                        carros.get(i).setTempo(mm.toString());
                                    }    
                                }
                            }
                            if(string.contains("Ciclo")){
                                for (int i = 0; i < carros.size(); i++) {
                                    if(carros.get(i).getEPC().contains(s[1])){
                                        carros.get(i).setCiclo(mm.toString());
                                    }    
                                }
                            }   
                        }
                    } else if("Resposta/Quali".equals(string)){
                        respostaQuali = mm.toString();
                    } else if("Resposta/Corrida".equals(string)){
                        respostaCorrida = mm.toString();
                    }
                }

                @Override
                public void connectionLost(Throwable thrwbl) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken imdt) {
                    try {
                        System.out.println("Delivery complete");
                        System.out.println(imdt.getClient());
                        System.out.println(imdt.getMessage());
                        System.out.println(imdt.getException());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
            
            MqttConnectOptions mqttOptions = new MqttConnectOptions();
            mqttOptions.setUserName("Cliente3");
            mqttOptions.setPassword("12345".toCharArray());
            mqttOptions.setAutomaticReconnect(true);
            mqttOptions.setCleanSession(false);
            
            cliente.connect(mqttOptions);
            cliente.subscribe("Resposta/#", 0);
            cliente.subscribe("Qualificatorio/#", 0); 
            cliente.subscribe("Corrida/#", 0); 
            cliente.subscribe("LeitorRFID/#", 0);
            cliente.subscribe("Config/Botao", 0);
            System.out.println("Conectado");
            
        } catch (MqttException ex) {
            System.out.println(ex);
        }
        
    }
    
    /**
    * Envia a configuração do leitor para o servidor configurá-lo.
    * 
    * @param portaSerial Identificação da porta serial do leitro.
    * @param baudrate Taxa de transmissão do servidor.
    * @param regiao Região de funcionamento do leitor.
    * @param antena Número de antenas existentes no leitor.
    * @param protocolo Nome do protocolo utilizado.
    * @param power Força de leitura do leitor.
    */
    public void POSTconfigLeitor(String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        MqttMessage msgPortaSerial = new MqttMessage((portaSerial).getBytes());
        msgPortaSerial.setQos(0);
        msgPortaSerial.setRetained(false);
        MqttMessage msgBaudrate = new MqttMessage((baudrate).getBytes());
        msgBaudrate.setQos(0);
        msgBaudrate.setRetained(false);
        MqttMessage msgRegiao = new MqttMessage((regiao).getBytes());
        msgRegiao.setQos(0);
        msgRegiao.setRetained(false);
        MqttMessage msgAntena = new MqttMessage((antena).getBytes());
        msgAntena.setQos(0);
        msgAntena.setRetained(false);
        MqttMessage msgProtocolo = new MqttMessage((protocolo).getBytes());
        msgProtocolo.setQos(0);
        msgProtocolo.setRetained(false);
        MqttMessage msgPower = new MqttMessage((power).getBytes());
        msgPower.setQos(0);
        msgPower.setRetained(false);
        MqttMessage msgConfigLeitor = new MqttMessage(("ConfigLeitor").getBytes());
        msgConfigLeitor.setQos(0);
        msgConfigLeitor.setRetained(false);
        try {
            
            cliente.publish("ConfigLeitor/ForcaLeitura", msgPower);
            cliente.publish("ConfigLeitor/Protocolo", msgProtocolo);
            cliente.publish("ConfigLeitor/Antena", msgAntena);
            cliente.publish("ConfigLeitor/Regiao", msgRegiao);
            cliente.publish("ConfigLeitor/Baudrate", msgBaudrate);
            cliente.publish("ConfigLeitor/Serial", msgPortaSerial);
            cliente.publish("Function", msgConfigLeitor);
            
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        
    }
  
    /**
    * Método que atualiza a mensagem em JSON recebida do servidor.
    * 
    * @param msg Novo JSON recebido do servidor.
    */
    public void recebeMensagem(JSONObject msg){
        if(msg != null){
            this.recebido = msg;
        } else {
            System.out.println("null");
        }
        
    }    
    
    /**
    * Solicita a continuação da corrida enviando as informações cruciais para a realização da corrida para o servidor.
    * 
    * @param quali Tempo em minutos para a realização do qualificatório.
    * @param voltas Número de voltas da corrida.
    * @param tempoMIN Tempo mínimo da conclusão de volta da pista.
    * @param nPilotos Número da quantidade de pilotos que irão participar da corrida.
    * @return Resposta do servidor para prosseguir a execução.
    */
    public String getDadosCorrida(String quali, String voltas, String tempoMIN, String nPilotos){
        respostaConfig = " ";
        MqttMessage msgQuali = new MqttMessage((quali).getBytes());
        msgQuali.setQos(0);
        msgQuali.setRetained(false);
        MqttMessage msgVoltas = new MqttMessage((voltas).getBytes());
        msgVoltas.setQos(0);
        msgVoltas.setRetained(false);
        MqttMessage msgTempoMin = new MqttMessage((tempoMIN).getBytes());
        msgTempoMin.setQos(0);
        msgTempoMin.setRetained(false);
        MqttMessage msgNPilotos = new MqttMessage((nPilotos).getBytes());
        msgNPilotos.setQos(0);
        msgNPilotos.setRetained(false);
        MqttMessage msgFuncao = new MqttMessage(("DadosCorrida").getBytes());
        msgFuncao.setQos(0);
        msgFuncao.setRetained(false);
        try {
            cliente.publish("Config/TempoQuali", msgQuali);
            cliente.publish("Config/NumeroVoltas", msgVoltas);
            cliente.publish("Config/TempoMinimo", msgTempoMin);
            cliente.publish("Config/QuantiCarros", msgNPilotos);
            cliente.publish("Function", msgFuncao);
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        while(!respostaConfig.equals("OK")){ System.out.println(respostaConfig); }
        return "OK";
    }

    /**
    * Solicita as EPCs das tags lidas pelo leitor, deve haver no mínimo 1 tag embaixo do sensor.
    * 
    * @return Um JSON com todas as EPCs das tags nominadas por EPCn, n sendo um número real.
    */
    public JSONObject getEPC() {
        //respostaConfig = " ";
        MqttMessage msgFuncao = new MqttMessage(("PegaEPC").getBytes());
        msgFuncao.setQos(0);
        msgFuncao.setRetained(false);
        try {
            cliente.publish("Function", msgFuncao);
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        while(!respostaConfig.equals("EPC")){System.out.println(respostaConfig);}
        if(!carros.isEmpty()){
            System.out.println(respostaConfig);
        }
        JSONObject retorno = new JSONObject();
        String a = "EPC";
        String b;
        for (int i = 0; i < carros.size(); i++) {
            b = a+i;
            retorno.put(b, carros.get(i).getEPC());
        }        
        respostaConfig = " ";
        return retorno;
    }

    /**
    * Solicita o servidor o início do qualificatório e o envio dos dados dos carros para a exibição na tela em tempo real.
    * 
    */
    public void getComecaQuali() {
        MqttMessage msgFuncao = new MqttMessage(("ComecaQuali").getBytes());
        msgFuncao.setQos(0);
        msgFuncao.setRetained(false);
        try {
            cliente.publish("Function", msgFuncao);
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }  
    }
    
    /**
    * Solicita o servidor o início da corrida e o envio dos dados dos carros para a exibição na tela em tempo real.
    * 
    */
    public void getComecaCorrida() {
        MqttMessage msgFuncao = new MqttMessage(("ComecaCorrida").getBytes());
        msgFuncao.setQos(0);
        msgFuncao.setRetained(false);
        try {
            cliente.publish("Function", msgFuncao);
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    /**
    * Encerra a conexão com o servidor, para a thread que escuta servidor e fecha as conexões.
    * 
    */
    public void PostEncerraConexao() {
        MqttMessage msgFuncao = new MqttMessage(("EncerraConexao").getBytes());
        msgFuncao.setQos(0);
        msgFuncao.setRetained(false);
        try {
            cliente.publish("Function", msgFuncao);
            cliente.disconnect();            
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }     
    }
}
