package accumulator;

import cpu.CPU;

public class Accumulator {
	public String acc_value;
	
	public Accumulator() {
		acc_value = "00000000";
	}
	
	/**
	 * Receives a string consisting of a binary value and performs a logical AND with the
	 * accumulator, storing the result back in the accumulator.
	 * 
	 * @param reg_value
	 */
	public void acc_and(String register_num_bin){
		String reg_value = getRegisterValue(register_num_bin);
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
		set_CPU_flags();
	}
	
	/**
	 * Receives a string consisting of a binary value and performs a logical OR with the
	 * accumulator, storing the result back in the accumulator.
	 * 
	 * @param reg_value
	 */
	public void acc_or(String register_num_bin){
		String reg_value = getRegisterValue(register_num_bin);
		String result = "";
		for(int i=0; i<8; i++)
		{
			String acc_bit = Character.toString(acc_value.charAt(i));
			String reg_bit = Character.toString(reg_value.charAt(i));
			if(acc_bit.equals("0") && reg_bit.equals("0"))
			{
				result = result + "0";
			}
			else
				result = result + "1";
		}
		acc_value = result;
		set_CPU_flags();
	}
	
	/**
	 * Receives a string consisting of a binary value and performs a logical XOR with the
	 * accumulator, storing the result back in the accumulator.
	 * 
	 * @param reg_value
	 */
	public void acc_xor(String register_num_bin){
		String reg_value = getRegisterValue(register_num_bin);
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
		set_CPU_flags();
	}
	
	public void acc_addc(String register_num_bin){
		String reg_value = getRegisterValue(register_num_bin);
		String result = "";
		for(int i=8; i>0; i--)
		{
			String acc_bit = acc_value.substring(i-1,i);
			String reg_bit = reg_value.substring(i-1,i);
			//Checking the amount of bits set to 1.
			int bits_1 = 0;
			if(acc_bit.equals("1"))
				bits_1++;
			if(reg_bit.equals("1"))
				bits_1++;
			if(CPU.SR.carry.equals("1"))
				bits_1++;
			//System.out.println(acc_value+"L");
			//System.out.println(reg_value+"L");
			//System.out.println(bits_1 + "L");
			
			//If 3 bits set to 1
			if(bits_1 == 3)
			{
				result = "1" + result;
				CPU.SR.carry = "1";
			}
			//If bits set to 1 is even amount
			else if(bits_1 == 2)
			{
				result = "0" + result;
				CPU.SR.carry = "1";
			}
			//If bits set to 1 is 1. (Only other possible case)
			else if(bits_1 == 1)
			{
				result = "1" + result;
				CPU.SR.carry = "0";
			}
			else
			{
				result = "0" + result;
				CPU.SR.carry = "0";
			}
		}
		
		acc_value = result;
		
		//Verify whether overflow bit should be toggled.
		//Get MSBs for each value added.
		String acc_MSB = acc_value.substring(0,1);
		String reg_MSB = reg_value.substring(0,1);
		String result_MSB = result.substring(0,1);
		if(acc_MSB.equals(reg_MSB))
			if(!result_MSB.equals(acc_MSB))
				CPU.SR.overflow = "1";
		set_CPU_flags();
	}
	
	/**
	 * Gets the value stored in a register, calculates its 2's complement
	 * and adds it to the accumulator. 
	 * @param reg_value The value stored in the register
	 */
	public void acc_sub (String reg_value) {
		
		// Calculate the two's complement of the number
		String temp = "";
		boolean oneFound = false;
		for (int i = reg_value.length(); i >= 0; i++) {
			if (!oneFound) {
				if (reg_value.charAt(i) == '0') {
					temp = "0" + temp;
				}
				if (reg_value.charAt(i) == '1') {
					temp = "1" + temp;
					oneFound = true;
				}
			}
			else {
				if (reg_value.charAt(i) == '0') {
					temp = "1" + temp;
				}
				else if (reg_value.charAt(i) == '1') {
					temp = "0" + temp;
				}
			}
			
		}
	
		// Now add bit-wise the number in temp, which is the two's complement
		// of the value of the register, to the accumulator
		acc_addc (temp);
	}
	
	/**
	 * Calculates the two's complement of the value of the accumulator
	 */
	public void acc_neg () {
		String temp = "";
		boolean oneFound = false;
		for (int i = acc_value.length()-1; i >= 0; i--) {
			if (!oneFound) {
				if (acc_value.charAt(i) == '0') {
					temp = "0" + temp;
				}
				if (acc_value.charAt(i) == '1') {
					temp = "1" + temp;
					oneFound = true;
				}
			}
			else {
				if (acc_value.charAt(i) == '0') {
					temp = "1" + temp;
				}
				else if (acc_value.charAt(i) == '1') {
					temp = "0" + temp;
				}
			}
		}
		acc_value = temp;
		set_CPU_flags();
	}
	
