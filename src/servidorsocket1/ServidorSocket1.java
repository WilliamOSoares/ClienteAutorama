
package servidorsocket1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidorSocket1 {

    
    public static void main(String[] args) {
        
        try {
            //Criando o servidor de escuta
            ServerSocket servidor = new ServerSocket(1337);
            //ServerSocket servidor = new ServerSocket(7000,100,InetAddress.getByName("augusto.ddns.net"));
            System.out.println("pqp, servidor criado");
            byte[] b = InetAddress.getByName("augusto.ddns.net").getAddress();
            System.out.println(b[0] + "." + b[1] + "." + b[2] + "." + b[3]);
            //Criando um canal de comunicação para o serviço
            Socket cliente = servidor.accept();
            System.out.println("Cliente pego com o IP:" + cliente.getInetAddress().getHostAddress());
            
            //Canal de informação
            Scanner entrada = new Scanner(cliente.getInputStream());
            while(entrada.hasNextLine()){
                String igual = entrada.nextLine();
                System.out.println(igual);        
                
                PrintStream saida;
                if(igual.equals("quero")){
                    try{
                        saida = new PrintStream(cliente.getOutputStream());
                        saida.println("Servidor: ''Se vc quer então toma: ¯|_(ツ)_|¯ ''");
                    }catch(IOException ex){
                        System.out.println("Deu merda pra enviar mensagem");
                    }
                } else {
                    saida = new PrintStream(cliente.getOutputStream());
                    saida.println("Servidor: ''Vou mandar nada não''");
                    System.out.println("Deu ruim");
                }
            }
            
            entrada.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorSocket1.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
}
