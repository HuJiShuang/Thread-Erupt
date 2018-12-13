package blockingqueue;

public class Demo {

	public static void main(String[] args) {
		Tmall tmall = new Tmall();
		PushTarget pushTarget = new PushTarget(tmall);
		TakeTarget takeTarget = new TakeTarget(tmall);
		for(int i =1;i<=10;i++) {
			new Thread(pushTarget).start();
		}
		for(int i=1;i<=10;i++) {
			new Thread(takeTarget).start();
		}
	}
}
