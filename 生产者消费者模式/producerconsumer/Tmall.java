package producerconsumer;

public class Tmall {
	
	private int count;
	public final int MAX_VALUE = 10;
	public synchronized void push() {
		if (count >= MAX_VALUE) {
			try {
				System.out.println(Thread.currentThread().getName() + "生产者库存数量达到上限，生产者停止生产");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName() + "生产者正在生产，库存数量为："+count);
		notifyAll();
	}
	public synchronized void take() {
		if (count<=0) {
			try {
				System.out.println(Thread.currentThread().getName() + "库存数量为零，消费者等待");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		count--;
		System.out.println(Thread.currentThread().getName() + "消费者消费了");
		notifyAll();
	}
}
