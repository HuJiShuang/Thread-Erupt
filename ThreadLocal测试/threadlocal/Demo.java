package threadlocal;

public class Demo {

	private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
	        return new Integer(0);
	    };
	};
	
	public Integer getValue() {
		Integer value = count.get();
		value++;
		count.set(value);
		return value;
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					System.out.println(Thread.currentThread().getName()+"的值是： "+demo.getValue());
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
					System.out.println(Thread.currentThread().getName()+"的值是： "+demo.getValue());
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
					System.out.println(Thread.currentThread().getName()+"的值是： "+demo.getValue());
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
