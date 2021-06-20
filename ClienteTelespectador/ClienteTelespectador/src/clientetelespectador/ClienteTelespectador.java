//Cliente Telespectador//
/*******************************************************************************
Autores: Víctor César da Rocha Bastos e William Oliveira Soares
Componente Curricular: MI de Concorrência e Conectividade
Concluido em: 23/06/2021
Declaro que este código foi elaborado por nós de forma coletiva e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não seja a nossa está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
***************************************************************************************/
package clientetelespectador;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Classe controlador de todo o programa programa.
 * 
 * @author Víctor César e William Soares.
 */
public class ClienteTelespectador {

    public MqttClient cliente; 
    public ArrayList<Piloto> pilotos = new ArrayList();
    public String etapa, tempoQuali, nomePista, recordista, record, numVoltas;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClienteTelespectador.getInstance().conectar();        
        
    }
        
    /**
    * Construtor da classe de ClienteTelespectador.
    * 
    */
    private ClienteTelespectador() {
    }
    
    //Criação da única instância.
    private static ClienteTelespectador uniqueInstance = new ClienteTelespectador();

    /**
    * Pega a única instância da classe existente no código, com isso faz a implementação do padrão de criação Singleton.
    * 
    * @return Retorna a única instância.
    */
    public static ClienteTelespectador getInstance() {
	return uniqueInstance;
    }
    
    /**
     * Conecta com o broker e se inscreve no tópico fan/#.
     */
    public void conectar(){
        try{
            cliente = new MqttClient("tcp://pblredes.ddns.net:1883", "FanSub");
            cliente.setCallback(new MqttCallback() {

                @Override
                public void messageArrived(String string, MqttMessage mm) throws Exception {
                    
                    //System.out.println(string);
                    //System.out.println(mm);
                    
                    if(string.contains("piloto")){
                        String s[] = string.split("/");
                        if(s[1].equals("piloto0")){
                            if(s[2].equals("nome")){
                                if(pilotos.isEmpty()){
                                    pilotos.add(new Piloto(mm.toString()));
                                } else {
                                    pilotos.get(0).setNome(mm.toString());
                                } 
                            } else if(s[2].equals("equipe")){
                                pilotos.get(0).setEquipe(mm.toString());
                            } else if(s[2].equals("posicao")){
                                pilotos.get(0).setPos(mm.toString());
                            } else if(s[2].equals("carro")){
                                pilotos.get(0).setCarro(mm.toString());
                            } else if(s[2].equals("voltas")){
                                pilotos.get(0).setVoltas(mm.toString());
                            } else if(s[2].equals("tempoGeral")){
                                pilotos.get(0).setTempoGeral(mm.toString());
                            } else if(s[2].equals("tempoVolta")){
                                pilotos.get(0).setTempoVolta(mm.toString());
                            } else if(s[2].equals("tempoMelhor")){
                                pilotos.get(0).setTempoMelhor(mm.toString());
                            } else if(s[2].equals("tempoCima")){
                                pilotos.get(0).setTempoCima(mm.toString());
                            } else if(s[2].equals("tempoBaixo")){
                                pilotos.get(0).setTempoBaixo(mm.toString());
                            } else{
                                System.out.println(mm.toString());
                            }                            
                        } else if(s[1].equals("piloto1")){
                            if(s[2].equals("nome")){
                                if(pilotos.size()==1){
                                    pilotos.add(new Piloto(mm.toString()));
                                } else {
                                    pilotos.get(1).setNome(mm.toString());
                                } 
                            } else if(s[2].equals("equipe")){
                                pilotos.get(1).setEquipe(mm.toString());
                            } else if(s[2].equals("posicao")){
                                pilotos.get(1).setPos(mm.toString());
                            } else if(s[2].equals("carro")){
                                pilotos.get(1).setCarro(mm.toString());
                            } else if(s[2].equals("voltas")){
                                pilotos.get(1).setVoltas(mm.toString());
                            } else if(s[2].equals("tempoGeral")){
                                pilotos.get(1).setTempoGeral(mm.toString());
                            } else if(s[2].equals("tempoVolta")){
                                pilotos.get(1).setTempoVolta(mm.toString());
                            } else if(s[2].equals("tempoMelhor")){
                                pilotos.get(1).setTempoMelhor(mm.toString());
                            } else if(s[2].equals("tempoCima")){
                                pilotos.get(1).setTempoCima(mm.toString());
                            } else if(s[2].equals("tempoBaixo")){
                                pilotos.get(1).setTempoBaixo(mm.toString());
                            } else{
                                System.out.println(mm.toString());
                            }                            
                        } else if(s[1].equals("piloto2")){
                            if(s[2].equals("nome")){
                                if(pilotos.size()==2){
                                    pilotos.add(new Piloto(mm.toString()));
                                } else {
                                    pilotos.get(2).setNome(mm.toString());
                                } 
                            } else if(s[2].equals("equipe")){
                                pilotos.get(2).setEquipe(mm.toString());
                            } else if(s[2].equals("posicao")){
                                pilotos.get(2).setPos(mm.toString());
                            } else if(s[2].equals("carro")){
                                pilotos.get(2).setCarro(mm.toString());
                            } else if(s[2].equals("voltas")){
                                pilotos.get(2).setVoltas(mm.toString());
                            } else if(s[2].equals("tempoGeral")){
                                pilotos.get(2).setTempoGeral(mm.toString());
                            } else if(s[2].equals("tempoVolta")){
                                pilotos.get(2).setTempoVolta(mm.toString());
                            } else if(s[2].equals("tempoMelhor")){
                                pilotos.get(2).setTempoMelhor(mm.toString());
                            } else if(s[2].equals("tempoCima")){
                                pilotos.get(2).setTempoCima(mm.toString());
                            } else if(s[2].equals("tempoBaixo")){
                                pilotos.get(2).setTempoBaixo(mm.toString());
                            } else{
                                System.out.println(mm.toString());
                            }                            
                        } else if(s[1].equals("piloto3")){
                            if(s[2].equals("nome")){
                                if(pilotos.size()==3){
                                    pilotos.add(new Piloto(mm.toString()));
                                } else {
                                    pilotos.get(3).setNome(mm.toString());
                                } 
                            } else if(s[2].equals("equipe")){
                                pilotos.get(3).setEquipe(mm.toString());
                            } else if(s[2].equals("posicao")){
                                pilotos.get(3).setPos(mm.toString());
                            } else if(s[2].equals("carro")){
                                pilotos.get(3).setCarro(mm.toString());
                            } else if(s[2].equals("voltas")){
                                pilotos.get(3).setVoltas(mm.toString());
                            } else if(s[2].equals("tempoGeral")){
                                pilotos.get(3).setTempoGeral(mm.toString());
                            } else if(s[2].equals("tempoVolta")){
                                pilotos.get(3).setTempoVolta(mm.toString());
                            } else if(s[2].equals("tempoMelhor")){
                                pilotos.get(3).setTempoMelhor(mm.toString());
                            } else if(s[2].equals("tempoCima")){
                                pilotos.get(3).setTempoCima(mm.toString());
                            } else if(s[2].equals("tempoBaixo")){
                                pilotos.get(3).setTempoBaixo(mm.toString());
                            } else{
                                System.out.println(mm.toString());
                            }                            
                        } else{
                            if(s[2].equals("nome")){
                                if(pilotos.size()==4){
                                    pilotos.add(new Piloto(mm.toString()));
                                } else {
                                    pilotos.get(4).setNome(mm.toString());
                                } 
                            } else if(s[2].equals("equipe")){
                                pilotos.get(4).setEquipe(mm.toString());
                            } else if(s[2].equals("posicao")){
                                pilotos.get(4).setPos(mm.toString());
                            } else if(s[2].equals("carro")){
                                pilotos.get(4).setCarro(mm.toString());
                            } else if(s[2].equals("voltas")){
                                pilotos.get(4).setVoltas(mm.toString());
                            } else if(s[2].equals("tempoGeral")){
                                pilotos.get(4).setTempoGeral(mm.toString());
                            } else if(s[2].equals("tempoVolta")){
                                pilotos.get(4).setTempoVolta(mm.toString());
                            } else if(s[2].equals("tempoMelhor")){
                                pilotos.get(4).setTempoMelhor(mm.toString());
                            } else if(s[2].equals("tempoCima")){
                                pilotos.get(4).setTempoCima(mm.toString());
                            } else if(s[2].equals("tempoBaixo")){
                                pilotos.get(4).setTempoBaixo(mm.toString());
                            } else{
                                System.out.println(mm.toString());
                            }                            
                        }                   
                    } else if (string.equals("fan/tempoQuali")){
                        tempoQuali = mm.toString();
                    } else if (string.equals("fan/nomePista")){
                        nomePista = mm.toString();
                    } else if (string.equals("fan/recordista")){
                        recordista = mm.toString();
                    } else if (string.equals("fan/record")){
                        record = mm.toString();
                    } else if (string.equals("fan/numVoltas")){
                        numVoltas = mm.toString();
                    }  else if (string.equals("fan/etapa")){
                        etapa = mm.toString();
                    } else {
                        System.out.println(mm.toString());
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
            cliente.subscribe("fan/#", 0);
            System.out.println("Conectado");
            
        } catch (MqttException ex) {
            System.out.println(ex);
        }
    
    }
    
    /**
     * Pega os pilotos
     * @return pilotos
     */
    public ArrayList<Piloto> getPilotos(){
        return pilotos;
    }

    /**
     * Pega a etapa que está ocorrendo
     * @return Quali ou Corrida
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * Pega o tempo do qualificatório
     * @return tempo
     */
    public String getTempoQuali() {
        return tempoQuali;
    }

    /**
     * Pega o nome da pista
     * @return nome da pista
     */
    public String getNomePista() {
        return nomePista;
    }

    /**
     * Pega o nome do autor do record
     * @return nome do recordista
     */
    public String getRecordista() {
        return recordista;
    }

    /**
     * Pega o record da pista
     * @return o tempo record
     */
    public String getRecord() {
        return record;
    }

    /**
     * Pega o numero de voltas da corrida
     * @return numero de voltas
     */
    public String getNumVoltas() {
        return numVoltas;
    }    
    
    
}
