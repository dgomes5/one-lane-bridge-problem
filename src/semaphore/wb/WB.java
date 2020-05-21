/**
 * @author Dr. Haiping Xu
 * Created at the CIS Department, UMass Dartmouth
 */

package semaphore.wb;

import java.util.concurrent.Semaphore;

public class WB {
	static int N = 3;
	static int nr = 0;
	static Semaphore mutexR = new Semaphore(1);
	static Semaphore rw = new Semaphore(1);

	public static void main(String[] args) {
		Reader p[] = new Reader[N];
		Writer q[] = new Writer[N];

		System.out.println("Creating 3 readers: Reader[0], Reader[1], and Reader[2] ...");
		for (int i = 0; i < N; i++) {
			p[i] = new Reader(i);
			p[i].start();
		}

		System.out.println("Creating 3 writers: Writer[0], Writer[1], and Writer[2] ...");
		for (int i = 0; i < N; i++) {
			q[i] = new Writer(i);
			q[i].start();
		}
	}
}
