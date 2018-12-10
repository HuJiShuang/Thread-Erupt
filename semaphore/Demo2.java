package semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Demo2 {

	public void method() {
		//ʹ��acquire������ͼ���ִ��Ȩ�ޣ������ʱ�̴߳���permit������̵߳ȴ�
		System.out.println(Thread.currentThread().getName()+"����ִ��");
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
