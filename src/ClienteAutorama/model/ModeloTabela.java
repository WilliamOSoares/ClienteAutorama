
package ClienteAutorama.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

    private ArrayList<Piloto> corredores = new ArrayList<Piloto>();
    private String[] colunas = {"Pos","Piloto","Equipe","Melhor volta","Voltas"};

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
                        return "00:00.000";
                    } else{
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
                    }
                } else {
                    if(corredores.get(0).getTempoVolta()==null){
                        return "+00.000";
                    } else if (corredores.get(linha).getTempoVolta()==null){
                        return "00.000";
                    } else{
                        int first, second;
                        String s = ".";
                        if(corredores.get(linha).getMelhorMili() < corredores.get(0).getMelhorMili()){
                            second = (corredores.get(linha).getMelhorMili()+1000) - corredores.get(0).getMelhorMili();
                            first = (corredores.get(linha).getMelhorSec()-1) - corredores.get(0).getMelhorSec();
                            if(second <10){
                                s = ".00";
                            } else if(second<100){
                                s = ".0";
                            }
                        } else{
                            first = corredores.get(linha).getMelhorSec() - corredores.get(0).getMelhorSec();
                            second = corredores.get(linha).getMelhorMili() - corredores.get(0).getMelhorMili();
                            if(second <10){
                                s = ".00";
                            } else if(second<100){
                                s = ".0";
                            }
                            
                        }                                          
                        return "+" + first + s + second;
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
