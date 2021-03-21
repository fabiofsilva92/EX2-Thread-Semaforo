package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class CozinhaThread extends Thread {

	public CozinhaThread(int pratoId, Semaphore semaforo) {
		this.pratoId = pratoId;
		this.semaforo = semaforo;
	}

	private int pratoId;
	private Semaphore semaforo;
	private String nomePrato;
	

	@Override
	public void run() {
		definirNome();
		cozinhar();
		try {
			semaforo.acquire();
			entregarPrato();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	public void definirNome() {
		if(pratoId%2==0) {
			nomePrato = "Lasanha a Bolonhesa";
		}
		else {
			nomePrato = "Sopa de Cebola";
		}
	}
	
	public void cozinhar() {

		int tempo;

		if (pratoId % 2 == 0) {
			String qualPrato = "Lasanha a Bolonhesa";
			System.out.println("#ID -> " + pratoId + " -> " + qualPrato);
			tempo = (int) ((Math.random() * 601) + 600);
			tempoCozinhando(tempo, qualPrato);
		} else {
			String qualPrato = "Sopa de Cebola";
			System.out.println("#ID -> " + pratoId + " -> " + qualPrato);
			tempo = (int) ((Math.random() * 301) + 500);
			tempoCozinhando(tempo, qualPrato);
		}

	}

	public void tempoCozinhando(int tempo, String qualPrato) {

		DecimalFormat df = new DecimalFormat("###.##");

		double percentual = 0;
		double aux = (double) tempo;
		// System.out.println(aux);
		aux = 10000 / aux;

		double porcentagem = aux;
		System.out.println("ID "+pratoId+" -> "+df.format(porcentagem) + "% a cada 0.1 s");

		int sleepAux = 100;
		int sleepContador = tempo;

		for (double i = 0; i <= (tempo / 100); i++) {
			percentual = percentual + porcentagem;
			try {
				sleep(sleepAux); // utilizando sleepAux com padrão de 100ms, mas verificando e garantindo que
									// laço ocorra no tempo exato randomizado.
				sleepContador = sleepContador - sleepAux;
				if (sleepContador < 100) {
					sleepAux = sleepContador;
				}
				if (percentual > 100) {
					percentual = 100;
				}
				System.out.println("#ID " + pratoId + " -> " + df.format(percentual) + "% ->" + qualPrato);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("#ID " + pratoId + " -> " + qualPrato + " Finalizado");

	}
	
	public void entregarPrato() {
		System.out.println("#ID " + pratoId + "-> Entregando prato " + nomePrato);
		try {
			sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("#ID " + pratoId + " - > ENTREGUE");

	}

}
