package cpu;

public class StatusRegister {

	public int overflow;
	public int carry;
	public int negative;
	public int zero;
	
	public StatusRegister(){
		overflow = 0;
		carry = 0;
		negative = 0;
		zero = 0;
	}
}
