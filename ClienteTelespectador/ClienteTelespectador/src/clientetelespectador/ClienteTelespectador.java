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
    
    public void conectar(){
        try{
            cliente = new MqttClient("tcp://pblredes.ddns.net:1883", "FanSub");
            cliente.setCallback(new MqttCallback() {

                @Override
                public void messageArrived(String string, MqttMessage mm) throws Exception {
                    
                    System.out.println(string);
                    System.out.println(mm);
                                 
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
            cliente.subscribe("ConfigLeitor/#", 0);
            cliente.subscribe("Config/#", 0); 
            cliente.subscribe("Function/#", 0); 
            cliente.subscribe("LeitorRFID/#", 0);
            cliente.subscribe("Config/Botao", 0);
            System.out.println("Conectado");
            
        } catch (MqttException ex) {
            System.out.println(ex);
        }
    
    }
    
    
    
    
}
