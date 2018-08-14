import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameCanvas extends JPanel {

    boolean rightPress = false;
    boolean leftPress = false;
    boolean upPress = false;
    boolean downPress = false;
    boolean xpress=false;
    Image background;
    Image player;
    Image bloodCell;

    ArrayList<PlayerBullet> bs;



    BufferedImage backbuffer;
    Graphics backbufferGraphics;




    int x = 300 - 32;
    int y = 700 - 40;

    String text = "Press Enter to start game";


    public GameCanvas() {
        bs =new ArrayList<>();



        try {
            background = ImageIO.read(new File("images/background/background.png"));
            player = ImageIO.read(new File("images/player/MB-69/player1.png"));
            bloodCell = ImageIO.read(new File("images/blood cells/blood-cell1.png"));

        } catch (IOException e) {
            System.out.println("oh no");
            e.printStackTrace();
        }

        backbuffer=new BufferedImage(600,800,BufferedImage.TYPE_INT_ARGB);
        backbufferGraphics=backbuffer.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
       g.drawImage(backbuffer,0,0,null);

    }




    void KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPress = true;

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPress = true;

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPress = true;

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPress = true;

        }
        if(e.getKeyCode()==KeyEvent.VK_X){
            xpress=true;
        }
    }

    void KeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPress = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPress = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPress = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPress = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_X){
            xpress=false;
        }
    }


    void run() {
        if (rightPress)
            x += 5;
        if (leftPress)
            x -= 5;
        if (upPress)
            y -= 5;
        if (downPress)
            y += 5;
        for(PlayerBullet b:bs){
            b.y-=10;
        }
        if(xpress && !ShootLock){
            PlayerBullet newB=new PlayerBullet();
            newB.x=x;
            newB.y=y;
            try {
                newB.image = ImageIO.read(new File("images/bullet/player/mb69bullet1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bs.add(newB);
            ShootLock =true;
        }
        if(ShootLock){
            count++;
            if(count >40){
                ShootLock =false;
                count =0;
            }
        }

    }
    boolean ShootLock =false;
    int count;



    void render() {
        backbufferGraphics.drawImage(background, 0, 0, null);
        backbufferGraphics.drawImage(bloodCell, 200, 384, null);
        backbufferGraphics.drawImage(player, x, y, null);

        for(PlayerBullet b:bs){
            backbufferGraphics.drawImage(b.image,b.x,b.y,null);
        }

        this.repaint();
    }
}
