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
    
    private GerenciadorArquivo() {
        configInicial = new File("arquivos\\configInicial.txt");
    }
    
    private static GerenciadorArquivo uniqueInstance = new GerenciadorArquivo();

    public static GerenciadorArquivo getInstance() {
	return uniqueInstance;
    }
    
    public File configInicialLeitor(String portaSerial, String baudrate, String regiao, String antena, String protocolo, String power){
        
        try { 
            configInicial.createNewFile();
            //FileOutputStream arq = new FileOutputStream(configInicial.getPath(), true);
            PrintWriter escrita = new PrintWriter(configInicial); 
            escrita.println("configLeitor");
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
        
        return configInicial;
    }
    
}
