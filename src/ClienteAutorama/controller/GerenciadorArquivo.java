package ClienteAutorama.controller;

import ClienteAutorama.model.Carro;
import ClienteAutorama.model.Equipe;
import ClienteAutorama.model.Piloto;
import ClienteAutorama.model.Pista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciadorArquivo {
    
    public File configInicial;
    public File carros;
    public File pilotos = new File("arquivos\\Pilotos.AutoramaBD");
    public File pistas = new File("arquivos\\Pistas.AutoramaBD");
    public File equipes = new File("arquivos\\Equipes.AutoramaBD");
    public ArrayList<Carro> bdCarros = new ArrayList();
    public ArrayList<Piloto> bdPilotos = new ArrayList();
    public ArrayList<Pista> bdPistas = new ArrayList();
    public ArrayList<Equipe> bdEquipes = new ArrayList();
    
    private GerenciadorArquivo() {
        
    }
    
    private static GerenciadorArquivo uniqueInstance = new GerenciadorArquivo();

    public static GerenciadorArquivo getInstance() {
	return uniqueInstance;
    }
    
    public int salvaEquipe(Equipe equipe){
        
        for(int i = 0; i<bdEquipes.size(); i++){
            if(bdEquipes.get(i).getId().equals(equipe.getId())){
                return 1;
            }
            else if(bdEquipes.get(i).getNome().equals(equipe.getNome())){
                return 2;
            }
            else if(bdEquipes.get(i).getNacionalidade().equals(equipe.getNacionalidade())){
                return 3;
            }
            else if(bdEquipes.get(i).getAno().equals(equipe.getAno())){
                return 4;
            }
        }
        
        bdEquipes.add(equipe);
        
        return 0;
    }
    
    public int serealiza(){
        
        if(equipes.exists()){
            equipes.delete();
        }
        if(pistas.exists()){
            pistas.delete();
        }
        if(pilotos.exists()){
            pilotos.delete();
        }
        try {
            equipes.createNewFile();
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(equipes));
            objOutput.writeObject(bdEquipes);
            objOutput.close();
            objOutput.flush();
            
            pistas.createNewFile();
            objOutput = new ObjectOutputStream(new FileOutputStream(pistas));
            objOutput.writeObject(bdPistas);
            objOutput.close();
            objOutput.flush();
            
            pilotos.createNewFile();
            objOutput = new ObjectOutputStream(new FileOutputStream(pilotos));
            objOutput.writeObject(bdPilotos);
            objOutput.close();
            objOutput.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public int desserealiza(){
        
        try{
           if(equipes.exists()){
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(equipes));
            bdEquipes = (ArrayList<Equipe>)objInput.readObject();
            objInput.close();
           }
           else{
               return 2;
           }
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    public ArrayList<Carro> getBdCarros() {
        return bdCarros;
    }

    public ArrayList<Piloto> getBdPilotos() {
        return bdPilotos;
    }

    public ArrayList<Pista> getBdPistas() {
        return bdPistas;
    }

    public ArrayList<Equipe> getBdEquipes() {
        return bdEquipes;
    }
    
    
//    public int salvaEquipe(Equipe equipe){
//        
//        try {
//            if(!equipes.exists()){
//                equipes.createNewFile();
//            }
//
//              FileReader fr = new FileReader(equipes);
//              BufferedReader br = new BufferedReader(fr);
//              
//              String linha;
//              
//              while(br.ready()){
//                  linha = br.readLine();
//                  System.out.println(linha);
//                  if(linha.contains(equipe.getId()))
//                      return 1;
//                  if(linha.contains(equipe.getNome()))
//                      return 2;
//                  if(linha.contains(equipe.getNacionalidade()))
//                      return 3;
//                  if(linha.contains(equipe.getAno()))
//                      return 4;
//              }
//              
//              fr.close();
//              br.close();
//              
//              FileWriter fw = new FileWriter(equipes, true);
//              BufferedWriter bw = new BufferedWriter(fw);
//              
//              bw.write(equipe.getId() + " ");
//              bw.write(equipe.getNome() + " ");
//              bw.write(equipe.getAno() + " ");
//              bw.write(equipe.getNacionalidade());
//              bw.newLine();
//              bw.flush();
//              
//              fw.close();
//              bw.close();
//              
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return 0;
//    }
//    
//    public int carregaEquipes(){
//        try {
//           FileReader  fr = new FileReader(equipes);
//           BufferedReader br = new BufferedReader(fr);
//           String linha;
//           String[] aux;
//           
//           String id;
//           String nome;
//           String nacionaliade;
//           String ano;
//           
//           
//           while(br.ready()){
//               linha = br.readLine();
//               aux = linha.split(" ");
//               Equipe equipe = new Equipe(aux[0], aux[1], aux[2], aux[3]);
//                
//            
//           }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(GerenciadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//           return 0;   
//            
//    }
    
 
    public static void main(String[] args) {
        //GerenciadorArquivo gerentArq = GerenciadorArquivo.getInstance();
        //Equipe test = new Equipe("1", "vc", "1555", "alemao");
        
        //System.out.println(gerentArq.salvaEquipe(test));
        //System.out.println(gerentArq.serealizaEquipes());
        //System.out.println(gerentArq.desserealizaEquipes());
        
        //for(int i = 0; i<gerentArq.getBdEquipes().size(); i++){
            //System.out.println(gerentArq.bdEquipes.get(i).getNome());
        //}
        
//        int  x = gerentArq.salvaEquipe(test);
//        System.out.println(x);
//        
//        String a = "1 vc alemao 1555";
//        String[] b = a.split(" ");
//        System.out.println(b[0]);
//        System.out.println(b[1]);
//        System.out.println(b[2]);
//        System.out.println(b[3]);
//        
    }
    
}
