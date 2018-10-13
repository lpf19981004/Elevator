package com.lscw.elevator.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lscw.domain.Elevator;
import com.lscw.domain.MyResult;
import com.lscw.domain.Request;

public class Controller {

	LinkedList<Request> list = new LinkedList<Request>();

	//获取电梯对象
	Elevator elevator = Elevator.getElevator();

	
	List<MyResult> handleRequest() 
	{

		List<MyResult> resultList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			/*
			 * 1.循环遍历每个请求
			 * 2.处理请求并得到处理结果 
			 * 3.将返回结果保存到结果集中
			 */
			Request request = list.get(i);
			request.setCurrentNum(elevator.getFloorNum());
			MyResult result = getResult(request);
			resultList.add(result);

		}

		return resultList;
	}

	private MyResult getResult(Request request) {
		MyResult result = new MyResult();
		int currentNum = request.getCurrentNum();
		int targetNum = request.getTargetNum();
		double requestTime = request.getTime();
		double runTime = requestTime > elevator.getTotalRunTime()? 
					requestTime:elevator.getTotalRunTime();
		//如果电梯在当前层则进行一次开关门操作
		if(targetNum==currentNum) {
			this.elevator.setTotalRunTime(1+runTime);
			result.setState("STILL");
		}else {
			//电梯需要运行几层
			int cnt = Math.abs(currentNum-targetNum);
			//电梯开关门加上电梯
			this.elevator.setTotalRunTime(1+cnt*0.5+runTime);
		}
		
		this.elevator.setFloorNum(targetNum);
		result.setCurrentNum(targetNum);
		if(targetNum>currentNum)
			result.setState("UP");
		else if(targetNum<currentNum)
			result.setState("DOWN");
		result.setTotalRunTime(this.elevator.getTotalRunTime());
		return result;
	}

	void addRequest(Request request) {
		if (isCanonical(request)) {
			list.add(request);
		}
	}
	//判断是否为符合规范的请求,过滤冲突请求
	private boolean isCanonical(Request request)
	{
		Request req = null;
		if(!list.isEmpty())
			req = list.getLast();
		else
			return true;
		double runtime = Math.abs(request.getTargetNum()-req.getTargetNum())*0.5+1;
		double totalRunTime = runtime+req.getTime();
		
		if(!request.isFR()){
			if(req.getTargetNum()==request.getTargetNum())
			{
				if(totalRunTime>=request.getTime())
				{
					return false;
				}
			}
		}else if(list.contains(request))
		{
			if(totalRunTime>=request.getTime())
			{
				return false;
			}
		}
		if(req.getTime()>request.getTime())
			return false;
		
		return true;
	}
	
}
