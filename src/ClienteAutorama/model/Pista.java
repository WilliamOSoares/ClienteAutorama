package ClienteAutorama.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pista implements Serializable {
    
    private String id, nome, pais, tempoRecordPista, tempoMin, recordista;
    private int melhorSec = 0, melhorMili = 0;

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
            System.out.println("recordista zerado" +melhorSec+"-"+melhorMili);
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
            System.out.println("recordista novo" +melhorSec+"-"+melhorMili);
        } else{
            if(piloto.getMelhorSec()<this.melhorSec){
                System.out.println("recordista com menor segundo" +melhorSec+"-"+melhorMili);
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
                System.out.println("recordista novo" +melhorSec+"-"+melhorMili);
            } else if(piloto.getMelhorSec()==this.melhorSec){
                if(piloto.getMelhorMili()<this.melhorMili){
                    System.out.println("recordista com menor mili" +melhorSec+"-"+melhorMili);
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
                    System.out.println("recordista novo" +melhorSec+"-"+melhorMili);
                }
            } 
        }
    }
}
