package automic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class Sequence {
	//原子更新基本类型演示
	private AtomicInteger value = new AtomicInteger(0);
	
	//原子更新数组演示
	private int[] s = {1,2,3,4,5};
	private AtomicIntegerArray array = new AtomicIntegerArray(s);
	
	//原子更新抽象类型演示
	private AtomicReference<User> reference = new AtomicReference<>();
	
	//原子更新字段演示
	private AtomicIntegerFieldUpdater<User> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
	
	
	
	public int getNext() {
		//return value.getAndDecrement();//相当于value--
		array.getAndIncrement(2);
		array.addAndGet(2, 3);
		//原子更新字段演示
		User user = new User();
		fieldUpdater.getAndIncrement(user);
		System.out.println(user.getAge());
		return value.getAndAdd(10);
	}	
	
	public static void main(String[] args) {
		
		Sequence sequence = new Sequence();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.println(Thread.currentThread().getName()+" "+sequence.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					System.out.println(Thread.currentThread().getName()+" "+sequence.getNext());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		}).start();
	}
}

