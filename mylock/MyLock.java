package mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/**
 * 编写一个可重入锁
 * @author Administrator
 *
 */
public class MyLock implements Lock {

	private boolean isLocked = false;
	//使用下面两个属性实现可重入锁
	Thread initThread = null;
	int lockCount = 0;
	@Override
	//此处为重写的方法添加synchronized关键字
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
