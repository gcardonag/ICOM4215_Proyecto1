package cpu;

import java.util.Scanner;

import memory.Memory;
import accumulator.Accumulator;

public class CPU {
	
	public static StatusRegister SR = new StatusRegister();
	
	// Registers
	public static String R0, R1, R2, R3, R4, R5, R6, R7;
	public static String PC = "00000000";
	public static Accumulator acc;
	public static Memory mem;
	public static String IR = "0000000000000000";
	public static String VBuff = "00000000";
	public static String v0Stack = "0000000000000000";
	public static String v1Stack = "0000000000000000";
	public static int v0StackSize = 0;
	public static int v1StackSize = 0;
	public static boolean running = true;
	
	public CPU(Scanner scan)
	{
		R0 = "00000000";
		R1 = "00000000";
		R2 = "00000000";
		R3 = "00000000";
		R4 = "00000000";
		R5 = "00000000";
		R6 = "00000000";
		R7 = "00000000";
		acc = new Accumulator();
		mem = new Memory();
		int addr_counter = 0;
		while(scan.hasNext())
		{
			String hex_addr = Integer.toHexString(addr_counter);
			String hex_addr_1 = Integer.toHexString(addr_counter+1);
			while(hex_addr.length() < 2)
			{
				hex_addr = "0" + hex_addr;
				hex_addr_1 = "0" + hex_addr_1;
			}
			String hex_values = scan.next();
			mem.addToMemory(hex_addr, hex_values.substring(0,2));
			mem.addToMemory(hex_addr_1, hex_values.substring(2,4));
			addr_counter = addr_counter + 2;
		}
	}
	
	public void run()
	{
		while(running)
			step();
	}
	
	public void step()
	{
		if(!running)
			return;
		String PC_hex = Integer.toHexString(Integer.parseInt(PC, 2));
		String PC1 = Integer.toHexString(Integer.parseInt(PC, 2)+1);
		String PC_next = String.format("%8s", Integer.toBinaryString(Integer.parseInt(PC, 2)+2)).replace(" ", "0");
		String hex_value = mem.getFromMemory(PC_hex) + mem.getFromMemory(PC1);
		PC = PC_next;
		String bin_value = String.format("%16s", Integer.toBinaryString(Integer.parseInt(hex_value, 16))).replace(" ", "0");
		IR = bin_value;
		int opcode = Integer.parseInt(bin_value.substring(0, 5), 2);
		switch(opcode){
			case(0):
				acc.acc_and(bin_value.substring(5,8));
				break;
			case(1):
				acc.acc_or(bin_value.substring(5,8));
				break;
			case(2):
				acc.acc_xor(bin_value.substring(5,8));
				break;
			case(3):
				acc.acc_addc(bin_value.substring(5,8));
				break;
			case(4):
				acc.acc_sub(bin_value.substring(5,8));
				break;
			case(5):
				vadd();
				break;
			case(6):
				acc.acc_neg();
				break;
			case(7):
				acc.acc_not();
				break;
			case(8):
				acc.acc_rlc();
				break;
			case(9):
				acc.acc_rrc();
				break;	
			case(10):
				acc.acc_lda_rf(bin_value.substring(5,8));
				break;
			case(11):
				acc.acc_sta_rf(bin_value.substring(5,8));
				break;
			case(12):
				acc.acc_lda_addr(hex_value.substring(2,4));
				break;
			case(13):
				acc.acc_sta_addr(hex_value.substring(2,4));
				break;
			case(14):
				acc.acc_ldi(hex_value.substring(2,4));
				break;
			case(16):
				brz();
				break;
			case(17):
				brc();
				break;
			case(18):
				brn();
				break;
			case(19):
				bro();
				break;
			case(31):
				stop();
				break;
			case(24):
				break;
			case(20):
				v0push();
				break;
			case(21):
				v1push();
				break;
			case(22):
				v0pop(bin_value.substring(5,8));
				break;
			case(23):
				v1pop(bin_value.substring(5,8));
				break;
			
		}
	}
	
	/**
	 * Stores an ascii value input by a user in its designated location in memory.
	 * 
	 * @param ascii_value
	 */
	public void store_input_key(int ascii_value){
		String hex_value = Integer.toHexString(ascii_value).toUpperCase();
		while(hex_value.length() < 4)
			hex_value = "0"+hex_value;
		mem.addToMemory("FB", hex_value.substring(0,2));
		mem.addToMemory("FA", hex_value.substring(2,4));
	}
	
