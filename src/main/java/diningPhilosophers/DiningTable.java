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
 * 
 * 
 * 
 * 
 *                    Dostoevsky
 *                      ......
 *                 _.d^^^^^^^^^^b._
 *              .d''              ``b.     
 *            .p'                    `q. 
 *   Camus   .d'                      `b.    Kafka
 *          .d'                        `b.
 *          ::                          ::
 *          ::                          ::
 *          ::                          ::
 *          `p.                        .q'
 *           `p.                      .q'
 *            `b.                    .d' 
 *              `q..              ..p'   
 *        Sartre   `^q..........p^``     Nietzsche
 *                     `''''''`
 * 
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
            //System.out.println("forks[i]==new Fork(philosopher[" + (i % 5) + "], philosopher[" + (i + 1) % 5 + "]);");
        }
        for(int i=0; i<forks.length;i++){
            philosophers[i].assignForks(forks[(i+4)%5], forks[(i)%5]);
        }
    }

    public static void main(String[]args){
        System.out.println("Program started.");
        String[] existentialists ={"Nietzsche", "Sartre", "Camus", "Dostoevsky", "Kafka"};
        DiningTable myTable=new DiningTable(existentialists);
        myTable.printTable();
        myTable.startEating();
        System.out.println("Program finished.");
    }

    public void startEating(){
        //for(int i=0; i<philosophers.length;i++) {

        System.out.println("Starting " + philosophers[0].getPhilosophersName());
        Thread t0=new Thread(philosophers[0]);
        t0.start();

        System.out.println("Starting " + philosophers[1].getPhilosophersName());
        Thread t1=new Thread(philosophers[1]);
        t1.start();

        System.out.println("Starting " + philosophers[2].getPhilosophersName());
        Thread t2=new Thread(philosophers[2]);
        t2.start();

        System.out.println("Starting " + philosophers[3].getPhilosophersName());
        Thread t3=new Thread(philosophers[3]);
        t3.start();

        System.out.println("Starting " + philosophers[4].getPhilosophersName());
        Thread t4=new Thread(philosophers[4]);
        t4.start();

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
