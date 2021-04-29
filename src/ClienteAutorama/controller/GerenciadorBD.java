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

/**
 * Classe do gerenciador do banco de dados do sistema.
 * 
 * @author Víctor César e William Soares.
 */
public class GerenciadorBD {
    
    public File configInicial;
    public File carros = new File("arquivos\\Carros.AutoramaBD");
    public File pilotos = new File("arquivos\\Pilotos.AutoramaBD");
    public File pistas = new File("arquivos\\Pistas.AutoramaBD");
    public File equipes = new File("arquivos\\Equipes.AutoramaBD");
    public ArrayList<Carro> bdCarros = new ArrayList<>();
    public ArrayList<Piloto> bdPilotos = new ArrayList<>();
    public ArrayList<Pista> bdPistas = new ArrayList<>();
    public ArrayList<Equipe> bdEquipes = new ArrayList<>();
    
    /**
    * Construtor da classe gerenciadora do banco de dados.
    * 
    */
    private GerenciadorBD() {
        
    }
    
    //Criação da única instância.
    private static GerenciadorBD uniqueInstance = new GerenciadorBD();

    /**
    * Pega a única instância da classe existente no código, com isso faz a implementação do padrão de criação Singleton.
    * 
    * @return Retorna a única instância.
    */
    public static GerenciadorBD getInstance() {
	return uniqueInstance;
    }
   
    /**
    * Adiciona uma nova equipe no banco de dados.
    * 
    * @param equipe Novo equipe que pertencerá ao banco de dados.
    * @return 0 se foi efetuado o cadastro, 1 caso id da equipe já exista e 2 caso o nome da equipe já exista.
    */
    public int salvaEquipe(Equipe equipe){
        
        for(int i = 0; i<bdEquipes.size(); i++){
            if(bdEquipes.get(i).getId().equals(equipe.getId())){
                return 1; //Id da equipe igual
            }
            else if(bdEquipes.get(i).getNome().equals(equipe.getNome())){
                return 2; //Nome da equipe igual
            }
        }
        
        bdEquipes.add(equipe);
        
        return 0; // conseguiu fazer o cadastro
    }
    
    /**
    * Adiciona um novo piloto no banco de dados.
    * 
    * @param piloto Novo piloto que pertencerá ao banco de dados.
    * @return 0 se foi efetuado o cadastro, 1 caso id do piloto já exista e 2 caso o nome do piloto já exista.
    */
    public int salvaPiloto(Piloto piloto){
        for(int i = 0; i<bdPilotos.size(); i++){
            if(bdPilotos.get(i).getId().equals(piloto.getId())){
                return 1; // id do piloto ja existe
            }
           
            else if(bdPilotos.get(i).getNome().equals(piloto.getNome())){
                return 2; // nome do piloto ja existe
            }
        }
        
        bdPilotos.add(piloto);
        
        return 0; // conseguiu fazer o cadastro
    }
    
    /**
    * Adiciona uma nova pista no banco de dados.
    * 
     * @param pista Nova pista que pertencerá ao banco de dados.
    * @return 0 se foi efetuado o cadastro, 1 caso id da pista já exista e 2 caso o nome da pista já exista.
    */
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
    
    /**
    * Adiciona um novo carro no banco de dados.
    * 
    * @param carro Novo carro que pertencerá ao banco de dados.
    * @return 0 se foi efetuado o cadastro, 1 caso EPC do carro já exista e 2 caso o número do carro já exista.
    */
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
    
    /**
    * Adiciona um piloto na equipe e a equipe no piloto no banco de dados.
    * 
    * @param idPiloto id do piloto que será adicionado a equipe.
    * @param idEquipe id da equipe que receberá o piloto.
    * @return 0 se foi efetuado o cadastro, 1 caso não exista o piloto, 2 caso não exista a equipe e 3 caso o piloto já esteja na equipe.
    */
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
                for(int j = 0; j<bdEquipes.get(i).getPilotos().size(); j++){
                    if(bdEquipes.get(i).getPilotos().get(j).getId().equals(idPiloto)){
                        return 3; //piloto na equipe
                    }
                }
                bdEquipes.get(i).addPilotos(pilotoAux);
                bdPilotos.get(indiceAux).setEquipe(bdEquipes.get(i));
                return 0; // Conseguiu fazer o cadastro
            }
        }
        return 2; // Não Existe a equipe
    }
    
    /**
    * Adiciona um carro na equipe e a equipe no carro no banco de dados.
    * 
    * @param idCarro Número do carro que será da equipe.
    * @param idEquipe id da equipe que terá o carro.
    * @return 0 se foi efetuado o cadastro, 1 caso não exista o carro, 2 caso não exista a equipe e 3 caso o carro já esteja na equipe.
    */
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
                for(int j = 0; j<bdEquipes.get(i).getCarros().size(); j++){
                    if(bdEquipes.get(i).getCarros().get(j).getNumero().equals(idCarro)){
                        return 3; //carro na equipe
                    }
                }
                bdEquipes.get(i).addCarros(carroAux);
                bdCarros.get(indiceAux).setEquipe(bdEquipes.get(i));
                return 0; // Conseguiu fazer o cadastro
            }
        }
        return 2; //Não existe a Equipe
    }
    
    /**
    * Adiciona um piloto no carro e o carro no piloto no banco de dados.
    * 
    * @param idPiloto id do piloto que será dono do carro.
    * @param idCarro Número do carro que será do piloto.
    * @return 0 se foi efetuado o cadastro, 1 caso não exista o piloto e 2 caso não exista o carro.
    */
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
    
    /**
    * Seraliza os dados existentes no banco, deletando os antigos.
    * 
    * @return 0 indicando o final da execução bem sucedida.
    */
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
    
    /**
    * Desserealiza os dados salvos para serem usados na execução do programa.
    * 
    * @return 0 caso a operação seja bem sucedida e 2 caso falhe no processo.
    */
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
               return 2; // Deu errado a desserealização
           }
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(GerenciadorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0; // Deu certo a desserealização
    }
    
    /**
    * Pega todos os carros do banco de dados.
    * 
    * @return Um ArrayList com todos os carros.
    */
    public ArrayList<Carro> getBdCarros() {
        return bdCarros;
    }

    /**
    * Pega todos os pilotos do banco de dados.
    * 
    * @return Um ArrayList com todos os pilotos.
    */
    public ArrayList<Piloto> getBdPilotos() {
        return bdPilotos;
    }

    /**
    * Pega todas as pistas do banco de dados.
    * 
    * @return Um ArrayList com todas as pistas.
    */
    public ArrayList<Pista> getBdPistas() {
        return bdPistas;
    }

    /**
    * Pega todas as equipes do banco de dados.
    * 
    * @return Um ArrayList com todas as equipes.
    */
    public ArrayList<Equipe> getBdEquipes() {
        return bdEquipes;
    }   
}
