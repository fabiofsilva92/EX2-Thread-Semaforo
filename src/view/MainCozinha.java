package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.CozinhaThread;

public class MainCozinha {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		
		
		int auxID;
		Random rnd = new Random();
		for(int i = 0; i<5; i++) {
			auxID = rnd.nextInt(50);
			//System.out.println(auxID);
			Thread cT = new CozinhaThread(auxID, semaforo);
			cT.start();
		}
		

	}

}
