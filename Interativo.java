import java.awt.*;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Interativo extends JFrame{
    private JPanel tela;
    private int px;
    private int py;
    private boolean jogando=true;
    private final int FPS = 1000/20; //50
    
    public void inicia(){
        long prxAtualizadao=0;
        while(jogando){
            if(System.currentTimeMillis() >= prxAtualizadao){
                tela.repaint();
                prxAtualizadao= System.currentTimeMillis()+ FPS;
            }
        }
    }

    public Interativo(){
        super.addKeyListener(new KeyListener(){
            @Override
            //tecla apertada
            public void keyTyped(KeyEvent e){

            }
            @Override
            //evento tecla liberada
            public void keyReleased(KeyEvent e){

            }
            @Override
            //evento tecla pressionada
            public void keyPressed(KeyEvent e){
                int tecla = e.getKeyCode();
                switch (tecla) {
                    case KeyEvent.VK_ESCAPE:
                    //tecla esc
                        jogando = false;
                        dispose();//para fechar janela
                        break;
                    case KeyEvent.VK_UP:
                    //seta para cima 
                    py--;
                    break;
                    
                    case KeyEvent.VK_DOWN:
                    //seta para baixo
                    py++;
                    break;

                    case KeyEvent.VK_LEFT:
                    //seta para esquerda
                    px--;
                    break;
                    
                    case KeyEvent.VK_RIGHT:
                    //seta para direita
                    px++;
                    break;
                }
            }

        });

        tela = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(Color.WHITE);
                g.fillRect(0,0,tela.getWidth(), tela.getHeight());

                int x = tela.getWidth()/ 2 -20 +px;
                int y = tela.getHeight()/2-20+py;

                g.setColor(Color.YELLOW);
                g.fillOval(x, y, 80, 80);

                 // desenhar os olhos
                g.setColor(Color.BLACK);
                g.fillOval(x+10, y+10, 5, 5);
                g.fillOval(x+25, y+10, 5, 5);


            // desenhar a boca sorridente
                g.setColor(Color.BLACK);
                g.drawArc(x+10, y+15, 20, 15, 180, 180);
                g.drawArc(x+10, y+15, 20, 15, 0, 180);
                g.drawString("Agora eu estou em "+ x + "x" + y,5,10);
            }
        };

        getContentPane().add(tela);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);
        setVisible(true);
    }
    public static void main(String[] args){
        Interativo jogo = new Interativo();
        jogo.inicia();
    }
}