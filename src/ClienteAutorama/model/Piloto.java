package ClienteAutorama.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private boolean primeiraLeitura = true;
    private int posicao;

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

    public int getVoltas() {
        return voltas;
    }

    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }

    public String getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta() {
        LocalDateTime aux;
        aux = this.tempoFinal.minusHours(this.tempoInit.getHour()).minusMinutes(this.tempoInit.getMinute()).minusSeconds(this.tempoInit.getSecond()).minusNanos(this.tempoInit.getNano());
        System.out.println(aux);
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
    }
    
    public void comparar(){
        
    }

    public int getTempoMili() {
        return tempoMili;
    }

    public void setTempoMili(int tempoMili) {
        this.tempoMili = tempoMili;
    }
    
    public int getTempoSec() {
        return tempoSec;
    }

    public void setTempoSec(int tempoSec) {
        this.tempoSec = tempoSec;
    }
    
    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public LocalDateTime getTempoInit() {
        return tempoInit;
    }

    public void setTempoInit(LocalDateTime tempoInit) {
        this.tempoInit = tempoInit;
    }

    public LocalDateTime getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(LocalDateTime tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public boolean isPrimeiraLeitura() {
        return primeiraLeitura;
    }

    public void setPrimeiraLeitura(boolean primeiraLeitura) {
        this.primeiraLeitura = primeiraLeitura;
    }
    /*
    public static void main(String[] args) {
        String s = "2021-04-03 18:48:30.123456";
        String t = "2021-04-03 18:52:30.654321";
        
        Piloto p = new Piloto("a","b","c");
        
        p.setTempoInit(LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"))); 
        p.setTempoFinal(LocalDateTime.parse(t, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
        LocalDateTime aux;
        System.out.println(p.getTempoInit());
        System.out.println(p.getTempoFinal());
        p.setTempoVolta();
        System.out.println(p.getTempoVolta());
        System.out.println(p.getTempoSec());
        System.out.println(p.getTempoMili());
        
    }
    */
}