package memory;

import java.util.Random;

public class Memory {
	private static final int MEM_SIZE = 256;
	
	//The memory representation, consisting of an array of a size determined by the memory's specified size.
	public String[] mem_array = new String[MEM_SIZE];
	
	//Initialize the memory and set random values for all addresses except for FA and FB, which are set to 0.
	public Memory(){
		Random rand = new Random(System.currentTimeMillis());
		for(int i=0; i<MEM_SIZE; i++){
			mem_array[i] = String.format("%02X", rand.nextInt(255));
		}
		addToMemory("FA" , "00");
		addToMemory("FB" , "00");
	}
	
	/**
	 * Add a value to memory at the specified location.
	 * 
	 * @param hex_addr
	 * @param hex_value
	 */
	public void addToMemory(String hex_addr, String hex_value)
	{
		int addr = Integer.parseInt(hex_addr, 16);
		mem_array[addr] = hex_value;
	}
	
	/**
	 * Get a value from memory from the specified address.
	 * 
	 * @param hex_addr
	 * @return The value stored in memory at the specified address.
	 */
	public String getFromMemory(String hex_addr){
		int addr = Integer.parseInt(hex_addr, 16);
		return mem_array[addr];
	}
	
}
