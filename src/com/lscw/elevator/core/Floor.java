package com.lscw.elevator.core;

import java.util.List;
import java.util.Scanner;

import com.lscw.domain.MyResult;
import com.lscw.domain.Request;

public class Floor {
	
	private Controller controller = new Controller();
 	
	private int num;
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 	楼层请求格式为：(FR, m, UP/DOWN, T)，其中FR为楼层请求标识，m为发出请求的楼层号，UP为向上请求，DOWN为向下请求，T为发出时刻。
	 * （注释：相当于请求者在楼道里的某楼层按“上行”或“下行”键） 
	 * 电梯内请求格式为：(ER, n, T)，其中ER为电梯内请求标识，n为请求前往的目标楼层号，T为发出时刻。
	 */
	Scanner scanner  = new Scanner(System.in);
	public List<MyResult> request() {
		this.input();
		while(this.controller.list.size()==0)
		{
			System.out.println("ERROR:没有可运行的指令,请重新输入");
			input();
		}
		return this.controller.handleRequest();
	}
	
	//读入请求数据
	private void input() {
		// 输入数据
		int cnt = 0;
		String strRequest = scanner.nextLine();
		cnt = cnt + 1;
		while (!"RUN".equals(strRequest)) {
			try {
				Request request = toRequest(strRequest, cnt);
				// 将当前楼层号设置给request对象
				request.setCurrentNum(this.getNum());
				this.sendRequest(request);
				// 继续读入请求
				strRequest = scanner.nextLine();
				cnt += 1;
				continue;
			} catch (ElevatorException e) {
				System.out.println("ERROR\n" + e.getMessage());
				strRequest = scanner.nextLine();
			}
		}
	}
	
	private  Request toRequest(String strRequest,int cnt) throws ElevatorException {
		String orgStr = strRequest;
		//过滤空格
		strRequest = strRequest.replace(" ", "");
		String[] regexs= {"^\\(FR,\\d{1,2},UP,\\d+\\)$",
						"^\\(FR,\\d+,DOWN,\\d+\\)$",
						"^\\(ER,\\d+,\\d+\\)$"};
		boolean flag = false;
		for (String str : regexs) {
			if(strRequest.matches(str))
			{
				flag=true;
				break;
			}
		}
		if(!flag)
			throw new ElevatorException("#："+orgStr+"指令格式有误");
		strRequest = strRequest.substring(1, strRequest.length()-1);
		//打散请求
		String[] reqs = strRequest.split(","); 
		
		//首次请求时间必须为0时刻
		if(cnt==1&&reqs.length==4&&Integer.parseInt(reqs[3])!=0){
			throw new ElevatorException("#："+orgStr+"首次请求时间必须为0时刻");
		}else if(cnt==1&&reqs.length==3&&Integer.parseInt(reqs[2])!=0){
			throw new ElevatorException("#："+orgStr+"首次请求时间必须为0时刻");
		}
		
		Request request = new Request();
		
		
		if("FR".equals(reqs[0]))
			request.setFR(true);
		else
			request.setFR(false);
		
		
		request.setTargetNum(Integer.parseInt(reqs[1]));
		
		if("UP".equals(reqs[2])) {
			request.setUP(true);
		}
		else if(reqs.length==3)
		{
			if(Integer.parseInt(reqs[1])>10||Integer.parseInt(reqs[1])<1)
			{
				throw new ElevatorException("#："+orgStr+"无效的请求楼层");
			}
			if(!request.isFR()&&cnt==1)
			{
				throw new ElevatorException("#："+orgStr+"首次请求应为电梯外请求");
			}
			request.setTime(Double.parseDouble(reqs[2]));
			return request;
		}else {
			request.setUP(false);
		}
		//判断无效的请求
		if(request.isFR())
		{
			if(request.getTargetNum()==1&&!request.isUP()){
				throw new ElevatorException("#："+orgStr+"该层已是最底层");
			}else if(request.getTargetNum()==10&&request.isUP()){
				throw new ElevatorException("#："+orgStr+"该层已是最高层");
			}
			if(Integer.parseInt(reqs[1])>10||Integer.parseInt(reqs[1])<1)
			{
				throw new ElevatorException("#："+orgStr+"无效的请求楼层");
			}
		}
		if(reqs[3].contains("-"))
			throw new ElevatorException("#"+orgStr+"无效的请求时刻");
		request.setTime(Double.parseDouble(reqs[3]));
		
		return request;
	}

	private void sendRequest(Request request) {
		
		this.controller.addRequest(request);
	}
}
