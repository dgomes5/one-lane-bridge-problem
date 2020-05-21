/**
 * @author Daniel Gomes, John Gomes and Robert Larrivee
 * CIS 481 Problem Set 3 Problem 3-5
 * Bridge.java
 */

package semaphore.olb;

import java.util.concurrent.Semaphore;

public class Bridge {
	static int N = 8;
	static int nN= 0; // number of northbound cars,
	static int nS = 0; // number of southbound cars
	static Semaphore e = new Semaphore(1); // controls entry to critical section
	static Semaphore n = new Semaphore(0); // used to delay northbound cars
	static Semaphore s = new Semaphore(0); // used to delay southbound cars
	static int dn = 0; // number of delayed northbound cars
	static int ds = 0; // number of delayed southbound cars


	public static void main(String[] args) {
		NorthBoundCar p[] = new NorthBoundCar[N];
		SouthBoundCar q[] = new SouthBoundCar[N];

		System.out.println("Creating 8 Northbound cars : NorthBoundCar[0], NorthBoundCar[1], and NorthBoundCar[2] ...");
		for (int i = 0; i < N; i++) {
			p[i] = new NorthBoundCar(i);
			p[i].start();
		}

		System.out.println("Creating 8 Southbound cars: SouthBoundCar[0], SouthBoundCar[1], and SouthBoundCar[2] ...");
		for (int i = 0; i < N; i++) {
			q[i] = new SouthBoundCar(i);
			q[i].start();
		}
	}
}
