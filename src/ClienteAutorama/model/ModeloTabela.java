
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
                //return "equipe";
                return corredores.get(linha).getEquipe().getNome();
            case 3:
                if(linha==0){
                    if(corredores.get(linha).getTempoVolta()==null){
                        return "00:00.000";
                    } else{
                        return corredores.get(linha).getTempoVolta();
                    }
                } else {
                    if(corredores.get(0).getTempoVolta()==null){
                        return "+00.000";
                    } else if (corredores.get(linha).getTempoVolta()==null){
                        return "00.000";
                    } else{
                        int first, second;
                        String s = ".";
                        if(corredores.get(linha).getTempoMili() < corredores.get(0).getTempoMili()){
                            second = (corredores.get(linha).getTempoMili()+1000) - corredores.get(0).getTempoMili();
                            first = (corredores.get(linha).getTempoSec()-1) - corredores.get(0).getTempoSec();
                            if(second <10){
                                s = ".00";
                            } else if(second<100){
                                s = ".0";
                            }
                        } else{
                            first = corredores.get(linha).getTempoSec() - corredores.get(0).getTempoSec();
                            second = corredores.get(linha).getTempoMili() - corredores.get(0).getTempoMili();
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
