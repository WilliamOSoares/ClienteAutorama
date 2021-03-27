package ClienteAutorama.model;

public class Partida {
    
    private int tempo, voltas;
    private Pista pista;
    private Piloto pilotos[];

    public Partida(int tempo, Pista pista, Piloto[] pilotos) {
        this.tempo = tempo;
        this.pista = pista;
        this.pilotos = pilotos;
    }
    
    public Partida(Pista pista, Piloto[] pilotos, int voltas) {
        this.voltas = voltas;
        this.pista = pista;
        this.pilotos = pilotos;
    }
    
    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getVoltas() {
        return voltas;
    }

    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public Piloto[] getPilotos() {
        return pilotos;
    }

    public void setPilotos(Piloto[] pilotos) {
        this.pilotos = pilotos;
    }
    
    
    
}
