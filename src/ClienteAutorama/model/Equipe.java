package ClienteAutorama.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipe implements Serializable {
    private String id, nome, nacionalidade, ano; 

    private ArrayList<Piloto> Pilotos;
    private ArrayList<Carro> Carros;

    public Equipe(String id, String nome, String nacionalidade, String ano) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.ano = ano;
        this.Carros = new ArrayList();
        this.Pilotos = new ArrayList();
    }

    public ArrayList<Piloto> getPilotos() {
        return Pilotos;
    }

    public void addPilotos(Piloto Piloto) {
        this.Pilotos.add(Piloto);
    }

    public ArrayList<Carro> getCarros() {
        return Carros;
    }

    public void addCarros(Carro Carro) {
        this.Carros.add(Carro);
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}