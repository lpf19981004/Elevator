package com.lscw.domain;

public class Elevator {
		
	private static Elevator elevator;
	
	private int FloorNum=1;
	
	private boolean isRun;
	
	private double totalRunTime;
	
	
	
	
	public double getTotalRunTime() {
		return totalRunTime;
	}

	public void setTotalRunTime(double totalRunTime) {
		this.totalRunTime = totalRunTime;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public int getFloorNum() {
		return FloorNum;
	}

	public void setFloorNum(int floorNum) {
		FloorNum = floorNum;
	}
	
	
	private Elevator() {
		super();
	}

	public static Elevator getElevator()
	{
		if(elevator==null)
		{
			elevator = new Elevator();
		}
		return elevator;
	}
}
