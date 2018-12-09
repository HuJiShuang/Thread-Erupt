package jointest;

public class Demo {
	
	public void a(Thread joinThread) {
		System.out.println("a方法开始执行了");
		//让子线程开始执行
		joinThread.start();
		try {
			joinThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("a方法执行完毕了");
	}
	
	public void b() {
		System.out.println("b方法开始执行了");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("b方法执行完毕了");
	}
	public static void main(String[] args) {
		Demo demo  = new Demo();
		Thread joinThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				demo.b();
			}
		});
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				demo.a(joinThread);
			}
		}).start();
	}
}
