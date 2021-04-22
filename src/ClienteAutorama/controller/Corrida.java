
package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Corrida implements Runnable{
    
    GerenciadorTelas gerenciador;
    public ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    public DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public Pista pistaLocal;
    public int numVoltas, ciclo = 0, nVolta = 0, retorno = 0;
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
        while(!isFimQuali() || !isFimCorrida()){
            while(!gerenciador.comunicacao.recebido.has("CicloLeitura") && !gerenciador.comunicacao.recebido.has("status")){ }
            String obj = Integer.toString(ciclo);
            
            if(ciclo>0){
                while(!gerenciador.comunicacao.recebido.get("CicloLeitura").equals(obj) && !gerenciador.comunicacao.recebido.has("status")){ }
            }
            
            if(validaLeitura()){            
                for (int i = 0; i < leitura.size(); i++) {
                    for (int j = 0; j < pilotos.size(); j++) {
                        if(leitura.get(i).getEPC().equals(pilotos.get(j).getCarro().getEPC())){
                            if(this.pilotos.get(j).isPrimeiraLeitura()){
                                this.pilotos.get(j).setTempoInit(leitura.get(i).getTempoVolta());                                
                                this.pilotos.get(j).setTempoGeral(leitura.get(i).getTempoVolta());
                                this.pilotos.get(j).setPrimeiraLeitura(false);                              
                            } else{
                                if(leitura.get(i).getTempoVolta().getMinute() != pilotos.get(j).getTempoInit().getMinute()){
                                    this.pilotos.get(j).setTempoFinal(leitura.get(i).getTempoVolta());
                                    this.pilotos.get(j).setTempoVolta();
                                    this.pilotos.get(j).setVoltas(this.pilotos.get(j).getVoltas()+1);
                                    this.pilotos.get(j).setTempoInit(leitura.get(i).getTempoVolta());
                                }
                            }
                        }
                    }
                }
            }           
                        
            if(pilotos.get(0).getVoltas()>0 && !isFimQuali()){
                System.out.println("Ordena quali");
                this.pilotos = this.insertionSort(pilotos);
                if (pilotos.get(0).getMelhorSec()!=0){
                    pistaLocal.novoRecord(pilotos.get(0));
                }                
            }    
            
            if(pilotos.get(0).getVoltas()>0 && !isFimCorrida()){
                System.out.println("Ordena corrida");
                this.pilotos = this.insertionSortCorrida(pilotos);
                for (int i = 0; i <pilotos.size(); i++) {
                    pistaLocal.novoRecord(pilotos.get(i));
                } 
            } 
            
            gerenciador.telaQuali.pilotos = this.pilotos;
            System.out.println(gerenciador.comunicacao.recebido.toString());
            ciclo++;
            
            if (gerenciador.comunicacao.recebido.has("status")){
                if (gerenciador.comunicacao.recebido.get("URL").equals("finalQuali")){    
                    setFimQuali(true);
                    setFimCorrida(true);
                    gerenciador.comunicacao.recebido.clear();
                    ciclo = 0;
                    this.stop();
                    for (int i = 0; i < gerenciador.bancoDados.getBdPistas().size(); i++) {
                        if(gerenciador.bancoDados.bdPistas.get(i).getNome().equals(pistaLocal.getNome())){
                            gerenciador.bancoDados.bdPistas.set(i, pistaLocal);
                        }
                    } 
                    gerenciador.bancoDados.serealiza();
                    gerenciador.abrirBotaoParaCorrida();
                } else if (gerenciador.comunicacao.recebido.get("URL").equals("finalCorrida")){
                    setFimQuali(true);
                    setFimCorrida(true);
                    gerenciador.comunicacao.recebido.clear();
                    ciclo = 0;
                    for (int i = 0; i < pilotos.size(); i++) {
                        pilotos.get(i).setPrimeiraLeitura(true);
                    }
                    this.stop();
                    for (int i = 0; i < gerenciador.bancoDados.getBdPistas().size(); i++) {
                        if(gerenciador.bancoDados.bdPistas.get(i).getNome().equals(pistaLocal.getNome())){
                            gerenciador.bancoDados.bdPistas.set(i, pistaLocal);
                        }
                    }
                    gerenciador.bancoDados.serealiza();
                    gerenciador.abrirBotaoParaInicio();
                }                
            }             
        }
        System.out.println("Passou direto");
    }
    
    public ArrayList<Piloto> insertionSortCorrida(ArrayList<Piloto> vetor) {
        for(int i = 0; i<vetor.size();i++){
            Piloto aux = vetor.get(i);
            String divide = vetor.get(i).getTempoGeral();
            String[] resto, geral = divide.split(":");
            resto = geral[geral.length-1].split("\\.");
            int[] atual = new int[geral.length+1];
            atual[0] = Integer.parseInt(geral[0]); //Horas corridas do piloto
            atual[1] = Integer.parseInt(geral[1]); //Minutos corridas do piloto
            atual[2] = Integer.parseInt(resto[0]); //Segundos corridas do piloto
            atual[3] = Integer.parseInt(resto[1]); //Milesimos corridas do piloto
            for(int j = i+1; j<=vetor.size()-1;j++){
                Piloto compara = vetor.get(j);
                String separa = vetor.get(j).getTempoGeral();
                String[] parte, todo = separa.split(":");
                parte = todo[todo.length-1].split("\\.");
                int[] comparado = new int[todo.length+1];
                comparado[0] = Integer.parseInt(todo[0]);
                comparado[1] = Integer.parseInt(todo[1]);
                comparado[2] = Integer.parseInt(parte[0]);
                comparado[3] = Integer.parseInt(parte[1]);
                if(compara.getVoltas()>=aux.getVoltas()){
                    if(compara.getVoltas()==aux.getVoltas()){
                        if(comparado[0]<=atual[0]){ //Comparando hora
                            if(comparado[0]==atual[0]){
                                if(comparado[1]<=atual[1]){ //Comparando minuto
                                    if(comparado[1]==atual[1]){
                                        if(comparado[2]<=atual[2]){ //Comparando segundo
                                            if(comparado[2]==atual[2]){
                                                if(comparado[3]<=atual[3]){ //Comparando milesimo
                                                    if(comparado[3]<atual[3]){
                                                        vetor.set((i),compara);
                                                        vetor.set((j),aux);
                                                        aux = vetor.get(i);
                                                    }  
                                                }
                                            } else{
                                                vetor.set((i),compara);
                                                vetor.set((j),aux);
                                                aux = vetor.get(i);
                                            }  
                                        }
                                    } else{
                                        vetor.set((i),compara);
                                        vetor.set((j),aux);
                                        aux = vetor.get(i);
                                    }
                                }
                            } else{
                                vetor.set((i),compara);
                                vetor.set((j),aux);
                                aux = vetor.get(i);
                            } 
                        }  
                    } else{
                        vetor.set((i),compara);
                        vetor.set((j),aux);
                        aux = vetor.get(i);
                    }
                }    
            }        
        }
        return vetor;
    }
    
    public ArrayList<Piloto> insertionSort(ArrayList<Piloto> vetor) {
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
                                retorno++;
                            }
                        }
                    }
            }
            if (retorno>=pilotos.size()) {
                retorno = 0;
                return true;
            } else {
                return false;
            }
        } else if(leitura!=null){ 
            String a = "CARRO";
            String b = "TEMPO";           
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
                                if(aux.getMinute()>=Integer.parseInt(pistaLocal.getTempoMin())){
                                    this.leitura.get(j).setTempoVolta(tempoBase);
                                    retorno++;
                                }
                            }                            
                        }
                        if(x==0){
                            leitura.add(new Carro(gerenciador.comunicacao.recebido.get(s).toString(), LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData)));
                            retorno++;
                        }
                    }                    
            }
        }if (retorno>=pilotos.size()) {
            retorno = 0;
            return true;
        } else {
            return false;
        }       
    } 
}
