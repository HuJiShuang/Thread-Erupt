package producerconsumer;

public class Tmall {
	
	private int count;
	public final int MAX_VALUE = 10;
	public synchronized void push() {
		if (count >= MAX_VALUE) {
			try {
				System.out.println(Thread.currentThread().getName() + "�����߿�������ﵽ���ޣ�������ֹͣ����");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName() + "�����������������������Ϊ��"+count);
		notifyAll();
	}
	public synchronized void take() {
		if (count<=0) {
			try {
				System.out.println(Thread.currentThread().getName() + "�������Ϊ�㣬�����ߵȴ�");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		count--;
		System.out.println(Thread.currentThread().getName() + "������������");
		notifyAll();
	}
}
