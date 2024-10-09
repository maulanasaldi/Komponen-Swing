package maulana.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MyButton extends JButton {
    
    private Animator animasi;    
    private int targetSize;
    private float ukuranAnimasi;
    private float alpha;
    private Point pressedPoint;
    private Color effectColor = new Color(255, 255, 255);

    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }
    
    
    public MyButton() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                ukuranAnimasi = 0;
                pressedPoint = e.getPoint();
                if (animasi.isRunning()) {
                    animasi.stop();
                }
                animasi.start();
            }            
        });
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                ukuranAnimasi = fraction * targetSize;
                repaint();
            }            
        };
        animasi = new Animator(700, target);
        animasi.setAcceleration(0.5f);
        animasi.setDeceleration(0.5f);
        animasi.setResolution(0);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, height, height);
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }    
}
