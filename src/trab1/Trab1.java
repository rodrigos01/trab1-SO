package trab1;

public class Trab1 {

	public static void main(String[] args) throws InterruptedException {
		
		AulaMuayThay aula = new AulaMuayThay(10);
		Thread threadAula = new Thread(aula);
		threadAula.start();
		
		while(!aula.fechada) {
			Thread threadAluno = new Thread(new Aluno(aula));
			threadAluno.start();
			Thread.sleep(Randomizer.getInt(1000, 5000));
		}

	}

}
