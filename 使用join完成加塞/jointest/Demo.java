package jointest;

public class Demo {
	
	public void a(Thread joinThread) {
		System.out.println("a������ʼִ����");
		//�����߳̿�ʼִ��
		joinThread.start();
		try {
			joinThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("a����ִ�������");
	}
	
	public void b() {
		System.out.println("b������ʼִ����");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("b����ִ�������");
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
