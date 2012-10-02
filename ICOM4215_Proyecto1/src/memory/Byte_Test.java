package memory;

import java.util.Random;

public class Byte_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*byte bytest = (byte) 000;
		System.out.println(bytest);*/
		//Binary Literals require Java 1.7 (We are using Java 1.6 so this is not viable),
		//using Strings instead.
		//See: http://stackoverflow.com/questions/8464808/bitwise-operators-java
		
		System.out.println(Integer.toHexString(Integer.parseInt("11101111", 2)).toUpperCase());
		System.out.println(Integer.toBinaryString(Integer.parseInt("EF",16)));
		
		Random rand = new Random(System.currentTimeMillis());
		for(int i=0; i<10; i++){
			System.out.println(rand.nextInt(255));
		}
	}
}
