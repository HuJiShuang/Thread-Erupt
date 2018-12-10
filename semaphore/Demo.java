package semaphore;

import java.util.concurrent.Semaphore;

public class Demo {

	public void method(Semaphore semaphore) {
		//ʹ��acquire������ͼ���ִ��Ȩ�ޣ������ʱ�̴߳���permit������̵߳ȴ�
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"����ִ��");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�ͷ�
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
