package clientetelespectador;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Classe de configuração da tabela da tela de qualificatório.
 * 
 * @author Víctor César e William Soares.
 */
public class ModeloTabelaQuali extends AbstractTableModel{

    private ArrayList<Piloto> corredores = new ArrayList<Piloto>();
    private String[] colunas = {"Pos","Piloto","Equipe","Melhor volta","Voltas"};

    /**
    * Construtor da tabela da corrida.
    * 
    * @param pilotos Pilotos que irão participar da corrida.
    */
    public ModeloTabelaQuali(ArrayList<Piloto> pilotos) {
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
                return corredores.get(linha).getEquipe();
            case 3:
                if(corredores.get(linha).getTempoMelhor()==null){
                    return "0:00.000";
                }else{
                    return corredores.get(linha).getTempoMelhor();
                }                
            case 4:
                if(corredores.get(linha).getVoltas()==null){
                    return "0";
                } else{
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
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
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
