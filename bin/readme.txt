1.楼层类：num  UP DOWN 
2.电梯类： targetNum
3.控制器：Run
4.结果类：电梯当前楼层号，最后一次请求方式，运行时间
5.请求类：method targetNum run time 

几个重要参数:
	电梯当前层

	目标层

	请求时间
	
	运行时间

输入：(外,请求楼层，DOWN，0)	1
	 (外,请求楼层，DOWN，1)	2
	 外,3,UP,0	1 + 1 		2S	当前电梯总共运行时间
	 外,3,UP,4	1			5S	
	 内,5,T		
	

	0,3
	
输出：当前层,UP/DOWN/STILL,最后关门的时间

1.楼层类
-> input 读入请求
-> check 验证
-> toRequest 封装成对象
-> send	 发送请求给处理器

2.处理器
-> filter 过滤请求
-> addRequest 添加请求
-> 处理请求

输入提示:
	请按照题意输入输入格式必须为(FR,m,UP/DOWN,T) 或 (ER,m,t) 其他输入均为无效输入
	电梯的第一次请求必须为FR请求,且第一次的请求时刻必须为0时刻
	例：(FR,3,DOWN,0)
	否则会判错

错误提示：
	提示格式:
		ERROR
		#：错误的指令

输出格式:
	(电梯所在的当前层,运动方向,最后一次完成关门操作后的时间)
	例:(3,UP,2.0)
	


	

