package ClienteAutorama.model;

import java.io.Serializable;

public class Pista implements Serializable {
    
    private String id, nome, pais, tempoRecordPista, tempoMin, recordista;
    private int melhorSec = 1000, melhorMili = 1000;

    public Pista(String id, String nome, String pais, String tempoMin) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.tempoMin = tempoMin;
        this.recordista = "Nenhum";
        this.tempoRecordPista = "Nenhum";
    }

    public String getTempoMin() {
        return tempoMin;
    }

    public void setTempoMin(String tempoMin) {
        this.tempoMin = tempoMin;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTempoRecordPista() {
        return tempoRecordPista;
    }

    public void setTempoRecordPista(String tempoRecordPista) {
        this.tempoRecordPista = tempoRecordPista;
    }

    public String getRecordista() {
        return recordista;
    }

    public void setRecordista(String recordista) {
        this.recordista = recordista;
    }

    public boolean novoRecord(Piloto piloto){
        System.out.println(piloto.getTempoSec() +"-piloto-"+piloto.getTempoMili());
        System.out.println(melhorSec+"-pista-"+melhorMili);
        if(piloto.getTempoSec()<this.melhorSec){
            this.melhorSec = piloto.getTempoSec();
            this.melhorMili = piloto.getTempoMili();
            this.setRecordista(piloto.getNome());
            int min = this.melhorSec/60;
            int sec = this.melhorSec%60;
            this.setTempoRecordPista(min+":"+sec +"."+this.melhorMili);
            System.out.println(min+"-min,sec-"+sec);
            System.out.println(piloto.getTempoSec() +"-piloto-"+piloto.getTempoMili());
            System.out.println(melhorSec+"-pista-"+melhorMili);
            return true;
        } else if(piloto.getTempoSec()==this.melhorSec){
            if(piloto.getTempoMili()<this.melhorMili){
                this.melhorMili = piloto.getTempoMili();
                this.setRecordista(piloto.getNome());
                int min = this.melhorSec/60;
                int sec = this.melhorSec%60;
                this.setTempoRecordPista(min+":"+sec +"."+this.melhorMili);
                System.out.println(min+"-min,sec-"+sec);
                System.out.println(piloto.getTempoSec() +"-piloto-"+piloto.getTempoMili());
                System.out.println(melhorSec+"-pista-"+melhorMili);
                return true;
            } else{
                System.out.println(piloto.getTempoSec() +"-piloto-"+piloto.getTempoMili());
                System.out.println(melhorSec+"-pista-"+melhorMili);
                return false;
            }          
        }else{
            System.out.println(piloto.getTempoSec() +"-piloto-"+piloto.getTempoMili());
            System.out.println(melhorSec+"-pista-"+melhorMili);
            return false;
        }        
    }
}
