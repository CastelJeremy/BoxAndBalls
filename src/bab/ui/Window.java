package bab.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bab.ui.panel.DisplayPanel;
import bab.ui.panel.HomePanel;
import bab.ui.panel.InputPanel;
import bab.ui.panel.SelectPanel;
import bab.util.Box;

/**
 * Main frame of this application, the {@code Window} class consists of a
 * {@code JPanel} with the {@code CardLayout} where each card is a different
 * section. Each section is a JPanel with its own graphic interface.
 * 
 * <p>The class also stores different values used by the software. Such as the
 * list of the different sections the list of possible algorithms, the desired
 * number of balls and boxes, the list of previously filled boxes and the
 * selected algorithm.
 */
public class Window extends JFrame {
    private static final long serialVersionUID = -2665608161311636325L;

    /**
     * The list of the different sections or panels with a {@code String} to be set as
     * a key when objects can't be stored. ({@code CardLayout} require a String)
     */
    public static enum Panel {
        HOMEPANEL ("Home Panel"),
        INPUTPANEL ("Input Panel"),
        SELECTPANEL ("Select Panel"),
        DISPLAYPANEL ("Display Panel");

        public final String txt;

        Panel(String txt) {
            this.txt = txt;
        }
    }

    /**
     * The list of possible algorithms.
     */
    public static enum Algo {
        CHAIN,
        DOUBLE,
        LINEAR,
        QUADRA
    }

    /**
     * Main panel of this frame, cards will use the CardLayout and display each
     * section.
     */
    private final JPanel cards;

    /**
     * The {@code DisplayPanel} is the only panel stored directly in the class
     * because when the user sets different values the panel needs to be
     * refreshed.
     */
    private final DisplayPanel d;

    /**
     * The number of boxes selected by the user.
     */
    private Integer nbBoxes = 0;

    /**
     * The number of balls selected by the user.
     */
    private Integer nbBalls = 0;

    /**
     * The list of processed boxes filled with balls.
     */
    public ArrayList<Box> boxes;

    /**
     * The selected algorithm.
     */
    private Algo algo;

    /**
     * Initializes a newly created {@code Window} object. Sets default values
     * and adds graphic components. Finally, displays a window to the user.
     */
    public Window() {
        super("BoxAndBalls");

        this.boxes = new ArrayList<Box>();

        this.cards = new JPanel(new CardLayout());
        this.d = new DisplayPanel(this);
        this.cards.add(new HomePanel(this), Panel.HOMEPANEL.txt);
        this.cards.add(new InputPanel(this), Panel.INPUTPANEL.txt);
        this.cards.add(new SelectPanel(this), Panel.SELECTPANEL.txt);
        this.cards.add(this.d, Panel.DISPLAYPANEL.txt);

        /*
         * this.setSize() takes into account the size of the title bar which is
         * different for windows and linux. this.pack() is better in our case.
         */
        this.cards.setPreferredSize(new Dimension(250, 260));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.disp(Panel.HOMEPANEL);
        this.getContentPane().add(this.cards);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Returns the number of boxes.
     */
    public Integer getNbBoxes() {
        return nbBoxes;
    }

    /**
     * Returns the number of balls.
     */
    public Integer getNbBalls() {
        return nbBalls;
    }

    /**
     * Returns the selected algorithm.
     */
    public Algo getAlgo() {
        return algo;
    }

    /**
     * Sets the number of boxes.
     * 
     * @param nbBoxes the number of boxes
     */
    public void setNbBoxes(Integer nbBoxes) {
        this.nbBoxes = nbBoxes;
    }

    /**
     * Sets the number of balls.
     * 
     * @param nbBalls the number of balls
     */
    public void setNbBalls(Integer nbBalls) {
        this.nbBalls = nbBalls;
    }

    /**
     * Sets the selected algorithm.
     * 
     * @param algo the selected algorithm
     */
    public void setAlgo(Algo algo) {
        this.algo = algo;
    }

    /**
     * Displays the selected panel on the window. Mostly used by panels to
     * switch between displays.
     * 
     * <p>If the selected panel is the {@code DisplayPanel}, its refresh method
     * is called to update the graph.
     * 
     * @param panel the selected panel
     * @see bab.ui.panel.DisplayPanel
     */
    public void disp(Panel panel) {
        if (panel == Panel.DISPLAYPANEL)
            this.d.refresh();

        ((CardLayout) this.cards.getLayout()).show(this.cards, panel.txt);
    }
}
