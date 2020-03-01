
public class CountThread extends Thread {
	private String preamble = "";

	public CountThread(int tnum) {
		super("t"+tnum);
		System.out.println("Starting thread " + this.getName());
		preamble = new String(new char[tnum]).replace("\0", "\t");
	}

	private void mySleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) { }
	}

	public void run() {
		for( int i = 0; i < 10; i++ ) {
			mySleep(500);  // milliseconds
			System.out.println(preamble + this.getName() + ":" + i );
		}
	}

	public static void main(String[] args) {
		Thread t1 = new CountThread(1);
		Thread t2 = new CountThread(2);
		Thread t3 = new CountThread(3);
		Thread t4 = new CountThread(4);
		t1.start(); t2.start(); t3.start(); t4.start();
	}
}
