package clientetelespectador;

import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Classe da tela do piloto.
 * 
 * @author Víctor César e William Soares.
 */
public class TelaPiloto extends javax.swing.JFrame {

    public ArrayList<Piloto> pilotos;
    ClienteTelespectador gerenciador;
    public String invoke;
    public int indice;
    
    /**
     * Cria uma Tela de Piloto
     * @param piloto todos os pilotos
     * @param indice lugar do piloto na lista
     * @param chama quem chama a tela (Qualificatório ou Corrida)
     */
    public TelaPiloto(ArrayList<Piloto> piloto, int indice, String chama) {
        initComponents();
        this.indice = indice;
        pilotos = piloto;
        invoke = chama;
        if(piloto.get(indice).getCarro()==null){
            nomePiloto.setText(piloto.get(indice).getNome());
            equipe.setText(piloto.get(indice).getEquipe());
            posicao.setText("?");
            carro.setText("Ainda não começou");
            voltas.setText("0");
            ultVolta.setText("Sem dados");
            melhorVolta.setText("Sem dados");
            tempoGeral.setText("Sem dados");
            frente.setText("Sem dados");
            tras.setText("Sem dados");
        } else{
            nomePiloto.setText(piloto.get(indice).getNome());
            equipe.setText(piloto.get(indice).getEquipe());
            posicao.setText(piloto.get(indice).getPos());
            carro.setText("carro: "+piloto.get(indice).getCarro());
            voltas.setText(piloto.get(indice).getVoltas());
            ultVolta.setText(piloto.get(indice).getTempoVolta());
            melhorVolta.setText(piloto.get(indice).getTempoMelhor());
            tempoGeral.setText(piloto.get(indice).getTempoGeral());
            if(indice==0){
                frente.setText(piloto.get(indice).getNome()+" está na 1º posição");
                tras.setText(piloto.get(indice+1).getNome()+": "+piloto.get(indice).getTempoBaixo());
            } else if(indice+1 == piloto.size()){            
                frente.setText(piloto.get(indice-1).getNome()+": "+piloto.get(indice).getTempoCima());
                tras.setText(piloto.get(indice).getNome()+" está na última posição ");
            } else{
                frente.setText(piloto.get(indice-1).getNome()+": "+piloto.get(indice).getTempoCima());
                tras.setText(piloto.get(indice+1).getNome()+": "+piloto.get(indice).getTempoBaixo());
            }            
        }
        gerenciador = ClienteTelespectador.getInstance();
        gerenciador.setPilot(this);
        this.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomePiloto = new javax.swing.JLabel();
        carro = new javax.swing.JLabel();
        posicao = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        melhorVolta = new javax.swing.JLabel();
        tempoGeral = new javax.swing.JLabel();
        ultVolta = new javax.swing.JLabel();
        equipe = new javax.swing.JLabel();
        voltas = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tras = new javax.swing.JLabel();
        frente = new javax.swing.JLabel();
        voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nomePiloto.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        nomePiloto.setText("Nome do piloto");

        carro.setText("Carro: ");

        posicao.setFont(new java.awt.Font("Ink Free", 1, 48)); // NOI18N
        posicao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        posicao.setText("5");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do piloto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ink Free", 1, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Equipe:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Voltas:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Tempo da melhor volta:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Tempo geral da corrida:");

        melhorVolta.setFont(new java.awt.Font("Ink Free", 1, 12)); // NOI18N
        melhorVolta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        melhorVolta.setText("Ulitma volta");

        tempoGeral.setFont(new java.awt.Font("Ink Free", 1, 12)); // NOI18N
        tempoGeral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tempoGeral.setText("Ulitma volta");

        ultVolta.setFont(new java.awt.Font("Ink Free", 1, 12)); // NOI18N
        ultVolta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ultVolta.setText("Ulitma volta");

        equipe.setFont(new java.awt.Font("Ink Free", 1, 14)); // NOI18N
        equipe.setText("equipes");

