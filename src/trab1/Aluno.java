package trab1;

import java.util.concurrent.Semaphore;

public class Aluno implements Runnable {
	
	private AulaMuayThay aula;
	public int cod;
	private static int count = 0;
	
	public Semaphore aulaSem = new Semaphore(0);
	
	public Aluno(AulaMuayThay aula) {
		this.aula = aula;
		cod = ++count;
	}

	@Override
	public void run() {
		while(!aula.fechada) {
			
			aula.inscreveAluno(this);
			
			aguardaAula();
			
			fazerCoisasAleatorias();
			
		}
		System.out.println("Aluno "+cod+" foi embora");
		return;
	}
	
	public void aguardaAula() {
		try {
			aulaSem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void fazerCoisasAleatorias() {
		try {
			Thread.sleep(Randomizer.getInt(5000, 10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
