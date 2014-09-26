package trab1;

public class Randomizer {
	
	public static java.util.Random generator = new java.util.Random();
	
	public static int getInt(int min, int max) {
		return generator.nextInt(max-min) + min;
	}
	
}
