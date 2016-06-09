package diningPhilosophers;

import error.ForkInUseException;
import error.NotYourForkException;

public class Fork {
    private int forkNumber;
    private Philosopher philosopherOnTheRight;
    private Philosopher philosopherOnTheLeft; 
    private Philosopher inUseBy;

    public Fork(int forkNumber, Philosopher philosopherOnTheRight, Philosopher philosopherOnTheLeft){
	    this.inUseBy=null;
        this.forkNumber=forkNumber;
    	this.philosopherOnTheLeft=philosopherOnTheLeft;
    	this.philosopherOnTheRight=philosopherOnTheRight;
    }

    /**
	 * Allows a philosopher to put down a fork. If the philosopher
     * is not using the fork, then do nothing.
     *
	 * @param p
	 * @throws ForkInUseException
     */
    public void putDown(Philosopher p) {
        if(p.equals(inUseBy))
            inUseBy=null;
    }

    /**
     * Pick up this fork to use it. Only two philosophers are allowed to
     * use this fork. This fork can only be used by one philosopher at a
     * time.
     *
     * @param p
     * @throws NotYourForkException
     * @throws ForkInUseException
     */
    public void pickUp(Philosopher p) throws NotYourForkException, ForkInUseException {
	
	if(!isValidUser(p))
        throw new NotYourForkException((this.getOwnerMessage() + " Please use your own fork " + p.getPhilosophersName() + "."));
	else if(this.forksInUse())
	    throw new ForkInUseException((this.getUseMessage()));
	else
	    inUseBy=p;
    }

    /**
     * Return true iff this fork is in use.
     * @return
     */
    public boolean forksInUse(){
	if(null==inUseBy)
	    return false;
	else
	    return true;
    }

    /**
     * Return a String the lists the philosophers allowed to use this fork.
     * @return
     */
    public String getOwnerMessage(){
	String message="This fork belongs to " +
            philosopherOnTheLeft.getPhilosophersName()
            + " and " +
            philosopherOnTheRight.getPhilosophersName()
            + ".";
        return message;
    }

    /**
     * Return a String that describes the current usage status of this fork.
     * @return
     */
    public String getUseMessage(){
	String message="";
        if(null==inUseBy)
            message+="This fork is not currently in use.";
        else
            message+="This fork is currently being used by " + inUseBy.getPhilosophersName() + ". Please wait your turn.";
        return message;
    }

    /**
     * Verify that the provided philosopher is a valid user.
     * @param p
     * @return
     */
    private boolean isValidUser(Philosopher p){
	return (p.equals(philosopherOnTheRight) || p.equals(philosopherOnTheLeft));
    }

    /**
     * Return the given fork number for this fork.
     * @return
     */
    public int getForkNumber(){
        return forkNumber;
    }
}
