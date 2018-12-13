package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tmall {
	
	public final int MAX_VALUE = 10;
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_VALUE);
	
	public void push() {
		try {
			queue.put(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("��ǰ���еĳ���Ϊ��"+queue.size());
	}
	public void take() {
		try {
			queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("��ǰ���еĳ���Ϊ��"+queue.size());
	}
}

