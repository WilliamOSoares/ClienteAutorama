
package ClienteAutorama.controller;

import ClienteAutorama.view.Cadastro;
import ClienteAutorama.view.ConfiguraCorrida;
import ClienteAutorama.view.Principal;
import ClienteAutorama.view.Qualificatorio;
import ClienteAutorama.view.TelaInicial;

public class GerenciadorTelas {
    
    public Comunication comunicacao = Comunication.getInstance();
    public GerenciadorArquivo arquivo = GerenciadorArquivo.getInstance();
    public Corrida corrida = Corrida.getInstance();
    public Principal telaPrincipal;
    public TelaInicial telaInicial = new TelaInicial();
    public Cadastro telaCadastro = new Cadastro();
    public ConfiguraCorrida telaConfig;
    public Qualificatorio telaQuali;
    public Corrida telaCorrida;
    
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
    
    public String getDadosCorrida(String quali, String voltas){
        return comunicacao.getDadosCorrida(quali, voltas);
    }
    
    public void setTelaPrincipal(Principal tela){
        telaPrincipal = tela;
    }
    
    public void abrirTelaInicial(){
        telaPrincipal.setEnabled(false);
        telaPrincipal.setVisible(false);
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

    public void abrirQualificatorio() {
        telaConfig.setEnabled(false);
        telaConfig.setVisible(false);
        telaQuali = new Qualificatorio();
        telaQuali.setLocationRelativeTo(telaQuali);
        telaQuali.setEnabled(true);
        telaQuali.setVisible(true);
    }
    
}
