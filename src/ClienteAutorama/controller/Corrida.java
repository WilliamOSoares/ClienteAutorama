
package ClienteAutorama.controller;

import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Corrida {
    
    GerenciadorTelas gerenciador;
    public ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    public DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public Pista pistaLocal;
    public int numVoltas, ciclo = 0;
    public String tempQuali;
    public boolean fimQuali = false;
    public boolean fimCorrida = false;
    
    
    public Corrida() {
    }
    
    public static Corrida uniqueInstance = new Corrida();

    public static Corrida getInstance() {
	return uniqueInstance;
    }  

    public void setDados() {
        
    }

    public ArrayList<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(ArrayList<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    public Pista getPistaLocal() {
        return pistaLocal;
    }

    public void setPistaLocal(Pista pistaLocal) {
        this.pistaLocal = pistaLocal;
    }

    public int getNumVoltas() {
        return numVoltas;
    }

    public void setNumVoltas(int numVoltas) {
        this.numVoltas = numVoltas;
    }

    public String getTempQuali() {
        return tempQuali;
    }

    public void setTempQuali(String tempQuali) {
        this.tempQuali = tempQuali;
    }

    public boolean isFimQuali() {
        return fimQuali;
    }

    public void setFimQuali(boolean fimQuali) {
        this.fimQuali = fimQuali;
    }

    public boolean isFimCorrida() {
        return fimCorrida;
    }

    public void setFimCorrida(boolean fimCorrida) {
        this.fimCorrida = fimCorrida;
    }    
    
    public void dadosCorrida(ArrayList<Piloto> corredores, int nVoltas, String tQuali, Pista pista) {
        pilotos = corredores;
        numVoltas = nVoltas;
        tempQuali = tQuali;
        pistaLocal = pista;
    }    

    public ArrayList<Piloto> getAtualizacao() {
        gerenciador = GerenciadorTelas.getInstance();
        while(!gerenciador.comunicacao.recebido.has("CicloLeitura") && !gerenciador.comunicacao.recebido.has("status")){             
            System.out.println(gerenciador.comunicacao.recebido.toString());
            System.out.println("esperando"); 
        }
        String obj = Integer.toString(ciclo);
        System.out.println(obj);
        while(!gerenciador.comunicacao.recebido.get("CicloLeitura").equals(obj) && !gerenciador.comunicacao.recebido.has("status")){
            System.out.println("esperando");
            System.out.println(gerenciador.comunicacao.recebido.get("CicloLeitura"));
        }
        
        if(gerenciador.comunicacao.recebido.has("CARRO0")){
            String s = "CARRO";
            String t = "TEMPO";
            for(int i = 0; i<5; i++){
                s.concat(Integer.toString(i));
                t.concat(Integer.toString(i));
                System.out.println(s+","+t);
                if(gerenciador.comunicacao.recebido.has(s)){
                    for(int j = 0; j<this.pilotos.size(); j++){
                        if(gerenciador.comunicacao.recebido.get(s).equals(this.pilotos.get(j).getCarro().getEPC())){
                            if(this.pilotos.get(j).isPrimeiraLeitura()){
                                System.out.println(gerenciador.comunicacao.recebido.get(t).toString());
                                this.pilotos.get(j).setTempoInit(LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData));
                                this.pilotos.get(j).setPrimeiraLeitura(false);
                            } else {
                                System.out.println(gerenciador.comunicacao.recebido.get(t).toString());
                                this.pilotos.get(j).setTempoFinal(LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData));
                                this.pilotos.get(j).setTempoVolta();
                                this.pilotos.get(j).setPrimeiraLeitura(true);
                            }
                        }
                    }
                }
            }
            ciclo++;
        } else if (gerenciador.comunicacao.recebido.has("status")){
            this.fimQuali = true;
            ciclo = 0;
        }
        ciclo++;
        System.out.println(gerenciador.comunicacao.recebido.toString());
        return this.pilotos;
    }
    
    
}
