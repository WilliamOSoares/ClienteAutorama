
package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Corrida implements Runnable{
    
    GerenciadorTelas gerenciador;
    public ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    public DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public Pista pistaLocal;
    public int numVoltas, ciclo = 0, nVolta = 0;
    public String tempQuali;
    public boolean fimQuali = false;
    public boolean fimCorrida = false;
    public boolean online = false;
    public ArrayList<Carro> leitura = new ArrayList<>();
    
    
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

    public  void getAtualizacao() {
        gerenciador = GerenciadorTelas.getInstance();
        while(!isFimQuali()){
        while(!gerenciador.comunicacao.recebido.has("CicloLeitura") && !gerenciador.comunicacao.recebido.has("status")){             
            
        }
        String obj = Integer.toString(ciclo);
        //System.out.println(obj);
        if(ciclo>0){
            while(!gerenciador.comunicacao.recebido.get("CicloLeitura").equals(obj) && !gerenciador.comunicacao.recebido.has("status")){
               
            }
        }
        
        if(validaLeitura()){
            //System.out.println("VALIDOU A LEITURA");
            
            for (int i = 0; i < leitura.size(); i++) {
                for (int j = 0; j < pilotos.size(); j++) {
                    if(leitura.get(i).getEPC().equals(pilotos.get(j).getCarro().getEPC())){
                        if(this.pilotos.get(j).isPrimeiraLeitura()){
                            this.pilotos.get(j).setTempoInit(leitura.get(i).getTempoVolta());
                            this.pilotos.get(j).setPrimeiraLeitura(false);
                        } else{
                            System.out.println("Passei no else, mas não entrei no if");
                            if(leitura.get(i).getTempoVolta().getMinute() != pilotos.get(j).getTempoInit().getMinute()){
                                //System.out.println("Alterou o tempo");
                                //System.out.println(leitura.get(i).getTempoVolta().toString());
                                //System.out.println(pilotos.get(j).getTempoInit().toString());
                                this.pilotos.get(j).setTempoFinal(leitura.get(i).getTempoVolta());
                                this.pilotos.get(j).setTempoVolta();
                                //System.out.println(this.pilotos.get(j).getTempoVolta());
                                this.pilotos.get(j).setVoltas(this.pilotos.get(j).getVoltas()+1);
                                this.pilotos.get(j).setTempoInit(leitura.get(i).getTempoVolta());
                            }
                        }
                    }
                }
            }
            if (gerenciador.comunicacao.recebido.has("status")){
                this.fimQuali = true;
                ciclo = 0;
                this.stop();
                for (int i = 0; i < gerenciador.bancoDados.getBdPistas().size(); i++) {
                    if(gerenciador.bancoDados.bdPistas.get(i).getNome().equals(pistaLocal.getNome())){
                        gerenciador.bancoDados.bdPistas.set(i, pistaLocal);
                    }
                }                
            } 
            //for(int i = 0; i<pilotos.size(); i++){System.out.println(this.pilotos.get(i).getNome());}
            if(pilotos.get(0).getVoltas()>0){
                this.pilotos = this.insertionSort(pilotos);
                pistaLocal.novoRecord(pilotos.get(0));
            }            
            //for(int i = 0; i<pilotos.size(); i++){System.out.println(this.pilotos.get(i).getNome());}
            gerenciador.telaQuali.pilotos = this.pilotos;
            System.out.println(gerenciador.comunicacao.recebido.toString());
        }
        
        ciclo++;
        }
        System.out.println("Passou direto");
    }
    
    public ArrayList<Piloto> insertionSort(ArrayList<Piloto> vetor) {
        ///*
        for(int i = 0; i<vetor.size();i++){
            Piloto aux = vetor.get(i);
            for(int j = i+1; j<=vetor.size()-1;j++){
                Piloto compara = vetor.get(j);
                if (compara.getMelhorSec()<= aux.getMelhorSec()){
                    if(compara.getMelhorSec()== aux.getMelhorSec()){
                        if(compara.getMelhorMili()< aux.getMelhorMili()){
                             vetor.set((i),compara);
                             vetor.set((j),aux);
                             aux = vetor.get(i);
                        }
                    }else {
                        vetor.set((i),compara);
                        vetor.set((j),aux);
                        aux = vetor.get(i);
                    }   
                }      
            }
        
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

    private boolean validaLeitura() {
        gerenciador = GerenciadorTelas.getInstance();
        if(leitura==null){
            String a = "CARRO";
            String b = "TEMPO";
            for(int i = 0; i<5; i++){
                    String s = a+i;
                    String t = b+i;
                    if(gerenciador.comunicacao.recebido.has(s)){
                        for(int j = 0; j<this.pilotos.size(); j++){
                            if(gerenciador.comunicacao.recebido.get(s).equals(this.pilotos.get(j).getCarro().getEPC())){
                                leitura.add(new Carro(gerenciador.comunicacao.recebido.get(s).toString(), LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData)));
                            }
                        }
                    }
            }
            return true;
        } else if(leitura!=null){ 
            String a = "CARRO";
            String b = "TEMPO";
            boolean retorno = false;            
            for(int i = 0; i<5; i++){
                    String s = a+i;
                    String t = b+i;
                    int x = 0;
                    if(gerenciador.comunicacao.recebido.has(s)){
                        for(int j = 0; j<this.leitura.size(); j++){
                            if(gerenciador.comunicacao.recebido.get(s).equals(this.leitura.get(j).getEPC())){
                                x++;
                                LocalDateTime tempoBase;
                                if(gerenciador.comunicacao.recebido.get(t).toString().length()<20){
                                    tempoBase = LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                } else{
                                    tempoBase = LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData);
                                }                                
                                LocalDateTime aux;
                                aux = tempoBase.minusHours(this.leitura.get(j).getTempoVolta().getHour()).minusMinutes(this.leitura.get(j).getTempoVolta().getMinute()).minusSeconds(this.leitura.get(j).getTempoVolta().getSecond()).minusNanos(this.leitura.get(j).getTempoVolta().getNano());
                                System.out.println(aux.toString());
                                if(aux.getMinute()>=Integer.parseInt(pistaLocal.getTempoMin())){
                                    this.leitura.get(j).setTempoVolta(tempoBase);
                                    retorno = true;
                                }
                            }                            
                        }
                        if(x==0){
                            leitura.add(new Carro(gerenciador.comunicacao.recebido.get(s).toString(), LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData)));
                            retorno = true;
                        }
                    }                    
            }
            return retorno;
        }else{        
            return false;
        }        
    }
}
