package semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Demo2 {

	public void method() {
		//使用acquire方法试图获得执行权限，如果此时线程大于permit，则此线程等待
		System.out.println(Thread.currentThread().getName()+"正在执行");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Demo2 demo = new Demo2();
		Semaphore semaphore = new Semaphore(10);
		int sum =100;
		ExecutorService threadpool = Executors.newFixedThreadPool(10);
		threadpool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				demo.method();
			}
		});
	}
}
