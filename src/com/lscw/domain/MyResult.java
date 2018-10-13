package com.lscw.domain;

public class MyResult {
	
	private int currentNum;
	private String state;
	private double totalRunTime;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public double getTotalRunTime() {
		return totalRunTime;
	}
	public void setTotalRunTime(double totalRunTime) {
		this.totalRunTime = totalRunTime;
	}
	@Override
	public String toString() {
		return "("+ currentNum + "," + state + "," + totalRunTime + ")";
	}
	public MyResult() {
		super();
	}
	
}
