package Snakenxia;

import javax.swing.JFrame;

import org.junit.Test;
import static org.junit.Assert.*;

public class  SnakeFrame extends JFrame {
    SnakeFrame(){
        this.add(new SankePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
}