package edu.mit.to_components_share_memory;

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