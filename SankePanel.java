package Snakenxia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
public class SankePanel extends JPanel implements ActionListener{
    static final int S_Width=1200;
    static final int S_Height=600;
    static final int Game_unit_size=50;
    Timer timer;
    Random random;
    int foodEAten;
    int foodX;
    int foodY;
    int bodylength = 2;
    boolean game_flag = false;
    char dir = 'R';
    static final int DELAY = 160;
    static final int G_Size=(S_Width*S_Height)/(Game_unit_size*Game_unit_size);
    final int x_snake[]=new int[G_Size];
    final int y_snake[]=new int[G_Size];
    SankePanel(){
    	this.setPreferredSize(new Dimension(S_Width,S_Height));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKey());
        random = new Random();
        Game_start();
    }
    public void Game_start() {
        newfoodPosition();
        game_flag=true;
        timer=new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        draw(graphic);
    }
    
    
    
    public void draw(Graphics graphic) {
        if(game_flag){
        	
        	String fon="SSUET";
            graphic.setColor(Color.CYAN);
            graphic.setFont(new Font("Consolas",Font.ITALIC,400));
            FontMetrics metrics = graphic.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(fon)) / 2;
            int y = getHeight();

            // Draw the String
            graphic.drawString(fon, 50, 370);
        	
        	
        	
            Graphics2D g2 = (Graphics2D) graphic;
            g2.setColor(Color.YELLOW);
            g2.fillRect(500, 0, 190, 70);
            
        	graphic.setColor(Color.blue);
            graphic.setFont(new Font("Times New Roman",Font.BOLD,40));
            FontMetrics font_me=getFontMetrics(graphic.getFont());
            graphic.drawString("Score:"+foodEAten,(S_Width-font_me.stringWidth("Score:"+foodEAten))/2,graphic.getFont().getSize());

        	
        	
        	
        	
            graphic.setColor(new Color(153,0,0));
            graphic.fillOval(foodX, foodY,Game_unit_size,Game_unit_size);
            for(int i=0;i<bodylength;i++){
                if(i==0){
                    graphic.setColor(Color.red);
                    graphic.fillRect(x_snake[i],y_snake[i],Game_unit_size,Game_unit_size);
                }
                else{
                    graphic.setColor(new Color(255, 165,0));
                    graphic.fillRect(x_snake[i],y_snake[i],Game_unit_size,Game_unit_size);
                }
            }
            
          
            
            
            
            
           /* 
           Graphics2D g3 = (Graphics2D) graphic;
           g3.setColor(Color.BLACK);
           g3.setStroke(new BasicStroke(2)); //set the width of the line
           g3.draw(new Line2D.Double(200, 400, 300, 100));
           g3.draw(new Line2D.Double(500, 0, 800, 200)); //(upper starting point, height start, lower point,lower touch)
           */
 
           
                      
           
           
           
        }
        
        else{
            gameOver(graphic);
        }
        
        
    }
    
    
  /*  class Snake extends Thread {
        private boolean running = true;
        public void run() {
            while(running) {
                // do nothing, just wait
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // if thread is interrupted, stop running
                    running = false;
                }
            }
        }
        public void stopTask() {
            running = false;
        }
    }
*/
    public void move() {
        for(int i=bodylength;i>0;i--){
            x_snake[i]=x_snake[i-1];
            y_snake[i]=y_snake[i-1];

        }
        switch (dir) {
            case 'U':
            y_snake[0]=y_snake[0]-Game_unit_size;
                break;
            case 'L':
                x_snake[0] = x_snake[0] - Game_unit_size;
                break;
            case 'D':
                y_snake[0] = y_snake[0] + Game_unit_size;
                break;
            case 'R':
                x_snake[0] = x_snake[0] + Game_unit_size;
                break;
        }
    }
    public void newfoodPosition() {// for displaying food at a random position on the screen
        foodX=random.nextInt((int)(S_Width/Game_unit_size))*Game_unit_size;
        foodY=random.nextInt((int)(S_Height/Game_unit_size))*Game_unit_size;
    }
    public void food_EatenOrNot() {// for checking the food has been eaten by snake or not
        if((x_snake[0]==foodX)&&(y_snake[0]==foodY)){
            bodylength++;
            foodEAten++;
            newfoodPosition();
        }
    }
    public void checkHit() {
// for checking if by mistake snake bite itself and if it collides with walls
        for (int i=bodylength;i>0;i--)
            {if((x_snake[0]==x_snake[i])&&(y_snake[0]==y_snake[i]))
                {game_flag=false;}}
        if(x_snake[0]<0)
            {game_flag=false;}
        if(x_snake[0]>S_Width)
           { game_flag=false;}
        if (y_snake[0] < 0) 
        {    game_flag = false;}
        if (y_snake[0] > S_Height) 
        {    game_flag = false;}
        if(!game_flag)
          {  timer.stop();}
    }
    public void gameOver(Graphics graphic) {// When ever game is over this function will be called.
        graphic.setColor(Color.red);
        graphic.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics font_me = getFontMetrics(graphic.getFont());
        graphic.drawString("Score:" + foodEAten, (S_Width - font_me.stringWidth("Score:" + foodEAten)) / 2,
                graphic.getFont().getSize());
        graphic.setColor(Color.red);
        graphic.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics font_me2 = getFontMetrics(graphic.getFont());
        graphic.drawString("Game Over", (S_Width - font_me2.stringWidth("Game Over")) / 2,
                S_Height/2);
                graphic.setColor(Color.red);
        graphic.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics font_me3 = getFontMetrics(graphic.getFont());
        graphic.drawString("Press R to Replay", (S_Width - font_me3.stringWidth("Press R to Replay")) / 2, S_Height / 2-150);

    }
    public class MyKey extends KeyAdapter{
        @Override
        public synchronized void keyPressed(KeyEvent e) {
            
        	
        	switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(dir!='R'){
                        dir='L';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (dir != 'D') {
                        dir = 'U';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (dir != 'L') {
                        dir = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (dir != 'U') {
                        dir = 'D';
                    }
                    break;
                case KeyEvent.VK_R:
                if(!game_flag){
                    foodEAten=0;
                    bodylength=2;
                    dir='R';
                   Arrays.fill(x_snake,0);
                   Arrays.fill(y_snake,0);
                    Game_start();
                }
                break;
            }            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (game_flag) {
            move();
            food_EatenOrNot();
            checkHit();
        }
        repaint();
    }
    
}
