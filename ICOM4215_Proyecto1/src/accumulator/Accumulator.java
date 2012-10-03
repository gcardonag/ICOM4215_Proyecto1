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
			int acc_bit = Integer.parseInt(acc_value.substring(i, i+1));
			int reg_bit = Integer.parseInt(reg_value.substring(i,i+1));
			if(acc_bit == 1 && reg_bit == 1)
			{
				result = result + "1";
			}
			else
				result = result + "0";
		}
		acc_value = result;
	}
}
