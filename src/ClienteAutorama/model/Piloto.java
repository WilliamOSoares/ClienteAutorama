package ClienteAutorama.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe do piloto.
 * 
 * @author Víctor César e William Soares.
 */
public class Piloto implements Serializable {

    private String id, nome, nacionalidade, tempoVolta;
    private Carro carro;
    private Equipe equipe;
    private int voltas = 0;
    private int tempoSec = 0;
    private int tempoMili = 0;
    private int melhorSec = 0;
    private int melhorMili = 0;
    private LocalDateTime tempoInit;
    private LocalDateTime tempoFinal;    
    private LocalDateTime tempoGeral;
    private boolean primeiraLeitura = true;

    /**
    * Construtor do piloto.
    * 
    * @param id id do piloto.
    * @param nome Nome do piloto.
    * @param nacionalidade Nacionalidade do piloto.
    */
    public Piloto(String id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    /**
    * Pega o id do piloto.
    * 
    * @return Uma String com id.
    */
    public String getId() {
        return id;
    }

    /**
    * Pega o nome.
    * 
    * @return Uma String com o nome do piloto.
    */
    public String getNome() {
        return nome;
    }

    /**
    * Pega a nacionalidade do piloto.
    * 
    * @return Uma String com a nacionalidade do piloto.
    */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
    * Pega o carro do piloto.
    * 
    * @return O objeto Carro do piloto.
    */
    public Carro getCarro() {
        return carro;
    }

    /**
    * Altera o Carro do piloto.
    * 
    * @param carro Novo carro do piloto.
    */
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    /**
    * Pega a equipe do piloto.
    * 
    * @return O objeto de Equipe do piloto.
    */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
    * Altera a Equipe do piloto.
    * 
    * @param equipe Nova equipe do piloto.
    */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
    * Pega as voltas do piloto.
    * 
    * @return Um inteiro com o número das voltas.
    */
    public int getVoltas() {
        return voltas;
    }

    /**
    * Altera o número de voltas do piloto.
    * 
    * @param voltas Novo número de voltas do piloto.
    */
    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }

    /**
    * Pega o tempo da volta do piloto.
    * 
    * @return Uma String com tempo da volta do piloto.
    */
    public String getTempoVolta() {
        if(tempoVolta==null){
            return "0:00.000";
        } else{
            return tempoVolta;
        }
    }

    /**
    * Pega o tempo geral do piloto.
    * 
    * @return Uma String com a diferença do primeiro valor pego com o ultimo valor pego.
    */
    public String getTempoGeral() {
        LocalDateTime aux;
        int hora, min, sec, mili;
        String horas, mins, secs, milis;
        
        if(this.tempoFinal==null){
            return "00:00.000";
        }
        
        aux = this.tempoFinal.minusHours(this.tempoGeral.getHour()).minusMinutes(this.tempoGeral.getMinute()).minusSeconds(this.tempoGeral.getSecond()).minusNanos(this.tempoGeral.getNano());
        hora = aux.getHour();
        min = aux.getMinute();
        sec = aux.getSecond();
        mili = aux.getNano()/1000000;
        
        if(hora<10){
            horas =  "0" + Integer.toString(hora);
        } else{
            horas = Integer.toString(hora);
        }
        
        if(min<10){
            mins = "0" + Integer.toString(min);
        } else{
            mins = Integer.toString(min);
        }
        
        if(sec<10){
            secs = "0" + Integer.toString(sec);
        }else{
            secs = Integer.toString(sec);
        }
        
        if(mili<10){
            milis =  "00" + Integer.toString(mili);
        } else if(mili<100){
            milis =  "0" + Integer.toString(mili);
        }else{
            milis = Integer.toString(mili);
        }
        
        return (horas + ":" + mins + ":" + secs + "." + milis);
    }   
    
    /**
    * Altera o tempo geral do piloto.
    * 
    * @param tempoGeral Novo tempo geral do piloto.
    */
    public void setTempoGeral(LocalDateTime tempoGeral) {
        this.tempoGeral = tempoGeral;
    }
    
