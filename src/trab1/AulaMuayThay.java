package trab1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class AulaMuayThay implements Runnable {

	public boolean fechada = false;
	private LinkedList<Aluno> fila = new LinkedList<Aluno>();
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private int maxAlunos = 10;
	private int aulas = 0;
	private int maxAulas;
	public Semaphore lotSem = new Semaphore(0),
			aulaSem = new Semaphore(0);
	
	public AulaMuayThay(int aulas) {
		maxAulas = aulas;
	}
	
	@Override
	public void run() {
		while(!fechada) {
			promoveAula();
		}
		System.out.println("finalizado");
	}
	
	public void inscreveParaAula(Aluno aluno) {
		System.out.println("aluno "+aluno.cod+" se inscreve na aula");
		fila.add(aluno);
		if(fila.size() >= maxAlunos) {
			aulaSem.release();
		}
	}
	
	public void promoveAula() {
		try {
			aulaSem.acquire();
			aulas++;
			while(alunos.size() <= maxAlunos) {
				alunos.add(fila.remove());
			}
			System.out.println("Aula nº "+aulas+" começa");
			System.out.println("Alunos na Aula nº "+aulas+":");
			for(Aluno a: alunos) {
				System.out.println("	"+a.cod);
			}
			Thread.sleep(10000);
			System.out.println("Aula nº "+aulas+" termina");
			while(alunos.size() > 0) {
				alunos.remove(0).aulaSem.release();
			}
			
			if(aulas >= maxAulas) {
				fechada = true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
