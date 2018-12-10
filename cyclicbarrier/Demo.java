package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo {

	Random random = new Random();
	public void meeting(CyclicBarrier barrier) {
		System.out.println(Thread.currentThread().getName()+"�Ѿ����������");
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"��ʼ����");
	}
	public static void main(String[] args) {
		Demo demo = new Demo();
		CyclicBarrier barrier = new CyclicBarrier(10,new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("��Ҷ����ˣ������ǿ�ʼ�����");
			}
		});
		for(int i=1;i<=10;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.meeting(barrier);
				}
			}).start();
		}
	}
}
