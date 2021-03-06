package diningPhilosophers;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import error.ForkInUseException;
import error.NotYourForkException;

public class Philosopher implements Runnable
{
    public static long CHEW_TIME=2000L;
    public static long PATIENCE=1000L;
    private BufferedWriter writer;
    private String philosophersName;
    private Fork rightFork;
    private Fork leftFork;
    private boolean finished;
    private static final String PATH="out/";


    public Philosopher(String philosophersName) {
        this.philosophersName = philosophersName;
        //this.assignForks(rightFork, leftFork);
        try {
            writer = new BufferedWriter(new FileWriter(PATH + this.getPhilosophersName()));
        } catch (IOException e) {
            System.err.println();
        }
    }

    public void assignForks(Fork rightFork, Fork leftFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /**
     * Return an array of bytes. Philosophers must eat you
     * know.
     * @return
     */
    private void takeBite() {
        byte[] bite = new byte[5];
        new Random().nextBytes(bite);

        try {
            System.out.println(this.getPhilosophersName() +": Nom nom nom...");
            writer.write("Nom nom nom...: ");
            for(int i=0;i<bite.length;i++)
                writer.write(bite[i] + " ");
            writer.write("\n");
        }catch(IOException e){
            e.printStackTrace();
            System.err.println("Could not write to file.");
            System.exit(0);
        }
    }

    /**
     * Intermittently attempt to pick up forks. If either forks is unavailable,
     * put the fork back down and try again this thread should drop the other fork if it
     * has it, then wait 3 seconds before attempting to pick up the
     * forks again.
     */
    //@Override
    public void run() {
        int i=0;
        //Random randomNumberGenerator = new Random(300897761);
        try {

        while(!finished)
            try {
                System.out.println(this.getPhilosophersName() +": *Attempts to pick up forks*");
                    rightFork.pickUp(this);
                    leftFork.pickUp(this);
                System.out.println(this.getPhilosophersName() +": *Begins to eat*");
                this.takeBite();
                    rightFork.putDown(this);
                    leftFork.putDown(this);


                Thread.sleep(CHEW_TIME);
                if(i>5)
                    finished=true;
                i++;
            } catch (NotYourForkException notYourFork) {
                System.err.println(this.getPhilosophersName()+" picked up the wrong fork. :/");
                notYourFork.printStackTrace();
                System.exit(0);
            } catch (ForkInUseException forkInUse) {
                System.out.println(this.getPhilosophersName() +": I cannot eat right now.");
                rightFork.putDown(this);
                leftFork.putDown(this);
                Thread.sleep(PATIENCE);
            }
        try{
            writer.close();
            int a=System.in.read();
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Could not write to file.");
            System.exit(0);
        }

    } catch (InterruptedException ie) {
        ie.printStackTrace();
        System.err.println("Thread error.");
        System.exit(0);
        }
    }

    /**
     * Return the name of this philosopher.
     * @return
     */
    public String getPhilosophersName(){ return philosophersName; }

    /**
     * Causes run to stop.
     */
    public void finishEating(){
        this.finished=true;
    }

    public boolean isFinished(){
        return finished;
    }


    public String getForkNames(){
        return this.getPhilosophersName() +" uses the forks: " + rightFork.getForkNumber() + " and " + leftFork.getForkNumber() + ".";
    }
}
