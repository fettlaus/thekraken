package de.fettlaus.thekraken.model;

public class TimeKeeper {
	private static long epoch = System.nanoTime();

	public static void reset() {
		epoch = System.nanoTime();
	}

	public static long time() {
		return System.nanoTime() - epoch;
	}
}
