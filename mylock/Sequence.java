package mylock;

import java.util.concurrent.locks.Lock;

public class Sequence {

	private int value;
	private Lock lock = new MyLock();
	public int getNext() {
		lock.lock();
		int a = value++;
		lock.unlock();
		return a;
	}
	public static void main(String[] args) {
		
		Sequence sequence  = new Sequence();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.println(sequence.getNext());
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
				System.out.println(sequence.getNext());
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