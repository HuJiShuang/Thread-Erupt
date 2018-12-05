package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {

	private int value;
	private Lock lock = new ReentrantLock();
	public int getNext() {
		//使用lock接口实现线程安全
		lock.lock();
		int a =value++;
		lock.unlock();
		return a;
	}
	public static void main(String[] args) {
		Sequence sequence = new Sequence();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.println(Thread.currentThread().getName()+" "+sequence.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.println(Thread.currentThread().getName()+" "+sequence.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
