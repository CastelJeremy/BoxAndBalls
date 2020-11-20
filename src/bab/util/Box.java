package bab.util;

/**
 * Store a number of balls and a {@code Boolean} to know if it was the first
 * selected box.
 * 
 * @see Util
 */
public class Box {
    /**
     * The number of balls in this box.
     */
    private Integer nbBalls = 0;

    /**
     * If this box was randomly selected or the box prior to this one was full.
     */
    private Boolean isFirst = false;

    /**
     * Initializes a newly created {@code Box} object.
     */
    public Box() { }

    /**
     * Returns the numbers of balls in this box.
     */
    public Integer getNbBalls() {
        return this.nbBalls;
    }

    /**
     * Returns the {@code isFirst} property.
     */
    public Boolean getIsFirst() {
        return this.isFirst;
    }

    /**
     * Increments the number of balls.
     */
    public void addBall() {
        this.nbBalls++;
    }

    /**
     * Sets the {@code isFirst} property with the new one.
     * 
     * @param isFirst differentiate if this is a random or visit box
     */
    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }
}
