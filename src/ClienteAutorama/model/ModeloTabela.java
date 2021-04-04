
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
                    return corredores.get(linha).getTempoVolta();
                } else {
                float result;
                    result = corredores.get(0).getTempoVolta() - corredores.get(linha).getTempoVolta();
                    return "+" + result;
                }//return corredores.get(linha).getTempoVolta();//"??:??:???";//corrida.getIsntance; corrida.getCorredorTempo(linha);
            case 4:
                return corredores.get(linha).getVoltas();//"??";//corrida.getIsntance; corrida.getCorredorVolta(linha);
        }
        
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }
    /*
    public void addRow(){//Piloto p
        //this.corredores.add(p);
        this.fireTableDataChanged();
    }
    
    public void setArray(int linha){
        //this.corredores.remove(linha);
        this.fireTableDataChanged();
    }*/
}
