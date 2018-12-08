package producerconsumer;

public class PushTarget implements Runnable{

	private Tmall tmall;
	public PushTarget(Tmall tmall) {
		// TODO Auto-generated constructor stub
		this.tmall = tmall;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			tmall.push();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
}
