package ClienteAutorama.model;

import java.io.Serializable;

public class Piloto implements Serializable {

    private String id, nome, nacionalidade;
    private Carro carro;
    private Equipe equipe;
    private int voltas = 0;
    private float tempoVolta = 0;
    private int posicao;

    public Piloto(String id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        //this.voltas=0;
        //this.tempoVolta=0;
    }
    /*
    public Piloto(String id, String nome, String nacionalidade, Equipe equipe) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.equipe = equipe;
    }*/

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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public int getVoltas() {
        return voltas;
    }

    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }

    public float getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(float tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    
}