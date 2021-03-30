
package ClienteAutorama.controller;

import ClienteAutorama.model.Piloto;
import java.util.ArrayList;

public class Corrida {
    
    public ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    
    
    public Corrida() {
    }
    
    public static Corrida uniqueInstance = new Corrida();

    public static Corrida getInstance() {
	return uniqueInstance;
    }  

    public void setDados() {
        
    }
    
    
    
}
