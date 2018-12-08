package producerconsumer.condtion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tmall {
	
	private int count;
	public final int MAX_VALUE = 10;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	public  void push() {
		lock.lock();
		if (count >= MAX_VALUE) {
			try {
				System.out.println(Thread.currentThread().getName() + "�����߿�������ﵽ���ޣ�������ֹͣ����");
				condition1.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName() + "�����������������������Ϊ��"+count);
		condition2.signal();
		lock.unlock();
	}
	public  void take() {
		lock.lock();
		if (count<=0) {
			try {
				System.out.println(Thread.currentThread().getName() + "�������Ϊ�㣬�����ߵȴ�");
				condition2.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		count--;
		System.out.println(Thread.currentThread().getName() + "������������");
		condition1.signal();
		lock.unlock();
	}
}
