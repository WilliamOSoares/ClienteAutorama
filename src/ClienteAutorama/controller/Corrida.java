package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import static java.awt.image.ImageObserver.WIDTH;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe da corrida.
 * 
 * @author Víctor César e William Soares.
 */
public class Corrida implements Runnable{
    
    GerenciadorTelas gerenciador;
    public ArrayList<Piloto> pilotos = new ArrayList<>();
    public DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public Pista pistaLocal;
    public int numVoltas, nVolta = 0;
    public String tempQuali;
    public boolean fimQuali = false;
    public boolean fimCorrida = false;
    public boolean online = false;
    public boolean botao = false;
    public ArrayList<Carro> leitura = new ArrayList<>();
    public LocalDateTime tempoBotao;
    
    /**
    * Construtor da classe corrida.
    * 
    */
    public Corrida() {
    }
    
    //Criação da única instância.
    public static Corrida uniqueInstance = new Corrida();

    /**
    * Pega a única instância da classe existente no código, com isso faz a implementação do padrão de criação Singleton.
    * 
    * @return Retorna a única instância.
    */
    public static Corrida getInstance() {
	return uniqueInstance;
    }  

    /**
    * Pega os pilotos que estão participando do qualificatório e da corrida.
    * 
    * @return Os corredores ativos.
    */
    public ArrayList<Piloto> getPilotos() {
        return pilotos;
    }

    /**
    * Pega a pista que sediará a corrida e o qualificatório.
    * 
    * @return a pista.
    */
    public Pista getPistaLocal() {
        return pistaLocal;
    }

    /**
    * Pega o número de voltas da corrida.
    * 
    * @return Um inteiro com o número de voltas.
    */
    public int getNumVoltas() {
        return numVoltas;
    }

    /**
    * Pega o tempo do qualificatório.
    * 
    * @return Um inteiro com o tempo do qualificatório em minutos.
    */
    public String getTempQuali() {
        return tempQuali;
    }

    /**
    * Pega o estado do final do qualificatório.
    * 
    * @return True caso a qualificação acabou ou a corrida está em progresso e False caso o qualificatório não acabou.
    */
    public boolean isFimQuali() {
        return fimQuali;
    }

    /**
    * Altera o estado do final do qualificatório.
    * 
    * @param fimQuali Novo estado do final do qualificatório.
    */
    public void setFimQuali(boolean fimQuali) {
        this.fimQuali = fimQuali;
    }

    /**
    * Pega o estado do final da corrida.
    * 
    * @return True caso a corrida acabou ou o qualificatório está em progresso e False caso a corrida não acabou.
    */
    public boolean isFimCorrida() {
        return fimCorrida;
    }

    /**
    * Altera o estado do final da corrida.
    * 
    * @param fimCorrida Novo estado da corrida.
    */
    public void setFimCorrida(boolean fimCorrida) {
        this.fimCorrida = fimCorrida;
    }    
    
    /**
    * Atribuí os dados necessários para o qualificatório e a corrida.
    * 
    * @param corredores Pilotos que irão participar da corrida.
    * @param nVoltas Número de voltas da corrida.
    * @param tQuali Tempo do qualificatório em minutos.
    * @param pista Pista que acontecerá o evento.
    */
    public void dadosCorrida(ArrayList<Piloto> corredores, int nVoltas, String tQuali, Pista pista) {
        pilotos = corredores;
        numVoltas = nVoltas;
        tempQuali = tQuali;
        pistaLocal = pista;
    }  

