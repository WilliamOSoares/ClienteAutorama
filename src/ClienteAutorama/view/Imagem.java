package ClienteAutorama.view;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * Classe para adicionar uma imagem no java swing.
 * 
 * @author Bruno Chagas do Programinhas.
 */
public class Imagem extends javax.swing.JPanel{
    
    private ImageIcon img;
    
    /**
    * Construtor da classe imagem que atribuí uma imagem a ela.
    * 
    */
    public Imagem(){
       img = new ImageIcon();
    }
    
    /**
    * Exibe a imagem redimensionada no painel.
    * 
    * @param g Gráficos.
    */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        
    }
    
    /**
    * Atribui a imagem ao painel.
    * 
    * @param img Nova ImageIcon.
    */
    public void setImg(ImageIcon img){
        this.img = img;
    }
    
    /**
    * Pega a imagem no painel.
    * 
    * @return A ImagemIcon atribuída.
    */
    public ImageIcon getImg(){
        return this.img;
    }
    
}