package ClienteAutorama.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe do carro.
 * 
 * @author Víctor César e William Soares.
 */
public class Carro implements Serializable {
    
    private String EPC, numero; 
    private Piloto piloto;
    private Equipe equipe;
    private LocalDateTime tempoVolta;
            
    /**
    * Construtor do carro.
    * 
    * @param EPC EPC da tag.
    * @param numero Número de identificação do carro.
    */
    public Carro(String EPC, String numero) {
        this.EPC = EPC;
        this.numero = numero;
    }
    
    /**
    * Construtor do carro auxiliar com os tempos.
    * 
     * @param EPC EPC da tag.
     * @param tempoVolta Tempo recebido da tag.
    */
    public Carro(String EPC, LocalDateTime tempoVolta) {
        this.EPC = EPC;
        this.tempoVolta = tempoVolta;
    }
    
    /**
    * Pega o EPC do carro.
    * 
    * @return Uma String com o EPC.
    */
    public String getEPC() {
        return EPC;
    }

    /**
    * Altera o EPC do carro.
    * 
    * @param EPC Novo EPC.
    */
    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    /**
    * Pega o número do carro.
    * 
    * @return Uma String com o número.
    */
    public String getNumero() {
        return numero;
    }

    /**
    * Pega o piloto do carro.
    * 
    * @return O piloto do carro.
    */
    public Piloto getPiloto() {
        return piloto;
    }

    /**
    * Altera o piloto do carro.
    * 
    * @param piloto Novo piloto do carro.
    */
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    /**
    * Pega a equipe do carro.
    * 
    * @return Equipe do piloto.
    */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
    * Altera a Equipe do carro.
    * 
    * @param equipe Nova equipe do carro.
    */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
    * Pega o tempo de volta do carro.
    * 
    * @return O tempo do carro.
    */
    public LocalDateTime getTempoVolta() {
        return tempoVolta;
    }

    /**
    * Altera o tempo de volta do carro.
    * 
    * @param tempoVolta Novo tempo do carro.
    */
    public void setTempoVolta(LocalDateTime tempoVolta) {
        this.tempoVolta = tempoVolta;
    }
    
    
    
}