package mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/**
 * ��дһ����������
 * @author Administrator
 *
 */
public class MyLock implements Lock {

	private boolean isLocked = false;
	//ʹ��������������ʵ�ֿ�������
	Thread initThread = null;
	int lockCount = 0;
	@Override
	//�˴�Ϊ��д�ķ������synchronized�ؼ���
	public synchronized void lock() {
		// TODO Auto-generated method stub
		Thread thread = Thread.currentThread();
		while(isLocked&&thread!=initThread) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isLocked = true;
		lockCount++;
		initThread = thread;
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void unlock() {
		// TODO Auto-generated method stub
		Thread thread = Thread.currentThread();
		if (initThread == thread) {
			lockCount--;
			if (lockCount == 0) {
				notify();
				isLocked = false;
			}
		}	
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
