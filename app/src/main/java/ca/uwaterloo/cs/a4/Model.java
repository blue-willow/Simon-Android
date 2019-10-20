package ca.uwaterloo.cs.a4;

/**
 * Created by hui on 2017-12-02.
 */

import android.util.Log;
import java.util.Observable;
import java.util.Observer;
import java.util.*;

public class Model extends Observable {
    /** member variables */
    int state;      // current state: 1-START, 2-COMPUTER, 3-HUMAN, 4-LOSE, 5-WIN
    int score;      // current score
    int difficulty; // level of difficulty: 1-easy, 2-normal, 3-hard
    int length;     // length of sequence
    int buttons;    // number of possible buttons
    int current;    // current button
    int[] sequence; // the sequence of buttons
    int speed;
    // Create static instance of this Model
    private static final Model ourInstance = new Model();

    /** Model Constructor */
    public Model() {
        state = 1;
        score = 0;
        length = 1;
        buttons = 4;
        difficulty = 2;
    }

    /** getter of ourInstance */
    static Model getInstance() {
        return ourInstance;
    }

    /** get the current state by string */
    public String getStateAsString() {
        switch (state) {
            case 1: return "START";
            case 2: return "COMPUTER";
            case 3: return "HUMAN";
            case 4: return "LOSE";
            case 5: return "WIN";
            default:return "Unkown State";
        }
    }

    /** method to start a new round */
    void newRound() {

        // reset if they lost last time
        if (state == 4) {
            length = 1;
            score = 0;
        }
        sequence = new int[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int b = rand.nextInt(buttons) + 1;
            sequence[i] = b;
        }
        current = 0;
        state = 2;  // COMPUTER

    }

    /** call this to get next button to show when computer is playing */
    int nextButton() {
        if (state != 2) {   // state != COMPUTER
            return -1;
        }
        // this is the next button to show in the sequence
        int button = sequence[current];
        // advance to next button
        current++;

        // if all the buttons were shown, give
        // the human a chance to guess the sequence
        if (current >= sequence.length) {
            current = 0;
            state = 3;   //HUMAN
        }
        return button;
    }

    /** verify if the user pressed the correct button */
    boolean verifyButton(int button) {

        if (state != 3) {   // state != HUMAN
            return false;
        }
        // did they press the right button?
        boolean correct = (button == sequence[current]);
        // advance to next button
        current++;

        // pushed the wrong buttons
        if (!correct) {
            state = 4;  // LOSE
        } else {
            // if last button, then the win the round
            if (current == sequence.length) {
                state = 5;  // WIN
                // update the score and increase the difficulty
                score++;
                length++;
            }
        }
        return correct;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    // Observable Methods
    ///////////////////////////////////////////////////////////////////////////////////////

    /** Helper method to make it easier to initialize all observers */
    public void initObservers() {
        setChanged();
        notifyObservers();
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:
     * <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }
}

