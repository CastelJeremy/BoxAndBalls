package bab.ui.panel;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import bab.ui.Window;
import bab.ui.Window.Algo;
import bab.ui.Window.Panel;
import bab.ui.component.BoxRender;
import bab.util.Box;
import bab.util.Util;

/**
 * Final panel of the software, the {@code DisplayPanel} computes the selected
 * algorithm and displays a {@code JScrollPane} with the boxes and their number
 * of balls.
 * 
 * <p>It also includes {@code JRadioButton} to switch between shape and number
 * display and therefore implements the {@code ActionListener} interface.
 */
public class DisplayPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 7878674609975907855L;

    /**
     * The parent window of this panel.
     */
    private final Window w;

    /**
     * The title graphic element displayed. (Needed to display the number of
     * visited boxes)
     */
    private JLabel title;

    /**
     * The graph element displayed. (Contains the {@code BoxRender} elements)
     */
    private JPanel graph;

    /**
     * The scroll graphic element displayed.
     */
    private JScrollPane scroll;

    /**
     * Determine which method to display the boxes.
     */
    private Boolean shaped = false;

    /**
     * Initializes a newly created {@code DisplayPanel} object. Sets default values
     * and adds graphic components.
     * 
     * @param w the parent window of this panel
     */    
    public DisplayPanel(Window w) {
        this.w = w;
        this.graph = new JPanel(new GridLayout(0, 4));
        this.scroll = new JScrollPane(this.graph);
        this.setLayout(null);

        this.title = new JLabel("Display", SwingConstants.CENTER);
        this.title.setFont(this.title.getFont().deriveFont(20f));
        this.title.setBounds(0, 5, 250, 30);
        this.add(this.title);

        JRadioButton numberRadio = new JRadioButton("Number", true);
        numberRadio.setBounds(0, 229, 80, 30);
        numberRadio.setActionCommand("number");
        numberRadio.addActionListener(this);
        this.add(numberRadio);

        JButton home = new JButton("Home");
        home.setBounds(169, 229, 80, 30);
        home.setActionCommand("home");
        home.addActionListener(this);
        this.add(home);

        JRadioButton shapeRadio = new JRadioButton("Shape");
        shapeRadio.setBounds(80, 229, 80, 30);
        shapeRadio.setActionCommand("shape");
        shapeRadio.addActionListener(this);
        this.add(shapeRadio);

        ButtonGroup group = new ButtonGroup();
        group.add(numberRadio);
        group.add(shapeRadio);
    }

    /**
     * Updates the {@code ArrayList} containing boxes with the selected
     * algorithm. Displays a {@code JOtionPane} when the open adressing
     * quadratic algorithm crashes. Calls the refreshGraph() method.
     */
    public void refresh() {
        Algo algo = this.w.getAlgo();

        if (algo == Algo.CHAIN)
            this.w.boxes = Util.algoChain(this.w.getNbBoxes(), this.w.getNbBalls());

        else if (algo == Algo.DOUBLE)
            this.w.boxes = Util.algoDouble(this.w.getNbBoxes(), this.w.getNbBalls());

        else if (algo == Algo.LINEAR)
            this.w.boxes = Util.algoLinear(this.w.getNbBoxes(), this.w.getNbBalls());

        else if (algo == Algo.QUADRA) {
            try {
                this.w.boxes = Util.algoQuadra(this.w.getNbBoxes(), this.w.getNbBalls());
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "The open quadratic adressing will sometimes\ncrash because of it's algorithm. You can try\nagain after pressing \"OK\".");
                this.w.boxes = new ArrayList<Box>();
            }
        }

        EventQueue.invokeLater(() -> {
            this.refreshGraph();
        });
    }

    /**
     * Updates the content pane of this panel and renders boxes. Must be called
     * as a {@code Runnable} in the invokeLater method.
     * 
     * https://stackoverflow.com/questions/3541373/should-we-use-eventqueue-invokelater-for-any-gui-update-in-a-java-desktop-applic
     * 
     * @see bab.ui.component.BoxRender
     */
    public void refreshGraph() {
        /*
         * This is mandatory, for some reasons if there are no differences in
         * the list of components, the graphical interface won't be updated
         * even with the invokeLater method. The paint method and the
         * shaped property from {@code BoxRender} are not considered.
         */
        this.title.setText("");

        if (Util.max != 0)
            this.title.setText("Max : " + String.valueOf(Util.max));
        else
            this.title.setText("Display");

        this.graph = new JPanel(new GridLayout(0, 4));

        for (Box box : this.w.boxes) {
            BoxRender b = new BoxRender(box, this.shaped);
            b.setPreferredSize(new Dimension(58, 36));
            this.graph.add(b);
        }

        this.remove(this.scroll);
        this.scroll = new JScrollPane(this.graph);
        this.scroll.setBounds(0, 34, 251, 195);
        this.add(this.scroll);
    }

    /**
     * Implements the actionPerformed method from the {@code ActionListener}.
     * Switches between display mode, calls the refreshGraph method and moves
     * to the {@code HomePanel}.
     * 
     * @param event the event passed by the {@code ActionListener} interface
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        if (action.equals("home")) {
            this.w.disp(Panel.HOMEPANEL);
        } else {
            this.shaped = action.equals("shape");

            EventQueue.invokeLater(() -> {
                this.refreshGraph();
            });
        }
    }
}
