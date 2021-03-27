package ClienteAutorama.model;

public class Pista {
    
    private String id, nome, pais, tempoRecordPista;
    private Piloto recordista;

    public Pista(String id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
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
