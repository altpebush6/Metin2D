package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    GamePanel gp;
    public MouseMotionHandler(GamePanel gp) {
        this.gp = gp;
        gp.addMouseMotionListener(this);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("x: "+e.getX()+" y: "+e.getY());
        
    }
    
}
