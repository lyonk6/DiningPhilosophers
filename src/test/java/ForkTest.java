import diningPhilosophers.Fork;
import diningPhilosophers.Philosopher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import static org.junit.Assert.fail;


/**
 * Created by kennethlyon on 6/3/16.
 */
public class ForkTest {
    private Philosopher plato;
    private Philosopher nietzsche;
    private Philosopher kant;
    private Fork sharedFork;

    @Before
    public void setUp() throws Exception {
        plato = new Philosopher("Plato");
        nietzsche = new Philosopher("Nietzsche");
        kant = new Philosopher("Kant");
        sharedFork = new Fork(plato, nietzsche);
    }


    /**
     * After doPost runs, set pertinent objects to null. 
     * @throws Exception 
     */
    @After
        public void tearDown() throws Exception {
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
	 * 3. Try to put down a fork that someone else is using.
	 * 4. Try to put down a fork that is not in use.
	 */
    }
}
