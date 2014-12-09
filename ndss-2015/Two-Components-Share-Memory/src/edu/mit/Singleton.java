package edu.mit;

public class Singleton {
    private static Singleton v;
    public String s;

    public static Singleton v() {
	if (v == null) {
	    v = new Singleton();
	}
	
	return v;
    }
}