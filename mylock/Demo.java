package mylock;

import java.util.concurrent.locks.Lock;

public class Demo {

	Lock lock = new MyLock();
	public void a() {
		lock.lock();
		System.out.println("a");
		b();
		lock.unlock();
	}
	public void b() {
		lock.lock();
        System.out.println("b");
        lock.unlock();
	}
	public static void main(String[] args) {
		Demo demo = new Demo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					demo.a();
				}
			}
		}).start();
	}
}
