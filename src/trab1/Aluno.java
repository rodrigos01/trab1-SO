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
			
			aula.inscreveParaAula(this);
			
			aguardaAula();
			
		}
		System.out.println("finalizado");
		
	}
	
	public void aguardaAula() {
		try {
			aulaSem.acquire();
			System.out.println("aluno "+cod+" saiu da aula");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