	/**
	 * Performs a logical bit-wise NOT operation on the accumulator
	 */
	public void acc_not () {
		String temp = "";
		for (int i = 0; i < acc_value.length(); i++) {
			if (acc_value.charAt(i) == '0') {
				temp = "1" + temp;
			}
			else if (acc_value.charAt(i) == '1') {
				temp = "0" + temp;
			}
		}
		acc_value = temp;
		set_CPU_flags();
	}
	
	/**
	 * Rotate the bits of the value of the accumulator one at a time to the left
	 * using the carry
	 */
	public void acc_rlc () {
		char msb = acc_value.charAt(0);
		acc_value = acc_value.substring(1) + CPU.SR.carry;
		CPU.SR.carry = Character.toString(msb);
		set_CPU_flags();
	}
	
	/**
	 * Rotates the bits of the value of the accumulator one at a time 
	 * to the right using the carry
	 */
	public void acc_rrc () {
		char lsb = acc_value.charAt(7);
		acc_value = acc_value.substring(0, 6);
		acc_value = CPU.SR.carry + acc_value;
		CPU.SR.carry = Character.toString(lsb);
		set_CPU_flags();
	}
	
	/**
	 * Receives a string that contains a register whose value to put in the accumulator
	 * @param reg_value
	 */
	public void acc_lda_rf (String reg_num_bin) {
		int register_num = Integer.parseInt(reg_num_bin, 2);
		switch(register_num)
		{
			case(0):
				acc_value = CPU.R0;
				break;
			case(1):
				acc_value = CPU.R1;
				break;
			case(2):
				acc_value = CPU.R2;
				break;
			case(3):
				acc_value = CPU.R3;
				break;
			case(4):
				acc_value = CPU.R4;
				break;
			case(5):
				acc_value = CPU.R5;
				break;
			case(6):
				acc_value = CPU.R6;
				break;
			case(7):
				acc_value = CPU.R7;
				break;
		}
		set_CPU_flags();
	}
	
	/**
	 * Receives a string that contains a value to put in the accumulator
	 * @param mem_addr
	 */
	public void acc_lda_addr (String mem_addr) {
		acc_value = String.format("%8s", Integer.toBinaryString(Integer.parseInt(CPU.mem.getFromMemory(mem_addr),16))).replace(" ", "0");
		set_CPU_flags();
	}
	
	/**
	 * Receives a string that contains a register to store the accumulator's current value.
	 * @param register_num_bin
	 */
	public void acc_sta_rf(String register_num_bin) {
		int register_num = Integer.parseInt(register_num_bin, 2);
		switch(register_num)
		{
			case(0):
				CPU.R0 = acc_value;
				break;
			case(1):
				CPU.R1 = acc_value;
				break;
			case(2):
				CPU.R2 = acc_value;
				break;
			case(3):
				CPU.R3 = acc_value;
				break;
			case(4):
				CPU.R4 = acc_value;
				break;
			case(5):
				CPU.R5 = acc_value;
				break;
			case(6):
				CPU.R6 = acc_value;
				break;
			case(7):
				CPU.R7 = acc_value;
				break;
		}
		set_CPU_flags();
	}
	
	/**
	 * Store accumulator value in memory.
	 * @param mem_addr
	 */
	public void acc_sta_addr(String mem_addr)
	{
		CPU.mem.addToMemory(mem_addr, Integer.toHexString(Integer.parseInt(acc_value, 2)));
	}
	
	/**
	 * Load a specified value into the accumulator.
	 * @param hex_value
	 */
	public void acc_ldi(String hex_value)
	{
		acc_value = String.format("%8s", Integer.toBinaryString(Integer.parseInt(hex_value, 16))).replace(" ", "0");
		set_CPU_flags();
	}
	
	private String getRegisterValue(String register_num_bin)
	{
		int register_num = Integer.parseInt(register_num_bin, 2);
		switch(register_num)
		{
			case(0):
				return CPU.R0;
			case(1):
				return CPU.R1;
			case(2):
				return CPU.R2;
			case(3):
				return CPU.R3;
			case(4):
				return CPU.R4;
			case(5):
				return CPU.R5;
			case(6):
				return CPU.R6;
			case(7):
				return CPU.R7;
			default:
				return "ERROR";
		}
	}
	
	/**
	 * Set the zero and negative flags based on the current value of the accumulator.
	 */
	private void set_CPU_flags()
	{	
		if(acc_value.equals("00000000"))
			CPU.SR.zero = "1";
		else
			CPU.SR.zero = "0";
		
		if(acc_value.startsWith("1"))
			CPU.SR.negative = "1";
		else
			CPU.SR.negative = "0";	
	}
}
