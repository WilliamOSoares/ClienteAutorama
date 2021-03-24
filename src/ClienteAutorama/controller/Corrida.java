
package ClienteAutorama.controller;

import java.util.ArrayList;

public class Corrida {
    
    private Corrida() {
    }
    
    private static Corrida uniqueInstance = new Corrida();

    public static Corrida getInstance() {
	return uniqueInstance;
    }  
    
    ArrayList<String> arrayEPC;
       
    
    public void getEPC(ArrayList<String> array){
        arrayEPC = new ArrayList(array);
    }
    
}
