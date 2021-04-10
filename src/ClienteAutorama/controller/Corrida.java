
package ClienteAutorama.controller;

import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Corrida implements Runnable{
    
    GerenciadorTelas gerenciador;
    public ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    public DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public Pista pistaLocal;
    public int numVoltas, ciclo = 0;
    public String tempQuali;
    public boolean fimQuali = false;
    public boolean fimCorrida = false;
    public boolean online = false;
    
    
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

    public  void getAtualizacao() {//ArrayList<Piloto> getAtualizacao() {
        gerenciador = GerenciadorTelas.getInstance();
        //teste
        while(!isFimQuali()){
        //teste
        while(!gerenciador.comunicacao.recebido.has("CicloLeitura") && !gerenciador.comunicacao.recebido.has("status")){             
            //System.out.println(gerenciador.comunicacao.recebido.toString());
            //System.out.println("esperando no while da primeira leitura");
            gerenciador.telaQuali.pilotos = this.pilotos;
        }
        String obj = Integer.toString(ciclo);
        System.out.println(obj);
        if(ciclo>0){
            while(!gerenciador.comunicacao.recebido.get("CicloLeitura").equals(obj) && !gerenciador.comunicacao.recebido.has("status")){
               // System.out.println("esperando no while da segunda leitura");
                //System.out.println(gerenciador.comunicacao.recebido.get("CicloLeitura"));
                //System.out.println(ciclo);
                gerenciador.telaQuali.pilotos = this.pilotos;
            }
        }
        
        if(gerenciador.comunicacao.recebido.has("CARRO0")){
            String a = "CARRO";
            String b = "TEMPO";
            for(int i = 0; i<5; i++){
                String s = a+i;
                String t = b+i;
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
                                System.out.println(this.pilotos.get(j).getTempoVolta());
                                this.pilotos.get(j).setVoltas(this.ciclo);
                                this.pilotos.get(j).setTempoInit(LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData));
                                //this.pilotos.get(j).setPrimeiraLeitura(true);
                            }
                        }
                    }
                }
            }
            ciclo++;
        } else if (gerenciador.comunicacao.recebido.has("status")){
            this.fimQuali = true;
            ciclo = 0;
            this.stop();
        }// ciclo++;
        
        for(int i = 0; i<pilotos.size(); i++){System.out.println(this.pilotos.get(i).getNome());}
        this.pilotos = this.insertionSort(pilotos);
        for(int i = 0; i<pilotos.size(); i++){System.out.println(this.pilotos.get(i).getNome());}
        gerenciador.telaQuali.pilotos = this.pilotos;
        System.out.println(gerenciador.comunicacao.recebido.toString());
        //return this.pilotos;
        //teste
        //gerenciador.telaQuali.modelo.setArray(pilotos);      
        }
        System.out.println("Passou direto");
        //teste
    }
    /*
    public static void main(String[] args) {
        String s = "CARRO";
        String t = "TEMPO";
        for(int i = 0; i<5; i++){
            System.out.println(Integer.toString(i));
            String x = Integer.toString(i);
            String a = s+x;
            String b = t+x;
            //s.concat(x);
            //t.concat(x);
            System.out.println(a+","+b);
        }
    }
    */
    
    public ArrayList<Piloto> insertionSort(ArrayList<Piloto> vetor) {
        int j;
        Piloto key;
        int i;

            for (j = 1; j < vetor.size(); j++){
                key = vetor.get(j);
                for (i = j - 1; (i >= 0) && (vetor.get(i).getTempoSec() > key.getTempoSec()); i--){
                    vetor.set((i+1),vetor.get(i));
                }
                vetor.set((i+1), key);
            }
        return vetor;
    }

    @Override
    public void run() {
        while(online){
            this.getAtualizacao();
        }
    }
    
    public void start(){
        if(!online){
            this.online = true;
            new Thread(this).start();
        }
        
    }
    
    public void stop(){
        this.online = false;        
    }
    
}
