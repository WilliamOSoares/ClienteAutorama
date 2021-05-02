package ClienteAutorama.controller;

import ClienteAutorama.model.ThreadEscutaServidor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * Classe de comunicação com o servidor.
 * 
 * @author Víctor César e William Soares.
 */
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
    public Semaphore semaforo = new Semaphore(1);
    
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
    * @param porta Porta de acesso da comunicação entre o servidor.
    */
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
        JSONObject dadosCorrida = new JSONObject();
        dadosCorrida.put("METODO", "GET");
        dadosCorrida.put("URL", "dadosCorrida");
        dadosCorrida.put("Quali", quali);
        dadosCorrida.put("Voltas", voltas);
        dadosCorrida.put("TempoMin", tempoMIN);
        dadosCorrida.put("CarrosQuant", nPilotos);
        try {
            saida.write(dadosCorrida.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(!recebido.has("return")){ System.out.println(recebido.has("return")); }
        return recebido.getString("return");
    }

    /**
    * Solicita as EPCs das tags lidas pelo leitor, deve haver no mínimo 1 tag embaixo do sensor.
    * 
    * @return Um JSON com todas as EPCs das tags nominadas por EPCn, n sendo um número real.
    */
    public JSONObject getEPC() {
        JSONObject getEPC = new JSONObject();
        getEPC.put("METODO", "GET");
        getEPC.put("URL", "retornaEPC");
        try {
            saida.write(getEPC.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(!recebido.has("EPC0")){ System.out.println(recebido.has("EPC0")); }
        return recebido;
    }

    /**
    * Solicita o servidor o início do qualificatório e o envio dos dados dos carros para a exibição na tela em tempo real.
    * 
    */
    public void getComecaQuali() {
        JSONObject comecaQuali = new JSONObject();
        comecaQuali.put("METODO", "GET");
        comecaQuali.put("URL", "comecaQuali");
        try {
            saida.write(comecaQuali.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }   
    }
    
    /**
    * Solicita o servidor o início da corrida e o envio dos dados dos carros para a exibição na tela em tempo real.
    * 
    */
    public void getComecaCorrida() {
        JSONObject comecaCorrida = new JSONObject();
        comecaCorrida.put("METODO", "GET");
        comecaCorrida.put("URL", "comecaCorrida");
        try {
            saida.write(comecaCorrida.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }  
    }

    /**
    * Encerra a conexão com o servidor, para a thread que escuta servidor e fecha as conexões.
    * 
    */
    public void PostEncerraConexao() {
        JSONObject configLeitor = new JSONObject();
        configLeitor.put("METODO", "POST");
        configLeitor.put("URL", "encerra");
        try {
            saida.write(configLeitor.toString());
            saida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        this.thread.stop();
        thread = null;
        try{
            saida.close();
            entrada.close();
            cliente.close();
        } catch (Exception ex){
            Logger.getLogger(Comunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }        
    }
}
