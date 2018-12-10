package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<Integer> callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("��ʼִ�������ˣ�������������");
				Thread.sleep(4000);
				return 1;
			}
		};
		//����FutureTask���������Runnalbe�ӿڣ����Կ���FutureTask���������ΪThread�Ĳ���
		FutureTask<Integer> task = new FutureTask<>(callable);
		Thread thread = new Thread(task);
		thread.start();
		
		System.out.println("���ȳ�ȥ����£��������˵������ٻ���");
		Integer result = task.get();
		System.out.println(result);
	}

}
