/**
 * @author Daniel Gomes, John Gomes and Robert Larrivee
 * CIS 481 Problem Set 3 Problem 3-5
 * SouthBoundCar.java
 */

package semaphore.olb;

import java.util.concurrent.Semaphore;

public class SouthBoundCar extends Thread {
	private int i;

	public SouthBoundCar(int i) {
		this.i = i;
	}

	private void SouthBoundCross(int i, String s) {
		System.out.println(" **SouthBoundCar[" + i + "] is crossing");
		for (int j = 0; j < 10; j++) {
			System.out.print("S" + i + ".");
			try {
				sleep(500);
			} catch (InterruptedException e) {
			}
			;
		}
		System.out.println(" **SouthBoundCar[" + i + "] done");
	}

	public void run() {
		do {
			P(Bridge.e);
			if (Bridge.nN > 0) {
				Bridge.ds = Bridge.ds + 1; 
				V(Bridge.e);
				P(Bridge.s);
			}
			Bridge.nS = Bridge.nS + 1;
			SIGNAL();
			SouthBoundCross(i, "Southbound car crossing"); // crossing
			P(Bridge.e);
			Bridge.nS = Bridge.nS - 1;
			SIGNAL();
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
	
	void SIGNAL() {
		if (Bridge.nS == 0 && Bridge.dn > 0) {
			Bridge.dn = Bridge.dn - 1; V(Bridge.n); 
		}
		else if (Bridge.nN == 0 && Bridge.ds > 0) {
			Bridge.ds = Bridge.ds - 1; V(Bridge.s); 
		}
	 	else V(Bridge.e);
	}
}
