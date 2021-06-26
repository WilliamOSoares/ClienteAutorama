package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.Piloto;
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
    public MqttClient clientePub;  
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
        
        try{
            this.ip = ip;
            clientePub = new MqttClient(ip, "ADM");//"Autorama2");
            MqttConnectOptions mqttOptions = new MqttConnectOptions();
            mqttOptions.setUserName(usuario);
            mqttOptions.setPassword(senha.toCharArray());
            mqttOptions.setAutomaticReconnect(true);
            mqttOptions.setCleanSession(false);            
            clientePub.connect(mqttOptions);
            System.out.println("Conectado");
        } catch (MqttException ex) {
            System.out.println(ex);
        }
        
        try{
            MqttClient cliente = new MqttClient(ip, "ADMsub");
            cliente.setCallback(new MqttCallback() {

                @Override
                public void messageArrived(String string, MqttMessage mm) throws Exception {
                    
                    //System.out.println(string);
                    //System.out.println(mm);
                                       
                    if("Resposta/Config".equals(string)){
                        respostaConfig = mm.toString();
                    } else if("Resposta/Confirm".equals(string)){
                        respostaConfirm = mm.toString();
                    } else if (string.contains("LeitorRFID")){
                        String s[] = string.split("/");
                        if(respostaQuali.equals("OK") || respostaCorrida.equals("OK")){
                            JSONObject recebe = new JSONObject();
                            if(s.length == 2){
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
                            }
                            else{
                                if(string.contains("Tempo")){
                                    for (int i = 0; i < carros.size(); i++) {
                                        if(carros.get(i).getEPC().contains(s[1])){
                                            carros.get(i).setTempo(mm.toString());
                                        }    
                                    }                                    
                                } else if(string.contains("Ciclo")){
                                    for (int i = 0; i < carros.size(); i++) {
                                        if(carros.get(i).getEPC().contains(s[1])){
                                            carros.get(i).setCiclo(mm.toString());
                                        }    
                                    }   
                                } else {
                                    System.out.println("rssi" +mm.toString());
                                }   
                                String a = "CARRO";
                                String c = "TEMPO";
                                String e = "CicloLeitura";
                                String b;
                                String d;
                                for (int i = 0; i < carros.size(); i++) {
                                    b = a+i;
                                    d = c+i;
                                    recebe.put(b, carros.get(i).getEPC());
                                    recebe.put(d, carros.get(i).getTempo());
                                    if(i==0){
                                        recebe.put(e, carros.get(i).getCiclo());
                                    }
                                }
                                //System.out.println(recebe.toString());                                
                                recebido = recebe;                                
                                //System.out.println(recebido.toString());
                            }
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
                        }
                    } else if("Resposta/Quali".equals(string)){
                        respostaQuali = mm.toString();
                        if(!respostaQuali.equals("OK")){
                            JSONObject recebe = new JSONObject();
                            recebe.put("status", respostaQuali);
                            recebe.put("URL", "finalQuali");
                            recebido = recebe;
                        }
                    } else if("Resposta/Corrida".equals(string)){
                        respostaCorrida = mm.toString();
                        if(!respostaCorrida.equals("OK")){
                            JSONObject recebe = new JSONObject();
                            recebe.put("status", respostaCorrida);
                            recebe.put("URL", "finalCorrida");
                            recebido = recebe;
                        }
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
            mqttOptions.setUserName("pblredes");
            mqttOptions.setPassword("pblredes1234".toCharArray());
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
            
            clientePub.publish("ConfigLeitor/ForcaLeitura", msgPower);
            clientePub.publish("ConfigLeitor/Protocolo", msgProtocolo);
            clientePub.publish("ConfigLeitor/Antena", msgAntena);
            clientePub.publish("ConfigLeitor/Regiao", msgRegiao);
            clientePub.publish("ConfigLeitor/Baudrate", msgBaudrate);
            clientePub.publish("ConfigLeitor/Serial", msgPortaSerial);
            clientePub.publish("Function", msgConfigLeitor);
            
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
        MqttMessage msgFinalCorrida = new MqttMessage(("0").getBytes());
        msgFinalCorrida.setQos(0);
        msgFinalCorrida.setRetained(false);
        try {
            clientePub.publish("Config/TempoQuali", msgQuali);
            clientePub.publish("Config/NumeroVoltas", msgVoltas);
            clientePub.publish("Config/TempoMinimo", msgTempoMin);
            clientePub.publish("Config/QuantiCarros", msgNPilotos);
            clientePub.publish("Config/AcabouCorrida", msgFinalCorrida);
            clientePub.publish("Function", msgFuncao);
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
            clientePub.publish("Function", msgFuncao);
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
            clientePub.publish("Function", msgFuncao);
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
            clientePub.publish("Function", msgFuncao);
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
            clientePub.publish("Function", msgFuncao);
            clientePub.disconnect();
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }     
    }

    /**
    * Manda as configurações inicias para o cliente telespectador.
    * 
    * 
    * @param tQuali tempo do qualificatório
    * @param nVoltas número de voltas
    * @param pista nome da pista
    * @param recordista nome do recordista
    * @param tempoRecordPista tempo record da pista
    * @param participantes corredores
    */
    public void enviaConfigFan(String tQuali, String nVoltas, String pista, String recordista, String tempoRecordPista, ArrayList<Piloto> participantes) {
        MqttMessage msgtQuali = new MqttMessage((tQuali).getBytes());
        msgtQuali.setQos(0);
        msgtQuali.setRetained(false);
        MqttMessage msgPista = new MqttMessage((pista).getBytes());
        msgPista.setQos(0);
        msgPista.setRetained(false);
        MqttMessage msgRecordista = new MqttMessage((recordista).getBytes());
        msgRecordista.setQos(0);
        msgRecordista.setRetained(false);
        MqttMessage msgTempoRecord = new MqttMessage((tempoRecordPista).getBytes());
        msgTempoRecord.setQos(0);
        msgTempoRecord.setRetained(false);
        MqttMessage msgnVoltas = new MqttMessage((nVoltas).getBytes());
        msgnVoltas.setQos(0);
        msgnVoltas.setRetained(false);
        MqttMessage msgEtapa = new MqttMessage(("quali").getBytes());
        msgEtapa.setQos(0);
        msgEtapa.setRetained(false);
        MqttMessage msgNome;
        MqttMessage msgEquipe;
        String topic = "fan/piloto";
        String topico;
        try {
            
            clientePub.publish("fan/tempoQuali", msgtQuali);
            clientePub.publish("fan/nomePista", msgPista);
            clientePub.publish("fan/recordista", msgRecordista);
            clientePub.publish("fan/record", msgTempoRecord);
            clientePub.publish("fan/numVoltas", msgnVoltas);
            clientePub.publish("fan/etapa", msgEtapa);
            
            for(int i = 0; i < participantes.size(); i++){
                
                msgNome = new MqttMessage((participantes.get(i).getNome()).getBytes());
                msgNome.setQos(0);
                msgNome.setRetained(false);
                msgEquipe = new MqttMessage((participantes.get(i).getEquipe().getNome()).getBytes());
                msgEquipe.setQos(0);
                msgEquipe.setRetained(false);
                topico = topic + Integer.toString(i) + "/nome";                
                clientePub.publish(topico, msgNome);
                topico = topic + Integer.toString(i) + "/equipe"; 
                clientePub.publish(topico, msgEquipe);
            }            
            
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        
    }

    /**
    * Manda as configurações do qualificatório e corrida para o cliente telespectador.
    * 
    * 
    * @param recordista nome do recordista
    * @param tempoRecordPista tempo record da pista
    * @param pilotos corredores
    * @param etapa etapa de corrida ou qualificatório
    */
    public void enviaDadosFan(ArrayList<Piloto> pilotos, String recordista, String tempoRecordPista, String etapa) {
        MqttMessage msgTempoRecord = new MqttMessage((tempoRecordPista).getBytes());
        msgTempoRecord.setQos(0);
        msgTempoRecord.setRetained(false);
        MqttMessage msgRecordista = new MqttMessage((recordista).getBytes());
        msgRecordista.setQos(0);
        msgRecordista.setRetained(false);
        MqttMessage msgEtapa = new MqttMessage((etapa).getBytes());
        msgEtapa.setQos(0);
        msgEtapa.setRetained(false);
        MqttMessage msgNome;
        MqttMessage msgEquipe;
        MqttMessage msgPos;
        MqttMessage msgCarro;
        MqttMessage msgVoltas;
        MqttMessage msgTempoGeral;
        MqttMessage msgTempoVolta;
        MqttMessage msgTempoMelhor;
        MqttMessage msgTempoCima;
        MqttMessage msgTempoBaixo;
        
        String topic = "fan/piloto";
        String topico;
        try {
            
            clientePub.publish("fan/recordista", msgRecordista);
            clientePub.publish("fan/record", msgTempoRecord);
            clientePub.publish("fan/etapa", msgEtapa);
            
            if(pilotos.get(0).getTempoVolta()==null){
                System.out.println("Enviando a corrida acabada");
            } else {
                for(int i = 0; i < pilotos.size(); i++){

                    msgNome = new MqttMessage((pilotos.get(i).getNome()).getBytes());
                    msgNome.setQos(0);
                    msgNome.setRetained(false);
                    msgEquipe = new MqttMessage((pilotos.get(i).getEquipe().getNome()).getBytes());
                    msgEquipe.setQos(0);
                    msgEquipe.setRetained(false);
                    msgPos = new MqttMessage(Integer.toString(i+1).getBytes());
                    msgPos.setQos(0);
                    msgPos.setRetained(false);
                    msgCarro = new MqttMessage(pilotos.get(i).getCarro().getEPC().getBytes());
                    msgCarro.setQos(0);
                    msgCarro.setRetained(false);
                    msgVoltas = new MqttMessage(Integer.toString(pilotos.get(i).getVoltas()).getBytes());
                    msgVoltas.setQos(0);
                    msgVoltas.setRetained(false);
                    msgTempoGeral = new MqttMessage(pilotos.get(i).getTempoGeral().getBytes());
                    msgTempoGeral.setQos(0);
                    msgTempoGeral.setRetained(false);
                    msgTempoVolta = new MqttMessage(pilotos.get(i).getTempoVolta().getBytes());
                    msgTempoVolta.setQos(0);
                    msgTempoVolta.setRetained(false);
                    String s = ".";
                    int segundo = pilotos.get(i).getMelhorSec();
                    int min = segundo/60;
                    int sec = segundo%60;
                    if(pilotos.get(i).getMelhorMili() <10){
                        s = ".00";
                    } else if(pilotos.get(i).getMelhorMili() <100){
                        s = ".0";
                    }
                    String melhorVolta = min +":"+sec+s+pilotos.get(i).getMelhorMili();
                    msgTempoMelhor = new MqttMessage(melhorVolta.getBytes());
                    msgTempoMelhor.setQos(0);
                    msgTempoMelhor.setRetained(false);
                    if(i==0){
                        msgTempoCima = new MqttMessage("0".getBytes());
                        msgTempoCima.setQos(0);
                        msgTempoCima.setRetained(false);
                    } else{
                        int first, second;
                        String k = ".";
                        if(pilotos.get(i).getMelhorMili() < pilotos.get(i-1).getMelhorMili()){
                            second = (pilotos.get(i).getMelhorMili()+1000) - pilotos.get(i-1).getMelhorMili();
                            first = (pilotos.get(i).getMelhorSec()-1) - pilotos.get(i-1).getMelhorSec();
                            if(second <10){
                                k = ".00";
                            } else if(second<100){
                                k = ".0";
                            }
                        } else{
                            first = pilotos.get(i).getMelhorSec() - pilotos.get(i-1).getMelhorSec();
                            second = pilotos.get(i).getMelhorMili() - pilotos.get(i-1).getMelhorMili();
                            if(second <10){
                                k = ".00";
                            } else if(second<100){
                                k = ".0";
                            }

                        }                                          
                        String msg = "+" + first + k + second;
                        msgTempoCima = new MqttMessage(msg.getBytes());
                        msgTempoCima.setQos(0);
                        msgTempoCima.setRetained(false);
                    }
                    if(i==pilotos.size()-1){
                        msgTempoBaixo = new MqttMessage("0".getBytes());
                        msgTempoBaixo.setQos(0);
                        msgTempoBaixo.setRetained(false);
                    } else{
                        int first, second;
                        String k = ".";
                        if(pilotos.get(i+1).getMelhorMili() < pilotos.get(i).getMelhorMili()){
                            second = (pilotos.get(i+1).getMelhorMili()+1000) - pilotos.get(i).getMelhorMili();
                            first = (pilotos.get(i+1).getMelhorSec()-1) - pilotos.get(i).getMelhorSec();
                            if(second <10){
                                k = ".00";
                            } else if(second<100){
                                k = ".0";
                            }
                        } else{
                            first = pilotos.get(i+1).getMelhorSec() - pilotos.get(i).getMelhorSec();
                            second = pilotos.get(i+1).getMelhorMili() - pilotos.get(i).getMelhorMili();
                            if(second <10){
                                k = ".00";
                            } else if(second<100){
                                k = ".0";
                            }

                        }                                          
                        String msg = "+" + first + k + second;
                        msgTempoBaixo = new MqttMessage(msg.getBytes());
                        msgTempoBaixo.setQos(0);
                        msgTempoBaixo.setRetained(false);
                    }



                    topico = topic + Integer.toString(i) + "/nome";                
                    clientePub.publish(topico, msgNome);
                    topico = topic + Integer.toString(i) + "/equipe"; 
                    clientePub.publish(topico, msgEquipe);
                    topico = topic + Integer.toString(i) + "/posicao"; 
                    clientePub.publish(topico, msgPos);
                    topico = topic + Integer.toString(i) + "/carro"; 
                    clientePub.publish(topico, msgCarro);
                    topico = topic + Integer.toString(i) + "/voltas"; 
                    clientePub.publish(topico, msgVoltas);
                    topico = topic + Integer.toString(i) + "/tempoGeral"; 
                    clientePub.publish(topico, msgTempoGeral);
                    topico = topic + Integer.toString(i) + "/tempoVolta"; 
                    clientePub.publish(topico, msgTempoVolta);
                    topico = topic + Integer.toString(i) + "/tempoMelhor"; 
                    clientePub.publish(topico, msgTempoMelhor);
                    topico = topic + Integer.toString(i) + "/tempoCima"; 
                    clientePub.publish(topico, msgTempoCima);
                    topico = topic + Integer.toString(i) + "/tempoBaixo"; 
                    clientePub.publish(topico, msgTempoBaixo);

                }            
            }
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    /**
     * Envia as voltas para encerrar a corrida no servidor
     * @param voltas numero de voltas do primeiro
     */
    public void enviaFinalCorrida(int voltas) {
        MqttMessage msgVoltas = new MqttMessage((Integer.toString(voltas)).getBytes());
        msgVoltas.setQos(0);
        msgVoltas.setRetained(false);
        try {
            clientePub.publish("Config/AcabouCorrida", msgVoltas);
        } catch (MqttException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } 
    }
}
