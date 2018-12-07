package aqs.maker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock2 implements Lock {
	
	private Helper helper = new Helper();
	
	static class Helper extends AbstractQueuedSynchronizer{
		@Override
		protected boolean tryAcquire(int arg) {
			// TODO Auto-generated method stub
			//如果第一个线程进来，可以拿到锁，因此返回true
			//如果第二个线程进来，则拿不到锁，返回false
			//如何判断进来的线程是当前线程还是其他线程？
			int state = getState();
			Thread thread = Thread.currentThread();
			if (state == 0) {
				if (compareAndSetState(0, arg)) {
					setExclusiveOwnerThread(thread);
					return true;
				}
			}else if (thread == getExclusiveOwnerThread()) {
					setState(state+1);
					return true;
			}
			return false;
		}
		@Override
		protected boolean tryRelease(int arg) {
			// TODO Auto-generated method stub
			//锁的获取和释放是一一对应的
			if (Thread.currentThread()!=getExclusiveOwnerThread()) {
				throw new RuntimeException();
			}
			int state = getState() - arg;
			boolean flag = false;
			if (state == 0) {
				setExclusiveOwnerThread(null);
				flag = true;
			}
			setState(state);
			return flag;
		}
		Condition newCondition() {
			return new ConditionObject();
		}
	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		helper.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		helper.acquireInterruptibly(1);
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return helper.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return helper.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		helper.release(1);
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return helper.newCondition();
	}
}
