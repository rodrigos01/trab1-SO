package trab1;

public class Aluno implements Runnable {
	
	AulaMuayThay aula;
	
	public Aluno(AulaMuayThay aula) {
		this.aula = aula;
	}

	@Override
	public void run() {
		while(!aula.fechada) {
			
			aula.inscreveParaAula();
			
			aula.aguardaAulaIniciar();
			
		}
		
	}

}
