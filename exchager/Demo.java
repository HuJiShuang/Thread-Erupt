package exchager;

import java.util.concurrent.Exchanger;

public class Demo {

	public void method1(Exchanger<String> exchanger) {
		System.out.println("method1�������ڱ�ִ��");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String res = "12345";
		try {
			System.out.println("method1ͣ�����˵ȴ���method2��������");
			exchanger.exchange(res);
			System.out.println("һ���ǽ�������ˣ���������method1����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���ˣ���method1�������Լ���ִ����");
		
	}
	public void method2(Exchanger<String> exchanger) {
		System.out.println("method2�������ڱ�ִ��");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String res = "12345";
		try {
			System.out.println("method2ͣ�����˵ȴ���method1��������");
			String result = exchanger.exchange(res);
			System.out.println(result.equals(res));
			System.out.println("һ���ǽ�������ˣ���������method2����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���ˣ���method2�������Լ���ִ����");
	}
	public static void main(String[] args) {
		Demo demo = new Demo();
		Exchanger<String> exchanger = new Exchanger<>(); 
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				demo.method1(exchanger);
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				demo.method2(exchanger);
			}
		}).start();
	}
}