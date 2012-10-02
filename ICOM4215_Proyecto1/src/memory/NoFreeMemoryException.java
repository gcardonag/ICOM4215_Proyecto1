package memory;

public class NoFreeMemoryException extends Exception {
	public NoFreeMemoryException()
	{
		super("There is not sufficient free contiguous memory to complete this operation.");
	}
}
