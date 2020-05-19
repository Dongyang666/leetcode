/**
 *
 * @author liudongyang
 */
public class DeadLock {
	static Object o1 = new Object();
	static Object o2 = new Object();

	public static void main(String[] args) {

		new Thread(() -> {
			while (true) {
				synchronized (o1) {
					synchronized (o2) {
						System.out.println("1");
					}
				}
			}
		}).start();

		new Thread(() -> {
			while (true) {
				synchronized (o2) {
					synchronized (o1) {
						System.out.println("2");
					}

				}
			}
		}).start();
	}
}
