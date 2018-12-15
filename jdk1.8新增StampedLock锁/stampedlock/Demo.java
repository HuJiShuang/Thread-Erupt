package stampedlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class Demo {

	private StampedLock lock = new StampedLock();
	private int balance;
	
	//�ֹ���
	public void conditionReadWrite(int value) {
		//�����ж�balanceֵ�Ƿ��������
		//Ӧ�����õ�����
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
				//�ͷŶ���
				lock.unlock(stamp);
				//��ȡд��
				stamp = lock.writeLock();
			}
		}
		lock.unlock(stamp);
	}
	public void optimisticRead() {
		long stamp = lock.tryOptimisticRead();
		//ִ�ж�����
		int c = balance;
		//����if�����˵����ʱ������д������Ӧ���ض�
		if(!lock.validate(stamp)) {
			long readstamp= lock.readLock();
			//�ٴ�ִ�ж�����
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
					System.out.println(Thread.currentThread().getName()+"���߳��Ѿ�������");
					System.out.println(demo.balance);
				}
			});
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					demo.write(1);
					System.out.println(Thread.currentThread().getName()+"д�߳��Ѿ�������");
					System.out.println(demo.balance);
				}
			});
		}
	}
}
