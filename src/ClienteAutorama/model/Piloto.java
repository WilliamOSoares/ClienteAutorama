package ClienteAutorama.model;

public class Piloto {
    
    private String id, nome, nacionalidade;
    private Carro carro;
    private Equipe equipe;

    public Piloto(String id, String nome, String nacionalidade, Equipe equipe) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.equipe = equipe;
    }
    
    public Piloto(String id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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
    
    
}
