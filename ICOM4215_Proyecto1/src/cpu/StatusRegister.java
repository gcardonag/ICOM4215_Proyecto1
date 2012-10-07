package cpu;

public class StatusRegister {

	public String overflow;
	public String carry;
	public String negative;
	public String zero;
	
	public StatusRegister(){
		overflow = "0";
		carry = "0";
		negative = "0";
		zero = "0";
	}
	
	public String getSR()
	{
		return overflow + negative + carry + zero;
	}
}
