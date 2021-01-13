package Kata07.apps.swing;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import Kata07.view.BlockDisplay;
import Kata07.model.Block;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BlockPanel extends JPanel implements BlockDisplay {
    private Block block;
    private final int BLOCK_SIZE;
    private final int MAX;
    private int x;
    private int y;
    private Moved moved = new Moved.Null();

    public BlockPanel(int MAX, int BLOCK_SIZE) {
        this.BLOCK_SIZE = BLOCK_SIZE;
        this.MAX = MAX;
        
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.black);
        int max = this.MAX * this.BLOCK_SIZE;
        
        for (int i = 0; i <= max; i+=BLOCK_SIZE) {
            g.drawLine(0, i, max, i);
            g.drawLine(i, 0, i, max);
        }
        g.setColor(Color.red);
        g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }

    @Override
    public void display(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }
    
    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseMotionListener, MouseListener {
        private boolean grabbed = false;
        
        @Override
        public void mouseDragged(MouseEvent event) {
            if (grabbed) moved.to(event.getX() / BLOCK_SIZE, event.getY() / BLOCK_SIZE);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            grabbed = event.getX() /  BLOCK_SIZE == x && event.getY() / BLOCK_SIZE == y;
        }

        @Override
        public void mouseReleased(MouseEvent event) {
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}