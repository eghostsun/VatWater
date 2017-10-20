package slf.test;

public class WaterVatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WaterVatException()
	{
		super();
	}
	public WaterVatException(String message)
	{
		super(message);
	}
	public WaterVatException(String message,Throwable e)
	{
		super(message, e);
	}
}
