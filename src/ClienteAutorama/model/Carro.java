package ClienteAutorama.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Carro implements Serializable {
    
    private String EPC, numero; 
    private Piloto piloto;
    private Equipe equipe;
    private LocalDateTime tempoVolta;
       
    public Carro(String EPC, String numero) {
        this.EPC = EPC;
        this.numero = numero;
    }
    
    public Carro(String EPC, LocalDateTime tempoVolta) {
        this.EPC = EPC;
        this.tempoVolta = tempoVolta;
    }
    

    public String getEPC() {
        return EPC;
    }

    public void setEPC(String EPC) {
        this.EPC = EPC;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public LocalDateTime getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(LocalDateTime tempoVolta) {
        this.tempoVolta = tempoVolta;
    }
    
    
    
}