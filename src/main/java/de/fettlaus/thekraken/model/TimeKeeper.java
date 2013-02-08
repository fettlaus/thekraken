package de.fettlaus.thekraken.model;

public class TimeKeeper {
	private static long epoch = System.nanoTime()/1000;

	public static void reset() {
		epoch = System.nanoTime()/1000;
	}

	public static long time() {
		return System.nanoTime()/1000 - epoch;
	}
}
