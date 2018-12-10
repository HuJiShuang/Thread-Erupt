package semaphore;

import java.util.concurrent.Semaphore;

public class Demo {

	public void method(Semaphore semaphore) {
		//使用acquire方法试图获得执行权限，如果此时线程大于permit，则此线程等待
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"正在执行");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//释放
		semaphore.release();
	}
	public static void main(String[] args) {
		Demo demo = new Demo();
		Semaphore semaphore = new Semaphore(10);
		int sum =100;
		while (sum-->0) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.method(semaphore);
				}
			}).start();
		}
	}
}