    /**
    * Método que executará em uma thread que irá pegar em tempo real os dados dos carros da corrida.
    * Também o método irá depurar os dados para que a volta do piloto seja maior que o tempo de conclusão mínimo da pista.
    * Além do mais. Este método é invocado de acordo com os dados, se isFimQuali for True e isFimCorrida for False, ele atualiza a corrida.
    * se isFimQuali for False e isFimCorrida for True, ele atualiza o Qualificatório.
    * Sendo assim este método é genérico de acordo com as informações fornecidas das telas de qualificatório e corrida.
    * Por último, o método chama a ordenação de acordo com quem ele está atualizando para manter as tabelas coesas.
    * 
    */
    public  void getAtualizacao() {
        gerenciador = GerenciadorTelas.getInstance();
        while(!isFimQuali() || !isFimCorrida()){
            while(!gerenciador.comunicacao.recebido.has("CicloLeitura") && !gerenciador.comunicacao.recebido.has("status")){ }
                        
            if(validaLeitura()){
                for (int i = 0; i < leitura.size(); i++) {
                    for (int j = 0; j < pilotos.size(); j++) {
                        if(leitura.get(i).getEPC().equals(pilotos.get(j).getCarro().getEPC())){
                            if(this.pilotos.get(j).isPrimeiraLeitura()){
                                this.pilotos.get(j).setTempoInit(leitura.get(i).getTempoVolta());                                
                                this.pilotos.get(j).setTempoGeral(leitura.get(i).getTempoVolta());
                                this.pilotos.get(j).setPrimeiraLeitura(false);                              
                            } else{
                                if(leitura.get(i).getTempoVolta().getMinute() != pilotos.get(j).getTempoInit().getMinute()){
                                    this.pilotos.get(j).setTempoFinal(leitura.get(i).getTempoVolta());
                                    this.pilotos.get(j).setTempoVolta();
                                    this.pilotos.get(j).setVoltas(this.pilotos.get(j).getVoltas()+1);
                                    this.pilotos.get(j).setTempoInit(leitura.get(i).getTempoVolta());
                                }
                            }
                        }
                    }
                }
                System.out.println("Ciclo pego: "+gerenciador.comunicacao.recebido.get("CicloLeitura"));
                for (int i = 0; i < pilotos.size(); i++) {
                    System.out.println(pilotos.get(i).getNome()+" - Primeira Leitura:"+pilotos.get(i).isPrimeiraLeitura()+" - Leitura:"+pilotos.get(i).getTempoInit());
                }
                leitura.clear();
            }           
                        
            if(pilotos.get(0).getVoltas()>0 && !isFimQuali()){
                System.out.println("Ordena quali");
                this.pilotos = this.insertionSort(pilotos);
                if (pilotos.get(0).getMelhorSec()!=0){
                    pistaLocal.novoRecord(pilotos.get(0));
                }                
            }    
            
            if(pilotos.get(0).getVoltas()>0 && !isFimCorrida()){
                System.out.println("Ordena corrida");
                this.pilotos = this.insertionSortCorrida(pilotos);
                for (int i = 0; i <pilotos.size(); i++) {
                    pistaLocal.novoRecord(pilotos.get(i));
                } 
            } 
            
            if (gerenciador.comunicacao.recebido.has("Botao")){
                if (gerenciador.comunicacao.recebido.get("Botao").equals("True")){ 
                    if(botao){
                        LocalDateTime tempoAgora = LocalDateTime.now();
                        tempoAgora = tempoAgora.minusHours(tempoBotao.getHour()).minusMinutes(tempoBotao.getMinute()).minusSeconds(tempoBotao.getSecond()).minusNanos(tempoBotao.getNano());
                        if(tempoAgora.getSecond()<10){
                            botao = false;
                            gerenciador.comunicacao.recebido.clear();
                            gerenciador.comunicacao.recebido.append("status", "True");
                            if(isFimQuali()){
                                gerenciador.comunicacao.recebido.append("URL", "finalCorrida");
                                JOptionPane.showMessageDialog(null, "Acabou corrida", null, WIDTH);
                            } else {
                                gerenciador.comunicacao.recebido.append("URL", "finalQuali");
                                JOptionPane.showMessageDialog(null, "Acabou qualificatório", null, WIDTH);
                            }
                        } else{
                            System.out.println("Passou o tempo: "+tempoBotao.toString());
                            botao = true;
                            tempoBotao = LocalDateTime.now();
                        }                        
                    } else{
                        botao = true;
                        tempoBotao = LocalDateTime.now();
                    }
                }
            }            
            
            if (gerenciador.comunicacao.recebido.has("status")){
                if (gerenciador.comunicacao.recebido.get("URL").equals("finalQuali")){    
                    setFimQuali(true);
                    setFimCorrida(true);
                    gerenciador.comunicacao.recebido.clear();
                    this.stop();
                    for (int i = 0; i < gerenciador.bancoDados.getBdPistas().size(); i++) {
                        if(gerenciador.bancoDados.bdPistas.get(i).getNome().equals(pistaLocal.getNome())){
                            gerenciador.bancoDados.bdPistas.set(i, pistaLocal);
                        }
                    } 
                    gerenciador.comunicacao.recebido.clear();
                    gerenciador.bancoDados.serealiza();
                    gerenciador.abrirBotaoParaCorrida();
                } else if (gerenciador.comunicacao.recebido.get("URL").equals("finalCorrida")){
                    setFimQuali(true);
                    setFimCorrida(true);
                    gerenciador.comunicacao.recebido.clear();
                    for (int i = 0; i < pilotos.size(); i++) {
                        pilotos.get(i).setPrimeiraLeitura(true);
                    }
                    this.stop();
                    for (int i = 0; i < gerenciador.bancoDados.getBdPistas().size(); i++) {
                        if(gerenciador.bancoDados.bdPistas.get(i).getNome().equals(pistaLocal.getNome())){
                            gerenciador.bancoDados.bdPistas.set(i, pistaLocal);
                        }
                    }
                    gerenciador.comunicacao.recebido.clear();
                    gerenciador.bancoDados.serealiza();
                    gerenciador.abrirBotaoParaInicio();
                }                
            }
            
            System.out.println(gerenciador.comunicacao.recebido.toString());
            if(gerenciador.comunicacao.recebido.has("CicloLeitura")){
                System.out.println("Ciclo antes de apagar: "+gerenciador.comunicacao.recebido.get("CicloLeitura"));
            }    
            gerenciador.comunicacao.recebido.clear();
        }
        System.out.println("Passou direto");
    }
    
