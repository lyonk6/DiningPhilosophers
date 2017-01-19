package diningPhilosophers;

/**
 * This class is the setting for the Dining Philosophers. There are 5 philosophers
 * and 5 threads. In order for a philosopher to eat, They must be able to pick up 
 * two forks, one from either side. If two forks are not available, then the 
 * philosopher will have to wait for their turn. 
 * 
 * Attempting to grab a fork that is in use should throw a ForkInUserException, and
 * grabbing a fork that does not belong to the philosopher trying to grab it should
 * throw a NotYourForkException.
 * 
 *             P 
 *         -  _ _  -
 *          /     \     
 *       P |       | P
 *       -  \ _ _ / -
 *         P   -   P      
 *
 * Created by kennethlyon on 6/1/16.
 */
public class DiningTable {

    private Philosopher[] philosophers;
    private Fork[] forks;
    
    public DiningTable(String[] names) {
        philosophers = new Philosopher[names.length];
        forks = new Fork[names.length];
        for (int i = 0; i < names.length; i++)
            philosophers[i] = new Philosopher(names[i]);
        for (int i = 0; i < names.length; i++) {
            forks[i]=new Fork(i, philosophers[(i)%5], philosophers[(i+1)%5]);
            System.out.println("forks[i]==new Fork(philosopher[" + (i % 5) + "], philosopher[" + (i + 1) % 5 + "];");
        }
        for(int i=0; i<forks.length;i++){
            philosophers[i].assignForks(forks[(i+4)%5], forks[(i)%5]);
        }
    }

    public static void main(String[]args){
        System.out.println("Program started.");
	    String[] existentialists ={"Nietzsche", "Sartre", "Camus", "Dostoevsky", "Kierkegaard"};
        DiningTable myTable=new DiningTable(existentialists);
        myTable.printTable();
        myTable.startEating();
        System.out.println("Program finished.");
    }

    public void startEating(){
        //for(int i=0; i<philosophers.length;i++) {
        System.out.println("Starting " + philosophers[0].getPhilosophersName());
        philosophers[0].run();

        System.out.println("Starting " + philosophers[1].getPhilosophersName());
        philosophers[1].run();

        System.out.println("Starting " + philosophers[2].getPhilosophersName());
        philosophers[2].run();

        System.out.println("Starting " + philosophers[3].getPhilosophersName());
        philosophers[3].run();

        System.out.println("Starting " + philosophers[4].getPhilosophersName());
        philosophers[4].run();

    }

    public void printTable(){
        for(int i=0;i<forks.length;i++){
            System.out.println(forks[i] + ": Fork " + i + " " +".  " + forks[i].getOwnerMessage());
        }

        for(int i=0;i<forks.length;i++){
            System.out.println(philosophers[i].getForkNames());
        }

    }
}
