package ClienteAutorama.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciadorArquivo {
    
    public File configInicial;
    public File carros;
    public File pilotos;
    public File pistas;
    public File equipe;
    
    private GerenciadorArquivo() {
        
    }
    
    private static GerenciadorArquivo uniqueInstance = new GerenciadorArquivo();

    public static GerenciadorArquivo getInstance() {
	return uniqueInstance;
    }
    
    public void configInicialLeitor(String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        
        try { 
            configInicial = new File("arquivos\\configInicial.txt");
            configInicial.createNewFile();
            PrintWriter escrita = new PrintWriter(configInicial); 
            escrita.println(portaSerial);
            escrita.println(baudrate);
            escrita.println(regiao);
            escrita.println(antena);
            escrita.println(protocolo);
            escrita.println(power);
            escrita.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
