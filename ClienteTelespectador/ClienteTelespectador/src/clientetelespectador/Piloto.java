package clientetelespectador;

import java.time.LocalDateTime;

/**
 * Classe do piloto, onde guarda as informações do piloto.
 * 
 * @author Víctor César e William Soares.
 */
public class Piloto {
    
    String nome, equipe, pos, carro, voltas, tempoGeral, tempoVolta, tempoMelhor, tempoCima, tempoBaixo;
    
    /**
     * Construtor de entidade piloto
     * 
     * @param nome nome do piloto
     */
    public Piloto(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna equipe
     * @return nome da equipe
     */
    public String getEquipe() {
        return equipe;
    }

    /**
     * Altera o nome da equipe
     * @param equipe novo nome da equipe
     */
    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    /**
     * Retorna posição
     * @return posição
     */
    public String getPos() {
        return pos;
    }

    /**
     * Altera a posição
     * @param pos nova posição
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * Retorna EPC do carro
     * @return EPC
     */
    public String getCarro() {
        return carro;
    }

    /**
     * Altera o EPC do carro
     * @param carro novo carro
     */
    public void setCarro(String carro) {
        this.carro = carro;
    }

    /**
     * Retorna numero de voltas
     * @return numero de voltas
     */
    public String getVoltas() {
        return voltas;
    }

    /**
     * Altera o numero de voltas
     * @param voltas novo numero de voltas
     */
    public void setVoltas(String voltas) {
        this.voltas = voltas;
    }

    /**
     * Retorna o tempo geral
     * @return tempo geral
     */
    public String getTempoGeral() {
        return tempoGeral;
    }

    /**
     * Altera o tempo geral
     * @param tempoGeral novo tempo geral
     */
    public void setTempoGeral(String tempoGeral) {
        this.tempoGeral = tempoGeral;
    }

    /**
     * Retorna tempo da volta
     * @return tempo da volta
     */
    public String getTempoVolta() {
        return tempoVolta;
    }

    /**
     * Altera o tempo de volta
     * @param tempoVolta novo tempo de volta
     */
    public void setTempoVolta(String tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    /**
     * Retorna tempo da melhor volta
     * @return melhor volta
     */
    public String getTempoMelhor() {
        return tempoMelhor;
    }

    /**
     * Altera o melhor tempo de volta
     * @param tempoMelhor novo melhor tempo
     */
    public void setTempoMelhor(String tempoMelhor) {
        this.tempoMelhor = tempoMelhor;
    }

    /**
     * Retorna o tempo para o piloto da frente
     * @return tempo
     */
    public String getTempoCima() {
        return tempoCima;
    }

    /**
     * Altera o tempo entre o carro da frente
     * @param tempoCima novo tempo
     */
    public void setTempoCima(String tempoCima) {
        this.tempoCima = tempoCima;
    }

    /**
     * Retorna o tempo para o piloto de trás
     * @return tempo
     */
    public String getTempoBaixo() {
        return tempoBaixo;
    }

    /**
     * Altera o tempo entre o carro de trás
     * @param tempoBaixo novo tempo
     */
    public void setTempoBaixo(String tempoBaixo) {
        this.tempoBaixo = tempoBaixo;
    }
    
    
    public static void main(String[] args) {
        System.out.println("Teste");
        LocalDateTime antes = LocalDateTime.now();
        LocalDateTime agora = LocalDateTime.now();
        while((agora.minusHours(antes.getHour()).minusMinutes(antes.getMinute()).minusSeconds(antes.getSecond())).getSecond() < 5){agora = LocalDateTime.now(); }
        System.out.println("5 segundos");
    }
    
}
