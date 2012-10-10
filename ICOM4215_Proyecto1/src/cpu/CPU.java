package cpu;

import java.util.Stack;

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
	public static Stack<String> VStack0 = new Stack<String>();
	public static Stack<String> VStack1 = new Stack <String>();
	
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
		acc = new Accumulator();
		mem = new Memory();
		VStack0.push("00000000");
		VStack0.push("00000000");
		VStack1.push("00000000");
		VStack1.push("00000000");
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
	
	public void step()
	{
		String PC1 = Integer.toHexString(Integer.parseInt(PC, 2)+1);
		String PC_next = Integer.toHexString(Integer.parseInt(PC, 2)+2);
		String hex_value = mem.getFromMemory(PC) + mem.getFromMemory(PC1);
		PC = PC_next;
		String bin_value = Integer.toBinaryString(Integer.parseInt(hex_value, 16));
		int opcode = Integer.parseInt(bin_value.substring(0, 5), 2);
		switch(opcode){
			case(0):
				acc.acc_and(getRegisterValue(bin_value.substring(5,8)));
				break;
			case(1):
				acc.acc_or(getRegisterValue(bin_value.substring(5,8)));
				break;
			case(2):
				acc.acc_xor(getRegisterValue(bin_value.substring(5,8)));
				break;
			case(3):
				acc.acc_addc(getRegisterValue(bin_value.substring(5,8)));
				break;
			case(4):
				acc.acc_sub(getRegisterValue(bin_value.substring(5,8)));
				break;
			case(5):
				//VECTOR ADD
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
				acc.acc_lda_rf(getRegisterValue(bin_value.substring(5,8)));
				break;
			case(11):
				acc.acc_sta_rf(getRegisterValue(bin_value.substring(5,8)));
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
			case(15):
				brz();
				break;
			case(16):
				brc();
				break;
			case(17):
				brn();
				break;
			case(18):
				bro();
				break;
			case(19):
				stop();
				break;
			case(20):
				
		}
	}
	
	public void store_input_key(int ascii_value){
		String hex_value = Integer.toHexString(ascii_value);
		mem.addToMemory("FA", hex_value.substring(0,2));
		mem.addToMemory("FB", hex_value.substring(2,4));
	}
	
	private void vpush(){
		
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
