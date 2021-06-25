
package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.Equipe;
import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import ClienteAutorama.view.ApertouBotao;
import ClienteAutorama.view.Cadastro;
import ClienteAutorama.view.ConfiguraCorrida;
import ClienteAutorama.view.Principal;
import ClienteAutorama.view.Qualificatorio;
import ClienteAutorama.view.TelaCorrida;
import ClienteAutorama.view.TelaInicial;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import org.json.JSONObject;

/**
 * Classe do gerenciador de telas, de repasse de função e de todos os dados do sistema.
 * 
 * @author Víctor César e William Soares.
 */
public class GerenciadorTelas {
    
    public Comunication comunicacao = Comunication.getInstance();
    public GerenciadorBD bancoDados = GerenciadorBD.getInstance();
    public Corrida corrida = Corrida.getInstance();
    public Principal telaPrincipal;
    public TelaInicial telaInicial;
    public Cadastro telaCadastro;// = new Cadastro();
    public ConfiguraCorrida telaConfig;
    public Qualificatorio telaQuali;
    public TelaCorrida telaCorrida;
    public ApertouBotao telaBotao;
    public Semaphore semaforo;
    public int flag = 2;
    public boolean play = true;
    
    /**
    * Construtor da classe gerenciadora da telas, de repasse de função e de todos os dados do sistema.
    * 
    */
    private GerenciadorTelas() {
    }
    
    //Criação da única instância.
    private static GerenciadorTelas uniqueInstance = new GerenciadorTelas();

    /**
    * Pega a única instância da classe existente no código, com isso faz a implementação do padrão de criação Singleton.
    * 
    * @return Retorna a única instância.
    */
    public static GerenciadorTelas getInstance() {
	return uniqueInstance;
    }
    
    /**
    * Repasse dos dados obtidos da tela principal para a classe comunication.
    * 
    * @param ip ip do outro computador.
    * @param usuario Usuario de acesso da comunicação entre o servidor MQTT.
    * @param senha Senha de acesso ao usuário MQTT
    * @param portaSerial Identificação da porta serial do leitro.
    * @param baudrate Taxa de transmissão do servidor.
    * @param regiao Região de funcionamento do leitor.
    * @param antena Número de antenas existentes no leitor.
    * @param protocolo Nome do protocolo utilizado.
    * @param power Força de leitura do leitor.
    */
    public void postConectConfiguraLeitor(String ip, String usuario, String senha,String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        comunicacao.iniciaCliente(ip, usuario, senha);
        //comunicacao.thread.start(comunicacao.cliente);
        comunicacao.POSTconfigLeitor(portaSerial, baudrate, regiao, antena, protocolo, power);
    }
    
    /**
    * Repasse dos dados obtidos da tela configuração da corrida para a classe comunication.
    * 
    * @param quali Tempo em minutos para a realização do qualificatório.
    * @param voltas Número de voltas da corrida.
    * @param tempoMIN Tempo mínimo da conclusão de volta da pista.
    * @param nPilotos Número da quantidade de pilotos que irão participar da corrida.
    * @return Resposta do servidor para prosseguir a execução.
    */
    public String getDadosCorrida(String quali, String voltas, String tempoMIN, String nPilotos){
        return comunicacao.getDadosCorrida(quali, voltas, tempoMIN, nPilotos);
    }
    
    /**
    * Altera a referência da tela principal.
    * 
    * @param tela Nova tela principal.
    */
    public void setTelaPrincipal(Principal tela){
        telaPrincipal = tela;
    }
    
    /**
    * Abre a tela inicial a partir da tela principal.
    * 
    */
    public void abrirTelaInicial(){
        telaPrincipal.setEnabled(false);
        telaPrincipal.setVisible(false);
        flag = bancoDados.desserealiza();
        telaInicial = new TelaInicial();
        telaInicial.setLocationRelativeTo(telaPrincipal);
        telaInicial.setVisible(true);
    }
    
