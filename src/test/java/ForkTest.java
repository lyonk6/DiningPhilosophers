import diningPhilosophers.Fork;
import diningPhilosophers.Philosopher;
import error.ForkInUseException;
import error.NotYourForkException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kennethlyon on 6/3/16.
 */
public class ForkTest {
    private Philosopher plato;
    private Philosopher aristotle;
    private Philosopher socrates;
    private Fork rightFork;
    private Fork leftFork;
    @Before
    public void setUp() throws Exception {

        plato = new Philosopher("Plato");
        plato.assignForks(rightFork, leftFork);
        aristotle = new Philosopher("Aristotle");
        aristotle.assignForks(rightFork, leftFork);
        socrates = new Philosopher("Socrates");
        socrates.assignForks(rightFork, leftFork);
        rightFork = new Fork(0,plato, aristotle);
        leftFork = new Fork(2,plato, aristotle);
    }

    /**
     * After doPost runs, set pertinent objects to null.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        plato = null;
        aristotle = null;
        socrates = null;
        rightFork = null;
        leftFork = null;
    }

    /**
     * @throws Exception
     */
    @Test
    public void doTest() throws Exception {
        //
	/* First we try to break the Fork class:
	 * 1. Try to pick up someone else's fork.
	 * 2. Try to pick up a fork that is in use.
	 *
	 */
        // 1. Try to pick up someone else's fork.
        System.out.println("\nTry picking up the wrong fork:");
        try {
            rightFork.pickUp(socrates);
        } catch (NotYourForkException nyfe) {
            System.out.println(nyfe.getMessage());
        }

        // 2. Try to pick up a fork that is in use.
        System.out.println("\nTry picking up a fork that is un use:");
        try {
            rightFork.pickUp(aristotle);
            rightFork.pickUp(plato);

        } catch (ForkInUseException fiue) {
            System.out.println(fiue.getMessage());
        }
        System.out.println("***************************************");
        System.out.println("\nTry taking turns:");
        try {

            System.out.println("Aristotle puts down the fork.\n");
            rightFork.putDown(aristotle);

            System.out.println("Now Plato can pick it up.");
            rightFork.pickUp(plato);

            System.out.println("Plato puts down the fork.\n");
            rightFork.putDown(plato);

            System.out.println("Now Aristotle can pick it up again.");
            rightFork.pickUp(aristotle);

            System.out.println("Aristotle puts down the fork.\n");
            rightFork.putDown(aristotle);
        } catch (NotYourForkException nyfe) {
            System.out.println(nyfe.getMessage());
        } catch (ForkInUseException fiue) {
            System.out.println(fiue.getMessage());
        }
    }
}