
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
                return corredores.get(linha).getEquipe().getNome();
            case 3:
                if(corredores.get(linha).getVoltas() == 0){
                    return "00:00:00.000";
                }else{
                    return corredores.get(linha).getTempoGeral();
                }
            case 4:
                String r = ".";
                int segundos = corredores.get(linha).getTempoSec();
                int minu = segundos/60;
                int secs = segundos%60;
                if(corredores.get(linha).getTempoMili() <10){
                    r = ".00";
                } else if(corredores.get(linha).getTempoMili() <100){
                    r = ".0";
                }
                return minu +":"+secs+r+corredores.get(linha).getTempoMili();
            case 5:
                String s = ".";
                int segundo = corredores.get(linha).getMelhorSec();
                int min = segundo/60;
                int sec = segundo%60;
                if(corredores.get(linha).getMelhorMili() <10){
                    s = ".00";
                } else if(corredores.get(linha).getMelhorMili() <100){
                    s = ".0";
                }
                return min +":"+sec+s+corredores.get(linha).getMelhorMili();
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
