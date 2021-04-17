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

    public void novoRecord(Piloto piloto){
        System.out.println(piloto.getMelhorSec() +"-piloto-"+piloto.getMelhorMili());
        System.out.println(melhorSec+"-pista-"+melhorMili);
        if(this.melhorSec == 0){
            this.melhorSec = piloto.getMelhorSec();
            this.melhorMili = piloto.getMelhorMili();
            int min = this.melhorSec/60;
            int sec = this.melhorSec%60;
            this.setTempoRecordPista(min+":"+sec +"."+this.melhorMili);
        } else{
            if(piloto.getMelhorSec()<this.melhorSec){
                this.melhorSec = piloto.getMelhorSec();
                this.melhorMili = piloto.getMelhorMili();
                this.setRecordista(piloto.getNome());
                int min = this.melhorSec/60;
                int sec = this.melhorSec%60;
                this.setTempoRecordPista(min+":"+sec +"."+this.melhorMili);
            } else if(piloto.getMelhorSec()==this.melhorSec){
                if(piloto.getMelhorMili()<this.melhorMili){
                    this.melhorMili = piloto.getMelhorMili();
                    this.setRecordista(piloto.getNome());
                    int min = this.melhorSec/60;
                    int sec = this.melhorSec%60;
                    this.setTempoRecordPista(min+":"+sec +"."+this.melhorMili);
                }
            } 
        }
    }
}
