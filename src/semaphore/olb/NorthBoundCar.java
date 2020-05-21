/**
 * @author Daniel Gomes, John Gomes and Robert Larrivee
 * CIS 481 Problem Set 3 Problem 3-5
 * NorthBoundCar.java
 */

package semaphore.olb;

import java.util.concurrent.Semaphore;

public class NorthBoundCar extends Thread {
	private int i;

	public NorthBoundCar(int i) {
		this.i = i;
	}

	private void NorthboundCross(int i, String s) {
		System.out.println(" **NorthBoundCar[" + i + "] is crossing");
		for (int j = 0; j < 10; j++) {
			System.out.print("N" + i + ".");
			try {
				sleep(500);
			} catch (InterruptedException e) {
			}
			;
		}
		System.out.println(" **NorthBoundCar[" + i + "] done");
	}

	public void run() {
		do {
			P(Bridge.e);
			if (Bridge.nS > 0) {
				Bridge.dn = Bridge.dn + 1; 
				V(Bridge.e);
				P(Bridge.n);
			}
			Bridge.nN = Bridge.nN + 1;
			SIGNAL();
			NorthboundCross(i, "Northbound car crossing"); // crossing
			P(Bridge.e);
			Bridge.nN = Bridge.nN - 1;
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
