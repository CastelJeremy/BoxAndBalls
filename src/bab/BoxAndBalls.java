package bab;

import javax.swing.UIManager;

import bab.ui.Window;

/**
 * Class implmenting the main method :<pre>
 *  public static void main (String[] args) {...}</pre>
 */
public class BoxAndBalls {
    /**
     * Instanciates the {@code Window} class. Tries to set the Look and Feel. 
     * (Style of java with the current OS)
     */
    public static void main (String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }

        new Window();
    }
}