    /**
    * Ordena através do método de ordenação por inserção os corredores na execução da corrida.
    * A ordenação é feita pelos tempos gerais dos pilotos, quem fica em primeiro é quem tem menos tempo de corrida ou mais voltas completas.
    * 
    * @param vetor Todos os corredores ativos.
    * @return Todos os corredores de acordo com a ordenação.
    */
    public ArrayList<Piloto> insertionSortCorrida(ArrayList<Piloto> vetor) {
        for(int i = 0; i<vetor.size();i++){
            Piloto aux = vetor.get(i);
            String divide = vetor.get(i).getTempoGeral();
            String[] resto, geral = divide.split(":");
            resto = geral[geral.length-1].split("\\.");
            int[] atual = new int[geral.length+1];
            atual[0] = Integer.parseInt(geral[0]); //Horas corridas do piloto
            atual[1] = Integer.parseInt(geral[1]); //Minutos corridas do piloto
            atual[2] = Integer.parseInt(resto[0]); //Segundos corridas do piloto
            atual[3] = Integer.parseInt(resto[1]); //Milesimos corridas do piloto
            for(int j = i+1; j<=vetor.size()-1;j++){
                Piloto compara = vetor.get(j);
                String separa = vetor.get(j).getTempoGeral();
                String[] parte, todo = separa.split(":");
                parte = todo[todo.length-1].split("\\.");
                int[] comparado = new int[todo.length+1];
                comparado[0] = Integer.parseInt(todo[0]);
                comparado[1] = Integer.parseInt(todo[1]);
                comparado[2] = Integer.parseInt(parte[0]);
                comparado[3] = Integer.parseInt(parte[1]);
                if(compara.getVoltas()>=aux.getVoltas()){
                    if(compara.getVoltas()==aux.getVoltas()){
                        if(comparado[0]<=atual[0]){ //Comparando hora
                            if(comparado[0]==atual[0]){
                                if(comparado[1]<=atual[1]){ //Comparando minuto
                                    if(comparado[1]==atual[1]){
                                        if(comparado[2]<=atual[2]){ //Comparando segundo
                                            if(comparado[2]==atual[2]){
                                                if(comparado[3]<=atual[3]){ //Comparando milesimo
                                                    if(comparado[3]<atual[3]){
                                                        vetor.set((i),compara);
                                                        vetor.set((j),aux);
                                                        aux = vetor.get(i);
                                                    }  
                                                }
                                            } else{
                                                vetor.set((i),compara);
                                                vetor.set((j),aux);
                                                aux = vetor.get(i);
                                            }  
                                        }
                                    } else{
                                        vetor.set((i),compara);
                                        vetor.set((j),aux);
                                        aux = vetor.get(i);
                                    }
                                }
                            } else{
                                vetor.set((i),compara);
                                vetor.set((j),aux);
                                aux = vetor.get(i);
                            } 
                        }  
                    } else{
                        vetor.set((i),compara);
                        vetor.set((j),aux);
                        aux = vetor.get(i);
                    }
                }    
            }        
        }
        return vetor;
    }
    
