package error;
public class NotYourForkException extends Exception{
   public NotYourForkException(String errMessage){
	super(errMessage);
    }
}