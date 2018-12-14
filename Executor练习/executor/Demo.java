package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Demo {
	public static void main(String[] args) {
		//1.�̶����ȵ��̳߳�
		//ExecutorService pool = Executors.newFixedThreadPool(10);
		//2.��������ʱ�Żᴴ���̣߳����������������̣߳��̵߳����ֵΪInteger.MAX_VALUE
		//ExecutorService pool = Executors.newCachedThreadPool();
		//3.ֻ��һ���̻߳�ִ�д�����
		//ExecutorService pool = Executors.newSingleThreadExecutor(new ThreadFactory() {
		//
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
			
		while (true) {
			pool.schedule(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+"����ִ��");
				}
			}, 2, TimeUnit.SECONDS);
		}
		
		
		/*while (true) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+"����ִ��");
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
