package cpu;

public class CPU {
	
	public static StatusRegister SR = new StatusRegister();
	
	// Registers
	public static String R0, R1, R2, R3, R4, R5, R6, R7;
	public static String PC = "00000000";
	
	public CPU()
	{
		R0 = "00000000";
		R1 = "00000000";
		R2 = "00000000";
		R3 = "00000000";
		R4 = "00000000";
		R5 = "00000000";
		R6 = "00000000";
		R7 = "00000000";
	}
	
	public void step(String hex_value)
	{
		
	}
	
	private void brz()
	{
		if(SR.zero.equals("1"))
			PC = R7;
	}
	
	private void brc()
	{
		if(SR.carry.equals("1"))
			PC = R7;
	}
	
	private void brn()
	{
		if(SR.negative.equals("1"))
			PC = R7;
	}
	
	private void bro()
	{
		if(SR.overflow.equals("1"))
			PC = R7;
	}
	
	private void stop()
	{
		PC = null;
	}
}
