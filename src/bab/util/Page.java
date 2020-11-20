package bab.util;

/**
 * An object to store a title and a description.
 * 
 * @see bab.ui.panel.HomePanel
 */
public class Page {
    /**
     * The title of this page.
     */
    public final String title;

    /**
     * The description of this page.
     */
    public final String desc;

    /**
     * Initializes a newly created {@code Page} object and sets the
     * title and description of this object.
     * 
     * @param title the title of this page
     * @param desc the description of this page
     */
    public Page (String title, String desc) {
        this.title = title;
        this.desc = desc;
    }
}
