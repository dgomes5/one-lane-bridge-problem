/**
 * @author Dr. Haiping Xu
 * Created at the CIS Department, UMass Dartmouth
 */

package semaphore.wb;

import java.util.concurrent.Semaphore;

public class Writer extends Thread {
	private int i;

	public Writer(int i) {
		this.i = i;
	}

	private void write(int i, String s) {
		System.out.println(" **Writer[" + i + "] is " + s);
		for (int j = 0; j < 10; j++) {
			System.out.print("W" + i + ".");
			try {
				sleep(500);
			} catch (InterruptedException e) {
			}
			;
		}
		System.out.println(" **Writer[" + i + "] " + s + " done");
	}

	public void run() {
		do {
			P(WB.rw); // Writing pre protocol

			write(i, "writing"); // Writing

			V(WB.rw); // Writing post protocol

			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			;

		} while (true);
	}

	void P(Semaphore s) {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void V(Semaphore s) {
		s.release();
	}
}