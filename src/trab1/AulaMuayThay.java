package trab1;

import java.util.concurrent.Semaphore;

import javax.sql.rowset.spi.SyncResolver;

public class AulaMuayThay implements Runnable {

	public boolean fechada = false;
	private int alunos = 0;
	private int maxAlunos = 10;
	private boolean lotado = false;
	private int aulas = 0;
	private int maxAulas;
	
	public AulaMuayThay(int aulas) {
		maxAulas = aulas;
	}
	
	@Override
	public void run() {
		aguardaLotacaoMaxima();
		promoveAula();
		
	}
	
	public void inscreveParaAula() {
		alunos++;
		if(alunos == maxAlunos) {
			alunos = 0;
			lotado = true;
		}
	}
	
	public synchronized void aguardaAulaIniciar() {
		
	}
	
	public void aguardaLotacaoMaxima() {
		System.out.println("Aguardando lotação");
		Semaphore sem = new Semaphore(0);
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void promoveAula() {
		
		System.out.println("Aula começa");
		
		aulas++;
		if(aulas >= maxAulas) {
			fechada = true;
		}
	}

}
