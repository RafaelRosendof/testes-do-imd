import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;

public class test4 extends JFrame{
    private JPanel tela;
    private int px;
    private int py;
    private boolean jogando=true;
    private Image imagem;
    private final int FPS = 1000/100; //20
    private boolean[] controleTecla = new boolean[4];

    public test4(){
        //Image imagem;
        
        try {
            imagem = ImageIO.read(new File("C:\\Users\\rafin\\OneDrive\\Imagens\\pepe.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
            }
            @Override
            public void keyReleased(KeyEvent e){
                setaTecla(e.getKeyCode(),false);
            }
            
            public void keyPressed(KeyEvent e){
                setaTecla(e.getKeyCode(),true);
            }
    });
    tela=new JPanel(){
        @Override
        public void paintComponent(Graphics g){

            g.setColor(Color.WHITE);
                g.fillRect(0,0,tela.getWidth(), tela.getHeight());

                int x = tela.getWidth()/ 5 -20 +px;
                int y = tela.getHeight()/5-20+py;
                
                g.drawImage(imagem, x, y, 80, 80, null);


                //g.setColor(Color.YELLOW);
                //g.fillOval(x, y, 80, 80);
                 // desenhar os olhos
               // g.setColor(Color.BLACK);
                //g.fillOval(x+10, y+10, 5, 5);
                //g.fillOval(x+25, y+10, 5, 5);
                g.setColor(Color.BLACK);
                //g.drawArc(x+10, y+15, 20, 15, 180, 180);
                //g.drawArc(x+10, y+15, 20, 15, 0, 180);
                g.drawString("Agora eu estou em "+ x + "x" + y,5,10);
         }
     }; 

     this.add(tela); // adiciona a tela ao JFrame
     this.setSize(400, 400); // define o tamanho da janela
     this.setVisible(true); // torna a janela visível
    }

    public void inicia(){
        long prxAtualizadao=0;
        while(jogando){
            if(System.currentTimeMillis() >= prxAtualizadao){
                atualizaJogo();
                tela.repaint();
                prxAtualizadao= System.currentTimeMillis()+ FPS;
            }
        }
    }
    

    private void atualizaJogo(){
        if(controleTecla[0])
        py--;
        else if(controleTecla[1])
        py++;

        if(controleTecla[2])
        px--;
        else if(controleTecla[3])
        px++;
    }

    private void setaTecla(int tecla,boolean pressionada){
        switch(tecla){
            case KeyEvent.VK_ESCAPE:
            //tecla esc
            jogando=false;
            dispose();
            break;

            case KeyEvent.VK_UP:
            //seta para cima
            controleTecla[0]= pressionada;
            break;

            case KeyEvent.VK_DOWN:
            //seta para baixo
            controleTecla[1]=pressionada;
            break;

            case KeyEvent.VK_LEFT:
            //seta para esquerda
            controleTecla[2]=pressionada;
            break;

            case KeyEvent.VK_RIGHT:
            //seta para direita
            controleTecla[3]=pressionada;
            break;
        }
    }

    public static void main(String[] args){
        test4 jogo = new test4();
        jogo.inicia();
    }
}