    /**
    * Altera a referência da tela inicial.
    * 
    * @param tela Nova tela inicial.
    */
    public void setTelaInicial(TelaInicial tela){
        telaInicial = tela;
    }
    
    /**
    * Abre a tela de cadastro a partir da tela inicial.
    * 
    */
    public void abrirTelaCadastro(){
        telaInicial.setEnabled(false);
        telaInicial.setVisible(false);
        telaCadastro = new Cadastro();
        telaCadastro.setLocationRelativeTo(telaInicial);
        telaCadastro.setEnabled(true);
        telaCadastro.setVisible(true);
    }
    
    /**
    * Altera a referência da tela de cadastro.
    * 
    * @param tela Nova tela de cadastro.
    */
    public void setTelaCadastro(Cadastro tela){
        telaCadastro = tela;
    }
    
    /**
    * Abre a tela inicial a partir do cadastro.
    * 
    */
    public void abrirTelaInicialDoCadastro(){
        telaCadastro.setEnabled(false);
        telaCadastro.setVisible(false);
        flag = bancoDados.desserealiza();
        telaInicial.setLocationRelativeTo(telaCadastro);
        telaInicial.setEnabled(true);
        telaInicial.setVisible(true);
    }
    
    /**
    * Abre a tela configuração de corrida a partir da tela inicial.
    * 
    */
    public void abrirTelaConfigCorrida(){
        telaInicial.setEnabled(false);
        telaInicial.setVisible(false);
        telaConfig = new ConfiguraCorrida();
        telaConfig.setLocationRelativeTo(telaConfig);
        telaConfig.setEnabled(true);
        telaConfig.setVisible(true);
    }

    /**
    * Altera a referência da tela de configuração de corrida.
    * 
    * @param tela Nova tela de configuração de corrida.
    */
    public void setTelaConfig(ConfiguraCorrida tela) {
        telaConfig = tela;
    }

    /**
    * Abre a tela inicial a partir da tela de configuração de corrida.
    * 
    */
    public void abrirTelaInicialDoConfig() {
        telaConfig.setEnabled(false);
        telaConfig.setVisible(false);
        telaConfig = null;
        telaInicial.setLocationRelativeTo(telaCadastro);
        telaInicial.setEnabled(true);
        telaInicial.setVisible(true);
    }

    /**
    * Abre a tela qualificatório a partir da tela de configuração da corrida, repassa os dados para a classe corrida e abre o botão virtual.
    * 
    * @param corredores Corredores que irão participar da corrida.
    * @param nVoltas Número de voltas da corrida.
    * @param tQuali Tempo do qualificatório.
    * @param pista Pista que acontecerá a corrida.
    */
    public void abrirQualificatorio(ArrayList<Piloto> corredores, int nVoltas, String tQuali, Pista pista) {
        corrida = Corrida.getInstance();
        corrida.dadosCorrida(corredores, nVoltas, tQuali+":00", pista);        
        telaConfig.setEnabled(false);
        telaConfig.setVisible(false);
        telaQuali = new Qualificatorio();
        telaQuali.setLocationRelativeTo(telaQuali);
        telaQuali.setEnabled(true); 
        telaQuali.setVisible(true); 
        telaBotao = new ApertouBotao(0);
        telaBotao.setLocationRelativeTo(telaQuali);
        telaBotao.setSize(436, 166); 
        telaBotao.setEnabled(true);
        telaBotao.setVisible(true);
    }
    
    /**
    * Altera a referência da tela qualificatório.
    * 
    * @param tela Nova tela de qualificatório.
    */
    public void setTelaQuali(Qualificatorio tela) {
        telaQuali = tela;
    }
    
    /**
    * Solicita os dados do qualificatório e retira o botão virtual da tela e exibe o qualificatório em tempo real.
    * 
    */
    public void mostrarQualificatorio(){
        comunicacao.getComecaQuali();
        telaBotao.setEnabled(false);
        telaBotao.setVisible(false);
        this.corrida.start();     
    }

