package ClienteAutorama.model;

import java.io.Serializable;

/**
 * Classe da pista.
 * 
 * @author Víctor César e William Soares.
 */
public class Pista implements Serializable {
    
    private String id, nome, pais, tempoRecordPista, tempoMin, recordista;
    private int melhorSec = 0, melhorMili = 0;

    /**
    * Construtor da classe da pista.
    * 
    * @param id id que usuário escolhe.
    * @param nome Nome da pista.
    * @param pais País onde se encontra a pista.
    * @param tempoMin Tempo mínimo de conclusão da pista.
    */
    public Pista(String id, String nome, String pais, String tempoMin) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.tempoMin = tempoMin;
        this.recordista = "Nenhum";
        this.tempoRecordPista = "Nenhum";
    }

    /**
    * Pega o tempo mínimo.
    * 
    * @return Uma String com o tempo.
    */
    public String getTempoMin() {
        return tempoMin;
    }
    
    /**
    * Pega o id da pista.
    * 
    * @return Uma String com a id.
    */
    public String getId() {
        return id;
    }
    
    /**
    * Pega o nome da pista.
    * 
    * @return Uma String com o nome da pista.
    */
    public String getNome() {
        return nome;
    }

    /**
    * Pega o país da pista.
    * 
    * @return Uma String com o nome do País.
    */
    public String getPais() {
        return pais;
    }

    /**
    * Pega o tempo record da pista.
    * 
    * @return Uma String com o tempo record da pista.
    */
    public String getTempoRecordPista() {
        return tempoRecordPista;
    }

    /**
    * Altera o tempo record da pista.
    * 
    * @param tempoRecordPista Novo tempo record de pista.
    */
    public void setTempoRecordPista(String tempoRecordPista) {
        this.tempoRecordPista = tempoRecordPista;
    }

    /**
    * Pega o recordista da pista.
    * 
    * @return Uma String com o nome do recordista da pista.
    */
    public String getRecordista() {
        return recordista;
    }

    /**
    * Altera o recordista da pista.
    * 
     * @param recordista Novo recordista da pista.
    */
    public void setRecordista(String recordista) {
        this.recordista = recordista;
    }

    /**
    * Verifica o tempo do recordista existente com o tempo do piloto, caso seja menor, é alterado o tempo e o recordita da pista.
    * 
    * @param piloto Um objeto Piloto.
    */
    public void novoRecord(Piloto piloto){
        if(this.melhorSec == 0){
            System.out.println("recordista zerado" +melhorSec+"-"+melhorMili);
            String s = ".";
            this.setRecordista(piloto.getNome());
            this.melhorSec = piloto.getMelhorSec();
            this.melhorMili = piloto.getMelhorMili();
            int min = this.melhorSec/60;
            int sec = this.melhorSec%60;
            if(melhorMili <10){
                s = ".00";
            } else if(melhorMili <100){
                s = ".0";
            }
            this.setTempoRecordPista(min+":"+sec +s+this.melhorMili);
            System.out.println("recordista novo" +melhorSec+"-"+melhorMili);
        } else{
            if(piloto.getMelhorSec()<this.melhorSec){
                System.out.println("recordista com menor segundo" +melhorSec+"-"+melhorMili);
                String s = ".";
                this.melhorSec = piloto.getMelhorSec();
                this.melhorMili = piloto.getMelhorMili();
                this.setRecordista(piloto.getNome());
                int min = this.melhorSec/60;
                int sec = this.melhorSec%60;
                if(melhorMili <10){
                    s = ".00";
                } else if(melhorMili <100){
                    s = ".0";
                }
                this.setTempoRecordPista(min+":"+sec +s+this.melhorMili);
                System.out.println("recordista novo" +melhorSec+"-"+melhorMili);
            } else if(piloto.getMelhorSec()==this.melhorSec){
                if(piloto.getMelhorMili()<this.melhorMili){
                    System.out.println("recordista com menor mili" +melhorSec+"-"+melhorMili);
                    String s = ".";
                    this.melhorMili = piloto.getMelhorMili();
                    this.setRecordista(piloto.getNome());
                    int min = this.melhorSec/60;
                    int sec = this.melhorSec%60;
                    if(melhorMili <10){
                        s = ".00";
                    } else if(melhorMili <100){
                        s = ".0";
                    }
                    this.setTempoRecordPista(min+":"+sec +s+this.melhorMili);
                    System.out.println("recordista novo" +melhorSec+"-"+melhorMili);
                }
            } 
        }
    }
}
