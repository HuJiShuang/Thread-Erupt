package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Demo {
	public static void main(String[] args) {
		//1.固定长度的线程池
		//ExecutorService pool = Executors.newFixedThreadPool(10);
		//2.当有任务时才会创建线程，任务结束后会销毁线程，线程的最大值为Integer.MAX_VALUE
		//ExecutorService pool = Executors.newCachedThreadPool();
		//3.只有一个线程会执行此任务
		//ExecutorService pool = Executors.newSingleThreadExecutor(new ThreadFactory() {
		//
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
			
		while (true) {
			pool.schedule(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+"正在执行");
				}
			}, 2, TimeUnit.SECONDS);
		}
		
		
		/*while (true) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+"正在执行");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}*/
	}
}
