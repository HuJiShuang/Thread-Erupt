package aqs.maker;

public class Sequence2 {

	private MyLock2 myLock2 = new MyLock2();
	public void a() {
		myLock2.lock();
		System.out.println("a");
		b();
		myLock2.unlock();
	}
	public void b() {
		myLock2.lock();
		System.out.println("b");
		myLock2.unlock();
	}
	public static void main(String[] args) {
		Sequence2 sequence2 = new Sequence2();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sequence2.a();
			}
		}).start();
	}
}
