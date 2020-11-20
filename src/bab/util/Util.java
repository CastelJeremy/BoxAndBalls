package bab.util;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements four different algorithms to sort and order balls in boxes. The
 * {@code max} property is used to store the number of filled boxes visited
 * before being able to place a ball. This property is used by open adressing
 * alorithms and reset by others.
 */
public class Util {
    /**
     * The number of filled boxes visited before being able to place a ball.
     */
    public static Integer max = 0;

    /**
     * Returns a new {@code ArrayList} containing boxes filled with balls.
     * 
     * <p>Each ball is placed in a random {@code Box}. A Box can contain
     * several balls.
     * 
     * @param k the desired number of boxes
     * @param n the desired number of balls
     */
    public static ArrayList<Box> algoChain(Integer k, Integer n) {
        max = 0;
        ArrayList<Box> boxes = new ArrayList<Box>();

        for (int i = 0; i < k; i++)
            boxes.add(new Box());

        while (n > 0) {
            int index = ThreadLocalRandom.current().nextInt(0, k);
            boxes.get(index).addBall();
            n--;
        }

        return boxes;
    }

    /**
     * Returns a new {@code ArrayList} containing boxes filled with balls.
     * 
     * <p>Two boxes are randomly selected (Since the operations are indepent
     * the same box can be selected twice), the ball is placed in the least filled
     * box.
     * 
     * @param k the desired number of boxes
     * @param n the desired number of balls
     */
    public static ArrayList<Box> algoDouble(Integer k, Integer n) {
        max = 0;
        ArrayList<Box> boxes = new ArrayList<Box>();

        for (int i = 0; i < k; i++)
            boxes.add(new Box());

        while (n > 0) {
            Box box1 = boxes.get(ThreadLocalRandom.current().nextInt(0, k));
            Box box2 = boxes.get(ThreadLocalRandom.current().nextInt(0, k));

            if (box1.getNbBalls() > box2.getNbBalls())
                box2.addBall();
            
            else
                box1.addBall();
            
            n--;
        }

        return boxes;
    }

    /**
     * Returns a new {@code ArrayList} containing boxes filled with balls.
     * 
     * <p>A {@code Box} must contain only one ball. To place a ball, a random
     * Box is selected. If the selected box is full, we go to the next one. If
     * the first Box we encountered was empty, its {@code isFirst} property is
     * set to {@code true}.
     * 
     * <p>Each time the algorithm visit a full box, it increments an index. 
     * This index is then used to set the {@code max} property.
     * 
     * @param k the desired number of boxes
     * @param n the desired number of balls
     */
    public static ArrayList<Box> algoLinear(Integer k, Integer n) {
        max = 0;
        ArrayList<Box> boxes = new ArrayList<Box>();

        for (int i = 0; i < k; i++)
            boxes.add(new Box());

        while (n > 0) {
            int index = ThreadLocalRandom.current().nextInt(0, k);
            int c = 0;
            Box box = boxes.get(index);

            if (box.getNbBalls() == 0) {
                c++;
                box.addBall();
                box.setIsFirst(true);
                n--;
            }

            else {
                while (box.getNbBalls() > 0) {
                    c++;
                    index = (index + 1) % k;
                    box = boxes.get(index);
                }

                box.addBall();
                n--;
            }

            if (c > max)
                max = c;
        }

        return boxes;
    }

    /**
     * Returns a new {@code ArrayList} containing boxes filled with balls.
     * 
     * <p>A {@code Box} must contain only one ball. To place a ball, a random
     * Box is selected. If the selected box is full, we go to the next one but
     * if it is also full we go to the i + 4 box then i + 9, i + 16, i + 25,
     * etc. If the first Box we encountered was empty, its {@code isFirst}
     * property is set to {@code true}.
     * 
     * <p>Each time the algorithm visit a full box, it increments an index. 
     * This index is then used to set the {@code max} property.
     * 
     * @param k the desired number of boxes
     * @param n the desired number of balls
     * @throws Exception if the index of visited boxes is superior to the total
     *         number of boxes.
     */
    public static ArrayList<Box> algoQuadra(Integer k, Integer n) throws Exception {
        max = 0;
        ArrayList<Box> boxes = new ArrayList<Box>();

        for (int i = 0; i < k; i++)
            boxes.add(new Box());

        while (n > 0) {
            int index = ThreadLocalRandom.current().nextInt(0, k);
            int c = 0;
            int inc = 0;
            Box box = boxes.get(index);

            if (box.getNbBalls() == 0) {
                c++;
                inc++;
                box.addBall();
                box.setIsFirst(true);
                n--;
            }

            else {
                index = (index + inc) % k;
                box = boxes.get(index);
                c++;

                while (box.getNbBalls() > 0) {
                    inc += 2;
                    c++;
                    index = (index + inc) % k;
                    box = boxes.get(index);

                    if (c > k + 1) {
                        max = 0;
                        throw new Exception(k + " boxes is invalid for the quadratic.");
                    }
                }

                box.addBall();
                n--;
            }
            
            if (c > max)
                max = c;
        }
        
        return boxes;
    }
}