package accumulator;

import cpu.CPU;

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
			String acc_bit = acc_value.substring(i,i+1);
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
			String acc_bit = acc_value.substring(i,i+1);
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
	
	/**
	 * Receives a string consisting of a binary value and performs a logical XOR with the
	 * accumulator, storing the result back in the accumulator.
	 * 
	 * @param reg_value
	 */
	public void acc_xor(String reg_value){
		String result = "";
		for(int i=0; i<8; i++)
		{
			String acc_bit = acc_value.substring(i,i+1);
			String reg_bit = reg_value.substring(i,i+1);
			if(acc_bit.equals(reg_bit))
			{
				result = result + "1";
			}
			else
				result = result + "0";
		}
		acc_value = result;
	}
	
	public void acc_addc(String reg_value){
		String result = "";
		for(int i=8-1; i>0; i--)
		{
			String acc_bit = acc_value.substring(i,i+1);
			String reg_bit = reg_value.substring(i,i+1);
			//Checking the amount of bits set to 1.
			int bits_1 = 0;
			if(acc_bit.equals("1"))
				bits_1++;
			if(reg_bit.equals("1"))
				bits_1++;
			if(CPU.SR.carry.equals("1"))
				bits_1++;
			
			//If 3 bits set to 1
			if(bits_1 == 3)
			{
				result = "1" + result;
				CPU.SR.carry = "1";
			}
			//If bits set to 1 is even amount
			else if(bits_1 % 2 == 0)
			{
				result = "0" + result;
				CPU.SR.carry = "1";
			}
			//If bits set to 1 is 1. (Only other possible case)
			else
			{
				result = "1" + result;
				CPU.SR.carry = "0";
			}
		}
		//Verify whether overflow bit should be toggled.
		//Get MSBs for each value added.
		String acc_MSB = acc_value.substring(0,1);
		String reg_MSB = reg_value.substring(0,1);
		String result_MSB = result.substring(0,1);
		if(acc_MSB.equals(reg_MSB))
			if(!result_MSB.equals(acc_MSB))
				CPU.SR.overflow = "1";
	}
}