    /**
    * Ordena através do método de ordenação por inserção os corredores na execução do qualificatório.
    * A ordenação é feita pelos menores tempos de volta, quem fica em primeiro é quem fez a volta no menor tempmo possível.
    * 
    * @param vetor Todos os corredores ativos.
    * @return Todos os corredores de acordo com a ordenação.
    */
    public ArrayList<Piloto> insertionSort(ArrayList<Piloto> vetor) {
        for(int i = 0; i<vetor.size();i++){
            Piloto aux = vetor.get(i);
            for(int j = i+1; j<=vetor.size()-1;j++){
                Piloto compara = vetor.get(j);
                if (compara.getMelhorSec()<= aux.getMelhorSec()){
                    if(compara.getMelhorSec()== aux.getMelhorSec()){
                        if(compara.getMelhorMili()< aux.getMelhorMili()){
                             vetor.set((i),compara);
                             vetor.set((j),aux);
                             aux = vetor.get(i);
                        }
                    }else {
                        vetor.set((i),compara);
                        vetor.set((j),aux);
                        aux = vetor.get(i);
                    }   
                }      
            }        
        }
        return vetor;
    }

    /**
    * Trecho de código que a thread Corrida irá executar.
    * 
    */
    @Override
    public void run() {
        while(online){
            this.getAtualizacao();
        }
    }
    
    /**
    * Invocação da thread Corrida, ela previne que seja iniciadas várias instâncias da mesma thread.
    * 
    */
    public void start(){
        if(!online){
            this.online = true;
            new Thread(this).start();
        }
        
    }
    
    /**
    * Para thread Corrida.
    * 
    */
    public void stop(){
        this.online = false;       
    }

    /**
    * Método auxiliar do getAtualizacao que só informa os dados de tempo de volta dos corredares caso eles façam uma volta maior do quê o tempo mínimo permitido.
    * 
    */
    private boolean validaLeitura() {
        gerenciador = GerenciadorTelas.getInstance();
        if(leitura.isEmpty()){
            String a = "CARRO";
            String b = "TEMPO";
            for(int i = 0; i<5; i++){
                String s = a+i;
                String t = b+i;
                if(gerenciador.comunicacao.recebido.has(s)){
                    for(int j = 0; j<this.pilotos.size(); j++){
                        if(gerenciador.comunicacao.recebido.get(s).equals(this.pilotos.get(j).getCarro().getEPC())){
                            LocalDateTime tempoBase;
                            if(gerenciador.comunicacao.recebido.get(t).toString().length()<20){
                                tempoBase = LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            } else{
                                tempoBase = LocalDateTime.parse(gerenciador.comunicacao.recebido.get(t).toString(), formatoData);
                            }
                            leitura.add(new Carro(gerenciador.comunicacao.recebido.get(s).toString(), tempoBase));
                        }
                    }
                }
            }
        }
        if (leitura.size()>0) {
            return true;
        } else {
            return false;
        }
    } 
}
