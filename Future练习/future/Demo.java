package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<Integer> callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("开始执行任务了，类似于做蛋糕");
				Thread.sleep(4000);
				return 1;
			}
		};
		//用于FutureTask简介闪现了Runnalbe接口，所以可以FutureTask对象可以作为Thread的参数
		FutureTask<Integer> task = new FutureTask<>(callable);
		Thread thread = new Thread(task);
		thread.start();
		
		System.out.println("我先出去办点事，你做好了蛋糕我再回来");
		Integer result = task.get();
		System.out.println(result);
	}

}
