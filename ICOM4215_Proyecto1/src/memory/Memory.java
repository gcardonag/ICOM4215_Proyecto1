package memory;

import java.util.Random;

public class Memory {
	private static final int MEM_SIZE = 255;
	
	private String[] mem_array = new String[MEM_SIZE];
	private boolean[] mem_usage = new boolean[MEM_SIZE];
	
	public Memory(){
		Random rand = new Random(System.currentTimeMillis());
		for(int i=0; i<MEM_SIZE; i++){
			mem_array[i] = String.format("%02X", rand.nextInt(255));
			//System.out.println(mem_array[i]);
		}
	}
	
	public void addToMemory(String hex_value) throws NoFreeMemoryException
	{
		//Doing a simple search throughout the array for two concurrent free memory locations
		for(int i=0; i<MEM_SIZE-1; i++)
			if(!mem_usage[i] && !mem_usage[i+1])
			{
				mem_array[i]=hex_value.substring(0,2);
				mem_array[i+1]=hex_value.substring(2,4);
				mem_usage[i]=true;
				mem_usage[i+1]=true;
				break;
			}
		//If no free concurrent locations are found, return an exception
		throw new NoFreeMemoryException();
	}
	
	public String getFromMemory(String hex_value){
		//TODO: Check how much is going to be retrieved from memory at a given time.
		return null;
	}
	
}
