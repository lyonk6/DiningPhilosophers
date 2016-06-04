package diningPhilosophers;

import error.ForkInUseException;
import error.NotYourForkException;

public class Fork {
    private Philosopher philosopherOnTheRight;
    private Philosopher philosopherOnTheLeft; 
    private Philosopher inUseBy;

    public Fork(Philosopher philosopherOnTheRight, Philosopher philosopherOnTheLeft){
	this.inUseBy=null;
	this.philosopherOnTheLeft=philosopherOnTheLeft;
	this.philosopherOnTheRight=philosopherOnTheRight;
    }

    /**
	 *
	 * @param p
	 * @throws ForkInUseException
     */
    private void putDown(Philosopher p) throws NotYourForkException {
	if(p.equals(inUseBy))
	    inUseBy=null;
    else
        throw new NotYourForkException((this.getOwnerMessage()));
    }

    private void pickUp(Philosopher p) throws NotYourForkException, ForkInUseException {
	
	if(!p.equals(philosopherOnTheRight) && !p.equals(philosopherOnTheLeft))
        throw new NotYourForkException((this.getOwnerMessage() + " Please use your own fork."));
	else if(this.forksInUse())
	    throw new ForkInUseException(("This for is in use by " + inUseBy.getPhilosophersName() + ". You will have to wait your turn."));
	else
	    inUseBy=p;
    }

    public boolean forksInUse(){
	if(null==inUseBy)
	    return false;
	else
	    return true;
    }
    
    public String getOwnerMessage(){
	String message="This fork belongs to " +
            philosopherOnTheLeft.getPhilosophersName()
            + " and " +
            philosopherOnTheRight.getPhilosophersName()
            + ".";
        if(null==inUseBy)
            message+=" This fork is not currently in use.";
        else
            message+=" This fork is currently in use by" + inUseBy.getPhilosophersName();
        return message;
    }
}