	/**
	 * Adds the top of stack and second of stack for both v0 and v1 vector stacks. The v1 vector stack is stored in the vector buffer.
	 * The v0 vector stack is stored in the accumulator. These values will be stored in register 0 temporarily to use existing accumulator 
	 * instructions, while the value for the register will be stored in temporary memory.
	 * 
	 * @param register_num_bin
	 */
	private void vadd()
	{
		String temp = CPU.R0;
		v1pop("000");
		acc.acc_lda_rf("000");
		v1pop("000");
		acc.acc_addc("000");
		CPU.VBuff = acc.acc_value;
		v0pop("000");
		acc.acc_lda_rf("000");
		v0pop("000");
		acc.acc_addc("000");
		CPU.R0 = temp;
	}
	
	/**
	 * Pushes the binary value to the top of the v0 vector stack.
	 * 
	 * @param bin_value
	 */
	private void v0push(){
		if(v0StackSize == 2)
			return;
		v0Stack = v0Stack.substring(8);
		v0Stack = acc.acc_value + v0Stack;
		v0StackSize++;
	}
	
	/**
	 * Pops the top of the v0 vector stack, storing the resulting value in the specified register.
	 * 
	 * @param register_num_bin
	 */
	private void v0pop(String register_num_bin){
		//if(v0StackSize == 0)
			//return;
		String temp = v0Stack.substring(0, 8);
		v0Stack = v0Stack.substring(8) + "00000000";
		int register_num = Integer.parseInt(register_num_bin, 2);
		switch(register_num)
		{
			case(0):
				CPU.R0 = temp;
				break;
			case(1):
				CPU.R1 = temp;
				break;
			case(2):
				CPU.R2 = temp;
				break;
			case(3):
				CPU.R3 = temp;
				break;
			case(4):
				CPU.R4 = temp;
				break;
			case(5):
				CPU.R5 = temp;
				break;
			case(6):
				CPU.R6 = temp;
				break;
			case(7):
				CPU.R7 = temp;
				break;
		}
	}
	
	/**
	 * Pushes the binary value to the top of the v1 vector stack.
	 * 
	 * @param bin_value
	 */
	private void v1push(){
		if(v1StackSize == 2)
			return;
		v1Stack = v1Stack.substring(8);
		v1Stack = acc.acc_value + v1Stack;
		v1StackSize++;
	}
	
	/**
	 * Pops the top of the v1 vector stack, storing the resulting value in the specified register.
	 * 
	 * @param register_num_bin
	 */
	private void v1pop(String register_num_bin){
		//if(v1StackSize == 0)
			//return;
		String temp = v0Stack.substring(0, 8);
		v0Stack = v0Stack.substring(8) + "00000000";
		int register_num = Integer.parseInt(register_num_bin, 2);
		switch(register_num)
		{
			case(0):
				CPU.R0 = temp;
				break;
			case(1):
				CPU.R1 = temp;
				break;
			case(2):
				CPU.R2 = temp;
				break;
			case(3):
				CPU.R3 = temp;
				break;
			case(4):
				CPU.R4 = temp;
				break;
			case(5):
				CPU.R5 = temp;
				break;
			case(6):
				CPU.R6 = temp;
				break;
			case(7):
				CPU.R7 = temp;
				break;
		}
		
	}
	
	/**
	 * Verifies if the status register's zero flag is set.
	 * If it is, the program counter is set to the value stored in register 7.
	 */
	private void brz()
	{
		if(SR.zero.equals("1"))
			PC = R7;
	}
	
	/**
	 * Verifies if the status register's carry flag is set.
	 * If it is, the program counter is set to the value stored in register 7.
	 */
	private void brc()
	{
		if(SR.carry.equals("1"))
			PC = R7;
	}
	
	/**
	 * Verifies if the status register's negative flag is set.
	 * If it is, the program counter is set to the value stored in register 7.
	 */
	private void brn()
	{
		if(SR.negative.equals("1"))
			PC = R7;
	}
	
	/**
	 * Verifies if the status register's overflow flag is set.
	 * If it is, the program counter is set to the value stored in register 7.
	 */
	private void bro()
	{
		if(SR.overflow.equals("1"))
			PC = R7;
	}
	
	/**
	 * Sets the CPU's running flag to false, preventing any further running or stepping by it.
	 */
	private void stop()
	{
		running = false;
	}
}
