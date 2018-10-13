package com.lscw.domain;

public class Request {
	
	private boolean isFR;
	private int targetNum;
	private boolean isUP;
	private double time;
	private int currentNum;
	
	
	
	public Request(boolean isFR, int targetNum, boolean isUP, double time) {
		super();
		this.isFR = isFR;
		this.targetNum = targetNum;
		this.isUP = isUP;
		this.time = time;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public boolean isFR() {
		return isFR;
	}
	public void setFR(boolean isFR) {
		this.isFR = isFR;
	}
	
	public int getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}
	public boolean isUP() {
		return isUP;
	}
	public void setUP(boolean isUP) {
		this.isUP = isUP;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public Request() {
		super();
	}
	@Override
	public String toString() {
		return "FRRequest [isFR=" + isFR + ", tagetNum=" + targetNum + ", isUP=" + isUP + ", time=" + time
				+ ", currentNum=" + currentNum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isFR ? 1231 : 1237);
		result = prime * result + (isUP ? 1231 : 1237);
		result = prime * result + targetNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (isFR != other.isFR)
			return false;
		if (isUP != other.isUP)
			return false;
		if (targetNum != other.targetNum)
			return false;
		return true;
	}
	
	
}
