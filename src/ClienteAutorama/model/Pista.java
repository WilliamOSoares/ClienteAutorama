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
        if(this.melhorSec == 0){
            String s = ".";
            this.setRecordista(piloto.getNome());
            this.melhorSec = piloto.getMelhorSec();
            this.melhorMili = piloto.getMelhorMili();
            int min = this.melhorSec/60;
            int sec = this.melhorSec%60;
            if(melhorMili <10){
                s = ".00";
            } else if(melhorMili <100){
                s = ".0";
            }
            this.setTempoRecordPista(min+":"+sec +s+this.melhorMili);
        } else{
            if(piloto.getMelhorSec()<this.melhorSec){
                String s = ".";
                this.melhorSec = piloto.getMelhorSec();
                this.melhorMili = piloto.getMelhorMili();
                this.setRecordista(piloto.getNome());
                int min = this.melhorSec/60;
                int sec = this.melhorSec%60;
                if(melhorMili <10){
                    s = ".00";
                } else if(melhorMili <100){
                    s = ".0";
                }
                this.setTempoRecordPista(min+":"+sec +s+this.melhorMili);
            } else if(piloto.getMelhorSec()==this.melhorSec){
                if(piloto.getMelhorMili()<this.melhorMili){
                    String s = ".";
                    this.melhorMili = piloto.getMelhorMili();
                    this.setRecordista(piloto.getNome());
                    int min = this.melhorSec/60;
                    int sec = this.melhorSec%60;
                    if(melhorMili <10){
                        s = ".00";
                    } else if(melhorMili <100){
                        s = ".0";
                    }
                    this.setTempoRecordPista(min+":"+sec +s+this.melhorMili);
                }
            } 
        }
    }
}
