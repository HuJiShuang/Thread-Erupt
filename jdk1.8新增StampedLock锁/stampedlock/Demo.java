package stampedlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class Demo {

	private StampedLock lock = new StampedLock();
	private int balance;
	
	//乐观锁
	public void conditionReadWrite(int value) {
		//首先判断balance值是否符合条件
		//应该先拿到读锁
		long stamp = lock.readLock();
		int c = balance;
		while(c > 0) {
			long writeStamp = lock.tryConvertToWriteLock(stamp);
			if(writeStamp!=0L) {
				stamp = writeStamp;
				balance += value;
				break;
			}
			else {
				//释放读锁
				lock.unlock(stamp);
				//获取写锁
				stamp = lock.writeLock();
			}
		}
		lock.unlock(stamp);
	}
	public void optimisticRead() {
		long stamp = lock.tryOptimisticRead();
		//执行读操作
		int c = balance;
		//进入if代码块说明此时发生了写操作，应该重读
		if(!lock.validate(stamp)) {
			long readstamp= lock.readLock();
			//再次执行读操作
			c = balance;
			stamp = readstamp;
			lock.unlockRead(stamp);
		}
	}
	public void read() {
		long stamp = lock.readLock();
		int c = balance;
		//...
		lock.unlock(stamp);
	}
	public void write(int value) {
		long stamp = lock.writeLock();
		balance += value;
		lock.unlockWrite(stamp);
	}
	public static void main(String[] args) {
		Demo demo = new Demo();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		while (true) {
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.read();
					System.out.println(Thread.currentThread().getName()+"读线程已经启动了");
					System.out.println(demo.balance);
				}
			});
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.write(1);
					System.out.println(Thread.currentThread().getName()+"写线程已经启动了");
					System.out.println(demo.balance);
				}
			});
		}
	}
}
