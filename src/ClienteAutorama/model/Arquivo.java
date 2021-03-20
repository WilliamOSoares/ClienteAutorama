package ClienteAutorama.model;

import java.io.File;
import java.io.Serializable;


public class Arquivo implements Serializable{

    public File arquivo;
    
    public Arquivo(File arq) {
        arquivo = arq;    
    }
           
    
}
