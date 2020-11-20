package bab.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bab.ui.Window;

/**
 * Displays four different buttons so the user can select the algorithm he
 * wants to use. Because buttons are involved, the {@code ActionListener}
 * interface is implemented.
 */
public class SelectPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 8554480312268339326L;

    /**
     * The parent window of this panel.
     */
    private final Window w;

    /**
     * Initializes a newly created {@code SelectPanel} object. Sets default values
     * and adds graphic components.
     * 
     * @param w the parent window of this panel
     */    
    public SelectPanel(Window w) {
        this.w = w;

        this.setLayout(null);

        JLabel title = new JLabel("Choose your algos", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(0, 5, 250, 30);
        this.add(title);

        JButton chainage = new JButton("Chaining");
        chainage.setBounds(40, 40, 170, 40);
        chainage.setActionCommand("chain");
        chainage.addActionListener(this);
        this.add(chainage);

        JButton choices = new JButton("Double choice");
        choices.setBounds(40, 90, 170, 40);
        choices.setActionCommand("double");
        choices.addActionListener(this);
        this.add(choices);

        JButton linear = new JButton("<html>Open linear addressing</html>");
        linear.setBounds(40, 140, 170, 40);
        linear.setActionCommand("linear");
        linear.addActionListener(this);
        this.add(linear);

        JButton quadratique = new JButton("<html>Open quadratic addressing</html>");
        quadratique.setBounds(40, 190, 170, 40);
        quadratique.setActionCommand("quadra");
        quadratique.addActionListener(this);
        this.add(quadratique);
    }

    /**
     * Implements the actionPerformed method from the {@code ActionListener}.
     * Sets the selected algo and moves to the next panel.
     * 
     * @param event the event passed by the {@code ActionListener} interface
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        if (action.equals("chain"))
            this.w.setAlgo(Window.Algo.CHAIN);

        else if (action.equals("double"))
            this.w.setAlgo(Window.Algo.DOUBLE);

        else if (action.equals("linear"))
            this.w.setAlgo(Window.Algo.LINEAR);

        else if (action.equals("quadra"))
            this.w.setAlgo(Window.Algo.QUADRA);

        this.w.disp(Window.Panel.DISPLAYPANEL);
    }
}
