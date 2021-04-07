
package ClienteAutorama.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabelaCorrida extends AbstractTableModel{

    private ArrayList<Piloto> corredores = new ArrayList<Piloto>();
    private String[] colunas = {"Pos","Piloto","Equipe","Tempo Corrida","Tempo de volta","Volta Mais Rapida","Voltas"};

    public ModeloTabelaCorrida(ArrayList<Piloto> pilotos) {
        corredores = pilotos;
    }
    
    
    @Override
    public int getRowCount() {
        return corredores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return linha+1;
            case 1:
                return corredores.get(linha).getNome();
            case 2: 
                return "equiPIADA";//corredores.get(linha).getEquipe().getNome();
            case 3:
                return "0:00:00.000000";
            case 4:
                return "00:00.000000";
            case 5:
                return "00:00.000000";
            case 6:
                return corredores.get(linha).getVoltas();
        }
        
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setArray(ArrayList<Piloto> novo){
        this.corredores = novo;
        this.fireTableDataChanged();
    }
    
}
