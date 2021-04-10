
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
import ClienteAutorama.view.TelaInicial;
import java.util.ArrayList;
import org.json.JSONObject;

public class GerenciadorTelas {
    
    public Comunication comunicacao = Comunication.getInstance();
    public GerenciadorBD bancoDados = GerenciadorBD.getInstance();
    public Corrida corrida = Corrida.getInstance();
    public Principal telaPrincipal;
    public TelaInicial telaInicial = new TelaInicial();
    public Cadastro telaCadastro = new Cadastro();
    public ConfiguraCorrida telaConfig;
    public Qualificatorio telaQuali;
    public Corrida telaCorrida;
    public ApertouBotao telaBotao;
    public int flag = 2;
    
    private GerenciadorTelas() {
    }
    
    private static GerenciadorTelas uniqueInstance = new GerenciadorTelas();

    public static GerenciadorTelas getInstance() {
	return uniqueInstance;
    }
    
    public void postConectConfiguraLeitor(String ip, String porta,String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        comunicacao.iniciaCliente(ip, porta);
        comunicacao.thread.start(comunicacao.entrada);
        comunicacao.POSTconfigLeitor(portaSerial, baudrate, regiao, antena, protocolo, power);
    }
    
    public String getDadosCorrida(String quali, String voltas, String tempoMIN){
        return comunicacao.getDadosCorrida(quali, voltas, tempoMIN);
    }
    
    public void setTelaPrincipal(Principal tela){
        telaPrincipal = tela;
    }
    
    public void abrirTelaInicial(){
        telaPrincipal.setEnabled(false);
        telaPrincipal.setVisible(false);
        flag = bancoDados.desserealiza();
        telaInicial.setLocationRelativeTo(telaPrincipal);
        telaInicial.setVisible(true);
    }
    
    public void setTelaInicial(TelaInicial tela){
        telaInicial = tela;
    }
    
    public void abrirTelaCadastro(){
        telaInicial.setEnabled(false);
        telaInicial.setVisible(false);
        telaCadastro.setLocationRelativeTo(telaInicial);
        telaCadastro.setEnabled(true);
        telaCadastro.setVisible(true);
    }
    
    public void setTelaCadastro(Cadastro tela){
        telaCadastro = tela;
    }
    
    public void abrirTelaInicialDoCadastro(){
        telaCadastro.setEnabled(false);
        telaCadastro.setVisible(false);
        flag = bancoDados.desserealiza();
        telaInicial.setLocationRelativeTo(telaCadastro);
        telaInicial.setEnabled(true);
        telaInicial.setVisible(true);
    }
    
    public void abrirTelaConfigCorrida(){
        telaInicial.setEnabled(false);
        telaInicial.setVisible(false);
        telaConfig = new ConfiguraCorrida();
        telaConfig.setLocationRelativeTo(telaConfig);
        telaConfig.setEnabled(true);
        telaConfig.setVisible(true);
    }

    public void setTelaConfig(ConfiguraCorrida tela) {
        telaConfig = tela;
    }

    public void abrirTelaInicialDoConfig() {
        telaConfig.setEnabled(false);
        telaConfig.setVisible(false);
        telaConfig = null;
        telaInicial.setLocationRelativeTo(telaCadastro);
        telaInicial.setEnabled(true);
        telaInicial.setVisible(true);
    }

    public void abrirQualificatorio(ArrayList<Piloto> corredores, int nVoltas, String tQuali, Pista pista) {
        corrida = Corrida.getInstance();
        corrida.dadosCorrida(corredores, nVoltas, tQuali, pista);        
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
    
    public void setTelaQuali(Qualificatorio tela) {
        telaQuali = tela;
    }
    
    public void mostrarQualificatorio(){
        comunicacao.getComecaQuali();
        telaBotao.setEnabled(false);
        telaBotao.setVisible(false);
        //corrida.getAtualizacao();
        this.corrida.start();
    }

    public int cadastrarPiloto(String ID, String nome, String nacio) {
        Piloto novoPiloto = new Piloto(ID, nome, nacio);        
        return bancoDados.salvaPiloto(novoPiloto);
    }

    public int cadastrarEquipe(String ID, String nome, String nacio, String ano) {
        Equipe novaEquipe = new Equipe(ID, nome, nacio, ano);        
        return bancoDados.salvaEquipe(novaEquipe);
    }

    public int cadastrarPista(String ID, String nome, String pais, String tempoMin) {
        Pista novaPista = new Pista(ID, nome, pais, tempoMin);
        return bancoDados.salvaPista(novaPista);
    }

    public int cadastrarCarro(String EPC, String n) {
        Carro novoCarro = new Carro(EPC, n);
        return bancoDados.salvaCarro(novoCarro);
    }

    public int cadastrarPilotoEquipe(String IDpiloto, String IDequipe) {
        return bancoDados.salvaPilotoEquipe(IDpiloto, IDequipe);
    }

    public int cadastrarCarroEquipe(String IDcarro, String IDequipe) {
        return bancoDados.salvaCarroEquipe(IDcarro, IDequipe);
    }

    public int cadastrarPilotoCarro(String IDpiloto, String IDcarro) {
        return bancoDados.salvaPilotoCarro(IDpiloto, IDcarro);
    }

    public int salvarCadastro() {
        return bancoDados.serealiza();
    }

    public JSONObject getEPC() {
        return comunicacao.getEPC();
    }

}
