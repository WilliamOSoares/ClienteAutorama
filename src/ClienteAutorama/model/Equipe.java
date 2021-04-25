package ClienteAutorama.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe de Equipe.
 * 
 * @author Víctor César e William Soares.
 */
public class Equipe implements Serializable {
    private String id, nome, nacionalidade, ano; 

    private ArrayList<Piloto> Pilotos;
    private ArrayList<Carro> Carros;

    /**
    * Construtor de Equipe.
    * 
    * @param id id da Equipe.
    * @param nome Nome da Equipe.
    * @param nacionalidade Nacionalidade da Equipe.
    * @param ano Ano de criação da Equipe.
    */
    public Equipe(String id, String nome, String nacionalidade, String ano) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.ano = ano;
        this.Carros = new ArrayList();
        this.Pilotos = new ArrayList();
    }

    /**
    * Pega os pilotos da equipe.
    * 
    * @return Todos os pilotos.
    */
    public ArrayList<Piloto> getPilotos() {
        return Pilotos;
    }

    /**
    * Adiciona um piloto na equipe.
    * 
    * @param Piloto Novo piloto da equipe.
    */
    public void addPilotos(Piloto Piloto) {
        this.Pilotos.add(Piloto);
    }

    /**
    * Pega os carros da equipe.
    * 
    * @return Todos os carros da equipe.
    */
    public ArrayList<Carro> getCarros() {
        return Carros;
    }

    /**
    * Adiciona um carro na equipe.
    * 
    * @param Carro Novo carro da equipe.
    */
    public void addCarros(Carro Carro) {
        this.Carros.add(Carro);
    }

    /**
    * Pega a id da equipe.
    * 
    * @return Uma String com o id.
    */
    public String getId() {
        return id;
    }

    /**
    * Pega o nome da equipe.
    * 
    * @return Uma String com o nome da equipe.
    */
    public String getNome() {
        return nome;
    }

    /**
    * Pega a nacionalidade da equipe.
    * 
    * @return Uma String com a nacionalidade da equipe.
    */
    public String getNacionalidade() {
        return nacionalidade;
    }
}