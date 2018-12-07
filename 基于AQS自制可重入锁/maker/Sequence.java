package aqs.maker;



public class Sequence {

	private int value;
	MyLock2 myLock2 = new MyLock2();
	public int getNext() {
		myLock2.lock();
		try {
			return value++;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}finally {
			myLock2.unlock();
		}
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
