package com.example.network_reflection;

public class ConcreteClass extends BaseClass {
	
	public void setLine1(String s){
		this.line1 = s;
	}

	public String getLine1(){
		return line1;
	}
	
	public String getUncritical() {
		return "No leak";
	}
}
