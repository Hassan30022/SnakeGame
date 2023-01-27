package Snakenxia;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.JFrame;

public class SnakeFrameTest {
    @Test
    public void testFrameProperties() throws InterruptedException {
        SnakeFrame frame = new SnakeFrame();
        Thread.sleep(1000);
        assertEquals("Snake", frame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertTrue(frame.isResizable());
        assertTrue(frame.isVisible());
    }
}


