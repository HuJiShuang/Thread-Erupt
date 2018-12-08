package producerconsumer;

public class TakeTarget implements Runnable{
	
	private Tmall tmall;
	public TakeTarget(Tmall tmall) {
		// TODO Auto-generated constructor stub
		this.tmall = tmall;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			tmall.take();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
