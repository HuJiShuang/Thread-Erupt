package forkandjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Demo extends RecursiveTask<Integer>  {

	private int begin;
	private int end;
	public Demo(int begin,int end) {
		// TODO Auto-generated constructor stub
		this.begin = begin;
		this.end = end;
	}
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		//拆分任务
		int sum = 0;
		if((end - begin) <= 2) {
			for(int i =begin;i<=end;i++) {
				sum += i;
			}
		}	
		else {
			//拆分
			Demo demo1 = new Demo(begin, (begin+end)/2);
			Demo demo2 = new Demo((begin+end)/2+1,end);
			//执行任务
			demo1.fork();
			demo2.fork();
			//合并结果
			Integer a = demo1.join();
			Integer b = demo2.join();
			sum = a + b;
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> future = pool.submit(new Demo(1,100));
		System.out.println("我们可以去干别的东西去了");
		System.out.println("计算的值为："+ future.get());	
	}
}
