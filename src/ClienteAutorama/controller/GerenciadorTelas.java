
package ClienteAutorama.controller;

import ClienteAutorama.view.Cadastro;
import ClienteAutorama.view.Principal;
import ClienteAutorama.view.TelaInicial;

public class GerenciadorTelas {
    
    public Comunication comunicacao = Comunication.getInstance();
    public GerenciadorArquivo arquivo = GerenciadorArquivo.getInstance();
    Corrida corrida = Corrida.getInstance();
    public Principal telaPrincipal;
    public TelaInicial telaInicial = new TelaInicial();
    public Cadastro telaCadastro = new Cadastro();
    
    private GerenciadorTelas() {
    }
    
    private static GerenciadorTelas uniqueInstance = new GerenciadorTelas();

    public static GerenciadorTelas getInstance() {
	return uniqueInstance;
    }
    
    public void conectConfiguraLeitor(String ip, String porta,String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        comunicacao.iniciaCliente(ip, porta);
        comunicacao.thread.start(comunicacao.entrada);
        comunicacao.POSTconfigLeitor(portaSerial, baudrate, regiao, antena, protocolo, power);
    }
    
    public void setTelaPrincipal(Principal tela){
        telaPrincipal = tela;
    }
    
    public void abrirTelaInicial(){
        telaPrincipal.setEnabled(false);
        telaPrincipal.setVisible(false);
        //telaInicial = new TelaInicial();
        telaInicial.setLocationRelativeTo(telaPrincipal);
        telaInicial.setVisible(true);
    }
    
    public void setTelaInicial(TelaInicial tela){
        telaInicial = tela;
    }
    
    public void abrirTelaCadastro(){
        telaInicial.setEnabled(false);
        telaInicial.setVisible(false);
        //telaInicial = new TelaInicial();
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
        //telaInicial = new TelaInicial();
        telaInicial.setLocationRelativeTo(telaCadastro);
        telaInicial.setEnabled(true);
        telaInicial.setVisible(true);
    }
    
}