    /**
    * Repassa a solicitação de salvar um piloto para a classe que gerencia o banco de dados.
    * 
    * @param ID id do piloto.
    * @param nome Nome do piloto.
    * @param nacio Nacionalidade do piloto
    * @return o mesmo retorno do método salvaPiloto.
    */
    public int cadastrarPiloto(String ID, String nome, String nacio) {
        Piloto novoPiloto = new Piloto(ID, nome, nacio);        
        return bancoDados.salvaPiloto(novoPiloto);
    }

    /**
    * Repassa a solicitação de salvar uma equipe para a classe que gerencia o banco de dados.
    * 
    * @param ID id da equipe.
    * @param nome Nome da equipe.
    * @param nacio Nacionalidade da equipe.
    * @param ano Ano de criação da equipe.
    * @return o mesmo retorno do método salvaEquipe.
    */
    public int cadastrarEquipe(String ID, String nome, String nacio, String ano) {
        Equipe novaEquipe = new Equipe(ID, nome, nacio, ano);        
        return bancoDados.salvaEquipe(novaEquipe);
    }

    /**
    * Repassa a solicitação de salvar uma pista para a classe que gerencia o banco de dados.
    * 
    * @param ID id da pista.
    * @param nome Nome da pista.
    * @param pais País da pista.
    * @param tempoMin Tempo mínimo de conclusão da pista.
    * @return o mesmo retorno do método salvaPista.
    */
    public int cadastrarPista(String ID, String nome, String pais, String tempoMin) {
        Pista novaPista = new Pista(ID, nome, pais, tempoMin);
        return bancoDados.salvaPista(novaPista);
    }

    /**
    * Repassa a solicitação de salvar um carro para a classe que gerencia o banco de dados.
    * 
    * @param EPC EPC da tag.
    * @param n Número do carro.
    * @return o mesmo retorno do método salvaCarro.
    */
    public int cadastrarCarro(String EPC, String n) {
        Carro novoCarro = new Carro(EPC, n);
        return bancoDados.salvaCarro(novoCarro);
    }

    /**
    * Repassa a solicitação de salvar o piloto na equipe e vice-versa para a classe que gerencia o banco de dados.
    * 
    * @param IDpiloto id do piloto.
    * @param IDequipe id da equipe.
    * @return o mesmo retorno do método salvaPilotoEquipe.
    */
    public int cadastrarPilotoEquipe(String IDpiloto, String IDequipe) {
        return bancoDados.salvaPilotoEquipe(IDpiloto, IDequipe);
    }

    /**
    * Repassa a solicitação de salvar o equipe no carro e vice-versa para a classe que gerencia o banco de dados.
    * 
    * @param IDequipe id da equipe.
    * @param IDcarro Número do carro.
    * @return o mesmo retorno do método salvaCarroEquipe.
    */
    public int cadastrarCarroEquipe(String IDcarro, String IDequipe) {
        return bancoDados.salvaCarroEquipe(IDcarro, IDequipe);
    }

    /**
    * Repassa a solicitação de salvar o piloto no carro e vice-versa para a classe que gerencia o banco de dados.
    * 
    * @param IDpiloto id do piloto.
    * @param IDcarro Número do carro.
    * @return o mesmo retorno do método salvaPilotoCarro.
    */
    public int cadastrarPilotoCarro(String IDpiloto, String IDcarro) {
        return bancoDados.salvaPilotoCarro(IDpiloto, IDcarro);
    }

    /**
    * Repassa a solicitação de serealização dos dados para a classe que gerencia o banco de dados.
    * 
    * @return o mesmo retorno do método serealiza.
    */
    public int salvarCadastro() {
        return bancoDados.serealiza();
    }

    /**
    * Repassa a solicitação de EPCs para a classe comunication.
    * 
    * @return O JSON com EPCs.
    */
    public JSONObject getEPC() {
        return comunicacao.getEPC();
    }

