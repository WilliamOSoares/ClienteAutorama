package ClienteAutorama.model;

import java.io.Serializable;

public class Pista implements Serializable {
    
    private String id, nome, pais, tempoRecordPista, tempoMin;
    private Piloto recordista;

    public Pista(String id, String nome, String pais, String tempoMin) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.tempoMin = tempoMin;
        this.recordista = null;
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

    public Piloto getRecordista() {
        return recordista;
    }

    public void setRecordista(Piloto recordista) {
        this.recordista = recordista;
    }
    
    
}
