package test;

import java.util.List;

import com.lscw.domain.MyResult;
import com.lscw.elevator.core.Floor;

public class myTest {
	public static void main(String[] args) {
		Floor floor = new Floor();
		List<MyResult> result = floor.request();
		if(result!=null)
			for (MyResult myResult : result) {
				System.out.println(myResult);
			}
	}
}
