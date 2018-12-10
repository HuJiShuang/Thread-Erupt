package exchager;

import java.util.concurrent.Exchanger;

public class Demo {

	public void method1(Exchanger<String> exchanger) {
		System.out.println("method1方法正在被执行");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String res = "12345";
		try {
			System.out.println("method1停下来了等待和method2交换数据");
			exchanger.exchange(res);
			System.out.println("一定是交换完成了，继续运行method1方法");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("好了，我method1方法可以继续执行了");
		
	}
	public void method2(Exchanger<String> exchanger) {
		System.out.println("method2方法正在被执行");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String res = "12345";
		try {
			System.out.println("method2停下来了等待和method1交换数据");
			String result = exchanger.exchange(res);
			System.out.println(result.equals(res));
			System.out.println("一定是交换完成了，继续运行method2方法");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("好了，我method2方法可以继续执行了");
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