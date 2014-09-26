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
			aguardaLotacao();
			promoveAula();
		}
		System.out.println("academia fechada");
		String ultimos = "Alunos ainda na fila: \n";
		while(!fila.isEmpty()) {
			Aluno a = fila.remove();
			ultimos += "	"+a.cod+"\n";
			a.aulaSem.release();
		}
		System.out.println(ultimos);
		return;
	}
	
	public void inscreveAluno(Aluno aluno) {
		System.out.println("aluno "+aluno.cod+" se inscreve na aula");
		fila.add(aluno);
		if(fila.size() >= maxAlunos) {
			lotSem.release();
		}
	}
	
	public void aguardaLotacao() {
		try {
			lotSem.acquire();
			
			for(int i=0; i<10; i++) {
				Aluno a = fila.remove();
				System.out.println("aluno "+a.cod+" entrou na aula");
				alunos.add(a);
			}
			
			aulaSem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void promoveAula() {
		try {
			aulaSem.acquire();
			aulas++;
			System.out.println("Aula nº "+aulas+" começa");
			if(aulas >= maxAulas) {
				fechada = true;
			}
			Thread.sleep(10000);
			
			String chamada = "Lista de chamada: \n";
			while(!alunos.isEmpty()) {
				Aluno a = alunos.remove(0);
				a.aulaSem.release();
				System.out.println("aluno "+a.cod+" saiu da aula");
				chamada += "	"+a.cod+"\n";
			}

			System.out.println("Aula nº "+aulas+" termina\n"+chamada);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
