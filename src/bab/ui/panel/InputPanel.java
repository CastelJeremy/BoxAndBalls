package bab.ui.panel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bab.ui.Window;
import bab.ui.component.CustomDocListener;

/**
 * Displays inputs on the window to set values for the number of boxes and the
 * number of balls. The {@code CustomDocListener} listen for event on those
 * inputs and will instantly try to validate the values. If there is an error,
 * a red text will be displayed on the screen.
 * 
 * <p>The {@code InputPanel} class implements the {@code ActionListener}
 * interface to allow the user to submit his values and re-evaluate them.
 * 
 * @see bab.ui.component.CustomDocListener
 */
public class InputPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 7319814864417275766L;

    /**
     * The parent window of this panel.
     */
    private final Window w;

    /**
     * The number of boxes input element displayed.
     */
    private final JTextField inputNbBoxes;

    /**
     * The number of balls input element displayed.
     */
    private final JTextField inputNbBalls;

    /**
     * The error graphic element displayed.
     */
    private final JLabel error;

    /**
     * The number of boxes set.
     */
    private Integer nbBoxes;

    /**
     * The number of balls set.
     */
    private Integer nbBalls;

    /**
     * Initializes a newly created {@code InputPanel} object. Sets default values
     * and adds graphic components.
     * 
     * @param w the parent window of this panel
     */
    public InputPanel (Window w) {
        this.w = w;

        this.setLayout(null);

        JLabel title = new JLabel("Insert your values", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(0, 5, 250, 30);
        this.add(title);

        this.error = new JLabel("",SwingConstants.CENTER);
        this.error.setBounds(25, 30, 200, 40);
        this.error.setForeground(Color.RED);
        this.add(this.error);

        JLabel labelBox = new JLabel("Number of boxes:");
        labelBox.setBounds(50, 70, 150, 20);
        this.add(labelBox);

        this.inputNbBoxes = new JTextField();
        this.inputNbBoxes.setBounds(50, 90, 150, 30);
        this.add(this.inputNbBoxes);

        JLabel labelBall = new JLabel("Number of balls:");
        labelBall.setBounds(50, 125, 150, 20);
        this.add(labelBall);

        this.inputNbBalls = new JTextField();
        this.inputNbBalls.setBounds(50, 145, 150, 30);
        this.add(this.inputNbBalls);

        JButton submit = new JButton("Submit");
        submit.setBounds(50, 190, 150, 30);
        submit.setActionCommand("submit");
        submit.addActionListener(this);
        this.add(submit);

        this.inputNbBoxes.getDocument().addDocumentListener(new CustomDocListener(() -> {
            try {
                nbBoxes = Integer.parseInt(inputNbBoxes.getText());
                EventQueue.invokeLater(() -> {
                    inputNbBalls.setText(String.valueOf((int) Math.ceil(nbBoxes / 2f)));
                });
                setErrorMessage("");
            } catch(NumberFormatException e) {
                setErrorMessage("A number is required.");
            }
        }));

        this.inputNbBalls.getDocument().addDocumentListener(new CustomDocListener(() -> {
            try {
                nbBalls = Integer.parseInt(inputNbBalls.getText());
                
                if (nbBoxes != null) {
                    if (nbBalls >= ( nbBoxes / 2f) && nbBalls <= nbBoxes) {
                        setErrorMessage("");
                    } else {
                        setErrorMessage("Number of balls invalid.");
                    }
                }
            } catch(NumberFormatException e) {
                setErrorMessage("A number is required.");
            }
        }));
    }

    /**
     * Displays the message on the screen.
     * 
     * @param message the message to display
     */
    private void setErrorMessage(String message) {
        EventQueue.invokeLater(() -> {
            this.error.setText(message);
        });
    }

    /**
     * Implements the actionPerformed method from the {@code ActionListener}.
     * Re-evaluates the number of boxes and the number of balls to make sure
     * they are valid. (Balls should be between boxes and half the boxes).
     * Moves to the next panel.
     * 
     * @param event the event passed by the {@code ActionListener} interface
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        if (action.equals("submit")) {
            try {
                Integer boxes = Integer.parseInt(this.inputNbBoxes.getText());
                Integer balls = Integer.parseInt(this.inputNbBalls.getText());

                if (balls >= (boxes / 2) && balls <= boxes) {
                    this.w.setNbBoxes(boxes);
                    this.w.setNbBalls(balls);
                    this.w.disp(Window.Panel.SELECTPANEL);
                } else {
                    this.setErrorMessage("Number of balls invalid.");
                }
            } catch(Exception e) {
                this.setErrorMessage("Invalid values.");
            }
        }
    }
}