    /**
    * Abre o botão virtual para ir para a tela corrida.
    * 
    */
    public void abrirBotaoParaCorrida() {
        telaBotao = new ApertouBotao(1);
        telaBotao.setLocationRelativeTo(telaQuali);
        telaBotao.setSize(436, 166); 
        telaBotao.setEnabled(true);
        telaBotao.setVisible(true);
    }

    /**
    * Mostra a tela corrida com os dados anteriores a ela e o botão para iniciar a corrida.
    * 
    */
    public void mostrarCorrida() {
        telaBotao.setEnabled(false);
        telaBotao.setVisible(false);       
        telaQuali.setEnabled(false);
        telaQuali.setVisible(false);
        telaCorrida = new TelaCorrida();
        telaCorrida.setLocationRelativeTo(telaQuali);
        telaCorrida.setEnabled(true); 
        telaCorrida.setVisible(true); 
        telaBotao = new ApertouBotao(2);
        telaBotao.setLocationRelativeTo(telaCorrida);
        telaBotao.setSize(436, 166); 
        telaBotao.setEnabled(true);
        telaBotao.setVisible(true);
    }

    /**
    * Solicita o início da corrida na classe comunication e inicia a corrida.
    * 
    */
    public void iniciarCorrida() {
        comunicacao.getComecaCorrida();
        telaBotao.setEnabled(false);
        telaBotao.setVisible(false);
        this.corrida.start();
    }

    /**
    * Altera a referência da tela de corrida.
    * 
    * @param tela Nova tela de corrida.
    */
    public void setTelaCorrida(TelaCorrida tela) {
        this.telaCorrida = tela;
    }

    /**
    * Abre o botão virtual para ir para a tela inicial.
    * 
    */
    public void abrirBotaoParaInicio() {
        telaBotao = new ApertouBotao(3);
        telaBotao.setLocationRelativeTo(telaCorrida);
        telaBotao.setSize(436, 166); 
        telaBotao.setEnabled(true);
        telaBotao.setVisible(true);
    }

    /**
    * Abre a tela inicial a partir da tela de corrida e serealiza os dados.
    * 
    */
    public void voltarParaTelaInicial() {
        for (int i = 0; i < this.corrida.pilotos.size(); i++) {
            corrida.pilotos.get(i).setMelhorMili(0);
            corrida.pilotos.get(i).setMelhorSec(0);
            corrida.pilotos.get(i).setVoltas(0);
            corrida.pilotos.get(i).setTempoSec(0);
            corrida.pilotos.get(i).setTempoMili(0);
            corrida.pilotos.get(i).setPrimeiraLeitura(true);
        }
        bancoDados.serealiza();
        telaCorrida.setEnabled(false);
        telaCorrida.setVisible(false);
        telaBotao.setEnabled(false);
        telaBotao.setVisible(false);
        telaInicial.setLocationRelativeTo(telaCadastro);
        telaInicial.setEnabled(true);
        telaInicial.setVisible(true);      
                
    }  

    /**
    * Repassa o pedido de encerramento da conexão com o servidor atual para a classe comunication.
    * 
    */
    public void encerraConexao() {
        comunicacao.PostEncerraConexao();
    }    
    
    /**
    * Método que altera o booleando para determinar o começo da contagem regressiva.
    * 
    * @param play determina se a contagem regressiva começa ou não.
    */
    public void setPlay(boolean play){
        this.play = play;
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
        comunicacao.enviaConfigFan(tQuali, nVoltas, pista, recordista, tempoRecordPista, participantes);
    }

    /**
    * Manda as configurações da corrida e qualificatório para o cliente telespectador.
    * 
    * 
    * @param pilotos pilotos da corrida
    * @param recordista nome do recordista
    * @param tempoRecordPista tempo record da pista
    * @param etapa etapa de corrida ou qualificatório
    */
    public void enviaDadosFan(ArrayList<Piloto> pilotos, String recordista, String tempoRecordPista, String etapa) {
        comunicacao.enviaDadosFan(pilotos, recordista, tempoRecordPista, etapa);
    }
    
    
}
