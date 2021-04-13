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

public class GerenciadorBD {
    
    public File configInicial;
    public File carros = new File("arquivos\\Carros.AutoramaBD");
    public File pilotos = new File("arquivos\\Pilotos.AutoramaBD");
    public File pistas = new File("arquivos\\Pistas.AutoramaBD");
    public File equipes = new File("arquivos\\Equipes.AutoramaBD");
    public ArrayList<Carro> bdCarros = new ArrayList<Carro>();
    public ArrayList<Piloto> bdPilotos = new ArrayList<Piloto>();
    public ArrayList<Pista> bdPistas = new ArrayList<Pista>();
    public ArrayList<Equipe> bdEquipes = new ArrayList<Equipe>();
    
    private GerenciadorBD() {
        
    }
    
    private static GerenciadorBD uniqueInstance = new GerenciadorBD();

    public static GerenciadorBD getInstance() {
	return uniqueInstance;
    }
    
    public int salvaEquipe(Equipe equipe){
        
        for(int i = 0; i<bdEquipes.size(); i++){
            if(bdEquipes.get(i).getId().equals(equipe.getId())){
                return 1; //Id da equipe igual
            }
            else if(bdEquipes.get(i).getNome().equals(equipe.getNome())){
                return 2; //Nome da equipe igual
            }
//            else if(bdEquipes.get(i).getNacionalidade().equals(equipe.getNacionalidade())){
//                return 3;
//            }
//            else if(bdEquipes.get(i).getAno().equals(equipe.getAno())){
//                return 4;
//            }
        }
        
        bdEquipes.add(equipe);
        
        return 0; // conseguiu fazer o cadastro
    }
    
    public int salvaPiloto(Piloto piloto){
        for(int i = 0; i<bdPilotos.size(); i++){
            if(bdPilotos.get(i).getId().equals(piloto.getId())){
                return 1; // id do piloto ja existe
            }
           
            else if(bdPilotos.get(i).getNome().equals(piloto.getNome())){
                return 2; // nome do piloto ja existe
            }
//            else if(bdPilotos.get(i).getNacionalidade().equals(piloto.getNacionalidade())){
//                return 2;
//            }
        }
        
        bdPilotos.add(piloto);
        
        return 0; // conseguiu fazer o cadastro
    }
    
    public int salvaPista(Pista pista){
        for(int i = 0; i<bdPistas.size(); i++){
            if(bdPistas.get(i).getId().equals(pista.getId())){
                return 1; // id da pista ja existe
            }
            else if(bdPistas.get(i).getNome().equals(pista.getNome())){
                return 2; // nome da pista existe
            }
        }
        
        bdPistas.add(pista);
        
        return 0; // conseguiu fazer o cadastro
    }
    
    public int salvaCarro(Carro carro){
        for(int i = 0; i<bdCarros.size(); i++){
            if(bdCarros.get(i).getEPC().equals(carro.getEPC())){
                return 1; // EPC ja existe
            }
            if(bdCarros.get(i).getNumero().equals(carro.getNumero())){
                return 2;// numero ja existe
            }
        }
        
        bdCarros.add(carro);
        
        return 0; // conseguiu fazer o cadastro
    }
    
    public int salvaPilotoEquipe(String idPiloto, String idEquipe){
        Piloto pilotoAux = null;
        int indiceAux = 0;
        
        
        for(int i = 0; i<bdPilotos.size(); i++){
            if(bdPilotos.get(i).getId().equals(idPiloto)){
                pilotoAux = bdPilotos.get(i);
                indiceAux = i;
            }
        }
        if(pilotoAux == null){
            return 1; //Não existe o piloto
        }
        
        for(int i = 0; i<bdEquipes.size(); i++){
            if(bdEquipes.get(i).getId().equals(idEquipe)){
                bdEquipes.get(i).addPilotos(pilotoAux);
                bdPilotos.get(indiceAux).setEquipe(bdEquipes.get(i));
                return 0; // Conseguiu fazer o cadastro
            }
        }
        return 2; // Não Existe a equipe
    }
    public int salvaCarroEquipe(String idCarro, String idEquipe){
        Carro carroAux = null;
        int indiceAux = 0;
        
        for(int i = 0; i<bdCarros.size(); i++){
            if(bdCarros.get(i).getNumero().equals(idCarro)){
                carroAux = bdCarros.get(i);
                indiceAux = i;
            }
        }
        
        if(carroAux == null){
            return 1; // Carro não existe
        }
        
        for(int i = 0; i<bdEquipes.size(); i++){
            if(bdEquipes.get(i).getId().equals(idEquipe)){
                bdEquipes.get(i).addCarros(carroAux);
                bdCarros.get(indiceAux).setEquipe(bdEquipes.get(i));
                return 0; // Conseguiu fazer o cadastro
            }
        }
        return 2; //Não existe a Equipe
    }
    
    public int salvaPilotoCarro(String idPiloto, String idCarro){
        Piloto pilotoAux = null;
        int indiceAux = 0;
        
        for(int i = 0; i<bdPilotos.size(); i++){
            if(bdPilotos.get(i).getId().equals(idPiloto)){
                pilotoAux = bdPilotos.get(i);
                indiceAux = i;
            }
        }
        
        if(pilotoAux == null){
            return 1; //Não existe o piloto
        }
        
        for(int i = 0; i<bdCarros.size(); i++){
            if(bdCarros.get(i).getNumero().equals(idCarro)){
                bdCarros.get(i).setPiloto(pilotoAux);
                bdPilotos.get(indiceAux).setCarro(bdCarros.get(i));
                return 0; //Salvou o carro no piloto
            }
        }
        return 2; //não existe Carro
    }
    /*
    public int salvaPilotoRecordista(String idPista, String idPiloto){
        Piloto pilotoAux = null;
        
        for(int i = 0; i<bdPilotos.size(); i++){
            if(bdPilotos.get(i).getId().equals(idPiloto)){
                pilotoAux = bdPilotos.get(i);
            }   
        }
        
        if(pilotoAux == null){
            return 1; //não achou o piloto
        }
        
        for(int i = 0; i<bdPistas.size(); i++){
            if(bdPistas.get(i).getId().endsWith(idPista)){
                bdPistas.get(i).setRecordista(pilotoAux);
                return 0; // Salvou o piloto recordista da pista
            }
        }
        
        return 2; //Não achou a pista correta
    }*/
    
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
        if(carros.exists()){
            carros.delete();
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
            
            carros.createNewFile();
            objOutput = new ObjectOutputStream(new FileOutputStream(carros));
            objOutput.writeObject(bdCarros);
            objOutput.close();
            objOutput.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public int desserealiza(){
        ObjectInputStream objInput;
        
        try{
           if(equipes.exists()){
               objInput = new ObjectInputStream(new FileInputStream(equipes));
               bdEquipes = (ArrayList<Equipe>)objInput.readObject();
               objInput.close();
           }
           if(pistas.exists()){
               objInput = new ObjectInputStream(new FileInputStream(pistas));
               bdPistas = (ArrayList<Pista>)objInput.readObject();
               objInput.close();
           }
           if(pilotos.exists()){
               objInput = new ObjectInputStream(new FileInputStream(pilotos));
               bdPilotos = (ArrayList<Piloto>)objInput.readObject();
               objInput.close();
           }
           if(carros.exists()){
               objInput = new ObjectInputStream(new FileInputStream(carros));
               bdCarros = (ArrayList<Carro>)objInput.readObject();
               objInput.close();
           }
           else{
               return 2; // Deu merda na desserealização
           }
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(GerenciadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0; // Deu certo a desserealização
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
}
