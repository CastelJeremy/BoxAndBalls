package bab.ui.panel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bab.ui.Window;
import bab.util.Page;

/**
 * First panel displayed on the screen, the {@code HomePanel} contains
 * different pages explaining how the software works. Each {@code Page}
 * is stored in a {@code ArrayList} and contains a title and a description.
 * 
 * <p>This panel includes buttons to navigate between pages and skip to the
 * rest of the software. To enable this feature the {@code HomePanel} class
 * implements the {@code ActionListener} interface.
 * 
 * @see bab.util.Page
 */
public class HomePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1253700299426662703L;

    /**
     * The parent window of this panel.
     */
    private final Window w;
    
    /**
     * The title graphic element displayed.
     */
    private final JLabel title;

    /**
     * The description graphic element displayed.
     */
    private final JLabel desc;

    /**
     * The back button element.
     */
    private final JButton back;

    /**
     * The skip button element.
     */
    private final JButton skip;

    /**
     * The next button element.
     */
    private final JButton next;

    /**
     * The list of possible pages with there titles and descriptions.
     */
    private final ArrayList<Page> pageList;

    /**
     * The index of the current page displayed.
     */
    private Integer pageIndex;

    /**
     * Initializes a newly created {@code Window} object. Sets default values
     * and adds graphic components.
     * 
     * @param w the parent window of this panel
     */
    public HomePanel(Window w) {
        this.w = w;

        this.setLayout(null);

        /**
         * The html tag is used to center horizontaly the description of the
         * pages and enable linebreaking.
         */
        this.pageList = new ArrayList<Page>();
        this.pageList.add(new Page(
            "Welcome!",
            "<html>Box and Balls is a software which will help you store and order your balls.<br/><br/>If you want to get the complete step by step guide then press the 'next' button. If you want to directly fill your boxes press 'skip'.<br/><br/><br/>author: Jérémy CASTEL</html>"
        ));
        this.pageList.add(new Page(
            "First",
            "<html>Input the numbers of boxes and balls you wish to use. Remember, the number of balls should be between half the number of boxes and the numbers of boxes.<br/><br/>For example if you use 10 boxes, you can set between 5 to 10 balls.<br/><br/><br/></html>"
        ));
        this.pageList.add(new Page(
            "Then",
            "<html>Select the algorithm you want and wait for the program to process.<br/><br/>Once this is done the window will switch and display your boxes.<br/><br/><br/><br/><br/><br/></html>"
        ));
        this.pageList.add(new Page(
            "And ?",
            "<html>You can view every box with the number of balls inside of it. You can choose between two different displays, number or shape.<br/><br/>Number: Displays the number of balls with a number in the middle of the boxes<br/><br/>Shape: Draws circles inside your boxes. One circle equals one ball.<br/></html>"
        ));
        this.pageList.add(new Page(
            "Encore",
            "<html>If you select the open adressing algorithms you may find colored box and a max value.<br/><br/>Colored boxes tells you how the algorithm selected them. (Black: randomly selected, Green: selected because others were full)<br/>Max: the number of boxes  visited before placing a ball.</html>"
        ));

        this.pageIndex = 0;
        Page currentPage = this.pageList.get(this.pageIndex);

        this.title = new JLabel(currentPage.title, SwingConstants.CENTER);
        this.title.setFont(this.title.getFont().deriveFont(20f));
        this.title.setBounds(0, 5, 250, 30);
        this.add(this.title);

        this.desc = new JLabel(currentPage.desc);
        this.desc.setBounds(3, 40, 250, 170);
        this.add(this.desc);

        this.back = new JButton("Back");
        this.back.setBounds(1, 229, 70, 30);
        this.back.setActionCommand("back");
        this.back.addActionListener(this);
        this.back.setVisible(false);
        this.add(this.back);

        this.skip = new JButton("Skip");
        this.skip.setBounds(90, 229, 70, 30);
        this.skip.setActionCommand("skip");
        this.skip.addActionListener(this);
        this.add(this.skip);

        this.next = new JButton("Next");
        this.next.setBounds(179, 229, 70, 30);
        this.next.setActionCommand("next");
        this.next.addActionListener(this);
        this.add(this.next);
    }

    /**
     * Implements the actionPerformed method from the {@code ActionListener}.
     * Enables navigation between pages, updates graphic component and moves to
     * the next panel.
     * 
     * @param event the event passed by the {@code ActionListener} interface
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        if (action.equals("back") || action.equals("next")) {
            if (action.equals("back")) {
                if (this.pageIndex > 0)
                    this.pageIndex--;
            }

            else if (action.equals("next")) {
                if (this.pageIndex < (this.pageList.size() - 1))
                    this.pageIndex++;
            }

            Page page = this.pageList.get(this.pageIndex);

            EventQueue.invokeLater(() -> {
                this.title.setText(page.title);
                this.desc.setText(page.desc);
                this.back.setVisible(this.pageIndex > 0);
                this.next.setText(this.pageIndex < (this.pageList.size() - 1) ? "Next" : "End");
                this.next.setActionCommand(this.pageIndex < (this.pageList.size() - 1) ? "next" : "skip");
            });
        }

        else if (action.equals("skip")) {
            this.w.disp(Window.Panel.INPUTPANEL);
        }
    }
}
