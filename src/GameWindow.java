import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameWindow extends JFrame {


    GameCanvas gameCanvas;


    public GameWindow() {
        //setup game windowc //?

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                gameCanvas.KeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameCanvas.KeyReleased(e);
            }
        });
        this.setTitle("Micro-war by vienmv");
        this.setResizable(false);
        this.setSize(600, 800);


        // setup canvas
        gameCanvas = new GameCanvas();
        this.setContentPane(gameCanvas);

        this.setVisible(true);


    }
    long lastTimeRender=0;

    void mainLoop(){
        while (true){
            long currentTime=System.nanoTime();
            if(currentTime- lastTimeRender>=17_000_000){
                gameCanvas.run();
                gameCanvas.render();
                lastTimeRender=currentTime;
            }

        }
    }
}
