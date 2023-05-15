import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test2 extends JFrame {
    private int px;
    private int py;
    private JPanel screen;
    private static final long serialVersionUID=1L;
    private boolean jogando=true;
    private final int FPS = 1000/100;
    private Point mouseClick=new Point();
    
    public test2(){
        screen= new JPanel(){
            private static final long serialVersionUID=1L;
            @Override
            public void paintComponent(Graphics g){
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
                int y= py-20;
                int x=px-20;
                g.setColor(Color.BLUE);
                g.fillRect(x, y, 50, 50);
                g.drawString("Agora estou em "+ x + "x" + y, 5, 10);
            }
        };
        
        screen.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e){
                //mouse liberado
            }
            @Override
            public void mousePressed(MouseEvent e){
                //botao mouse pressionado
            }
            @Override
            public void mouseExited(MouseEvent e){
                //mouse saiu da tela 
            }
            @Override
            public void mouseEntered(MouseEvent e){
                //entrou na tela 
            }
            @Override
            public void mouseClicked(MouseEvent e){
                mouseClick=e.getPoint();
            }
        });
        getContentPane().add(screen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);
        setVisible(true);
    }

    public void inicia(){
        long att=0;
        while(jogando){
            if(System.currentTimeMillis()>= att){
                atualizaJogo();
                screen.repaint();
                att=System.currentTimeMillis()+ FPS;
            }            
        }
    }
    
    private void atualizaJogo(){
        px=mouseClick.x;
        py=mouseClick.y;
    }
    
    public static void main(String[] args){
        test2 jogo= new test2();
        jogo.inicia();
    }
}

