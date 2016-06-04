package diningPhilosophers;

/**
 * This class is the setting for the Dining Philosophers. There are
 * 5 philosophers and 5 threads. In order for a philosopher to eat,
 *
 *
 *             0 
 *         -  _ _  -
 *          /     \     
 *       0 |       | 0  
 *       -  \ _ _ / -
 *         0   -   0      
 *
 * Created by kennethlyon on 6/1/16.
 */
public class DiningTable {

    public static void main(String[]args){
        Thread nietzsche = new Thread(new Philosopher("nietzsche"));
	    nietzsche.start();
        
	while(nietzsche.isAlive()){
	    
	}
    }
}
