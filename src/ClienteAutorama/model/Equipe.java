package ClienteAutorama.model;

public class Equipe {
    private String id, nome, nacionalidade, ano; 

    private Piloto pilotos[];
    private Carro carros[];

    public Equipe(String id, String nome, String nacionalidade, String ano) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.ano = ano;
    }
    
    public Piloto[] getPilotos() {
        return pilotos;
    }
    
    public Piloto getPilotosIndice(int i) {
        return pilotos[i];
    }   
    public void setPilotos(Piloto[] pilotos) {
        this.pilotos = pilotos;
    }

    public Carro[] getCarros() {
        return carros;
    }
    
    public Carro getCarrosIndice(int i) {
        return carros[i];
    }
    public void setCarros(Carro[] carros) {
        this.carros = carros;
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
