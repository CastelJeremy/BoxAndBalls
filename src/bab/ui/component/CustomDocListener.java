package bab.ui.component;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Implements the {@code DocumentListener} interface and calls its
 * {@code Runnable} each time there is an event.
 */
public class CustomDocListener implements DocumentListener {
    /**
     * The runnable that is called.
     */
    private final Runnable runnable;
    
    /**
     * Initializes a newly created {@code CustomDocListener} object. Sets the
     * runnable that is called at each event.
     * 
     * @param runnable the runnable that is called
     */
    public CustomDocListener(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Method inherited from the {@code DocumentListener} interface. Calls the
     * update method.
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        this.update();
    }

    /**
     * Method inherited from the {@code DocumentListener} interface. Calls the
     * update method.
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        this.update();
    }

    /**
     * Method inherited from the {@code DocumentListener} interface. Calls the
     * update method.
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        this.update();
    }
    
    /**
     * Calls the runnable.
     */
    private void update() {
        this.runnable.run();
    }
}
