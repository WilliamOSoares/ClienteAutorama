
package ClienteAutorama.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

    private ArrayList<Piloto> corredores = new ArrayList<Piloto>();
    private String[] colunas = {"Pos","Piloto","Equipe","Tempo de volta","Voltas"};

    public ModeloTabela(ArrayList<Piloto> pilotos) {
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
                return corredores.get(linha).getEquipe().getNome();
            case 3:
                if(linha==0){
                    if(corredores.get(linha).getTempoVolta()==null){
                        return "00:00.000000";
                    } else{
                        return corredores.get(linha).getTempoVolta();
                    }
                } else {
                    if(corredores.get(0).getTempoVolta()==null){
                        return "+00.000000";
                    } else if (corredores.get(linha).getTempoVolta()==null){
                        return "00.000000";
                    } else{                 
                        int first = corredores.get(0).getTempoSec() - corredores.get(linha).getTempoSec();
                        int second = corredores.get(0).getTempoMili() - corredores.get(linha).getTempoMili();                    
                        return "+" + first +"."+ second;
                    }
                }
            case 4:
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
