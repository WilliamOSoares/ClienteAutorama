package clientetelespectador;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 * Classe de configuração da tabela da tela corrida.
 * 
 * @author Víctor César e William Soares.
 */
public class ModeloTabelaCorrida extends AbstractTableModel{

    private ArrayList<Piloto> corredores = new ArrayList<Piloto>();
    private String[] colunas = {"Pos","Piloto","Equipe","Tempo Corrida","Tempo de volta","Volta Mais Rapida","Voltas"};

    /**
    * Construtor da tabela da corrida.
    * 
    * @param pilotos Pilotos que irão participar da corrida.
    */
    public ModeloTabelaCorrida(ArrayList<Piloto> pilotos) {
        corredores = pilotos;
    }
    
    /**
    * Pega o número de linhas.
    * 
    * @return Um inteiro com a quantidade de linhas.
    */
    @Override
    public int getRowCount() {
        return corredores.size();
    }

    /**
    * Pega o número de colunas.
    * 
    * @return Um inteiro com a quantidade de colunas.
    */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
    * Pega o valor da célula de acordo com a coordenada.
    * 
    * @param linha Número da linha.
    * @param coluna Número da coluna.
    * @return Objeto da célula.
    */
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return linha+1;
            case 1:
                return corredores.get(linha).getNome();
            case 2: 
                return corredores.get(linha).equipe;
            case 3:
                if(corredores.get(linha).getTempoGeral()==null){
                    return "0:00.000";
                }else {
                    return corredores.get(linha).getTempoGeral();
                }
            case 4:
                if(corredores.get(linha).getTempoVolta()==null){
                    return "0:00.000";
                }else if(linha>0){ 
                    return corredores.get(linha).getTempoCima();
                }else {
                    return corredores.get(linha).getTempoVolta();
                }
            case 5:
                if(corredores.get(linha).getTempoMelhor()==null){
                    return "0:00.000";
                }else {
                    return corredores.get(linha).getTempoMelhor();
                }                
            case 6:
                if(corredores.get(linha).getVoltas()==null){
                    return "0";
                }else {    
                    return corredores.get(linha).getVoltas();
                }
        }
        
        return null;
    }
    
    /**
    * Pega o nome da coluna.
    * 
    * @param column Número da coluna.
      @return O nome da coluna.
    */
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    /**
    * Altera os dados dos pilotos na tabela.
    * 
    * @param novo Dados dos pilotos atualizados.
    */
    public void setArray(ArrayList<Piloto> novo){
        this.corredores = novo;
        this.fireTableDataChanged();
    }
    
}
