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
 *                          Dostoevsky
 *                            ......
 *                  Ψ    _.d^^^^^^^^^^b._    Ψ
 *                    .d''              ``b.
 *                  .p'                    `q. 
 *         Camus   .d'                      `b.   Kafka
 *                .d'                        `b.
 *                ::                          ::
 *                ::                          ::
 *                ::                          ::
 *             Ψ  `p.                        .q'  Ψ
 *                 `p.                      .q'
 *                  `b.                    .d' 
 *                    `q..              ..p'   
 *              Sartre   `^q..      ..p^``   Nietzsche
 *                           `''''''`
 *                               Ψ
 *      
 *
 *
 * Created by kennethlyon on 6/1/16.
 */
public class DiningTable {
    private Thread[] threads;
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

    public static void main(String[]args)throws Exception {
        System.out.println("Welcome to Dining Philosophers.");
        String[] existentialists ={"Nietzsche", "Sartre", "Camus", "Dostoevsky", "Kafka"};
        DiningTable myTable=new DiningTable(existentialists);
        myTable.printTable();
        myTable.startEating();
        System.out.println("Give the philosophers a couple minutes to eat.");
        Thread.sleep(2*60*1000);
        myTable.finishEating();
    }

    public void startEating(){
        //for(int i=0; i<philosophers.length;i++) {
        threads=new Thread[5];
        System.out.println("Starting " + philosophers[0].getPhilosophersName());
        threads[0] =new Thread(philosophers[0]);
        threads[0].start();

        System.out.println("Starting " + philosophers[1].getPhilosophersName());
        threads[1]=new Thread(philosophers[1]);
        threads[1].start();

        System.out.println("Starting " + philosophers[2].getPhilosophersName());
        threads[2]=new Thread(philosophers[2]);
        threads[2].start();

        System.out.println("Starting " + philosophers[3].getPhilosophersName());
        threads[3]=new Thread(philosophers[3]);
        threads[3].start();

        System.out.println("Starting " + philosophers[4].getPhilosophersName());
        threads[4]=new Thread(philosophers[4]);
        threads[4].start();
    }

    public void finishEating(){
        boolean everyoneIsDoneEating=true;
        for(int i=0; i< threads.length; i++){
            if(philosophers[i].isFinished())
                threads[i].interrupt();
            else
                everyoneIsDoneEating=false;
        }

        if(everyoneIsDoneEating)
            System.out.println("Good. Everyone is done eating.");
        else
            System.out.println("Ya'all need to hurry up and finish.");
        System.exit(1);
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
