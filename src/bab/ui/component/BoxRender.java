package bab.ui.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

import bab.util.Box;

/**
 * Implements the element of the graphical interface to display boxes.
 */
public class BoxRender extends JComponent {
    private static final long serialVersionUID = 2561035491873542509L;

    /**
     * The box represented by this element.
     */
    private final Box box;

    /**
     * The current display mode, number or shape.
     */
    private final Boolean shaped;

    /**
     * Initializes a newly created {@code BoxRender} object. Sets the box
     * represented by this object and the method of drawing it.
     * 
     * @param box the box represented by this element
     * @param shaped the method of drawing this element
     */
    public BoxRender(Box box, Boolean shaped) {
        this.box = box;
        this.shaped = shaped;
    }

    /**
     * Method inherited by the {@code JComponent} class. Draws a rectangle and
     * a number or multiple shapes inside of it.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (this.box.getIsFirst())
            g2d.setColor(Color.GREEN);
        else
            g2d.setColor(Color.BLACK);
        
        g2d.drawRect(2, 2, 54, 32);

        g2d.setColor(Color.BLACK);

        if (shaped)
            for (int i = 0; i < box.getNbBalls(); i++) {
                g2d.fillOval(5 + (10 * i), 5, 10, 10);
            }
        else
            g2d.drawString(String.valueOf(this.box.getNbBalls()), 25, 23);
    }
}
