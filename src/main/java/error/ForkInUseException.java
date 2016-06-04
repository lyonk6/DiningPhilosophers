package error;

public class ForkInUseException extends Exception{
    public ForkInUseException(String errMessage){
	super(errMessage);
    }
}