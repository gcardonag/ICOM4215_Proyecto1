package accumulator;

public class Accumulator {
	private String acc_value;
	
	public Accumulator() {
		acc_value = "00000000";
	}
	
	/**
	 * Receives a string consisting of a binary value and performs a logical AND with the
	 * accumulator, storing the result back in the accumulator.
	 * 
	 * @param reg_value
	 */
	public void acc_and(String reg_value){
		String result = "";
		for(int i=0; i<8; i++)
		{
			String acc_bit = acc_value.substring(i, i+1);
			String reg_bit = reg_value.substring(i,i+1);
			if(acc_bit.equals("1") && reg_bit.equals("1"))
			{
				result = result + "1";
			}
			else
				result = result + "0";
		}
		acc_value = result;
	}
	
	/**
	 * Receives a string consisting of a binary value and performs a logical OR with the
	 * accumulator, storing the result back in the accumulator.
	 * 
	 * @param reg_value
	 */
	public void acc_or(String reg_value){
		String result = "";
		for(int i=0; i<8; i++)
		{
			String acc_bit = acc_value.substring(i, i+1);
			String reg_bit = reg_value.substring(i,i+1);
			if(acc_bit.equals("0") && reg_bit.equals("0"))
			{
				result = result + "0";
			}
			else
				result = result + "1";
		}
		acc_value = result;
	}
}
