package diningPhilosophers;

import java.util.Random;

public class Philosopher extends Thread implements Runnable
{
    
    private String philosophersName;

    public Philosopher(String philosophersName){
	    this.philosophersName=philosophersName;
    }

    /**
     * Return an array of bytes. Philosophers must eat you
     * know.
     * @return
     */
    private byte[] takeBite(){
        byte[] bite = new byte[5];
        new Random().nextBytes(bite);
        return bite;
    }

    /**
     * Intermittently attempt to pick up forks. If both forks are
     * not available, this thread should drop the other fork if it
     * has it, then wait 3 seconds before attempting to pick up the
     * forks again.
     */
    public void run(){	System.currentTimeMillis();    }

    /**
     * Return the name of this philosopher.
     * @return
     */
    public String getPhilosophersName(){
        return philosophersName;
    }
}