    /**
    * Altera o tempo de volta com base no tempo final e tempo inicial.
    * 
    */
    public void setTempoVolta() {
        LocalDateTime aux;
        aux = this.tempoFinal.minusHours(this.tempoInit.getHour()).minusMinutes(this.tempoInit.getMinute()).minusSeconds(this.tempoInit.getSecond()).minusNanos(this.tempoInit.getNano());
        int mili = aux.getNano()/1000000;
        this.setTempoMili(mili);
        if(this.tempoMili<10){
            this.tempoVolta = aux.getMinute() + ":" + aux.getSecond() + ".00" + mili;
        } else if(this.tempoMili<100){
            this.tempoVolta = aux.getMinute() + ":" + aux.getSecond() + ".0" + mili;
        }else{
            this.tempoVolta = aux.getMinute() + ":" + aux.getSecond() + "." + mili;
        }
        this.setTempoSec((aux.getMinute()*60)+aux.getSecond());
        if(this.melhorSec == 0 && this.melhorMili == 0){
            this.melhorSec = this.tempoSec;
            this.melhorMili = this.tempoMili;
        } else {
            if(this.tempoSec<this.melhorSec){
                this.melhorSec = this.tempoSec;
                this.melhorMili = this.tempoMili;
            } else if(this.tempoSec==this.melhorSec && this.melhorMili>this.tempoMili){
                    this.melhorMili = this.tempoMili;
            }        
        }
    }

    /**
    * Pega o melhor tempo em segundos do piloto.
    * 
    * @return Um inteiro com o valor do tempo convertido em segundos.
    */
    public int getMelhorSec() {
        return melhorSec;
    }

    /**
    * Altera o melhor segundo do piloto.
    * 
    * @param melhorSec Novo melhor tempo em segundos do piloto.
    */
    public void setMelhorSec(int melhorSec) {
        this.melhorSec = melhorSec;
    }

    /**
    * Pega o melhor milissegundos do piloto.
    * 
    * @return Um inteiro com apenas o melhor milissegundos do piloto de acordo com o melhor tempo.
    */
    public int getMelhorMili() {
        return melhorMili;
    }

    /**
    * Altera o melhor milissegundo do piloto.
    * 
    * @param melhorMili Novo melhor milissegundo do piloto.
    */
    public void setMelhorMili(int melhorMili) {
        this.melhorMili = melhorMili;
    }
    
    /**
    * Pega o milissegundos do tempo recente do piloto.
    * 
    * @return Um inteiro com o milissegundo do tempo recente do piloto.
    */
    public int getTempoMili() {
        return tempoMili;
    }

    /**
    * Altera o milissegundo do tempo recente do piloto.
    * 
    * @param tempoMili Novo milissegundo do tempo recente do piloto.
    */
    public void setTempoMili(int tempoMili) {
        this.tempoMili = tempoMili;
    }
    
    /**
    * Pega o segundos do tempo recente do piloto.
    * 
    * @return Um inteiro com o segundos do tempo recente do piloto.
    */
    public int getTempoSec() {
        return tempoSec;
    }

    /**
    * Altera o segundos do tempo recente do piloto.
    * 
    * @param tempoSec Novo segundos do tempo recente do piloto.
    */
    public void setTempoSec(int tempoSec) {
        this.tempoSec = tempoSec;
    }
    
    /**
    * Pega o tempo inicial da volta do piloto.
    * 
    * @return Um tempo exato do inicio da volta do piloto.
    */
    public LocalDateTime getTempoInit() {
        return tempoInit;
    }

    /**
    * Altera o tempo inicial de volta do piloto.
    * 
    * @param tempoInit Novo tempo exato do piloto.
    */
    public void setTempoInit(LocalDateTime tempoInit) {
        this.tempoInit = tempoInit;
    }

    /**
    * Pega o tempo final da volta do piloto com base no tempo mínimo da pista.
    * 
    * @return Um tempo exato do final da volta do piloto.
    */
    public LocalDateTime getTempoFinal() {
        return tempoFinal;
    }

    /**
    * Altera o tempo final de volta do piloto.
    * 
    * @param tempoFinal Novo tempo exato do piloto.
    */
    public void setTempoFinal(LocalDateTime tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    /**
    * Verifica se é a primeira leitura do piloto, o início da corrida.
    * 
    * @return True ou False de acordo com o metódo da classe corrida.
    */
    public boolean isPrimeiraLeitura() {
        return primeiraLeitura;
    }

    /**
    * Altera o estado da primeira leitura do piloto.
    * 
    * @param primeiraLeitura Novo estudo.
    */
    public void setPrimeiraLeitura(boolean primeiraLeitura) {
        this.primeiraLeitura = primeiraLeitura;
    }
    
}