        voltas.setFont(new java.awt.Font("Ink Free", 1, 14)); // NOI18N
        voltas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        voltas.setText("10");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Tempo da ultima volta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(melhorVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 20, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equipe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(voltas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ultVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tempoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equipe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(voltas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ultVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(melhorVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tempoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diferença entre os demais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ink Free", 1, 24))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Tempo para o carro da frente:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Tempo para o carro de trás:");

        tras.setFont(new java.awt.Font("Ink Free", 1, 14)); // NOI18N
        tras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tras.setText("nome e tempo");

        frente.setFont(new java.awt.Font("Ink Free", 1, 14)); // NOI18N
        frente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        frente.setText("nome e tempo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(0, 141, Short.MAX_VALUE))
                    .addComponent(frente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tras, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nomePiloto, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carro, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(posicao, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(posicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nomePiloto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(carro)))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        gerenciador = ClienteTelespectador.getInstance();
        if(invoke.equals("Quali")){            
            gerenciador.telaPilotoParaQuali();
        } else{
            gerenciador.telaPilotoParaCorrida();
        }
    }//GEN-LAST:event_voltarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carro;
    private javax.swing.JLabel equipe;
    private javax.swing.JLabel frente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel melhorVolta;
    private javax.swing.JLabel nomePiloto;
    private javax.swing.JLabel posicao;
    private javax.swing.JLabel tempoGeral;
    private javax.swing.JLabel tras;
    private javax.swing.JLabel ultVolta;
    private javax.swing.JButton voltar;
    private javax.swing.JLabel voltas;
    // End of variables declaration//GEN-END:variables
    
    /**
    * Thread de SwingWorker para alteração dos dados na tela piloto.
    */
    private void start(){
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
                ClienteTelespectador gerenciador;  
                gerenciador = ClienteTelespectador.getInstance();  
                String etapaInicio = gerenciador.getEtapa();
                int indice = gerenciador.pilot.indice;
                ArrayList<Piloto> piloto = gerenciador.pilot.pilotos;
                String etapaAgora = gerenciador.getEtapa();
                while(etapaAgora.equals(etapaInicio)){
                    gerenciador = ClienteTelespectador.getInstance();  
                    piloto = gerenciador.pilot.pilotos;
                    indice = gerenciador.pilot.indice;
                    etapaAgora = gerenciador.getEtapa();
                    if(piloto.get(indice).getCarro()==null){
                        gerenciador.pilot.nomePiloto.setText(piloto.get(indice).getNome());
                        gerenciador.pilot.equipe.setText(piloto.get(indice).getEquipe());
                        gerenciador.pilot.posicao.setText("?");
                        gerenciador.pilot.carro.setText("Ainda não começou");
                        gerenciador.pilot.voltas.setText("0");
                        gerenciador.pilot.ultVolta.setText("Sem dados");
                        gerenciador.pilot.melhorVolta.setText("Sem dados");
                        gerenciador.pilot.tempoGeral.setText("Sem dados");
                        gerenciador.pilot.frente.setText("Sem dados");
                        gerenciador.pilot.tras.setText("Sem dados");
                    } else if (piloto.get(indice).getVoltas().equals("0")){
                        gerenciador.pilot.nomePiloto.setText(piloto.get(indice).getNome());
                        gerenciador.pilot.equipe.setText(piloto.get(indice).getEquipe());
                        gerenciador.pilot.posicao.setText(piloto.get(indice).getPos());
                        gerenciador.pilot.carro.setText("carro: "+piloto.get(indice).getCarro());
                        gerenciador.pilot.voltas.setText(piloto.get(indice).getVoltas());
                        gerenciador.pilot.ultVolta.setText("Sem dados");
                        gerenciador.pilot.melhorVolta.setText("Sem dados");
                        gerenciador.pilot.tempoGeral.setText("Sem dados");
                        gerenciador.pilot.frente.setText(piloto.get(indice-1).getNome());
                        if(indice+1 == piloto.size()){  
                            gerenciador.pilot.tras.setText(piloto.get(indice).getNome()+" está na última posição");
                        } else{
                            gerenciador.pilot.tras.setText(piloto.get(indice+1).getNome());
                        }
                    }else{
                        gerenciador.pilot.nomePiloto.setText(piloto.get(indice).getNome());
                        gerenciador.pilot.equipe.setText(piloto.get(indice).getEquipe());
                        gerenciador.pilot.posicao.setText(piloto.get(indice).getPos());
                        gerenciador.pilot.carro.setText("carro: "+piloto.get(indice).getCarro());
                        gerenciador.pilot.voltas.setText(piloto.get(indice).getVoltas());
                        gerenciador.pilot.ultVolta.setText(piloto.get(indice).getTempoVolta());
                        gerenciador.pilot.melhorVolta.setText(piloto.get(indice).getTempoMelhor());
                        gerenciador.pilot.tempoGeral.setText(piloto.get(indice).getTempoGeral());
                        if(indice==0){
                            gerenciador.pilot.frente.setText(piloto.get(indice).getNome()+" está na 1º posição");
                            gerenciador.pilot.tras.setText(piloto.get(indice+1).getNome()+": "+piloto.get(indice).getTempoBaixo());
                        } else if(indice+1 == piloto.size()){            
                            gerenciador.pilot.frente.setText(piloto.get(indice-1).getNome()+": "+piloto.get(indice).getTempoCima());
                            gerenciador.pilot.tras.setText(piloto.get(indice).getNome()+" está na última posição ");
                        } else{
                            gerenciador.pilot.frente.setText(piloto.get(indice-1).getNome()+": "+piloto.get(indice).getTempoCima());
                            gerenciador.pilot.tras.setText(piloto.get(indice+1).getNome()+": "+piloto.get(indice).getTempoBaixo());
                        }            
                    }
                    Thread.sleep(5000);
                }              
                JOptionPane.showMessageDialog(null, "Já acabou esta etapa", null, WIDTH);

            return null;
            }
        };

        worker.execute();
    }
}
